package com.dawg6.web.sentry.shared.calculator;


public enum Rune {

	None("a", 0),
	
	// Impale
	Impact("b", 0),
	Chemical_Burn("c", 0),
	Overpenetration("a", 0),
	Ricochet("d", 0),
	Grievous_Wounds("e", 0),
	
	// Chakram
	Twin_Chakrams("a", 0),
	Serpentine("c", 0),
	Razor_Disk("d", 0),
	Boomerang("b", 0),
	Shuriken_Cloud("e", 0),
	
	// Elemental_Arrow
	Ball_Lightning("b", 0),
	Frost_Arrow("a", 0),
	Immolation_Arrow("c", 0),
	Lightning_Bolts("e", 0),
	Nether_Tentacles("d", 0),
	
	// Multishot
	Fire_at_Will("d", 0),
	Burst_Fire("b", 0),
	Suppression_Fire("e", 0),
	Full_Broadside("a", 0),
	Arsenal("c", 0),
	
	// Cluster Arrow
	Dazzling_Arrow("e", 0),
	Shooting_Stars("b", 0),
	Maelstrom("d", 0),
	Cluster_Bombs("c", 0),
	Loaded_For_Bear("a", 0),
	
	// Sentry
	Spitfire_Turret("c", 0),
	Impaling_Bolt("b", 0),
	Chain_of_Torment("a", 0),
	Polar_Station("d", 0),
	Guardian_Turret("e", 0),
	
	// Marked for Death
	Grim_Reaper("a", 0),
	Contagion("b", 0),
	Valley_Of_Death("c", 0),
	Mortal_Enemy("d", 0),
	Death_Toll("e", 0),
	
	All_Runes("x", 0),

	// Hungering Arrow
	Puncturing_Arrow("d", 0),
	Serrated_Arrow("a", 3),
	Shatter_Shot("b", 0),
	Devouring_Arrow("c", 0),
	Spray_of_Teeth("e", 0),
	
	// Entangling Shot
	Chain_Gang("b", 0),
	Shock_Collar("c", 0),
	Heavy_Burden("a", 0),
	Justice_is_Served("d", 3),
	Bounty_Hunter("e", 0),
	
	// Bolas
	Volatile_Exolosives("a", 0),
	Thunder_Ball("c", 3),
	Freezing_Strike("b", 0),
	Bitter_Pill("d", 0),
	Imminent_Doom("e", 0),
	
	// Evasive Fire
	Hardened("a", 0),
	Parting_Gift("c", 0),
	Covering_Fire("b", 0),
	Focus("e", 3),
	Surge("d", 0),
	
	// Grenade
	Tinkerer("d", 3),
	Cluster_Grenades("b", 0),
	Grenade_Cache("c", 0),
	Stun_Grenade("e", 0),
	Cold_Grenade("a", 0),
	
	// Caltrops
	Bait_the_Trap("e", 0);
	
	private String slug;
	private int hatred;
	
	private Rune(String slug, int hatred) {
		this.slug = slug;
		this.hatred = hatred;
	}
	
	public String getLongName() {
		return name().replaceAll("_", " ");
	}

	public String getSlug() {
		return slug;
	}

	public int getHatred() {
		return hatred;
	}
	
}
