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

public class VengeanceEvent extends CooldownEvent {

	private final Rune rune;

	public VengeanceEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.Vengeance);
		this.cooldown = 90.0 * (1.0 - data.getCdr());
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		boolean use = false;
		
		if ((rune == Rune.Seethe) && (state.getHatred() <= 50.0)) {
			use = true;
		} else if (rune != Rune.Seethe) {
			use = true;
		}

		if (use) {
			this.time += this.cooldown;
			state.getBuffs().set(Buff.Vengeance, state.getTime() + 15.0);
			
			if (rune == Rune.Seethe)
				state.getBuffs().set(Buff.Seethe, state.getTime() + 15.0);
			
		} else {
			this.time = queue.nextTime(this.time);
		}
		
		queue.push(this);
	}

}
