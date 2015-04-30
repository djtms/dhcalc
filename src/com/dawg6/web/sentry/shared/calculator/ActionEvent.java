package com.dawg6.web.sentry.shared.calculator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
	private final boolean meticulousBolts;

	public ActionEvent(CharacterData data) {
		this.hand = Hand.MainHand;
		this.bastions = data.isBastions();
		this.m4 = data.isSentry() && (data.getNumMarauders() >= 4);
		this.mortalEnemy = data.isMarked()
				&& (data.getMfdRune() == Rune.Mortal_Enemy);
		this.n2 = data.getNumNats() >= 2;
		this.ue2 = data.getNumUe() >= 2;
		this.kridershot = data.isKridershot();
		this.meticulousBolts = data.isMeticulousBolts();

		this.venRune = data.getSkills().get(ActiveSkill.Vengeance);

		this.markedAmount = 4.0 + (data.isHexingPants() ? ((4.0 * data
				.getHexingPantsUptime() * .25) - (4.0 * (1.0 - data
				.getHexingPantsUptime()) * data.getHexingPantsPercent())) : 0.0);

		this.mainHandAps = data.getAps();
		this.mainHandInterval = (1.0 / mainHandAps)
				+ (data.getDelay() / 1000.0);

		this.hasOffHand = data.getOffHand_weaponType() != null;
		this.offHandAps = data.getOffHand_aps();

		if (this.hasOffHand) {
			this.offHandInterval = (1.0 / offHandAps)
					+ (data.getDelay() / 1000.0);
		} else {
			this.offHandInterval = 0.0;
		}

		if (kridershot && meticulousBolts) {
			Rune eaRune = data.getSkills().get(ActiveSkill.EA);

			if (eaRune == Rune.Ball_Lightning)
				generator = new SkillAndRune(ActiveSkill.EA,
						Rune.Ball_Lightning);

		}

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

				if ((skr.getHatred(data) > 0)
						&& ((generator == null) || (skr.getHatred(data) > generator
								.getHatred(data)))) {

					if ((generator == null) || !kridershot || !meticulousBolts)
						generator = skr;
				}

				if ((spender == null) && (skr.getHatred(data) < 0))
					spender = skr;
			}
		}

		Collections.sort(skills, new SkillAndRune.HatredSorter(data));
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		SkillAndRune selected = null;

		double interval = (this.hand == Hand.MainHand) ? mainHandInterval
				: offHandInterval;
		double t = this.time;
		double hatred = state.getHatred();

		state.setHand(hand);

		if (bastions) {
			double bwgExpires = state.getBuffs().getBuffs().get(Buff.BwGen)
					.getExpires();
			double bwsExpires = state.getBuffs().getBuffs().get(Buff.BwSpend)
					.getExpires();

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

		if (selected == null) {
			for (SkillAndRune skr : skills) {
				double h = skr.getHatred(state.getData());

				if ((h + hatred) >= 0) {
					selected = skr;
					break;
				}
			}
		}

		if (selected != null) {

			this.time += interval;

			double h = selected.getHatred(state.getData());

			double actualHatred = state.addHatred(h);

			if (bastions) {
				if (h < 0) {
					state.getBuffs().set(Buff.BwSpend, t + 5.0);

				} else if (h > 0) {
					state.getBuffs().set(Buff.BwGen, t + 5.0);
				}
			}

			List<Damage> dList = DamageFunction.getDamages(true, false,
					"Player",
					new DamageSource(selected.getSkill(), selected.getRune()),
					state);
			
			for (Damage d : dList) {
				if (d.hatred > 0)
					d.hatred = actualHatred;
			}
			
			applyDamages(state, log, dList);

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

			if (m4 && (selected.getSkill().getSkillType() == SkillType.Spender)) {
				applyDamages(state, log, DamageFunction.getDamages(false, true,
						"Sentry", new DamageSource(selected.getSkill(),
								selected.getRune()), state));
				state.setLastSpenderTime(t);
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

			if (state.getBuffs().isActive(Buff.Vengeance)) {
				// TODO Handle Dark Heart DoT
				applyDamages(state, log, DamageFunction.getDamages(true, false,
						"Player", new DamageSource(ActiveSkill.Vengeance,
								venRune), state));
			}

			if (n2 && (rov != null)) {
				
				if (rov.getTime() > this.time) {
					queue.remove(rov);
					rov.setTime(Math.max(rov.getTime() - 2.0, this.time));
					queue.push(rov);
				}
			}

			// Gem procs
			applyDamages(state, log, DamageFunction.getDamages(false, false, "Player", null, state));
			
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

}
