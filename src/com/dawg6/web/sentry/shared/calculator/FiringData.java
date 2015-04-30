package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Vector;

public class FiringData {

	private static final double MAX_DURATION = 15.0 * 60.0;

	public static DamageResult calculateDamages(CharacterData data) {

		EventQueue eventQueue = new EventQueue();
		
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

		List<Damage> log = new Vector<Damage>();

//		double fokCd = (10.0 * (1 - cdr));
//		Rune fokRune = data.getSkills().get(ActiveSkill.FoK);
		
		if (data.isPreparation()
				&& (data.getPreparationRune() == Rune.Punishment)) 
			eventQueue.push(new PreparationEvent(data));
			
		if (data.isWolf() && (data.getWolfUptime() > 0))
			eventQueue.push(new BuffEvent.WolfBuff(data.getWolfUptime()));
		
		if (data.isBbv() && data.isSlamDance() && (data.getBbvUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.Bbv, 20.0, data.getBbvUptime()));
		
		if (data.isMassConfusion() && (data.getMassConfusionUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.Paranoia, 12.0, data.getMassConfusionUptime()));

		if (data.isPiranhas() && (data.getPiranhasUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.Piranhas, 8.0, data.getPiranhasUptime()));

		if (data.isInnerSanctuary() && (data.getInnerSanctuaryUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.InnerSanct, 6.0, data.getInnerSanctuaryUptime()));

		if (data.isCripplingWave() && (data.getCripplingWaveUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.CripWave, 3.0, data.getCripplingWaveUptime()));

		if (data.isConviction() && (data.getConvictionPassiveUptime() > 0))
			eventQueue.push(new BuffEvent.ConvictionPassiveBuff(data.getConvictionPassiveUptime()));

		if (data.isConviction() && (data.getConvictionActiveUptime() > 0))
			eventQueue.push(new BuffEvent.ConvictionActiveBuff(data.getConvictionActiveUptime()));

		if (data.isMarked() && (data.getMfdUptime() > 0)) 
			eventQueue.push(new BuffEvent.MfDPrimaryBuff(data));

		if (data.isMarked() && (data.getMfdAddUptime() > 0) && (data.getNumAdditional() > 0)) 
			eventQueue.push(new BuffEvent.MfDAdditionalBuff(data));

		if (data.isCalamityMdf() && (data.getCalamityUptime() > 0)) 
			eventQueue.push(new BuffEvent.CalamityBuff(data.getCalamityUptime()));

		if (data.isBotp())
			eventQueue.push(new BuffEvent.BotPBuff(data.getBotpLevel()));
		
		if (data.isCompanion() && (data.getNumMarauders() >= 2) || (data.getCompanionRune() == Rune.Bat) || (data.getCompanionRune() == Rune.Wolf)) 
			eventQueue.push(new CompanionBuffEvent(data));
		
		ActionEvent action = new ActionEvent(data);
		eventQueue.push(action);
		
		if (data.getSkills().containsKey(ActiveSkill.Vengeance))
			eventQueue.push(new VengeanceEvent(data));

		if (data.getSkills().containsKey(ActiveSkill.RoV)) {
			RoVEvent rov = new RoVEvent(data);
			eventQueue.push(rov);
			action.setRov(rov);
		}
		
		if (data.isSentry())
			eventQueue.push(new SentryBoltEvent(data));
		
		if (data.isCompanion())
			eventQueue.push(new CompanionDamageEvent(data));
		
		eventQueue.push(new DotEvent());
		eventQueue.push(new RegenEvent(data));
		eventQueue.push(new DiscRegenEvent(data));
		
//		if (fokRune == Rune.Pinpoint_Accuracy)
//			fokCd = 15.0 * (1 - cdr);
//
//		double nextFok = 0;
//
//		if ((fokRune == null) || (fokRune == Rune.Knives_Expert))
//			nextFok = Double.MAX_VALUE;
//
//		int numFok = 0;

		SimulationState state = new SimulationState(data, targets);
		
		if (data.isBastions()) {
			
			if (data.hasGenerator())
				state.getBuffs().set(Buff.BwGen, 5.0);
			
			if (data.hasSpender())
				state.getBuffs().set(Buff.BwSpend, 5.0);
		}
		
		while (targets.getPrimary().isAlive()) {
			Event event = eventQueue.pop();
			double t = event.getTime();
			
			if (t > FiringData.MAX_DURATION)
				break;
			
			state.setTime(t);
			
			event.execute(eventQueue, log, state);
		}

//			if ((fokRune != null) && (t > nextFok)) {
//				numFok++;
//				nextFok += fokCd;
//			}
//
//			if (venActive && (t > venEnds)) {
//				venActive = false;
//			}
//
//			if ((venRune != null) && (t >= nextVen)) {
//				if ((venRune != Rune.Seethe) || (hatred < (maxHatred / 2))) {
//					venActive = true;
//					nextVen = t + venCd;
//					venEnds = t + 15.0;
//				}
//			}


		double duration = Math.round(state.getTime() * 100.0) / 100.0;


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


		result.damages = log.toArray(new Damage[0]);
		result.duration = duration;

		return result;
	}

}
