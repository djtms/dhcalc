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


public enum Rune {

	None("", 0, 0),
	
	// Impale
	Impact("b", 0, 1.0),
	Chemical_Burn("c", 0, 1.0),
	Overpenetration("a", 0, 0.4),
	Ricochet("d", 0, 0.4),
	Grievous_Wounds("e", 0, 1.0),
	
	// Chakram
	Twin_Chakrams("a", 0, 1.0/3.0),
	Serpentine("c", 0, 0.5),
	Razor_Disk("d", 0, 0.4),
	Boomerang("b", 0, 0.5),
	Shuriken_Cloud("e", 0, 0.5),
	
	// Elemental_Arrow
	Ball_Lightning("b", 0, 0.25),
	Frost_Arrow("a", 0, 1.0 / 3.0),
	Immolation_Arrow("c", 0, 0.5),
	Lightning_Bolts("e", 0, 0.5),
	Nether_Tentacles("d", 0, 0.5),
	
	// Multishot
	Fire_at_Will("d", 7, 0.25),
	Burst_Fire("b", 0, 0.2),
	Suppression_Fire("e", 0, 0.25),
	Full_Broadside("a", 0, 0.25),
	Arsenal("c", 0, 0.18),
	
	// Cluster Arrow
	Dazzling_Arrow("e", 0, 0.2),
	Shooting_Stars("b", 0, 0.2),
	Maelstrom("d", 0, 0.2),
	Cluster_Bombs("c", 0, 0.15),
	Loaded_For_Bear("a", 0, 0.15),
	
	// Sentry
	Spitfire_Turret("c", 0, 0),
	Impaling_Bolt("b", 0, 0),
	Chain_of_Torment("a", 0, 0),
	Polar_Station("d", 0, 0),
	Guardian_Turret("e", 0, 0),
	
	// Marked for Death
	Grim_Reaper("a", 0, 0),
	Contagion("b", 0, 0),
	Valley_Of_Death("c", 0, 0),
	Mortal_Enemy("d", 0, 0),
	Death_Toll("e", 0, 0),
	
	All_Runes("x", 0, 0),

	// Hungering Arrow
	Puncturing_Arrow("d", 0, 0.55),
	Serrated_Arrow("a", 3, 0.65),
	Shatter_Shot("b", 0, 0.59),
	Devouring_Arrow("c", 0, 0.65),
	Spray_of_Teeth("e", 0, 0.65),
	
	// Entangling Shot
	Chain_Gang("b", 0, 1.0),
	Shock_Collar("c", 0, 0.4),
	Heavy_Burden("a", 0, 1.0),
	Justice_is_Served("d", 3, 1.0),
	Bounty_Hunter("e", 0, 1.0),
	
	// Bolas
	Volatile_Exolosives("a", 0, 1.0 / 3.0),
	Thunder_Ball("c", 3, 0.4),
	Freezing_Strike("b", 0, 0.4),
	Bitter_Pill("d", 0, 0.4),
	Imminent_Doom("e", 0, 0.4),
	
	// Evasive Fire
	Hardened("a", 0, 1.0 / 3.0),
	Parting_Gift("c", 0, 0.278),
	Covering_Fire("b", 0, 1.0 / 3.0),
	Focus("e", 3, 1.0 / 3.0),
	Surge("d", 0, 1.0 / 3.0),
	
	// Grenade
	Tinkerer("d", 3, 2.0 / 3.0),
	Cluster_Grenades("b", 0, 0.5),
	Grenade_Cache("c", 0, 0.4),
	Stun_Grenade("e", 0, 2.0 / 3.0),
	Cold_Grenade("a", 0, 1.0 / 3.0),
	
	// Caltrops
	Jagged_Spikes("a", 0, 0),
	Hooked_Spines("b", 0, 0),
	Torturous_Ground("c", 0, 0),
	Carved_Stakes("d", 0, 0),
	Bait_the_Trap("e", 0, 0),
	
	// Companion
	Spider("a", 0, 0),
	Boar("b", 0, 0),
	Wolf("c", 0, 0),
	Bat("d", 0, 0),
	Ferret("e", 0, 0),

	// Spike Trap
	Long_Fuse("a", 0, 0.15),
	Echoing_Blast("b", 0, 0.15),
	Sticky_Trap("c", 0, 1.0 / 3.0),
	Scatter("d", 0, 0.5),
	Lightning_Rod("e", 0, 0.15),

	// Rain of Vengeance
	Shade("a", 0, 0.04),
	Dark_Cloud("b", 0, 0.06),
	Anathema("c", 0, 0.12),
	Flying_Strike("d", 0, 0.15),
	Stampede("e", 0, 0.15),
	
	// Preparation
	Invigoration("b", 0, 0),
	Punishment("a", 0, 0),
	Battle_Scars("d", 0, 0),
	Focused_Mind("c", 0, 0),
	Backup_Plan("e", 0, 0),
	
	// Rapid Fire
	Withering_Fire("d", 0, 0.166),
	Frost_Shots("e", 0, 0.166),
	Fire_Support("c", 0, 0.166),
	High_Velocity("b", 0, 0.056),
	Bombardment("a", 0, 1.0 / 9.0),
	
	// Smoke Screen
	Displacement("e", 0, 0),
	Lingering_Fog("b", 0, 0),
	Healing_Vapors("c", 0, 0),
	Special_Recipe("d", 0, 0),
	Vanishing_Powder("a", 0, 0),
	
	// Vault
	Action_Shot("c", 0, 0.25),
	Rattling_Roll("e", 0, 0),
	Tumble("d", 0, 0),
	Acrobatics("b", 0, 0),
	Trail_of_Cinders("a", 0, 0.4),
	
	// Fan of Knives
	Pinpoint_Accuracy("d", 0, 0.4),
	Bladed_Armor("e", 0, 1.0 / 3.0),
	Knives_Expert("a", 0, 0.2),
	Fan_of_Daggers("c", 0, 1.0 / 3.0),
	Assassins_Knives("b", 0, 0.2),
	
	// Shadow Power
	Night_Bane("a", 0, 0),
	Blood_Moon("e", 0, 0),
	Well_of_Darkness("d", 0, 0),
	Gloom("c", 0, 0),
	Shadow_Glide("b", 0, 0),
	
	// Strafe
	Icy_Trail("b", 0, 0.06),
	Drifting_Shadow("d", 0, 0.25),
	Stinging_Steel("e", 0, 0.25),
	Rocket_Storm("c", 0, 0.2),
	Demolition("a", 0, 0.5),
	
	//  Vengeance
	Personal_Mortar("c", 0, 0.05),
	Dark_Heart("b", 0, 0.05),
	Side_Cannons("d", 0, 0.2),
	Seethe("e", 0, 0.05),
	From_the_Shadows("a", 0, 0.1),
	
	;
	
	private String slug;
	private int hatred;
	private double proc;
	
	private Rune(String slug, int hatred, double proc) {
		this.slug = slug;
		this.hatred = hatred;
		this.proc = proc;
	}

	public double getProc() {
		return proc;
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
