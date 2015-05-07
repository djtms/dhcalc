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

public class SpikeTrapEvent extends Event {

	private final Rune rune;
	private int charges;
	private final int num;
	
	public SpikeTrapEvent(CharacterData data, int num) {
		this.rune = data.getSkills().get(ActiveSkill.ST);
		this.charges = 3;
		this.num = num;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		charges--;

		List<Damage> dList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.ST, rune), state);
		
		String noteStr = "Trap#" +num + " Charge #" + (3 - this.charges);
		
		applyDamages(state, log, dList, noteStr);
		
		if (charges > 0) {
			this.time += ((rune == Rune.Long_Fuse) ? 3.0 : 2.0);
			queue.push(this);
		} else {
			state.removeSpikeTrap(num);
		}
	}

}
