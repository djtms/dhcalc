package com.dawg6.web.sentry.shared.calculator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class FiringData {

	private static final double MAX_DURATION = 600.0;

	public static DamageResult calculateDamages(CharacterData data) {

		TargetList targets = new TargetList();
		DamageResult result = new DamageResult();

		TargetHolder primary = new TargetHolder();
		primary.setTargetType(TargetType.Primary);
		primary.setMonsterType(data.getPrimaryTargetType());
		primary.setMaxHp(data.getPrimaryTargetHealth());
		primary.setCurrentHp(data.getPrimaryTargetHealth());
		targets.setPrimary(primary);

		int numTargets = data.getNumAdditional();
		List<TargetHolder> adds = new Vector<TargetHolder>(numTargets);

		for (int i = 0; i < numTargets; i++) {
			TargetHolder add = new TargetHolder();
			add.setTargetType(TargetType.Additional);
			add.setMonsterType(data.getAdditionalTargetType());
			add.setMaxHp(data.getAdditionalTargetHealth());
			add.setCurrentHp(data.getAdditionalTargetHealth());
			adds.add(add);
		}

		targets.setAdditional(adds);

		List<Damage> list = new Vector<Damage>();

		List<SkillAndRune> skills = new Vector<SkillAndRune>();
		Map<SkillAndRune, Integer> skillQty = new TreeMap<SkillAndRune, Integer>();
		SkillAndRune spender = null;
		SkillAndRune generator = null;

		for (Map.Entry<ActiveSkill, Rune> e : data.getSkills().entrySet()) {
			ActiveSkill skill = e.getKey();
			SkillType type = skill.getSkillType();
			Rune rune = e.getValue();

			if ((type == SkillType.Primary)
					|| (type == SkillType.Spender)
					|| (type == SkillType.Channeled)
					|| ((skill == ActiveSkill.FoK) && (rune == Rune.Knives_Expert))) {
				SkillAndRune skr = new SkillAndRune(skill, rune);
				skills.add(skr);
				skillQty.put(skr, 0);

				if ((skr.getHatred(data) > 0)
						&& ((generator == null) || (skr.getHatred(data) > generator
								.getHatred(data))))
					generator = skr;

				if ((spender == null) && (skr.getHatred(data) < 0))
					spender = skr;
			}
		}

		Collections.sort(skills, new SkillAndRune.HatredSorter(data));

		double maxHatred = data.getMaxHatred();
		double hatred = maxHatred;
		double batAmount = 50.0;
		double prepAmount = 75.0;
		double bvAmount = 30.0;
		double markedAmount = (4.0 * data.getMfdUptime())
				+ (4.0 * data.getNumAdditional() * data.getMfdAddUptime());
		double reaperAmount = maxHatred * data.getReapersWrapsPercent();
		Rune companionRune = data.getCompanionRune();
		double regen = 5.0
				+ data.getHatredPerSecond()
				+ (data.isInspire() ? 1.0 : 0.0)
				+ (((companionRune == Rune.Bat) || ((companionRune != null) && data
						.getNumMarauders() >= 2)) ? 1.0 : 0.0)
				+ ((data.isArchery()
						&& (data.getWeaponType() == WeaponType.HandCrossbow) && (data
						.getOffHand_weaponType() == WeaponType.HandCrossbow)) ? 1.0
						: 0.0);
		double nextRegen = 1.0;
		double venRegenTick = 10.0;

		if (data.isHexingPants()) {
			venRegenTick = venRegenTick
					+ (venRegenTick * data.getHexingPantsUptime() * .25)
					- (venRegenTick * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			regen = regen
					+ (regen * data.getHexingPantsUptime() * .25)
					- (regen * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			batAmount = batAmount
					+ (batAmount * data.getHexingPantsUptime() * .25)
					- (batAmount * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			prepAmount = prepAmount
					+ (prepAmount * data.getHexingPantsUptime() * .25)
					- (prepAmount * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			bvAmount = bvAmount
					+ (bvAmount * data.getHexingPantsUptime() * .25)
					- (bvAmount * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			reaperAmount = reaperAmount
					+ (reaperAmount * data.getHexingPantsUptime() * .25)
					- (reaperAmount * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			markedAmount = markedAmount
					+ (markedAmount * data.getHexingPantsUptime() * .25)
					- (markedAmount * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
		}

		double t = 0.0;
		double aps = data.getAps();
		double interval = (1.0 / aps) + (data.getDelay() / 1000.0);
		Rune sentryRune = Rune.None;

		if (data.isSentry())
			sentryRune = data.getSentryRune();

		double healthGlobeHpInterval = (data.getNumHealthGlobes() > 0) ? (1.0 / (data
				.getNumHealthGlobes() + 1.0)) : 1.0;
		double nextHealthHpGlobe = 1.0 - healthGlobeHpInterval;
		double cdr = data.getCdr();
		double prepCd = 20.0 * (1.0 - cdr);
		double prepAvail = 0;
		double batCd = 30.0 * (1.0 - cdr);
		double batAvail = 0;
		boolean hasBat = data.isCompanion() && ((data.getNumMarauders() >= 2) || (data.getCompanionRune() == Rune.Bat));
		int numBat = 0;
		int numPrep = 0;
		int numHealthGlobes = 0;
		double healthGlobeHatred = 0.0;
		double regenHatred = 0.0;
		int numMarked = 0;
		double markedHatred = 0;
		int totalHits = 0;
		double rovCd = (30.0 * (1 - cdr));
		double fokCd = (10.0 * (1 - cdr));
		Rune fokRune = data.getSkills().get(ActiveSkill.FoK);
		double venCd = (90.0 * (1 - cdr));
		Rune venRune = data.getSkills().get(ActiveSkill.Vengeance);
		double nextVen = 0.0;
		boolean venActive = false;
		int numVen = 0;
		int numVenAttacks = 0;
		double venEnds = 0.0;
		double venRegen = 0.0;
		
		Map<Buff, BuffHolder> playerBuffs = new TreeMap<Buff, BuffHolder>();
		
		if (data.isWolf() && (data.getWolfUptime() > 0))
			playerBuffs.put(Buff.OtherWolf, new BuffHolder(Buff.OtherWolf, 10.0, data.getWolfUptime()));
		
		if (data.isBbv() && data.isSlamDance() && (data.getBbvUptime() > 0))
			playerBuffs.put(Buff.Bbv, new BuffHolder(Buff.Bbv, 20.0, data.getBbvUptime()));
		
		if (data.isMassConfusion() && (data.getMassConfusionUptime() > 0))
			playerBuffs.put(Buff.Paranoia, new BuffHolder(Buff.Paranoia, 12.0, data.getMassConfusionUptime()));

		if (data.isPiranhas() && (data.getPiranhasUptime() > 0))
			playerBuffs.put(Buff.Piranhas, new BuffHolder(Buff.Piranhas, 8.0, data.getPiranhasUptime()));

		if (data.isInnerSanctuary() && (data.getInnerSanctuaryUptime() > 0))
			playerBuffs.put(Buff.InnerSanct, new BuffHolder(Buff.InnerSanct, 6.0, data.getInnerSanctuaryUptime()));

		if (data.isCripplingWave() && (data.getCripplingWaveUptime() > 0))
			playerBuffs.put(Buff.CripWave, new BuffHolder(Buff.CripWave, 3.0, data.getCripplingWaveUptime()));

		if (data.isConviction() && (data.getConvictionPassiveUptime() > 0))
			playerBuffs.put(Buff.ConvictionPassive, new BuffHolder(Buff.ConvictionPassive, 3.0, data.getConvictionPassiveUptime()));

		if (data.isConviction() && (data.getConvictionActiveUptime() > 0))
			playerBuffs.put(Buff.ConvictionActive, new BuffHolder(Buff.ConvictionActive, 3.0, data.getConvictionActiveUptime()));

		if (data.isMarked() && (data.getMfdUptime() > 0)) 
			playerBuffs.put(Buff.MfdPrimary, new BuffHolder(Buff.MfdPrimary, 30.0, data.getMfdUptime()));

		if (data.isMarked() && (data.getMfdAddUptime() > 0) && (data.getNumAdditional() > 0)) 
			playerBuffs.put(Buff.MfdAdditional, new BuffHolder(Buff.MfdAdditional, 30.0, data.getMfdAddUptime()));

		if (data.isCalamityMdf() && (data.getCalamityUptime() > 0)) 
			playerBuffs.put(Buff.Calamity, new BuffHolder(Buff.Calamity, 30.0, data.getCalamityUptime()));

		if (data.isBotp())
			playerBuffs.put(Buff.BotP, new BuffHolder(Buff.BotP, 20.0 + data.getBotpLevel(), -1));
		
		boolean hasWolf = data.isCompanion() && ((data.getNumMarauders() >= 2) || (data.getCompanionRune() == Rune.Wolf));

		if (fokRune == Rune.Pinpoint_Accuracy)
			fokCd = 15.0 * (1 - cdr);

		double nextFok = 0;

		if ((fokRune == null) || (fokRune == Rune.Knives_Expert))
			nextFok = Double.MAX_VALUE;

		int numFok = 0;

		double rovTime = 5.0;
		Rune rovRune = data.getRovRune();

		if (rovRune == Rune.Dark_Cloud)
			rovTime = 8.0;
		else if (rovRune == Rune.Stampede)
			rovTime = 6.0;

		// if (data.getNumNats() >= 4) {
		// rovCd = Math.max(rovTime, rovCd - (2.0 * data.getRovKilled()));
		// }

		int numRov = 0;
		double nextRov = 0;

		BreakPoint bp = BreakPoint.getBp(data.getBp());
		double boltAps = (double) bp.getQty() / (double) BreakPoint.DURATION;
		double boltInterval = 1.0 / boltAps;
		double nextBolt = 0.0;

		double petAps = 1.0;

		if (data.isTnt())
			petAps = petAps * (1 + data.getTntPercent());

		double petInterval = 1.0 / petAps;
		double nextPet = data.isCompanion() ? 0.0 : Double.MAX_VALUE;
		Rune[] companionRunes = new Rune[] { data.getCompanionRune() };

		if (data.getNumMarauders() >= 2)
			companionRunes = ActiveSkill.Companion.getRunes();
		double bwgExpires = 5.0;
		double bwsExpires = 5.0;

		SimulationState state = new SimulationState(data, targets);
		
		if (data.isBastions()) {
			
			if (data.hasGenerator())
				state.getBuffs().set(Buff.BwGen, bwgExpires);
			
			if (data.hasSpender())
				state.getBuffs().set(Buff.BwSpend, bwsExpires);
		}
		
		while (targets.getPrimary().isAlive() && (t < FiringData.MAX_DURATION)) {
			
			double primaryHpPercent = targets.getPrimary().getPercentHealth();

			if (data.isRov() && (t > nextRov)) {
				numRov++;
				nextRov += rovCd;
			}

			if ((fokRune != null) && (t > nextFok)) {
				numFok++;
				nextFok += fokCd;
			}

			if (venActive && (t > venEnds)) {
				venActive = false;
			}

			if ((primaryHpPercent <= nextHealthHpGlobe) && (hatred <= 75.0)) {
				nextHealthHpGlobe -= healthGlobeHpInterval;
				double h = 0;

				if (data.isBloodVengeance()) {
					h += bvAmount;
				}

				if (data.isReapersWraps()) {
					h += reaperAmount;
				}

				double actual = Math.min(maxHatred - hatred, h);
				hatred += actual;

				Damage d = new Damage();
				d.shooter = "Health Globe";
				d.hatred = actual;
				d.qty = 1;
				d.time = t;
				d.currentHatred = hatred;
				list.add(d);
			}

			if (data.isPreparation()
					&& (data.getPreparationRune() == Rune.Punishment)
					&& ((maxHatred - hatred) >= prepAmount) && (prepAvail < t)) {
				hatred += prepAmount;
				prepAvail = t + prepCd;
				numPrep++;
			}

			while (t > nextRegen) {
				double tick = regen;

				if (venActive && (venRune == Rune.Seethe)) {
					tick += venRegenTick;
				}

				double regenTick = Math.min(tick, maxHatred - hatred);
				hatred += regenTick;

				if (regenTick > 0) {
					Damage d = new Damage();
					d.shooter = "Player";
					d.hatred = regenTick;
					d.currentHatred = hatred;
					d.time = nextRegen;
					d.qty = 1;
					d.note = "Hatred Regen";
					list.add(d);
				}

				nextRegen += 1.0;
			}

			if (data.isCompanion()) {
				while (t > nextPet) {

					for (Rune r : companionRunes) {

						applyDamages(nextPet, hatred,
								DamageFunction.getDamages(false, false,
										ActiveSkill.Companion.getLongName(),
										new DamageSource(ActiveSkill.Companion,
												r), state), targets,
								list);
					}

					nextPet += petInterval;
				}
			}

			if (data.isSentry()) {
				while (t > nextBolt) {
					applyDamages(nextBolt, hatred, DamageFunction.getDamages(
							false, true, "Sentry", new DamageSource(
									ActiveSkill.BOLT, sentryRune), state), targets, list);
					applyDamages(nextBolt, hatred, DamageFunction.getDamages(
							false, true, "Sentry", new DamageSource(
									ActiveSkill.SENTRY, sentryRune), state), targets, list);
					nextBolt += boltInterval;
				}
			}

			if ((venRune != null) && (t >= nextVen)) {
				if ((venRune != Rune.Seethe) || (hatred < (maxHatred / 2))) {
					venActive = true;
					numVen++;
					nextVen = t + venCd;
					venEnds = t + 15.0;
				}
			}

			if ((hasBat || hasWolf) && (batAvail <= t)) {
				
				boolean use = false;
				
				if (hasBat && (hatred <= 50.0)) {
					use = true;
				} else if (hasWolf && !state.getBuffs().isActive(Buff.OtherWolf)) {
					use = true;
				}
				
				if (use) {


					if (hasBat) {
						double h = Math.min(batAmount, maxHatred - hatred);
						
						if (h > 0) {
							hatred += h;
		
							Damage d = new Damage();
							d.shooter = "Companion";
							d.source = new DamageSource(ActiveSkill.Companion, Rune.Bat);
							d.hatred = batAmount;
							d.currentHatred = hatred;
							d.note = "Bat Hatred";
							d.qty = 1;
							d.time = t;
							list.add(d);
						}
					}

					batAvail = t + batCd;

					if (hasWolf) {
						state.getBuffs().set(Buff.Wolf, t + 10.0);
						
						Damage d = new Damage();
						d.shooter = "Companion";
						d.source = new DamageSource(ActiveSkill.Companion, Rune.Wolf);
						d.note = "Wolf Howl";
						d.time = t;
						d.qty = 1;
						list.add(d);
					}
				}
			}

			for (BuffHolder buff : playerBuffs.values()) {
				if (buff.getAvail() <= t) {
					if ((buff.getBuff() == Buff.OtherWolf)  && state.getBuffs().isActive(Buff.Wolf)) {
						break;
					}
					
					if ((buff.getBuff() == Buff.ConvictionActive)  && state.getBuffs().isActive(Buff.ConvictionPassive)) {
						break;
					}
				
					if ((buff.getBuff() == Buff.ConvictionPassive)  && state.getBuffs().isActive(Buff.ConvictionActive)) {
						break;
					}
				
					buff.setAvail(t + buff.getCd());
					state.getBuffs().set(buff.getBuff(), t + buff.getDuration());

					Damage d = new Damage();
					

					if (buff.getBuff() == Buff.BotP) {
						d.shooter = "Player";
						d.note = "BotP Active";
					} else if (buff.getBuff() == Buff.MfdPrimary) {
						d.shooter = "Player";
						d.source = new DamageSource(ActiveSkill.MFD, data.getMfdRune());
						d.note = "MfD Primary";
					} else if (buff.getBuff() == Buff.MfdAdditional) {
						d.shooter = "Player";
						d.source = new DamageSource(ActiveSkill.MFD, data.getMfdRune());
						d.note = "MfD Additional";
					} else if (buff.getBuff() == Buff.Calamity) {
						d.shooter = "Player";
						d.source = new DamageSource(ActiveSkill.MFD, Rune.None);
						d.note = "Calamity MfD";
					} else {
						d.shooter = "Other Player";
					
						if (buff.getBuff() == Buff.OtherWolf) {
							d.source = new DamageSource(ActiveSkill.Companion, Rune.Wolf);
							d.note = "Other Wolf Howl";
						} else {
							d.note = "Other Player " + buff.getBuff().toString();
						}
					}
					
					d.time = t;
					d.qty = 1;
					list.add(d);
				}
			}
			
			SkillAndRune selected = null;

			if (data.isBastions()) {
				if ((t + interval) >= bwgExpires) {
					selected = generator;
				} 
				
				if ((t + interval) >= bwsExpires) {

					if (spender != null) {
						double h = spender.getHatred(data);

						if (h <= hatred) {
							selected = spender;
						}
					}
				}
			}

			if (selected == null) {
				for (SkillAndRune skr : skills) {
					double h = skr.getHatred(data);

					if ((h + hatred) >= 0) {
						selected = skr;
						break;
					}
				}
			}

			state.setTime(t);

			if (selected != null) {
				double h = selected.getHatred(data);

				Integer n = skillQty.get(selected) + 1;
				skillQty.put(selected, n);
				hatred = Math.min(maxHatred, hatred + h);

				if (data.isBastions()) {
					if (h < 0) {
						bwsExpires = t + 5.0;
						state.getBuffs().set(Buff.BwSpend, bwsExpires);
						
					} else if (h > 0) {
						bwgExpires = t + 5.0;
						state.getBuffs().set(Buff.BwGen, bwgExpires);
					}
				}

				totalHits++;
				applyDamages(t, hatred, DamageFunction.getDamages(true, false,
						"Player", new DamageSource(selected.getSkill(),
								selected.getRune()), state), targets,
						list);

				if (data.isSentry()) {
					if ((data.getNumMarauders() >= 4)
							&& (selected.getSkill().getSkillType() == SkillType.Spender)) {
						applyDamages(t, hatred, DamageFunction.getDamages(
								false,
								true,
								"Sentry",
								new DamageSource(selected.getSkill(), selected
										.getRune()), state), targets,
								list);

						nextBolt += boltInterval;
					}
				}

				if (
						data.isMarked() && 
						(data.getMfdRune() == Rune.Mortal_Enemy) && 
						(state.getBuffs().isActive(Buff.MfdPrimary) || state.getBuffs().isActive(Buff.MfdAdditional))
					) {

					numMarked++;
					double mh = markedAmount;

					if (hatred > (maxHatred - mh)) {
						mh = maxHatred - mh;
					}

					if (mh > 0) {
						hatred += mh;

						Damage d = new Damage();
						d.shooter = "Player";
						d.source = new DamageSource(ActiveSkill.MFD,
								Rune.Mortal_Enemy);
						d.hatred = mh;
						d.qty = 1;
						d.time = t;
						d.note = "MfD/ME Hatred";
						d.currentHatred = hatred;
						list.add(d);
					}
				}

				if (venActive) {
					// TODO Handle Dark Heart DoT
					applyDamages(t, hatred, DamageFunction.getDamages(true, false,
							"Player", new DamageSource(ActiveSkill.Vengeance,
									venRune), state), targets, list);
				}

				if (data.getNumNats() >= 2) {
					nextRov -= 2.0;
				}
			}

			t += interval;
		}

		double duration = Math.round(t * 100.0) / 100.0;

		// TODO Handle RoV
		// if (numRov > 0) {
		// list.addAll(DamageFunction.getDamages(true, false, "Player",
		// new DamageSource(ActiveSkill.RoV, data.getRovRune()),
		// numRov, data));
		//
		// if (data.isCrashingRain()) {
		// list.addAll(DamageFunction.getDamages(true, false, "Player",
		// new DamageSource(ActiveSkill.CR, data.getRovRune()),
		// numRov, data));
		// }
		// }

		// TODO Handle FoK
		// if (numFok > 0) {
		// list.addAll(DamageFunction.getDamages(true, false, "Player",
		// new DamageSource(ActiveSkill.FoK, fokRune), numFok, data));
		// }

		// TODO Handle Caltrops
		// if (data.isCaltrops()) {
		// list.addAll(DamageFunction.getDamages(
		// true,
		// false,
		// "Player",
		// new DamageSource(ActiveSkill.Caltrops, data
		// .getCaltropsRune()),
		// (int) (duration * data.getCaltropsUptime()),
		// data));
		// }

		// TODO Handle SpikeTrap
		// if (data.isSpikeTrap()) {
		// list.addAll(DamageFunction.getDamages(true, false, "Player",
		// new DamageSource(ActiveSkill.ST, data.getSpikeTrapRune()),
		// data.getNumSpikeTraps() * 3, data));
		// }

		// TODO Handle Helltrapper Spike Trap
		// if (data.isHelltrapper()) {
		// int num = (int) Math.round(totalHits * data.getHelltrapperPercent()
		// * 0.5);
		//
		// if (num > 0) {
		// list.addAll(DamageFunction.getDamages(false, false,
		// "Helltrapper",
		// new DamageSource(ActiveSkill.ST,
		// (data.isSpikeTrap() ? data.getSpikeTrapRune()
		// : Rune.None)), num * 3, data));
		//
		// // Can't have double DoT
		// // list.addAll(DamageFunction.getDamages(false, false,
		// // "Helltrapper",
		// // new DamageSource(ActiveSkill.Caltrops, (data.isCaltrops() ?
		// // data.getCaltropsRune() : Rune.None)), 1,
		// // data));
		// }
		// }

		// TODO Add gem procs
		// list.addAll(DamageFunction.getDamages(false, false, "Player", null,
		// (int)duration, data));

		if (numPrep > 0) {
			Damage d = new Damage();
			d.shooter = "Preparation";
			d.hatred = numPrep * prepAmount;
			d.qty = numPrep;
			list.add(d);
		}

		result.damages = list.toArray(new Damage[0]);
		result.duration = duration;

		return result;
	}

	private static void applyDamages(double t, double hatred,
			List<Damage> source, TargetList targets, List<Damage> dest) {
		applyDamages(t, hatred, source, targets, dest, null);
	}

	private static void applyDamages(double t, double hatred,
			List<Damage> source, TargetList targets, List<Damage> dest,
			String notes) {
		for (Damage dr : source) {
			dr.time = t;
			// TODO Handle Additional
			if (dr.target == TargetType.Primary) {
				dr.actualDamage = targets.getPrimary().applyDamage(dr.damage);
				dr.targetHp = targets.getPrimary().getCurrentHp();
				dr.targetHpPercent = targets.getPrimary().getPercentHealth();
				dr.currentHatred = hatred;

				if (notes != null) {
					if ((dr.note == null) || (dr.note.length() == 0)) {
						dr.note = notes;
					} else {
						dr.note = dr.note + " " + notes;
					}
				}

				if (dr.actualDamage > 0) {
					dest.add(dr);
				}
			}
		}

	}
}
