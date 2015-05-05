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

import java.util.Map;
import java.util.TreeMap;

public enum Passive {

	Thrill_of_the_Hunt,
	Tactical_Advantage,
	Blood_Vengeance,
	Steady_Aim,
	Cull_the_Weak,
	Night_Stalker,
	Brooding,
	Hot_Pursuit,
	Archery,
	Numbing_Traps,
	Perfectionist,
	Custom_Engineering,
	Grenadier,
	Sharpshooter,
	Ballistics,
	Ambush,
	Awareness,
	Single_Out;
	
	private static final Map<String, Passive> slugs = new TreeMap<String, Passive>();
	private static final Map<String, Passive> names = new TreeMap<String, Passive>();

	private Passive() {
	}
	
	public String getLongName() {
		return name().replaceAll("_", " ");
	}

	public String getSlug() {
		return name().toLowerCase().replaceAll("_", "-");
	}
	
	public String getUrl() {
		return "http://us.battle.net/d3/en/class/demon-hunter/passive/" + getSlug();
	}

	
	public static Passive fromSlug(String slug) {
		
		if (slugs.isEmpty()) {
			for (Passive p : values())
				slugs.put(p.getSlug(), p);
		}

		return slugs.get(slug);
	}

	public static Passive fromName(String name) {
		if (names.isEmpty()) {
			for (Passive p : values())
				names.put(p.getLongName(), p);
		}

		return names.get(name);
	}
}
