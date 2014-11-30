package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Vector;

import com.dawg6.common.util.Combination;

public enum ActiveSkill {

	CA("CA", "Cluster Arrow", DamageMultiplier.CA, new Rune[] { Rune.None,
			Rune.Dazzling_Arrow, Rune.Shooting_Stars, Rune.Maelstrom,
			Rune.Cluster_Bombs, Rune.Loaded_For_Bear }, "http://us.battle.net/d3/en/class/demon-hunter/active/cluster-arrow"),
	
	MS("MS", "Multishot", DamageMultiplier.MS, new Rune[] { Rune.None,
			Rune.Fire_at_Will, Rune.Burst_Fire, Rune.Suppression_Fire,
			Rune.Full_Broadside, Rune.Arsenal }, "http://us.battle.net/d3/en/class/demon-hunter/active/multishot"),
	
	EA("EA", "Elemental Arrow", DamageMultiplier.EA, new Rune[] { Rune.None,
			Rune.Ball_Lightning, Rune.Frost_Arrow, Rune.Immolation_Arrow,
			Rune.Lightning_Bolts, Rune.Nether_Tentacles }, "http://us.battle.net/d3/en/class/demon-hunter/active/elemental-arrow"),
	
	IMP("Imp", "Impale", DamageMultiplier.IMP, new Rune[] { Rune.None, Rune.Impact,
			Rune.Chemical_Burn, Rune.Overpenetration, Rune.Ricochet,
			Rune.Grievous_Wounds }, "http://us.battle.net/d3/en/class/demon-hunter/active/impale"),
	
	CHAK("Chak", "Chakram", DamageMultiplier.CHAK, new Rune[] { Rune.None,
			Rune.Twin_Chakrams, Rune.Serpentine, Rune.Razor_Disk,
			Rune.Boomerang, Rune.Shuriken_Cloud }, "http://us.battle.net/d3/en/class/demon-hunter/active/chakram"),
	
	BOLT("Bolt", "Sentry-Bolt", null, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret }, "http://us.battle.net/d3/en/class/demon-hunter/active/sentry"),
	
	SENTRY("Sentry", "Sentry", null, new Rune[] { Rune.None,
			Rune.Spitfire_Turret, Rune.Impaling_Bolt, Rune.Chain_of_Torment,
			Rune.Polar_Station, Rune.Guardian_Turret }, "http://us.battle.net/d3/en/class/demon-hunter/active/sentry"),

	Any("Any Skill", "Any Skill", null, new Rune[0], null);
	
	private String name;
	@SuppressWarnings("unused")
	private DamageFunction fn;
	private Rune[] runes;
	private String shortName;
	private DamageMultiplier multiplier;
	private String url;
	
	private ActiveSkill(String shortName, String name, DamageMultiplier multiplier, Rune[] runes, String url) {
		this.name = name;
		this.runes = runes;
		this.shortName = shortName;
		this.multiplier = multiplier;
		this.url = url;
	}

	public String getUrl() {
		return url;
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
	
	public static void main(String[] args) {
		System.out.println(com.dawg6.common.util.Util.toString(getCombinations(1)));
		System.out.println(com.dawg6.common.util.Util.toString(getCombinations(2)));
		System.out.println(com.dawg6.common.util.Util.toString(getCombinations(3)));
	}

	public DamageMultiplier getDamageMultiplier() {
		return multiplier;
	}
}
