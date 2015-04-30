package com.dawg6.web.sentry.shared.calculator;

import java.util.List;

public class PreparationEvent extends Event {

	private double hatred;
	private double cd;
	private final Rune rune;
	private double threshold;
	private double disc;
	
	public PreparationEvent(CharacterData data) {
		this.rune = data.getPreparationRune();
		this.cd = 45.0 * (1.0 - data.getCdr());
		this.disc = 0.0;
		this.hatred = 0.0;
		
		if (data.getNumUe() >= 6) {
			this.threshold = data.getMaxDiscipline() - 5.0;
		} else {
			this.threshold = 8.0;
		}
		
		if (rune == Rune.Punishment) {
			this.hatred = 75.0 + (data.isHexingPants() ? ((75.0 * data
					.getHexingPantsUptime() * .25) - (75.0 * (1.0 - data
					.getHexingPantsUptime()) * data.getHexingPantsPercent())) : 0.0);
	
			this.cd = 20.0 * (1.0 - data.getCdr());
		} else if (rune != Rune.Focused_Mind) {
			this.disc = 30.0;
		}
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		boolean use = false;
		
		if (rune == Rune.Punishment) {
			if ((state.getHatred() < 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {
				double actual = state.addHatred(hatred);
				use = true;
	
				Damage d = new Damage();
				d.shooter = "Player";
				d.source = new DamageSource(ActiveSkill.Preparation, rune);
				d.hatred = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.time = state.getTime();
				d.note = "Punishment Hatred";
				log.add(d);
			}
		} else if (state.getDisc() < threshold) {
			use = true;
			
			double actual = state.addDisc(disc);

			Damage d = new Damage();
			d.shooter = "Player";
			d.source = new DamageSource(ActiveSkill.Preparation,
					rune);
			d.disc = actual;
			d.currentHatred = state.getHatred();
			d.currentDisc = state.getDisc();
			d.time = state.getTime();
			d.note = "Preparation";
			log.add(d);

			if (rune == Rune.Focused_Mind)
				state.getBuffs().set(Buff.FocusedMind, this.time + 15.0);
			
		}
		
		if (use) {
			this.time += cd;
		} else {
			this.time = queue.nextTime(this.time);
		}

		queue.push(this);
	}

}
