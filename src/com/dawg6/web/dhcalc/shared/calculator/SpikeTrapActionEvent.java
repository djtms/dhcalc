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

public class SpikeTrapActionEvent extends Event {

	private final Rune rune;
	private final double cost;
	private final int max;
	private final SkillAndRune skr;
	private int charges;
	private double rechargeTime;
	
	public SpikeTrapActionEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.ST);
		this.max = data.getMaxTraps();
		this.skr = new SkillAndRune(ActiveSkill.ST, rune);
		this.charges = max;
		this.rechargeTime = 0.0;
		
		double cost = 15.0 + (data.isHexingPants() ? ((15.0 * data
				.getPercentMoving() * .25) - (15.0 * (1.0 - data
				.getPercentMoving()) * data.getHexingPantsPercent())) : 0.0);
		
		this.cost = cost * (1.0 - data.getRcr());
		this.time = 0.0;
	}

	public int getQtyAvailable(SimulationState state) {
		
		while ((charges < max) && (state.getTime() >= rechargeTime)) {
			charges++;
			rechargeTime += 10.0;
		}
		
		if (charges < 1)
			return 0;
		
		int num = state.getNumSpikeTraps();
		int qty = 1;
		
		if (((num + qty) <= max) && (state.getHatred() >= cost)) {
			return qty;
		} else {
			return 0;
		}
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		int qty = getQtyAvailable(state);
		
		if (qty > 0) {
			
			if (charges == max)
				this.rechargeTime = state.getTime() + 10.0;
			
			charges--;
			int trapNumber = state.addSpikeTrap();
			state.addHatred(-cost);
			
			Damage d = new Damage();
			d.time = state.getTime();
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.ST, rune);
			d.hatred = -cost;
			d.currentDisc = state.getDisc();
			d.currentHatred = state.getHatred();
			d.note = "Deploy Trap#" + trapNumber + ((rune == Rune.Scatter) ? ("x2") : "");
			log.add(d);
		} 
	}

	public SkillAndRune getSkillAndRune() {
		return skr;
	}
}
