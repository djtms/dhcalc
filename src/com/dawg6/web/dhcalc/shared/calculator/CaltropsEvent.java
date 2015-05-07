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
import java.util.Vector;

public class CaltropsEvent extends Event {

	private final Rune rune;
	private final double cost;

	public CaltropsEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.Caltrops);
		double cost = (rune == Rune.Carved_Stakes) ? 3.0 : 6.0;
		this.cost = cost * (1.0 - data.getRcr());
		this.time = 0.0;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		if (state.getDisc() >= cost) {
			state.addDisc(-cost);
			List<Damage> dList = new Vector<Damage>();
			
			if (rune == Rune.Jagged_Spikes) {
				dList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.Caltrops, rune), state);
				
				for (Damage d : dList) {
					d.disc = -cost;
					d.currentDisc = state.getDisc();
				}
				
				applyDamages(state, log, dList);
				
			} else {
				Damage d = new Damage();
				d.shooter = "Player";
				d.source = new DamageSource(ActiveSkill.Caltrops, rune);
				d.disc = -cost;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.note = "Caltrops";
				log.add(d);
			}

			this.time += 6.0;
		} else {
			this.time = queue.nextTime(this.time);
		}
		
		queue.push(this);
	}

}
