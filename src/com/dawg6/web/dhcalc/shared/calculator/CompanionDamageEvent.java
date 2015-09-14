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

public class CompanionDamageEvent extends Event {

	private Rune[] runes;
	private final double interval;
	
	public CompanionDamageEvent(CharacterData data) {
		
		if (data.getNumMarauders() >= 2)
			runes = ActiveSkill.Companion.getRunes();
		else
			runes = new Rune[]{ data.getCompanionRune() };
		
		double petAps = 1.0;

		if (data.isTnt())
			petAps = petAps * (1 + data.getTntPercent());
		
		this.interval = 1.0 / petAps;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		for (Rune r : runes) {

			applyDamages(state, log, DamageFunction.getDamages(false, false,
							ActiveSkill.Companion.getLongName(),
							new DamageSource(ActiveSkill.Companion,
									r), state));
			
			if (state.getBuffs().isActive(Buff.CompanionActive))
				state.getBuffs().clear(Buff.CompanionActive);
		}
		
		this.time += interval;
		
		queue.push(this);
	}

}
