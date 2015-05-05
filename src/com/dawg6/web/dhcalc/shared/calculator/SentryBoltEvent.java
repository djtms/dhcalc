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

public class SentryBoltEvent extends Event {

	private final Rune rune;
	private final double boltInterval;
	private final boolean spenders;

	public SentryBoltEvent(CharacterData data) {
		this.rune = data.getSentryRune();

		BreakPoint bp = BreakPoint.getBp(data.getBp());
		double boltAps = (double) bp.getQty() / (double) BreakPoint.DURATION;
		this.boltInterval = 1.0 / boltAps;
		
		this.spenders = (data.getNumMarauders() >= 4) && data.hasSpender();
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		
		if (!spenders || (state.getLastSpenderTime() < (state.getTime() - boltInterval))) {
			applyDamages(state, log, DamageFunction.getDamages(
					false, true, "Sentry", new DamageSource(
							ActiveSkill.BOLT, rune), state));
		}

		applyDamages(state, log, DamageFunction.getDamages(
				false, true, "Sentry", new DamageSource(
						ActiveSkill.SENTRY, rune), state));
		
		this.time += this.boltInterval;
		queue.push(this);
	}

}
