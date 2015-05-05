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

public class DiscRegenEvent extends Event {

	private final double regen;
	private final double focusedMindRegen;
	
	public DiscRegenEvent(CharacterData data) {
		this.time = 1.0;
		this.focusedMindRegen = 3.0;
		regen = 1.0;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		if (regen > 0.0)
		{
			double h = regen;
			
			if (state.getBuffs().isActive(Buff.FocusedMind))
				h += this.focusedMindRegen;
		
			double actual = state.addDisc(h);
			
			if (actual > 0) {
				Damage d = new Damage();
				d.time = state.getTime();
				d.shooter = "Player";
				d.disc = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.note = "Disc Regen";
				log.add(d);
			}
		}

		this.time += 1.0;
		
		queue.push(this);
	}

}
