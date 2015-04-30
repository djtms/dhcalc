package com.dawg6.web.sentry.shared.calculator;

import java.util.List;

public class RoVEvent extends CooldownEvent {

	private final Rune rune;
	private final boolean n6;
	private final boolean cr;

	public RoVEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.RoV);
		this.cooldown = 30.0 * (1.0 - data.getCdr());
		this.cr = data.isCrashingRain();
		this.n6 = data.getNumNats() >= 6;
		this.time = 0.0;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		if (n6) {
			state.getBuffs().set(Buff.N6, state.getTime() + 5.0);
		}

		applyDamages(state, log, DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.RoV, rune), state));

		if (cr) {
			applyDamages(state, log, DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.CR, rune), state));
		}

		this.time += this.cooldown;
		
		queue.push(this);
	}

}
