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

import java.util.List;
import java.util.Vector;

import com.dawg6.common.util.Combination;

public enum ActiveSkill {

	CA(SkillType.Spender, "CA", "Cluster Arrow", -40, DamageMultiplier.CA, 0.15, new Rune[] { Rune.None,
			Rune.Dazzling_Arrow, Rune.Shooting_Stars, Rune.Maelstrom,
			Rune.Cluster_Bombs, Rune.Loaded_For_Bear },
			"http://us.battle.net/d3/en/class/demon-hunter/active/cluster-arrow", 18),

	MS(SkillType.Spender, "MS", "Multishot", -25, DamageMultiplier.MS, 0.25, new Rune[] { Rune.None,
			Rune.Fire_at_Will, Rune.Wind_Chill, Rune.Suppression_Fire,
			Rune.Full_Broadside, Rune.Arsenal },
			"http://us.battle.net/d3/en/class/demon-hunter/active/multishot", 18),

	EA(SkillType.Spender, "EA", "Elemental Arrow", -10, DamageMultiplier.EA, 0.5, new Rune[] { Rune.None,
			Rune.Ball_Lightning, Rune.Frost_Arrow, Rune.Immolation_Arrow,
			Rune.Lightning_Bolts, Rune.Nether_Tentacles },
			"http://us.battle.net/d3/en/class/demon-hunter/active/elemental-arrow", 18),

	IMP(SkillType.Spender, "Imp", "Impale", -20, DamageMultiplier.IMP, 1.0, new Rune[] { Rune.None,
			Rune.Impact, Rune.Chemical_Burn, Rune.Overpenetration,
			Rune.Ricochet, Rune.Grievous_Wounds },
			"http://us.battle.net/d3/en/class/demon-hunter/active/impale", 31),

	CHAK(SkillType.Spender, "Chak", "Chakram", -10, DamageMultiplier.CHAK, 0.5, new Rune[] { Rune.None,
			Rune.Twin_Chakrams, Rune.Serpentine, Rune.Razor_Disk,
			Rune.Boomerang, Rune.Shuriken_Cloud },
			"http://us.battle.net/d3/en/class/demon-hunter/active/chakram", 17),

