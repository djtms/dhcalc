/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class CompanionBuffEvent extends Event {

	private final boolean hasBat;
	private final boolean hasWolf;
	private final double batAmount;
	private final double cd;

	public CompanionBuffEvent(CharacterData data) {
		this.hasBat = (data.getNumMarauders() >= 2)
				|| (data.getCompanionRune() == Rune.Bat);
		this.hasWolf = (data.getNumMarauders() >= 2)
				|| (data.getCompanionRune() == Rune.Wolf);

		this.batAmount = 50.0 + (data.isHexingPants() ? ((50.0 * data
				.getHexingPantsUptime() * .25) - (50.0 * (1.0 - data
				.getHexingPantsUptime()) * data.getHexingPantsPercent())) : 0.0);

		cd = 30.0 * (1.0 - data.getCdr());
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		boolean use = false;

		if (hasBat && (state.getHatred() <= 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {
			use = true;
		} else if (hasWolf && !state.getBuffs().isActive(Buff.OtherWolf)) {
			use = true;
		}

		if (use) {

			if (hasBat) {
				double h = state.addHatred(batAmount);

				if (h > 0) {
					Damage d = new Damage();
					d.shooter = "Companion";
					d.source = new DamageSource(ActiveSkill.Companion, Rune.Bat);
					d.hatred = h;
					d.currentHatred = state.getHatred();
					d.note = "Bat Hatred";
					d.currentDisc = state.getDisc();
					d.time = state.getTime();
					log.add(d);
				}
			}

			if (hasWolf) {
				state.getBuffs().set(Buff.Wolf, state.getTime() + 10.0);
				state.getBuffs().set(Buff.CompanionActive, Double.MAX_VALUE);

				Damage d = new Damage();
				d.shooter = "Companion";
				d.source = new DamageSource(ActiveSkill.Companion, Rune.Wolf);
				d.note = "Wolf Howl";
				d.time = state.getTime();
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				log.add(d);
			}

			this.time += cd;
		} else {
			this.time = queue.nextTime(this.time);
		}

		queue.push(this);
	}

}
