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

import java.util.Comparator;

import com.dawg6.web.dhcalc.shared.calculator.d3api.Const;

public enum ItemSet {

	BW("Bastions of Will", Const.BASTIONS_OF_WILL, "focus", false, 2),
	Marauders("Embodiment of the Marauder", Const.MARAUDERS, "marauders-visage", false, 6),
	UE("Unhallowed Essence", Const.UE, "accursed-visage", false, 6),
	Nats("Natalya's Vengeance", Const.NATS, "natalyas-slayer", false, 7),
	Crimson("Captain Crimson's Trimmings", Const.CAPTAIN_CRIMSON, "captain-crimsons-silk-girdle", true, 3),
	Borns("Born's Command", Const.BORNS, "borns-frozen-soul", true, 3),
	
	;
	
	private final String name;
	private final String slug;
	private final int max;
	private final String ref;
	private boolean crafted;

	private ItemSet(String name, String slug, String ref, boolean crafted, int max) {
		this.name = name;
		this.slug = slug;
		this.ref = ref;
		this.max = max;
		this.crafted = crafted;
	}

	public String getName() {
		return name;
	}

	public String getSlug() {
		return slug;
	}

	public int getMaxPieces() {
		return max;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getUrl() {
		return ((crafted) ? "http://us.battle.net/d3/en/artisan/blacksmith/recipe/" : "http://us.battle.net/d3/en/item/") + ref + ((crafted) ? "" :"?itemLevel=70");
	}
	
	public static final Comparator<ItemSet> SORTER = new Comparator<ItemSet>(){

		@Override
		public int compare(ItemSet o1, ItemSet o2) {
			return o1.name.toLowerCase().compareTo(o2.name.toLowerCase());
		}};
}