	BOLT(SkillType.NA, "Bolt", "Sentry-Bolt", 0, DamageMultiplier.Sentry, 0, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret },
			"http://us.battle.net/d3/en/class/demon-hunter/active/sentry", 10),

	SENTRY(SkillType.Sentry, "Sentry", "Sentry", -20, DamageMultiplier.Sentry, 0, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret },
			"http://us.battle.net/d3/en/class/demon-hunter/active/sentry", 15, 8, 30),

	HA(SkillType.Primary, "HA", "Hungering Arrow", 4, DamageMultiplier.HA,
			0.65, new Rune[] { Rune.None, Rune.Puncturing_Arrow, Rune.Serrated_Arrow, Rune.Shatter_Shot, Rune.Devouring_Arrow, Rune.Spray_of_Teeth },
			"http://us.battle.net/d3/en/class/demon-hunter/active/hungering-arrow", -1),
			
	ES(SkillType.Primary, "ES", "Entangling Shot", 4, DamageMultiplier.ES,
			1.0, new Rune[] { Rune.None, Rune.Chain_Gang, Rune.Shock_Collar, Rune.Heavy_Burden, Rune.Justice_is_Served, Rune.Bounty_Hunter },
			"http://us.battle.net/d3/en/class/demon-hunter/active/entangling-shot", -1),
			
	BOLAS(SkillType.Primary, "BOLAS", "Bolas", 4, DamageMultiplier.BOLAS,
			0.4, new Rune[] { Rune.None, Rune.Volatile_Exolosives, Rune.Thunder_Ball, Rune.Freezing_Strike, Rune.Bitter_Pill, Rune.Imminent_Doom },
			"http://us.battle.net/d3/en/class/demon-hunter/active/bolas", 18),
			
	EF(SkillType.Primary, "EF", "Evasive Fire", 4, DamageMultiplier.EF,
			1.0 / 3.0, new Rune[] { Rune.None, Rune.Hardened, Rune.Parting_Gift, Rune.Covering_Fire, Rune.Focus, Rune.Surge },
			"http://us.battle.net/d3/en/class/demon-hunter/active/evasive-fire", -1),
			
	GRENADE(SkillType.Primary, "GRENADE", "Grenade", 4, DamageMultiplier.GRENADE,
			2.0 / 3.0, new Rune[] { Rune.None, Rune.Tinkerer, Rune.Cluster_Grenades, Rune.Grenade_Cache, Rune.Stun_Grenade, Rune.Cold_Grenade },
			"http://us.battle.net/d3/en/class/demon-hunter/active/grenade", 17),

	Companion(SkillType.Cooldown, "Companion", "Companion", 0, DamageMultiplier.Companion, 0, new Rune[] {
			Rune.None, Rune.Bat, Rune.Spider, Rune.Wolf, Rune.Boar, Rune.Ferret
		}, "http://us.battle.net/d3/en/class/demon-hunter/active/companion", 27, 30, 0),
		
	ST(SkillType.NA, "ST", "Spike Trap", 0, DamageMultiplier.ST, 0.15, new Rune[] { 
			Rune.None, Rune.Echoing_Blast, Rune.Sticky_Trap, Rune.Long_Fuse, Rune.Lightning_Rod, Rune.Scatter }, "http://us.battle.net/d3/en/class/demon-hunter/active/spike-trap", 21),
		
	Preparation(SkillType.Cooldown, "Preparation", "Preparation", 0, null, 0, new Rune[] { 
			Rune.None, Rune.Invigoration, Rune.Punishment, Rune.Battle_Scars, Rune.Focused_Mind, Rune.Backup_Plan }, "http://us.battle.net/d3/en/class/demon-hunter/active/preparation", 34),

	Caltrops(SkillType.NA, "Caltrops", "Caltrops", 0, null, 0, new Rune[] { 
			Rune.None, Rune.Hooked_Spines, Rune.Torturous_Ground, Rune.Jagged_Spikes, Rune.Carved_Stakes, Rune.Bait_the_Trap }, "http://us.battle.net/d3/en/class/demon-hunter/active/caltrops", 8),

	RoV(SkillType.Cooldown, "RoV", "Rain of Vengeance", 0, DamageMultiplier.RoV, 0.04, new Rune[] { 
			Rune.None, Rune.Dark_Cloud, Rune.Shade, Rune.Stampede, Rune.Anathema, Rune.Flying_Strike }, "http://us.battle.net/d3/en/class/demon-hunter/active/rain-of-vengeance", 23),
	
	CR(SkillType.NA, "CR", "Crashing Rain", 0, DamageMultiplier.RoV, 0, new Rune[0], "http://us.battle.net/d3/en/item/crashing-rain", 0),

	SS(SkillType.Cooldown, "SS", "Smoke Screen", 0, null, 
			0, new Rune[] { Rune.None, Rune.Displacement, Rune.Lingering_Fog, Rune.Healing_Vapors, Rune.Special_Recipe, Rune.Vanishing_Powder }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/smoke-screen", 34),
	
	Vault(SkillType.NA, "Vault", "Vault", 0, null, 
			0, new Rune[] { Rune.None, Rune.Action_Shot, Rune.Rattling_Roll, Rune.Tumble, Rune.Acrobatics, Rune.Trail_of_Cinders }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/vault", 18),

	FoK(SkillType.Cooldown, "FoK", "Fan of Knives", 0, DamageMultiplier.FoK, 
			1.0 / 3.0, new Rune[] { Rune.None, Rune.Pinpoint_Accuracy, Rune.Bladed_Armor, Rune.Knives_Expert, Rune.Fan_of_Daggers, Rune.Assassins_Knives }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/fan-of-knives", 15),

	SP(SkillType.NA, "SP", "Shadow Power", 0, null, 
			0, new Rune[] { Rune.None, Rune.Night_Bane, Rune.Blood_Moon, Rune.Well_of_Darkness, Rune.Gloom, Rune.Shadow_Glide }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/shadow-power", 34),

	Strafe(SkillType.Channeled, "Strafe", "Strafe", -12, DamageMultiplier.Strafe, 
			0.25, new Rune[] { Rune.None, Rune.Icy_Trail, Rune.Drifting_Shadow, Rune.Stinging_Steel, Rune.Rocket_Storm, Rune.Demolition }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/strafe", 51),

	Vengeance(SkillType.Cooldown, "Ven", "Vengeance", 0, DamageMultiplier.Vengeance, 
			0.05, new Rune[] { Rune.None, Rune.Personal_Mortar, Rune.Dark_Heart, Rune.Side_Cannons, Rune.Seethe, Rune.From_the_Shadows }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/vengeance", 34, 90, 20),

	RF(SkillType.Channeled, "RF", "Rapid Fire", 0, DamageMultiplier.RF, 
			0.166, new Rune[] { Rune.None, Rune.Withering_Fire, Rune.Frost_Shots, Rune.Fire_Support, Rune.High_Velocity, Rune.Bombardment }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/rapid-fire", 5),

	MFD(SkillType.NA, "MFD", "Marked for Death", 0, null, 
			0, new Rune[] { Rune.None, Rune.Contagion, Rune.Valley_Of_Death, Rune.Grim_Reaper, Rune.Mortal_Enemy, Rune.Death_Toll }, 
			"http://us.battle.net/d3/en/class/demon-hunter/active/marked-for-death", 28),

	Any(SkillType.NA, "Any Skill", "Any Skill", 0, null, 0, new Rune[0], null, 0);

	private String name;
	@SuppressWarnings("unused")
	private DamageFunction fn;
	private Rune[] runes;
	private String shortName;
	private DamageMultiplier multiplier;
	private String url;
	private int hatred;
	private SkillType skillType;
	private String damageAttribute;	
	private String slug;
	private int frames;
	private double proc;
	private double cd;
	private double duration;
	
	private ActiveSkill(SkillType skillType, String shortName, String name, int hatred,
			DamageMultiplier multiplier, double proc, Rune[] runes, String url, int frames) {
		this(skillType, shortName, name, hatred, multiplier, proc, runes, url, frames, 0.0, 0.0);
	}
	
	private ActiveSkill(SkillType skillType, String shortName, String name, int hatred,
			DamageMultiplier multiplier, double proc, Rune[] runes, String url, int frames, double cd, double duration) {
		this.name = name;
		this.runes = runes;
		this.shortName = shortName;
		this.multiplier = multiplier;
		this.url = url;
		this.proc = proc;
		this.hatred = hatred;
		this.skillType = skillType;
		this.damageAttribute = name.replaceAll(" of ", "Of").replaceAll(" ", "");
		this.slug = name.toLowerCase().replaceAll(" ", "-");
		this.frames = frames;
		this.cd = cd;
		this.duration = duration;
	}

	public String getSlug() {
		return slug;
	}

	public boolean doesDamage() {
		return multiplier != null;
	}
	
	public String getUrl(Rune rune) {
		if ((rune == null) || (rune == Rune.None))
			return url;
		else
			return url + "#" + rune.getSlug() + "+";
	}
	
	public String getUrl() {
		return getUrl(null);
	}

	public double getProc() {
		return proc;
	}
	
	public SkillType getSkillType() {
		return skillType;
	}
	
	public String getDamageAttribute() {
		return damageAttribute;
	}
	
	public int getFrames() {
		return frames;
	}
	
	public int getHatred() {
		return hatred;
	}
	
	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return name;
	}

	public Rune[] getRunes() {
		return runes;
	}

	public static List<SkillSet> getCombinations(int num) {
		List<SkillSet> list = new Vector<SkillSet>();
		SkillSet all = new SkillSet(values());
		all.remove(ActiveSkill.SENTRY);
		all.remove(ActiveSkill.BOLT);
		all.remove(ActiveSkill.Any);
		ActiveSkill[] array = all.toArray(new ActiveSkill[0]);

		Combination comb = new Combination(array.length, num);

		while (comb.hasNext()) {
			int[] i = comb.next();

			SkillSet set = new SkillSet();

			for (int j : i)
				set.add(array[j]);

			list.add(set);
		}

		return list;
	}

	public DamageMultiplier getDamageMultiplier() {
		return multiplier;
	}
	
	public double getCooldown(Rune rune) {
		if ((rune == null) || (rune == Rune.None))
			return cd;
		else
			return rune.getCooldown();
	}
	
	public double getDuration(Rune rune) {
		if ((rune == null) || (rune == Rune.None))
			return cd;
		else
			return rune.getDuration();
		
	}
}
