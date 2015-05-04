package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public class DiscRegenEvent extends Event {

	private final double regen;
	private final double focusedMindRegen;
	
	public DiscRegenEvent(CharacterData data) {
		this.time = 1.0;
		this.focusedMindRegen = 3.0;
		regen = 1.0;
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		if (regen > 0.0)
		{
			double h = regen;
			
			if (state.getBuffs().isActive(Buff.FocusedMind))
				h += this.focusedMindRegen;
		
			double actual = state.addDisc(h);
			
			if (actual > 0) {
				Damage d = new Damage();
				d.time = state.getTime();
				d.shooter = "Player";
				d.disc = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.note = "Disc Regen";
				log.add(d);
			}
		}

		this.time += 1.0;
		
		queue.push(this);
	}

}
