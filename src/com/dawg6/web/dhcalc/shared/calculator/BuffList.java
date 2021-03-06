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

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class BuffList implements Serializable {

	private static final long serialVersionUID = 6176187451717518503L;

	private Map<Buff, BuffState> buffs = new TreeMap<Buff, BuffState>();
	
	public BuffList() { 
		for (Buff b : Buff.values()) {
			buffs.put(b, new BuffState(b));
		}
	}

	public Map<Buff, BuffState> getBuffs() {
		return buffs;
	}

	public void setBuffs(Map<Buff, BuffState> buffs) {
		this.buffs = buffs;
	}

	public void set(Buff buff, double expires) {
		BuffState state = buffs.get(buff);
		state.setActive(true);
		state.setExpires(expires);
	}

	public void clear(Buff buff) {
		BuffState state = buffs.get(buff);
		state.setActive(false);
		state.setExpires(0.0);
	}

	public void expire(double time) {
		
		for (BuffState state : buffs.values()) {
			state.expire(time);
		}
	}

	public boolean isActive(Buff buff) {
		return buffs.get(buff).isActive();
	}
}
