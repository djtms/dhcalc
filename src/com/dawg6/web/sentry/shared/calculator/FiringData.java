package com.dawg6.web.sentry.shared.calculator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class FiringData {

	public static final int DURATION = 30;

	public static Damage[] calculateDamages(Map<ActiveSkill, Rune> runes, CharacterData data) {
		List<Damage> list = new Vector<Damage>();

		List<SkillAndRune> skills = new Vector<SkillAndRune>();
		Map<SkillAndRune, Integer> skillQty = new TreeMap<SkillAndRune, Integer>();
		
		for (Map.Entry<ActiveSkill, Rune> e : runes.entrySet()) {
			ActiveSkill skill = e.getKey();
			Rune rune = e.getValue();
			
			if ((skill != ActiveSkill.SENTRY) && (skill != ActiveSkill.BOLT)) {
				SkillAndRune skr = new SkillAndRune(skill, rune); 
				skills.add(skr);
				skillQty.put(skr, 0);
			}
		}

		Collections.sort(skills, SkillAndRune.HatredSorter);

		double maxHatred = data.getMaxHatred();
		double hatred = maxHatred;
		double regen = data.getHatredPerSecond();
		double t = 0.0;
		double aps = data.getAps();
		double as = 1.0 / aps;
		int total = 0;
		int totalSpender = 0;
		Rune sentryRune = data.getSentryRune();
		
		while (t < DURATION) {
		
			for (SkillAndRune skr : skills) {
				double h = skr.getHatred();
				
				if ((h + hatred) >= 0) {
					Integer n = skillQty.get(skr) + 1;
					skillQty.put(skr, n);
					hatred = Math.min(maxHatred, hatred + h);
					total++;
					
					break;
				}
			}
			
			t += as;

			hatred = Math.min(maxHatred, hatred + (as * regen));
		}
		
		for (SkillAndRune skr : skills) {
			
			ActiveSkill skill = skr.getSkill();
			Rune rune = skr.getRune();
			
			if ((skill != ActiveSkill.SENTRY) && (skill != ActiveSkill.BOLT)) {
				int qty = skillQty.get(skr);
				
				if (qty > 0) {
					list.addAll(DamageFunction.getDamages(true, false, "Player", new DamageSource(skill, rune), qty, data));
					
					if ((data.getNumMarauders() >= 4) && (skill.getSkillType() == SkillType.Spender)) {
						list.addAll(DamageFunction.getDamages(false, true, "Sentry-M4", new DamageSource(skill, rune), qty, data));
						totalSpender += qty;
					}
				}
			}
		}
		
		BreakPoint bp = BreakPoint.getBp(data.getBp());
		int boltQty = Math.max(0, bp.getQty() - totalSpender);
		
		if (boltQty > 0)
			list.addAll(DamageFunction.getDamages(false, true, "Sentry-Bolt", new DamageSource(ActiveSkill.BOLT, sentryRune), boltQty, data));
		
		// gem procs
		list.addAll(DamageFunction.getDamages(false, false, "Gems", null, FiringData.DURATION, data));

		return list.toArray(new Damage[0]);
	}

}
