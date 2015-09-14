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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class PreparationEvent extends Event {

	private double hatred;
	private double cd;
	private final Rune rune;
	private double threshold;
	private double disc;
	
	public PreparationEvent(CharacterData data) {
		this.rune = data.getPreparationRune();
		this.cd = 45.0 * (1.0 - data.getCdr());
		this.disc = 0.0;
		this.hatred = 0.0;
		
		if (data.getNumUe() >= 6) {
			this.threshold = data.getMaxDiscipline() - 5.0;
		} else {
			this.threshold = 8.0;
		}
		
		if (rune == Rune.Punishment) {
			this.hatred = 75.0 + (data.isHexingPants() ? ((75.0 * data
					.getHexingPantsUptime() * .25) - (75.0 * (1.0 - data
					.getHexingPantsUptime()) * data.getHexingPantsPercent())) : 0.0);
	
			this.cd = 20.0 * (1.0 - data.getCdr());
		} else if (rune != Rune.Focused_Mind) {
			this.disc = 30.0;
		}
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		boolean use = false;
		
		if (rune == Rune.Punishment) {
			if ((state.getHatred() < 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {
				double actual = state.addHatred(hatred);
				use = true;
	
				Damage d = new Damage();
				d.shooter = "Player";
				d.source = new DamageSource(ActiveSkill.Preparation, rune);
				d.hatred = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.time = state.getTime();
				d.note = "Punishment Hatred";
				log.add(d);
			}
		} else if (state.getDisc() < threshold) {
			use = true;
			
			double actual = state.addDisc(disc);

			Damage d = new Damage();
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.Preparation,
					rune);
			d.disc = actual;
			d.currentHatred = state.getHatred();
			d.currentDisc = state.getDisc();
			d.time = state.getTime();
			d.note = "Preparation";
			log.add(d);

			if (rune == Rune.Focused_Mind)
				state.getBuffs().set(Buff.FocusedMind, this.time + 15.0);
			
		}
		
		if (use) {
			this.time += cd;
		} else {
			this.time = queue.nextTime(this.time);
		}

		queue.push(this);
	}

}
