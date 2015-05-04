package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

		List<Damage> dList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.RoV, rune), state);

		if (cr) {
			List<Damage> crList = DamageFunction.getDamages(true, false, "Player", new DamageSource(ActiveSkill.CR, rune), state);
			dList.addAll(crList);
		}

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
