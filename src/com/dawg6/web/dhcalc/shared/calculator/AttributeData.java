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
import java.util.Set;
import java.util.TreeMap;

public class AttributeData implements Serializable {

	private static final long serialVersionUID = -214789934437588367L;

	public Map<String, Integer> attributes = new TreeMap<String, Integer>();
	
	public AttributeData() { 

	}
	
	public AttributeData(AttributeData o) {
		this.attributes.putAll(o.attributes);
	}

	public void put(String key, Integer value) {
		attributes.put(key, value);
	}
	
	public Integer get(String key) {
		return attributes.get(key);
	}
	
	public Set<Map.Entry<String, Integer>> entrySet() {
		return attributes.entrySet();
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append("{");
		boolean first = true;
		
		for (Map.Entry<String, Integer> e : attributes.entrySet()) {
			buf.append(e.getKey() + "=" + e.getValue());
			
			if (first)
				first = false;
			else
				buf.append(",");
		}
		
		buf.append("}");
		
		return buf.toString();
	}
}
