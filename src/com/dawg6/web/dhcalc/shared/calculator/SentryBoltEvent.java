package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class SentryBoltEvent extends Event {

	private final Rune rune;
	private final double boltInterval;
	private final boolean spenders;

	public SentryBoltEvent(CharacterData data) {
		this.rune = data.getSentryRune();

		BreakPoint bp = BreakPoint.getBp(data.getBp());
		double boltAps = (double) bp.getQty() / (double) BreakPoint.DURATION;
		this.boltInterval = 1.0 / boltAps;
		
		this.spenders = (data.getNumMarauders() >= 4) && data.hasSpender();
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		
		if (!spenders || (state.getLastSpenderTime() < (state.getTime() - boltInterval))) {
			applyDamages(state, log, DamageFunction.getDamages(
					false, true, "Sentry", new DamageSource(
							ActiveSkill.BOLT, rune), state));
		}

		applyDamages(state, log, DamageFunction.getDamages(
				false, true, "Sentry", new DamageSource(
						ActiveSkill.SENTRY, rune), state));
		
		this.time += this.boltInterval;
		queue.push(this);
	}

}
