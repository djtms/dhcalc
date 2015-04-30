package com.dawg6.web.sentry.shared.calculator;

import java.util.List;

public class CompanionDamageEvent extends Event {

	private Rune[] runes;
	private final double interval;
	
	public CompanionDamageEvent(CharacterData data) {
		
		if (data.getNumMarauders() >= 2)
			runes = ActiveSkill.Companion.getRunes();
		else
			runes = new Rune[]{ data.getCompanionRune() };
		
		double petAps = 1.0;

		if (data.isTnt())
			petAps = petAps * (1 + data.getTntPercent());
		
		this.interval = 1.0 / petAps;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		for (Rune r : runes) {

			applyDamages(state, log, DamageFunction.getDamages(false, false,
							ActiveSkill.Companion.getLongName(),
							new DamageSource(ActiveSkill.Companion,
									r), state));
			
			if (state.getBuffs().isActive(Buff.CompanionActive))
				state.getBuffs().clear(Buff.CompanionActive);
		}
		
		this.time += interval;
		
		queue.push(this);
	}

}
