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

import com.dawg6.d3api.shared.Const;

public enum WeaponType {

	Mace("Mace", Const.MACE, 1.2, 316, 585, 23, true), 
	Spear("Spear", Const.SPEAR, 1.2, 353, 526, 23, true), 
	Axe("Axe", Const.AXE, 1.3, 249, 461, 23, true), 
	Sword("Sword", Const.SWORD, 1.4, 168, 392, 19, true), 
	Dagger("Dagger", Const.DAGGER, 1.5, 107, 321, 19, true), 
	HandCrossbow("Hand Crossbow", Const.HANDXBOW, 1.6, 126, 714, 19, false), 
	Bow("2H Bow", Const.BOW, 1.4, 143, 815, 27, false), 
	Crossbow("2H Crossbow", Const.CROSSBOW, 1.1, 779, 945, 23, false);

	String name;
	double aps;
	int min;
	int max;
	int frames;
	boolean melee;
	String slug;
	
	private WeaponType(String name, String slug, double aps, int min, int max, int frames, boolean melee) {
		this.name = name;
		this.aps = aps;
		this.min = min;
		this.max = max;
		this.frames = frames;
		this.melee = melee;
		this.slug = slug;
	}

	public static WeaponType fromSlug(String slug) {
		for (WeaponType t : values())
			if (t.slug.equals(slug))
				return t;
		
		return null;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public boolean isMelee() {
		return melee;
	}
	
	public String getName() {
		return name;
	}

	public double getAps() {
		return aps;
	}

	public int getMin() {
		return min;
	}
	
	public int getFrames() {
		return frames;
	}

	public int getMax() {
		return max;
	}
}
