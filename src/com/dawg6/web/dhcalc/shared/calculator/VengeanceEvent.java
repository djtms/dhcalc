package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class VengeanceEvent extends CooldownEvent {

	private final Rune rune;

	public VengeanceEvent(CharacterData data) {
		this.rune = data.getSkills().get(ActiveSkill.Vengeance);
		this.cooldown = 90.0 * (1.0 - data.getCdr());
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		boolean use = false;
		
		if ((rune == Rune.Seethe) && (state.getHatred() <= 50.0)) {
			use = true;
		} else if (rune != Rune.Seethe) {
			use = true;
		}

		if (use) {
			this.time += this.cooldown;
			state.getBuffs().set(Buff.Vengeance, state.getTime() + 15.0);
			
			if (rune == Rune.Seethe)
				state.getBuffs().set(Buff.Seethe, state.getTime() + 15.0);
			
		} else {
			this.time = queue.nextTime(this.time);
		}
		
		queue.push(this);
	}

}
