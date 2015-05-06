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

public class BuffEvent extends Event {

	private final BuffHolder holder;

	public BuffEvent(Buff buff, double duration, double upTime) {
		this.holder = new BuffHolder(buff, duration, upTime);
		this.time = holder.getAvail();
	}
	
	protected boolean test(SimulationState state) {
		return true;
	}
	
	protected void updateState(SimulationState state) {
		
	}
	
	
	@Override
	public void execute(EventQueue queue, List<Damage> log, SimulationState state) {
		
		if (!test(state)) {
			this.time = queue.nextTime(this.time);
		} else {
			state.getBuffs().set(holder.getBuff(), this.time + holder.getDuration());
			this.time += holder.getCd();
			
			updateState(state);
			
			Damage d = new Damage();
			d.time = state.getTime();
			d.currentHatred = state.getHatred();
			d.currentDisc = state.getDisc();
			d.shooter = "Other Player";
			d.note = "Other Player " + holder.getBuff().toString();
			populateDamage(d);
			log.add(d);
		}
		
		queue.push(this);
	}

	protected void populateDamage(Damage d) {
	}

	public static class WolfBuff extends BuffEvent {

		public WolfBuff(double upTime) {
			super(Buff.OtherWolf, 10.0, upTime);
		}
		
		@Override
		protected boolean test(SimulationState state) {
			return !state.getBuffs().isActive(Buff.Wolf);
		}
		
		@Override
		protected void populateDamage(Damage d) {
			d.source = new DamageSource(ActiveSkill.Companion, Rune.Wolf);
			d.note = "Other Wolf Howl";
		}
	}
	
	public static class BotPBuff extends BuffEvent {

		public BotPBuff(int level) {
			super(Buff.BotP, 30.0 + level, -1.0);
		}
		
		@Override
		protected void populateDamage(Damage d) {
			d.shooter = "Player";
			d.note = "BotP Active";
		}
	}

	public static class MfDPrimaryBuff extends BuffEvent {

		private final Rune rune;
		private final double cost;

		public MfDPrimaryBuff(CharacterData data) {
			super(Buff.MfdPrimary, 30.0, data.getMfdUptime());
			this.rune = data.getMfdRune();
			this.cost = 3.0 * (1.0 - data.getRcr());
		}
		
		@Override
		protected boolean test(SimulationState state) {
			return state.getDisc() >= cost;
		}
		
		@Override
		protected void updateState(SimulationState state) {
			state.addDisc(-cost);
		}

		@Override
		protected void populateDamage(Damage d) {
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.MFD, rune);
			d.note = "MfD Primary";
			d.disc = -cost;
		}
	}

	public static class CaltropsBuffEvent extends BuffEvent {

		private final double cost;

		public CaltropsBuffEvent(CharacterData data) {
			super(Buff.Caltrops, 6.0, data.getCaltropsUptime());
			this.cost = 6.0 * (1.0 - data.getRcr());
		}
		
		@Override
		protected boolean test(SimulationState state) {
			return state.getDisc() >= cost;
		}
		
		@Override
		protected void updateState(SimulationState state) {
			state.addDisc(-cost);
		}

		@Override
		protected void populateDamage(Damage d) {
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.Caltrops, Rune.Bait_the_Trap);
			d.note = "Caltrops";
			d.disc = -cost;
		}
	}

	public static class MfDAdditionalBuff extends BuffEvent {

		private final Rune rune;
		private final double cost;

		public MfDAdditionalBuff(CharacterData data) {
			super(Buff.MfdAdditional, 30.0, data.getMfdAddUptime());
			this.rune = data.getMfdRune();
			this.cost = 0.0;
		}
		
		@Override
		protected void updateState(SimulationState state) {
			state.addDisc(-cost);
		}

		@Override
		protected void populateDamage(Damage d) {
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.MFD, rune);
			d.note = "MfD Additional";
			d.disc = -cost;
		}
	}

	public static class CalamityBuff extends BuffEvent {

		public CalamityBuff(double upTime) {
			super(Buff.Calamity, 30.0, upTime);
		}
		
		@Override
		protected void populateDamage(Damage d) {
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.MFD, Rune.None);
			d.note = "Calamity MfD";
		}
	}

	public static class ConvictionActiveBuff extends BuffEvent {

		public ConvictionActiveBuff(double upTime) {
			super(Buff.ConvictionActive, 3.0, upTime);
		}
		
		@Override
		protected boolean test(SimulationState state) {
			return !state.getBuffs().isActive(Buff.ConvictionPassive);
		}
		
	}

	public static class ConvictionPassiveBuff extends BuffEvent {

		public ConvictionPassiveBuff(double upTime) {
			super(Buff.ConvictionPassive, 3.0, upTime);
		}
		
		@Override
		protected boolean test(SimulationState state) {
			return !state.getBuffs().isActive(Buff.ConvictionActive);
		}
		
	}
}
