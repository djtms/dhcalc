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

		Collections.sort(skills, new SkillAndRune.HatredSorter(data));

		double maxHatred = data.getMaxHatred();
		double hatred = maxHatred;
		double regen = data.getHatredPerSecond() + (data.isInspire() ? 1.0 : 0.0);
		double t = 0.0;
		double aps = data.getAps();
		double interval = (1.0 / aps) + (data.getDelay() / 1000.0);
		int totalSpender = 0;
		Rune sentryRune = data.getSentryRune();
		double healthGlobeInterval = (data.getNumHealthGlobes() > 0) ? (FiringData.DURATION / (data.getNumHealthGlobes() + 1.0)) : (FiringData.DURATION * 2.0);
		double nextHealthGlobe = healthGlobeInterval;
		
		while (t < DURATION) {

			if (t >= nextHealthGlobe) {
				nextHealthGlobe += healthGlobeInterval;
				
				if (data.isBloodVengeance()) {
					hatred = Math.min(maxHatred, hatred + 30.0);
				}
				
				if (data.isReapersWraps()) {
					hatred = Math.min(maxHatred, hatred + (maxHatred * data.getReapersWrapsPercent()));
				}
			}
			
			for (SkillAndRune skr : skills) {
				double h = skr.getHatred(data);
				
				if ((h + hatred) >= 0) {
					Integer n = skillQty.get(skr) + 1;
					skillQty.put(skr, n);
					hatred = Math.min(maxHatred, hatred + h);
					
					break;
				}
			}
			
			t += interval;

			hatred = Math.min(maxHatred, hatred + (interval * regen));
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
		
		if (boltQty > 0) {
			list.addAll(DamageFunction.getDamages(false, true, "Sentry", new DamageSource(ActiveSkill.SENTRY, sentryRune), boltQty, data));
			list.addAll(DamageFunction.getDamages(false, true, "Sentry-Bolt", new DamageSource(ActiveSkill.BOLT, sentryRune), boltQty, data));
		}
		
		// gem procs
		list.addAll(DamageFunction.getDamages(false, false, "Gems", null, FiringData.DURATION, data));

		return list.toArray(new Damage[0]);
	}

}
