package com.dawg6.web.sentry.shared.calculator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class FiringData {

	public static final int DURATION = 30;

	public static Damage[] calculateDamages(Map<ActiveSkill, Rune> runes,
			CharacterData data) {
		List<Damage> list = new Vector<Damage>();
		List<Damage> hatredLog = new Vector<Damage>();

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
		double regen = data.getHatredPerSecond()
				+ (data.isInspire() ? 1.0 : 0.0)
				+ ((data.isArchery()
						&& (data.getWeaponType() == WeaponType.HandCrossbow) && (data
						.getOffHand_weaponType() == WeaponType.HandCrossbow)) ? 1.0
						: 0.0);
		double t = 0.0;
		double aps = data.getAps();
		double interval = (1.0 / aps) + (data.getDelay() / 1000.0);
		int totalSpender = 0;
		Rune sentryRune = data.getSentryRune();
		double healthGlobeInterval = (data.getNumHealthGlobes() > 0) ? (FiringData.DURATION / (data
				.getNumHealthGlobes() + 1.0)) : (FiringData.DURATION * 2.0);
		double nextHealthGlobe = healthGlobeInterval;
		double cdr = data.getCdr();
		double prepCd = 20.0 * (1.0 - cdr);
		double prepAvail = 0;
		double batCd = 30.0 * (1.0 - cdr);
		double batAvail = 0;
		int numBat = 0;
		int numPrep = 0;
		int numHealthGlobes = 0;
		double healthGlobeHatred = 0.0;
		double regenHatred = 0.0;
		int numMarked = 0;
		double markedHatred = 0;
		int totalHits = 0;

		while (t < DURATION) {

			if (t >= nextHealthGlobe) {
				nextHealthGlobe += healthGlobeInterval;
				numHealthGlobes++;

				if (data.isBloodVengeance()) {
					healthGlobeHatred += Math.min(30.0, maxHatred - hatred);
					hatred = Math.min(maxHatred, hatred + 30.0);
				}

				if (data.isReapersWraps()) {
					healthGlobeHatred += Math.min(
							maxHatred * data.getReapersWrapsPercent(),
							maxHatred - hatred);
					hatred = Math.min(
							maxHatred,
							hatred
									+ (maxHatred * data
											.getReapersWrapsPercent()));
				}
			}

			if (data.isPreparationPunishment()
					&& ((maxHatred - hatred) >= 75.0) && (prepAvail <= t)) {
				hatred += 75.0;
				prepAvail = t + prepCd;
				numPrep++;
			}

			if (data.isCompanion() && ((data.getNumMarauders() >= 2) || (data.getCompanionRune() == Rune.Bat))) {
				if (((maxHatred - hatred) >= 50.0) && (batAvail <= t)) {
					hatred += 50.0;
					batAvail = t + batCd;
					numBat++;
				}
			}

			for (SkillAndRune skr : skills) {
				double h = skr.getHatred(data);

				if ((h + hatred) >= 0) {
					Integer n = skillQty.get(skr) + 1;
					skillQty.put(skr, n);
					hatred = Math.min(maxHatred, hatred + h);

					if (data.isMarked() && (data.getMfdRune() == Rune.Mortal_Enemy)) {
						
						numMarked++;
						double mh = (4.0 * data.getMfdUptime()) + (4.0 * data.getNumAdditional() * data.getMfdAddUptime());

						if (hatred > (maxHatred - mh)) {
							mh = maxHatred - mh;
						}
						
						if (mh > 0) {
							hatred += mh;
							markedHatred += mh;
						}
					}

					break;
					
				}
			}

			t += interval;

			if (t < FiringData.DURATION) {
				regenHatred += Math.min(interval * regen, maxHatred - hatred);
				hatred = Math.min(maxHatred, hatred + (interval * regen));
			}
		}

		for (SkillAndRune skr : skills) {

			ActiveSkill skill = skr.getSkill();
			Rune rune = skr.getRune();

			if ((skill != ActiveSkill.SENTRY) && (skill != ActiveSkill.BOLT)) {
				int qty = skillQty.get(skr);

				if (qty > 0) {
					
					totalHits += qty;
					
					list.addAll(DamageFunction.getDamages(true, false,
							"Player", new DamageSource(skill, rune), qty, data));

					if ((data.getNumMarauders() >= 4)
							&& (skill.getSkillType() == SkillType.Spender)) {
						list.addAll(DamageFunction.getDamages(false, true,
								"Sentry", new DamageSource(skill, rune), qty,
								data));
						totalSpender += qty;
					}
				}
			}
		}

		BreakPoint bp = BreakPoint.getBp(data.getBp());
		int boltQty = Math.max(0, bp.getQty() - totalSpender);

		if (boltQty > 0) {
			list.addAll(DamageFunction.getDamages(false, true, "Sentry",
					new DamageSource(ActiveSkill.SENTRY, sentryRune), boltQty,
					data));
			list.addAll(DamageFunction.getDamages(false, true, "Sentry",
					new DamageSource(ActiveSkill.BOLT, sentryRune), boltQty,
					data));
		}

		if (data.isCaltrops()) {
			list.addAll(DamageFunction.getDamages(true, false, "Player",
					new DamageSource(ActiveSkill.Caltrops, data.getCaltropsRune()), (int)(FiringData.DURATION * data.getCaltropsUptime()),
					data));
		}
		
		if (data.isSpikeTrap()) {
			list.addAll(DamageFunction.getDamages(true, false, "Player",
					new DamageSource(ActiveSkill.ST, data.getSpikeTrapRune()), data.getNumSpikeTraps() * 3,
					data));
		}
		
		if (data.isHelltrapper()) {
			int num = (int) Math.round(totalHits * data.getHelltrapperPercent() * 0.5);
			
			if (num > 0) {
				list.addAll(DamageFunction.getDamages(false, false, "Helltrapper",
						new DamageSource(ActiveSkill.ST, (data.isSpikeTrap() ? data.getSpikeTrapRune() : Rune.None)), num * 3,
						data));
				
				// Can't have double DoT
//				list.addAll(DamageFunction.getDamages(false, false, "Helltrapper",
//						new DamageSource(ActiveSkill.Caltrops, (data.isCaltrops() ? data.getCaltropsRune() : Rune.None)), 1,
//						data));
			}
		}
		
		if (data.isCompanion()) {
			
			Rune[] companionRunes = new Rune[] { data.getCompanionRune() };
			
			if (data.getNumMarauders() >= 2)
				companionRunes = ActiveSkill.Companion.getRunes();

			double attacks = FiringData.DURATION;
			
			if (data.isTnt())
				attacks = FiringData.DURATION * (1 + data.getTntPercent());

			for (Rune r : companionRunes) {
				
				list.addAll(DamageFunction.getDamages(false, false, ActiveSkill.Companion.getLongName(),
						new DamageSource(ActiveSkill.Companion, r),
						(int) Math.round(attacks), data));
			}
		}

		// gem procs
		list.addAll(DamageFunction.getDamages(false, false, "Player", null,
				FiringData.DURATION, data));

		if (numPrep > 0) {
			Damage d = new Damage();
			d.shooter = "Preparation";
			d.hatred = numPrep * 75.0;
			d.qty = numPrep;
			list.add(d);
		}

		if (numBat > 0) {
			Damage d = new Damage();
			d.shooter = "Companion";
			d.hatred = numBat * 50.0;
			d.qty = numBat;
			list.add(d);
		}

		if (numHealthGlobes > 0) {
			Damage d = new Damage();
			d.shooter = "Health Globes";
			d.hatred = healthGlobeHatred;
			d.qty = numHealthGlobes;
			list.add(d);
		}

		if (numMarked > 0) {
			Damage d = new Damage();
			d.shooter = "MFD";
			d.hatred = markedHatred;
			d.qty = numMarked;
			list.add(d);
		}
		
		Damage d = new Damage();
		d.shooter = "Hatred Regen";
		d.hatred = regenHatred;
		d.qty = FiringData.DURATION;
		list.add(d);

		return list.toArray(new Damage[0]);
	}

}
