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

public enum WeaponType {

	Melee("Melee", 1.4, 126, 714, 19), 
	HandCrossbow("Hand Crossbow", 1.6, 126, 714, 19), 
	Bow("2H Bow", 1.4, 143, 815, 27), 
	Crossbow("2H Crossbow", 1.1, 779, 945, 23);

	String name;
	double aps;
	int min;
	int max;
	int frames;

	private WeaponType(String name, double aps, int min, int max, int frames) {
		this.name = name;
		this.aps = aps;
		this.min = min;
		this.max = max;
		this.frames = frames;
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
