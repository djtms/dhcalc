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

public class SpikeTrapActionEvent extends Event {

	private final Rune rune;
	private final double cost;

	public SpikeTrapActionEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.ST);
		double cost = 30.0 + (data.isHexingPants() ? ((30.0 * data
				.getHexingPantsUptime() * .25) - (30.0 * (1.0 - data
				.getHexingPantsUptime()) * data.getHexingPantsPercent())) : 0.0);
		this.cost = cost * (1.0 - data.getRcr());
		this.time = 0.0;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		int num = state.getNumSpikeTraps();
		
		if ((num < 3) && (state.getHatred() >= cost)) {
			int n = (rune == Rune.Scatter) ? (3 - state.getNumSpikeTraps()) : 1;
			int trapNumber = 0;

			state.addHatred(-cost);
			
			for (int i = 0; i < n; i++) {
				trapNumber = state.addSpikeTrap();
				SpikeTrapEvent e = new SpikeTrapEvent(state.getData(), trapNumber);
				e.time = this.time + ((rune == Rune.Long_Fuse) ? 3.0 : 2.0);
				queue.push(e);
			}
			
			Damage d = new Damage();
			d.time = this.time;
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.ST, rune);
			d.hatred = -cost;
			d.currentDisc = state.getDisc();
			d.currentHatred = state.getHatred();
			d.note = "Deploy Trap" + ((rune == Rune.Scatter) ? "s" : ("#" + trapNumber));
			log.add(d);
			
			this.time += 1.0;
		} else {
			this.time = queue.nextTime(this.time);
		}
		
		queue.push(this);
	}

}
