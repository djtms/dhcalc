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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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

		
		if (data.getNumHealthGlobes() > 0)
			eventQueue.push(new HealthGlobeEvent(data));
		
		if (data.isCoe())
			eventQueue.push(new CoEBuffEvent());
		
		if (data.isPreparation()
				&& (data.getPreparationRune() == Rune.Punishment)) 
			eventQueue.push(new PreparationEvent(data));
			
		if (data.isWolf() && (data.getWolfUptime() > 0))
			eventQueue.push(new BuffEvent.WolfBuff(data.getWolfUptime()));
		
		if (data.isBbv() && data.isSlamDance() && (data.getBbvUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.Bbv, 20.0, data.getBbvUptime()));
		
		if (data.isTimeWarp() && (data.getTimeWarpUptime() > 0))
			eventQueue.push(new BuffEvent(Buff.TimeWarp, 15.0, data.getTimeWarpUptime()));

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

		if (data.isSpikeTrap()) 
			eventQueue.push(new SpikeTrapActionEvent(data));

		Rune caltropsRune = data.getCaltropsRune();
		
		if ((caltropsRune != null) && (data.getCaltropsUptime() > 0)) {
			
			if (caltropsRune == Rune.Bait_the_Trap)
				eventQueue.push(new BuffEvent.CaltropsBuffEvent(data));
			else
				eventQueue.push(new CaltropsEvent(data));
		}

		if (data.isMarked() && (data.getMfdAddUptime() > 0) && (data.getNumAdditional() > 0)) 
			eventQueue.push(new BuffEvent.MfDAdditionalBuff(data));

		if (data.isCalamityMdf()) 
			eventQueue.push(new BuffEvent.CalamityBuff());

		if (data.isVaxo()) 
			eventQueue.push(new BuffEvent.VaxoBuff(data));

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
