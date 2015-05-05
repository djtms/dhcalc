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

public class HealthGlobeEvent extends Event {

	private final double healthGlobeHpInterval;
	private double nextHealthHpGlobe;
	private final double hatred;

	public HealthGlobeEvent(CharacterData data) {
		healthGlobeHpInterval = 1.0 / (data.getNumHealthGlobes() + 1.0);
		nextHealthHpGlobe = 1.0 - healthGlobeHpInterval;

		double h = 0.0;

		if (data.isBloodVengeance())
			h += 30.0;

		if (data.isReapersWraps())
			h += (data.getMaxHatred() * data.getReapersWrapsPercent());

		if (data.isHexingPants()) {
			h = h
					+ (h * data.getHexingPantsUptime() * .25)
					- (h * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
		}
		
		this.hatred = h;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		if ((state.getTargets().getTarget(TargetType.Primary).getPercentHealth() <= nextHealthHpGlobe)
				&& (state.getHatred() < 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {

			nextHealthHpGlobe -= healthGlobeHpInterval;

			double actual = state.addHatred(hatred);

			Damage d = new Damage();
			d.shooter = "Health Globe";
			d.hatred = actual;
			d.time = state.getTime();
			d.currentHatred = state.getHatred();
			d.disc = state.getDisc();
			log.add(d);
		}

		this.time = queue.nextTime(this.time);

		queue.push(this);
	}

}
