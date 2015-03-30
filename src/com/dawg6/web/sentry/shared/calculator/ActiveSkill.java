package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Vector;

import com.dawg6.common.util.Combination;

public enum ActiveSkill {

	CA(SkillType.Spender, "CA", "Cluster Arrow", -40, DamageMultiplier.CA, new Rune[] { Rune.None,
			Rune.Dazzling_Arrow, Rune.Shooting_Stars, Rune.Maelstrom,
			Rune.Cluster_Bombs, Rune.Loaded_For_Bear },
			"http://us.battle.net/d3/en/class/demon-hunter/active/cluster-arrow"),

	MS(SkillType.Spender, "MS", "Multishot", -25, DamageMultiplier.MS, new Rune[] { Rune.None,
			Rune.Fire_at_Will, Rune.Burst_Fire, Rune.Suppression_Fire,
			Rune.Full_Broadside, Rune.Arsenal },
			"http://us.battle.net/d3/en/class/demon-hunter/active/multishot"),

	EA(SkillType.Spender, "EA", "Elemental Arrow", -10, DamageMultiplier.EA, new Rune[] { Rune.None,
			Rune.Ball_Lightning, Rune.Frost_Arrow, Rune.Immolation_Arrow,
			Rune.Lightning_Bolts, Rune.Nether_Tentacles },
			"http://us.battle.net/d3/en/class/demon-hunter/active/elemental-arrow"),

	IMP(SkillType.Spender, "Imp", "Impale", -20, DamageMultiplier.IMP, new Rune[] { Rune.None,
			Rune.Impact, Rune.Chemical_Burn, Rune.Overpenetration,
			Rune.Ricochet, Rune.Grievous_Wounds },
			"http://us.battle.net/d3/en/class/demon-hunter/active/impale"),

	CHAK(SkillType.Spender, "Chak", "Chakram", -10, DamageMultiplier.CHAK, new Rune[] { Rune.None,
			Rune.Twin_Chakrams, Rune.Serpentine, Rune.Razor_Disk,
			Rune.Boomerang, Rune.Shuriken_Cloud },
			"http://us.battle.net/d3/en/class/demon-hunter/active/chakram"),

	BOLT(SkillType.Sentry, "Bolt", "Sentry-Bolt", 0, null, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret },
			"http://us.battle.net/d3/en/class/demon-hunter/active/sentry"),

	SENTRY(SkillType.Sentry, "Sentry", "Sentry", -20, null, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret },
			"http://us.battle.net/d3/en/class/demon-hunter/active/sentry"),

	HA(SkillType.Primary, "HA", "Hungering Arrow", 3, DamageMultiplier.HA,
			new Rune[] { Rune.None, Rune.Puncturing_Arrow, Rune.Serrated_Arrow, Rune.Shatter_Shot, Rune.Devouring_Arrow, Rune.Spray_of_Teeth },
			"http://us.battle.net/d3/en/class/demon-hunter/active/hungering-arrow"),
	ES(SkillType.Primary, "ES", "Entangling Shot", 3, DamageMultiplier.ES,
			new Rune[] { Rune.None, Rune.Chain_Gang, Rune.Shock_Collar, Rune.Heavy_Burden, Rune.Justice_is_Served, Rune.Bounty_Hunter },
			"http://us.battle.net/d3/en/class/demon-hunter/active/entangling-shot"),
	BOLAS(SkillType.Primary, "BOLAS", "Bolas", 3, DamageMultiplier.BOLAS,
			new Rune[] { Rune.None, Rune.Volatile_Exolosives, Rune.Thunder_Ball, Rune.Freezing_Strike, Rune.Bitter_Pill, Rune.Imminent_Doom },
			"http://us.battle.net/d3/en/class/demon-hunter/active/bolas"),
	EF(SkillType.Primary, "EF", "Evasive Fire", 4, DamageMultiplier.EF,
			new Rune[] { Rune.None, Rune.Hardened, Rune.Parting_Gift, Rune.Covering_Fire, Rune.Focus, Rune.Surge },
			"http://us.battle.net/d3/en/class/demon-hunter/active/evasive-fire"),
	GRENADE(SkillType.Primary, "GRENADE", "Grenade", 3, DamageMultiplier.GRENADE,
			new Rune[] { Rune.None, Rune.Tinkerer, Rune.Cluster_Grenades, Rune.Grenade_Cache, Rune.Stun_Grenade, Rune.Cold_Grenade },
			"http://us.battle.net/d3/en/class/demon-hunter/active/grenade"),

	Companion(SkillType.Cooldown, "Companion", "Companion", 0, null, new Rune[] {
			Rune.None, Rune.Bat, Rune.Spider, Rune.Wolf, Rune.Boar, Rune.Ferret
		}, "http://us.battle.net/d3/en/class/demon-hunter/active/companion"),
		
	ST(SkillType.Cooldown, "ST", "Spike Trap", 0, DamageMultiplier.ST, new Rune[] { 
			Rune.None, Rune.Echoing_Blast, Rune.Sticky_Trap, Rune.Long_Fuse, Rune.Lightning_Rod, Rune.Scatter }, "http://us.battle.net/d3/en/class/demon-hunter/active/spike-trap"),
		
	Preparation(SkillType.Cooldown, "Preparation", "Preparation", 0, null, new Rune[] { 
			Rune.None, Rune.Invigoration, Rune.Punishment, Rune.Battle_Scars, Rune.Focused_Mind, Rune.Backup_Plan }, "http://us.battle.net/d3/en/class/demon-hunter/active/preparation"),

	Caltrops(SkillType.Cooldown, "Caltrops", "Caltrops", 0, null, new Rune[] { 
			Rune.None, Rune.Hooked_Spines, Rune.Torturous_Ground, Rune.Jagged_Spikes, Rune.Carved_Stakes, Rune.Bait_the_Trap }, "http://us.battle.net/d3/en/class/demon-hunter/active/caltrops"),

	RoV(SkillType.Cooldown, "RoV", "Rain of Vengeance", 0, DamageMultiplier.RoV, new Rune[] { 
			Rune.None, Rune.Dark_Cloud, Rune.Shade, Rune.Stampede, Rune.Anathema, Rune.Flying_Strike }, "http://us.battle.net/d3/en/class/demon-hunter/active/rain-of-vengeance"),

	Any(SkillType.NA, "Any Skill", "Any Skill", 0, null, new Rune[0], null);

	private String name;
	@SuppressWarnings("unused")
	private DamageFunction fn;
	private Rune[] runes;
	private String shortName;
	private DamageMultiplier multiplier;
	private String url;
	private int hatred;
	private SkillType skillType;
	
	
	private ActiveSkill(SkillType skillType, String shortName, String name, int hatred,
			DamageMultiplier multiplier, Rune[] runes, String url) {
		this.name = name;
		this.runes = runes;
		this.shortName = shortName;
		this.multiplier = multiplier;
		this.url = url;
		this.hatred = hatred;
		this.skillType = skillType;
	}

	public String getUrl() {
		return url;
	}

	public SkillType getSkillType() {
		return skillType;
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
}
