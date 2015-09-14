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

public enum Slot {

	Head("Helm", "head"),
	Torso("Chest", "torso"),
	Feet("Boots", "feet"),
	Hands("Gloves", "hands"),
	Shoulders("Shoulders", "shoulders"),
	Legs("Pants", "legs"),
	Bracers("Bracers", "bracers"),
	MainHand("Main Hand", "mainHand"),
	OffHand("Off Hand", "offHand"),
	Waist("Belt", "waist"),
	Ring1("Ring1", "leftFinger"),
	Ring2("Ring2", "rightFinger"),
	Necklace("Necklace", "neck"),
	CubeWeapon("Cube Main/Off Hand", "cube1"),
	CubeArmor("Cube Armor", "cube2"),
	CubeJewelry("Cube Jewelry", "cube3"),
	;
	
	private final String slot;
	private final String name;
	private final boolean isCube;
	
	Slot(String name, String slot) {
		this.name = name;
		this.slot = slot;
		this.isCube = name.toLowerCase().startsWith("cube");
	}
	
	public String getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}
	
	public boolean isCube() {
		return isCube;
	}
	
	public static Slot getSlot(String slot) {
		for (Slot s : values()) {
			if (s.slot.equalsIgnoreCase(slot))
				return s;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return name();
	}
}
