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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class ActionEvent extends Event {

	private Hand hand;
	private final boolean bastions;

	private final double mainHandAps;
	private final double offHandAps;
	private final double mainHandInterval;
	private final double offHandInterval;
	private final boolean hasOffHand;
	private final List<SkillAndRune> skills = new Vector<SkillAndRune>();
	private SkillAndRune spender = null;
	private SkillAndRune generator = null;
	private final boolean m4;
	private final boolean mortalEnemy;
	private final double markedAmount;
	private final Rune venRune;
	private RoVEvent rov;
	private final boolean n2;
	private final boolean ue2;
	private final boolean kridershot;
	private final boolean yangs;
	private final boolean huntersWrath;
	private final boolean meticulousBolts;
	private final boolean odysseys;
	private final Rune esRune;
	private double esDuration;
	private final Map<ActiveSkill, Breakpoint> bpMap = new TreeMap<ActiveSkill, Breakpoint>();
	private final double delay;
	private final WeaponType mainHand;
	private final boolean bots;
	private final boolean karleis;
	private final double karleisHatred;
	private final SpenderLogic spenderLogic;
	private final GeneratorLogic generatorLogic;
	private SpikeTrapActionEvent spikeTrap;
	private final double maxHatred;
	private final boolean hasIllWill;

	public ActionEvent(CharacterData data) {
		this.hand = Hand.MainHand;
		this.bastions = data.isBastions();
		this.m4 = data.isSentry() && (data.getNumMarauders() >= 4);
		this.mortalEnemy = data.isMarked()
				&& (data.getMfdRune() == Rune.Mortal_Enemy);
		this.n2 = data.getNumNats() >= 2;
		this.ue2 = data.getNumUe() >= 2;
		this.kridershot = data.isKridershot();
		this.yangs = data.isYangs();
		this.huntersWrath = data.isHuntersWrath();
		this.meticulousBolts = data.isMeticulousBolts();
		this.odysseys = data.isOdysseysEnd();
		this.mainHand = data.getWeaponType();
		this.bots = data.isBotS();
		this.venRune = data.getSkills().get(ActiveSkill.Vengeance);
		this.karleis = data.isKarleis();
		this.spenderLogic = data.getSpenderLogic();
		this.generatorLogic = data.getGeneratorLogic();
		this.maxHatred = data.getMaxHatred();
		this.hasIllWill = data.isIllWill();

		this.markedAmount = 4.0 + (data.isHexingPants() ? ((4.0 * data
				.getPercentMoving() * .25) - (4.0 * (1.0 - data
				.getPercentMoving()) * data.getHexingPantsPercent())) : 0.0);

		this.karleisHatred = data.getKarlieshatred()
				+ (data.isHexingPants() ? ((data.getKarlieshatred()
						* data.getPercentMoving() * .25) - (data
						.getKarlieshatred() * (1.0 - data.getPercentMoving()) * data
						.getHexingPantsPercent())) : 0.0);

		this.mainHandAps = data.getAps();
		this.delay = data.getDelay() / 1000.0;

		this.mainHandInterval = (1.0 / mainHandAps) + delay;

		this.hasOffHand = data.getOffHand_weaponType() != null;
		this.offHandAps = data.getOffHand_aps();

		if (this.hasOffHand) {
			this.offHandInterval = (1.0 / offHandAps) + delay;
		} else {
			this.offHandInterval = 0.0;
		}

		if (kridershot && meticulousBolts) {
			Rune eaRune = data.getSkills().get(ActiveSkill.EA);

			if (eaRune == Rune.Ball_Lightning)
				generator = new SkillAndRune(ActiveSkill.EA,
						Rune.Ball_Lightning);

		}

		esRune = data.getSkills().get(ActiveSkill.ES);

		if (this.odysseys && (esRune != null)) {
			esDuration = (esRune == Rune.Heavy_Burden ? 4.0 : 2.0);
		}

		for (Map.Entry<ActiveSkill, Rune> e : data.getSkills().entrySet()) {
			ActiveSkill skill = e.getKey();
			SkillType type = skill.getSkillType();
			Rune rune = e.getValue();

			if ((type == SkillType.Primary)
					|| (type == SkillType.Spender)
					|| (type == SkillType.Channeled)
					|| ((skill == ActiveSkill.FoK) && (rune == Rune.Knives_Expert)) 
					|| ((skill == ActiveSkill.Vault) && (rune == Rune.Action_Shot) && data.isDanettas() && (spender == null) && data.isBastions())
					) {
				SkillAndRune skr = new SkillAndRune(skill, rune);
				skills.add(skr);

				double h = skr.getHatred(data);
				
//				GWT.log("skill = " + skill.name() + " h = " + h);
				
				if ((h > 0)
						&& ((generator == null) || (h > generator
								.getHatred(data)))) {

					if ((generator == null) || !kridershot || !meticulousBolts)
						generator = skr;
				}

				if ((spender == null) && (h < 0)) {
					spender = skr;
//					GWT.log("Spender = " + spender.getSkill().name());
				}
			}
		}

		Collections.sort(skills, new SkillAndRune.HatredSorter(data));

		if (data.isSpikeTrap())
			spikeTrap = new SpikeTrapActionEvent(data);
		else
			spikeTrap = null;

	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		SkillAndRune selected = null;

		if (spikeTrap != null) {
			int qty = spikeTrap.getQtyAvailable(state);

			if (qty > 0) {
				selected = spikeTrap.getSkillAndRune();
			}
		}

		double interval = (this.hand == Hand.MainHand) ? mainHandInterval
				: offHandInterval;
		double t = this.time;
		double hatred = state.getHatred();

		state.setHand(hand);

		if (selected == null) {
			if (this.odysseys && (esRune != null)) {

				BuffState oeBuff = state.getBuffs().getBuffs()
						.get(Buff.OdysseysEnd);

				if ((oeBuff == null) || ((t + interval) >= oeBuff.getExpires())) {
					selected = new SkillAndRune(ActiveSkill.ES, esRune);
				}
			}

			if (bastions) {
				double bwgExpires = state.getBuffs().getBuffs().get(Buff.BwGen)
						.getExpires();
				double bwsExpires = state.getBuffs().getBuffs()
						.get(Buff.BwSpend).getExpires();

				if ((t + interval) >= bwgExpires) {
					selected = generator;
				}

				if ((t + interval) >= bwsExpires) {

					if (spender != null) {
						double h = spender.getHatred(state.getData());

						if (h <= hatred) {
							selected = spender;
						}
					}
				}
			}

			if ((selected == null) && kridershot && meticulousBolts
					&& (generator != null)
					&& (generator.getSkill() == ActiveSkill.EA)
					&& (generator.getRune() == Rune.Ball_Lightning)) {
				selected = generator;
			}

			if ((selected == null) && (generator != null) && (spenderLogic == SpenderLogic.FullHatred) && (hatred < maxHatred))
				selected = generator;
			
			if ((selected == null) && (generator != null) && ((spender == null) || (spender.getSkill() == ActiveSkill.Vault)))
					selected = generator;
			
			if (selected == null) {
				for (SkillAndRune skr : skills) {
					double h = skr.getHatred(state.getData());

					if ((h + hatred) >= 0) {
						selected = skr;
						break;
					}

				}
			}
		}

		if (selected != null) {

			state.setLastAttack(selected);

			String botsLog = null;

			if (selected.getSkill() != ActiveSkill.ST) {
				if (bots) {
					TargetHolder target = state.getTargets().getTarget(
							TargetType.Primary);

					if (target.isAlive() && (target.getNextBots() <= t)) {
						target.setBotsStacks(target.getBotsStacks() + 1);
						target.setNextBots(t + 0.3);
						botsLog = " BotS(" + target.getBotsStacks() + ")";
					}
				}
			}

			Breakpoint.Data bpData = this.getBpData(selected.getSkill(), hand);

			double actualInterval = bpData.interval + this.delay;

			if (this.yangs && (selected.getSkill() == ActiveSkill.MS)) {
				actualInterval = (bpData.interval / 1.5) + this.delay;
			}

			if (this.huntersWrath
					&& (selected.getSkill().getSkillType() == SkillType.Primary)) {
				actualInterval = (bpData.interval / 1.3) + this.delay;
			}

			state.setLastAps(1.0 / actualInterval);

			this.time += actualInterval;

			double h = selected.getHatred(state.getData());

			double actualHatred = state.addHatred(h);

			if (this.odysseys && (selected.getSkill() == ActiveSkill.ES)) {
				state.getBuffs().set(Buff.OdysseysEnd, t + this.esDuration);
			}

			if (bastions) {
				if (h < 0) {
					state.getBuffs().set(Buff.BwSpend, t + 5.0);

				} else if (h > 0) {
					state.getBuffs().set(Buff.BwGen, t + 5.0);
				}
			}

			List<Damage> dList = new Vector<Damage>();

			if (selected.getSkill() == ActiveSkill.ST) {
				spikeTrap.execute(queue, log, state);
			} else {
				dList = DamageFunction.getDamages(
						true,
						false,
						"Player",
						new DamageSource(selected.getSkill(), selected
								.getRune()), state);

				boolean wasSpender = false;
				boolean wasGenerator = false;

				if (selected.getSkill().getSkillType() == SkillType.Primary)
					wasGenerator = true;
				else if (selected.getSkill().getSkillType() == SkillType.Spender) {
					if (h < 0)
						wasSpender = true;
					else
						wasGenerator = true;

				}
				for (Damage d : dList) {

					if ((botsLog != null) && (d.target == TargetType.Primary)) {
						d.note += botsLog;
						botsLog = null;
					}

					if (d.hatred > 0)
						d.hatred = actualHatred;
				}

				applyDamages(state, log, dList);

				if (state.hasSpikeTraps()) {
					Rune r = state.getData().getSpikeTrapRune();

					if (((r == Rune.Custom_Trigger) && wasGenerator)
							|| ((r != Rune.Custom_Trigger) && wasSpender)) {
						state.detonateTraps(queue, log);
					}
				}

				if (ue2 && (h > 0)) {
					double actual = state.addDisc(1.0);

					if (actual > 0) {
						Damage d = new Damage();
						d.time = state.getTime();
						d.disc = actual;
						d.shooter = "Player";
						d.note = "UE2 Disc";
						d.currentDisc = state.getDisc();
						d.currentHatred = state.getHatred();
						log.add(d);
					}
				}

				if (m4
						&& (selected.getSkill().getSkillType() == SkillType.Spender)) {

					List<Damage> sList = DamageFunction.getDamages(false, true,
							"Sentry", new DamageSource(selected.getSkill(),
									selected.getRune()), state);
					applyDamages(state, log, sList);
					state.setLastSpenderTime(t);
					dList.addAll(sList);
				}

				if (mortalEnemy
						&& (state.getBuffs().isActive(Buff.MfdPrimary) || state
								.getBuffs().isActive(Buff.MfdAdditional))) {

					double mh = state.addHatred(markedAmount);

					if (mh > 0) {

						Damage d = new Damage();
						d.shooter = "Player";
						d.source = new DamageSource(ActiveSkill.MFD,
								Rune.Mortal_Enemy);
						d.hatred = mh;
						d.time = t;
						d.note = "MfD/ME Hatred";
						d.currentHatred = state.getHatred();
						d.currentDisc = state.getDisc();
						log.add(d);
					}
				}
			}

			if (state.getBuffs().isActive(Buff.Vengeance)) {
				List<Damage> vList = DamageFunction.getDamages(true, false,
						"Player", new DamageSource(ActiveSkill.Vengeance,
								venRune), state);
				applyDamages(state, log, vList);
				dList.addAll(vList);
			}

			if (state.getData().isSashOfKnives()) {
				List<Damage> l2 = DamageFunction.getDamages(true, false,
						"Sash of Knives", new DamageSource(ActiveSkill.SoK,
								Rune.None), state);
				applyDamages(state, log, l2);
				dList.addAll(l2);
			}

			if (n2 && (rov != null)) {

				if (rov.getTime() > this.time) {
					queue.remove(rov);
					rov.setTime(Math.max(rov.getTime() - 4.0, this.time));
					queue.push(rov);
				}
			}

			Set<TargetType> targetsHit = new TreeSet<TargetType>();

			for (Damage d : dList) {
				if ((d.target != null) && (d.damage > 0)
						&& state.getTargets().getTarget(d.target).isAlive())
					targetsHit.add(d.target);
			}

			if (karleis && (selected.getSkill() == ActiveSkill.IMP)) {

				double mh = karleisHatred;

				if (mh > 0) {
					int mm = 0;
	
					for (TargetType target : targetsHit) {
						if (state.getTargets().getTarget(target).isImpaled())
							mm++;
					}
					
					if ((mm > 0) && state.getData().isHolyPointShot()) {
						mm += 2;
					}
					
					double mmh = state.addHatred(mh*mm);

					if (mmh > 0) {
						Damage d = new Damage();
						d.shooter = "Player";
						d.source = new DamageSource(selected.getSkill(),
								selected.getRune());
						d.hatred = mmh;
						d.time = t;
						d.note = "Karlei's Point Hatred (" + mm + " x " + mh +")";
						d.currentHatred = state.getHatred();
						d.currentDisc = state.getDisc();
						log.add(d);
					}
				}
			}
			
			if (selected.getSkill() == ActiveSkill.IMP) {
				for (TargetType target : targetsHit) {
					state.getTargets().getTarget(target).setImpaled(true);
				}
			}
			
			// Gem procs
			if (!targetsHit.isEmpty()) {
				applyDamages(state, log, DamageFunction.getDamages(false,
						false, "Player", null, state, targetsHit));
			}

			if (hasOffHand) {
				this.hand = (this.hand == Hand.MainHand) ? Hand.OffHand
						: Hand.MainHand;
			}

		} else {
			this.time = queue.nextTime(this.time);
		}

		queue.push(this);
	}

	public RoVEvent getRov() {
		return rov;
	}

	public void setRov(RoVEvent rov) {
		this.rov = rov;
	}

	private Breakpoint getBreakpoint(ActiveSkill skill) {
		Breakpoint bp = bpMap.get(skill);

		if (bp == null) {
			int frames = skill.getFrames();

			if (frames < 0) {
				frames = mainHand.getFrames();
			}

			bp = new Breakpoint(frames);
			bpMap.put(skill, bp);
		}

		return bp;
	}

	private Breakpoint.Data getBpData(ActiveSkill skill, Hand hand) {
		return getBreakpoint(skill).get(
				(hand == Hand.MainHand) ? this.mainHandAps : this.offHandAps);
	}

}
