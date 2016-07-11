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

public class CoEBuffEvent extends Event {

	private int index;
	private DamageType type;
	
	public static final DamageType[] TYPES = { DamageType.Cold, DamageType.Fire, DamageType.Lightning, DamageType.Physical };
	public static final Buff[] BUFFS = { Buff.CoeCold, Buff.CoeFire, Buff.CoeLightning, Buff.CoePhysical };
	
	public CoEBuffEvent() {
		index = 0;
		this.time = 0.0;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		this.type = TYPES[index];
		Buff buff = BUFFS[index++];

		state.getBuffs().set(buff, this.time + 4.0);
		
		Damage d = new Damage();
		d.shooter = "Player";
		d.time = this.time;
		d.note = buff.toString();
		d.currentHatred = state.getHatred();
		d.currentDisc = state.getDisc();
		log.add(d);
		
		if (index >= BUFFS.length)
			index = 0;
		
		this.time += 4.0;
		
		queue.push(this);
	}

	public DamageType getDamageType() {
		return type;
	}

}
