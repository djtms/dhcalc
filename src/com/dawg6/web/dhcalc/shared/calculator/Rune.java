package com.dawg6.web.dhcalc.shared.calculator;


public enum Rune {

	None("", 0),
	
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
	Jagged_Spikes("a", 0),
	Hooked_Spines("b", 0),
	Torturous_Ground("c", 0),
	Carved_Stakes("d", 0),
	Bait_the_Trap("e", 0),
	
	// Companion
	Spider("a", 0),
	Boar("b", 0),
	Wolf("c", 0),
	Bat("d", 0),
	Ferret("e", 0),

	// Spike Trap
	Long_Fuse("a", 0),
	Echoing_Blast("b", 0),
	Sticky_Trap("c", 0),
	Scatter("d", 0),
	Lightning_Rod("e", 0),

	// Rain of Vengeance
	Shade("a", 0),
	Dark_Cloud("b", 0),
	Anathema("c", 0),
	Flying_Strike("d", 0),
	Stampede("e", 0),
	
	// Preparation
	Invigoration("b", 0),
	Punishment("a", 0),
	Battle_Scars("d", 0),
	Focused_Mind("c", 0),
	Backup_Plan("e", 0),
	
	// Rapid Fire
	Withering_Fire("d", 0),
	Frost_Shots("e", 0),
	Fire_Support("c", 0),
	High_Velocity("b", 0),
	Bombardment("a", 0),
	
	// Smoke Screen
	Displacement("e", 0),
	Lingering_Fog("b", 0),
	Healing_Vapors("c", 0),
	Special_Recipe("d", 0),
	Vanishing_Powder("a", 0),
	
	// Vault
	Action_Shot("c", 0),
	Rattling_Roll("e", 0),
	Tumble("d", 0),
	Acrobatics("b", 0),
	Trail_of_Cinders("a", 0),
	
	// Fan of Knives
	Pinpoint_Accuracy("d", 0),
	Bladed_Armor("e", 0),
	Knives_Expert("a", 0),
	Fan_of_Daggers("c", 0),
	Assassins_Knives("b", 0),
	
	// Shadow Power
	Night_Bane("a", 0),
	Blood_Moon("e", 0),
	Well_of_Darkness("d", 0),
	Gloom("c", 0),
	Shadow_Glide("b", 0),
	
	// Strafe
	Icy_Trail("b", 0),
	Drifting_Shadow("d", 0),
	Stinging_Steel("e", 0),
	Rocket_Storm("c", 0),
	Demolition("a", 0),
	
	//  Vengeance
	Personal_Mortar("c", 0),
	Dark_Heart("b", 0),
	Side_Cannons("d", 0),
	Seethe("e", 0),
	From_the_Shadows("a", 0),
	
	;
	
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
