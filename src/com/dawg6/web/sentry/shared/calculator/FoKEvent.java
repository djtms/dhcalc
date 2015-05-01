package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FoKEvent extends CooldownEvent {

	private final Rune rune;

	public FoKEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.FoK);
		this.cooldown = ((rune == Rune.Pinpoint_Accuracy) ? 15.0 : 10.0) * (1.0 - data.getCdr());
		this.time = 0.0;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		List<Damage> dList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.FoK, rune), state);

		applyDamages(state, log, dList);

		Set<TargetType> targetsHit = new TreeSet<TargetType>();
		
		for (Damage d : dList) {
			if ((d.target != null) && (d.damage > 0) && state.getTargets().getTarget(d.target).isAlive())
				targetsHit.add(d.target);
		}
		
		if (!targetsHit.isEmpty())
			applyDamages(state, log, DamageFunction.getDamages(false, false, "Player", null, state, targetsHit));

		this.time += this.cooldown;
		
		queue.push(this);
	}

}
