package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class FiringData {

	public static final int DURATION = 30;

	public static Damage[] calculateDamages(Map<ActiveSkill, Rune> runes, CharacterData data) {
		List<Damage> list = new Vector<Damage>();

		int totalQty = (int) (DURATION * data.getAps());
		
		for (Map.Entry<ActiveSkill, Rune> e : runes.entrySet()) {
			
			ActiveSkill skill = e.getKey();
			Rune rune = e.getValue();
			
			if ((skill != ActiveSkill.SENTRY) && (skill != ActiveSkill.BOLT)) {
				int qty = totalQty; // TODO calculate qty for each skill
				// TODO account for Hatred
				list.addAll(DamageFunction.getDamages(true, false, "Player", new DamageSource(skill, rune), qty, data));
			}
		}
		
		Rune sentryRune = data.getSentryRune();

		BreakPoint bp = BreakPoint.getBp(data.getBp());
		
		list.addAll(DamageFunction.getDamages(false, true, "Sentry", new DamageSource(ActiveSkill.BOLT, sentryRune), bp.getQty(), data));
		list.addAll(DamageFunction.getDamages(false, true, "Sentry", new DamageSource(ActiveSkill.SENTRY, sentryRune), totalQty, data));
		
		// gem procs
		list.addAll(DamageFunction.getDamages(false, false, "Gems", null, FiringData.DURATION, data));

		return list.toArray(new Damage[0]);
	}

}
