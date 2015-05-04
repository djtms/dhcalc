package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;
import java.util.Vector;

public class FiringData {

	public static final int MAX_DURATION = 15 * 60;

	public static DamageResult calculateDamages(CharacterData data) {

		EventQueue eventQueue = new EventQueue();
		
		TargetList targets = new TargetList();
		DamageResult result = new DamageResult();

		TargetHolder primary = new TargetHolder();
		primary.setTargetType(TargetType.Primary);
		primary.setMonsterType(data.getPrimaryTargetType());
		primary.setMaxHp(data.getPrimaryTargetHealth());
		primary.setCurrentHp(data.getPrimaryTargetHealth());
		targets.setTarget(TargetType.Primary, primary);

		int numTargets = data.getNumAdditional();
		List<TargetHolder> adds = new Vector<TargetHolder>(numTargets);

		for (int i = 0; i < numTargets; i++) {
			TargetType type = TargetType.Additional(i+1);
			TargetHolder add = new TargetHolder();
			add.setTargetType(type);
			add.setMonsterType(data.getAdditionalTargetType());
			add.setMaxHp(data.getAdditionalTargetHealth());
			add.setCurrentHp(data.getAdditionalTargetHealth());
			adds.add(add);
			targets.setTarget(type, add);
		}

		List<Damage> log = new Vector<Damage>();

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
		
		if (data.getSkills().containsKey(ActiveSkill.FoK) && (data.getSkills().get(ActiveSkill.FoK) != Rune.Knives_Expert)) {
			eventQueue.push(new FoKEvent(data));
		}

		if (data.isSentry())
			eventQueue.push(new SentryBoltEvent(data));
		
		if (data.isCompanion())
			eventQueue.push(new CompanionDamageEvent(data));
		
		eventQueue.push(new DotEvent());
		eventQueue.push(new RegenEvent(data));
		eventQueue.push(new DiscRegenEvent(data));
		
		SimulationState state = new SimulationState(data, targets);
		
		if (data.isBastions()) {
			
			if (data.hasGenerator())
				state.getBuffs().set(Buff.BwGen, 5.0);
			
			if (data.hasSpender())
				state.getBuffs().set(Buff.BwSpend, 5.0);
		}
		
		double timeLimit = data.getTimeLimit();
		
		while (targets.getTarget(TargetType.Primary).isAlive()) {
			Event event = eventQueue.pop();
			double t = event.getTime();
			
			if (t > timeLimit)
				break;
			
			state.setTime(t);
			
			event.execute(eventQueue, log, state);
		}

		double duration = Math.round(state.getTime() * 100.0) / 100.0;

		result.damages = log.toArray(new Damage[0]);
		result.duration = duration;

		return result;
	}

}
