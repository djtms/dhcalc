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
		return ((crafted) ? "http://us.battle.net/d3/en/artisan/blacksmith/recipe/" : "http://us.battle.net/d3/en/item/") + ref;
	}
	
	public static final Comparator<ItemSet> SORTER = new Comparator<ItemSet>(){

		@Override
		public int compare(ItemSet o1, ItemSet o2) {
			return o1.name.toLowerCase().compareTo(o2.name.toLowerCase());
		}};
}
