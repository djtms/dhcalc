package com.dawg6.web.sentry.shared.calculator;


public enum Rune {

	None("a"),
	
	// Impale
	Impact("b"),
	Chemical_Burn("c"),
	Overpenetration("a"),
	Ricochet("d"),
	Grievous_Wounds("e"),
	
	// Chakram
	Twin_Chakrams("a"),
	Serpentine("c"),
	Razor_Disk("d"),
	Boomerang("b"),
	Shuriken_Cloud("e"),
	
	// Elemental_Arrow
	Ball_Lightning("b"),
	Frost_Arrow("a"),
	Immolation_Arrow("c"),
	Lightning_Bolts("e"),
	Nether_Tentacles("d"),
	
	// Multishot
	Fire_at_Will("d"),
	Burst_Fire("b"),
	Suppression_Fire("e"),
	Full_Broadside("a"),
	Arsenal("c"),
	
	// Cluster Arrow
	Dazzling_Arrow("e"),
	Shooting_Stars("b"),
	Maelstrom("d"),
	Cluster_Bombs("c"),
	Loaded_For_Bear("a"),
	
	// Sentry
	Spitfire_Turret("c"),
	Impaling_Bolt("b"),
	Chain_of_Torment("a"),
	Polar_Station("d"),
	Guardian_Turret("e"),
	
	// Marked for Death
	Grim_Reaper("a"),
	Contagion("b"),
	Valley_Of_Death("c"),
	Mortal_Enemy("d"),
	Death_Toll("e"),
	
	All_Runes("x"),
	
	// Caltrops
	Bait_the_Trap("e");
	
	private String slug;
	
	private Rune(String slug) {
		this.slug = slug;
	}
	
	public String getLongName() {
		return name().replaceAll("_", " ");
	}

	public String getSlug() {
		return slug;
	}
	
}
