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

import java.util.Collection;
import java.util.Vector;

public class DamageMultiplierList extends Vector<DamageMultiplier>  implements Comparable<DamageMultiplierList> {

	private static final long serialVersionUID = 1L;
	
	public DamageMultiplierList() { }

	public DamageMultiplierList(Collection<DamageMultiplier> copy) {
		super(copy);
	}
	
	@Override
	public boolean add(DamageMultiplier item) {
		if (!contains(item))
			return super.add(item);
		else
			return false;
	}
	
	public void addAll(DamageMultiplier[] items) {
		for (DamageMultiplier d : items) 
			add(d);
	}
	
	@Override
	public boolean addAll(Collection<? extends DamageMultiplier> items) {
		
		for (DamageMultiplier d : items) 
			add(d);
		
		return true;
	}
	
	public DamageMultiplierList(DamageMultiplier... items) {
		addAll(items);
	}

	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();

		boolean first = true;
		for (DamageMultiplier s : this) {
			
			if (first)
				first = false;
			else
				b.append(" + ");
			
			b.append(s.getAbbreviation());
		}
		
		return b.toString();
	}

	@Override
	public int compareTo(DamageMultiplierList o) {
		return toString().compareTo(o.toString());
	}
}
