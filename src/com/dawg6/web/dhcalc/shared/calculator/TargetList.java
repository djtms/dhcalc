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

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TargetList implements Serializable {

	private static final long serialVersionUID = -570917745839246976L;
	
	private final Map<TargetType, TargetHolder> targets = new TreeMap<TargetType, TargetHolder>();
	
	public void setTarget(TargetType type, TargetHolder target) {
		this.targets.put(type, target);
	}

	public TargetHolder getTarget(TargetType type) {
		return targets.get(type);
	}
	
	public int getNumTargets() {
		return targets.size();
	}
	
	public int getNumAdditional() {
		int i = targets.size();
		
		if (targets.containsKey(TargetType.Primary))
			i--;
		
		return i;
	}
	
	public int getNumAlive() {
		int i = 0;

		for (TargetHolder t : targets.values()) {
			if (t.isAlive())
				i++;
		}
		
		return i;
	}

	public Collection<TargetType> toList() {
		return new TreeSet<TargetType>(targets.keySet());
	}
	
}
