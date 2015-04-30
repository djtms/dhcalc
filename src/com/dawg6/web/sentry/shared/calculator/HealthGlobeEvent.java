package com.dawg6.web.sentry.shared.calculator;

import java.util.List;

public class HealthGlobeEvent extends Event {

	private final double healthGlobeHpInterval;
	private double nextHealthHpGlobe;
	private final double hatred;

	public HealthGlobeEvent(CharacterData data) {
		healthGlobeHpInterval = 1.0 / (data.getNumHealthGlobes() + 1.0);
		nextHealthHpGlobe = 1.0 - healthGlobeHpInterval;

		double h = 0.0;

		if (data.isBloodVengeance())
			h += 30.0;

		if (data.isReapersWraps())
			h += (data.getMaxHatred() * data.getReapersWrapsPercent());

		if (data.isHexingPants()) {
			h = h
					+ (h * data.getHexingPantsUptime() * .25)
					- (h * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
		}
		
		this.hatred = h;
	}

	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {

		if ((state.getTargets().getPrimary().getPercentHealth() <= nextHealthHpGlobe)
				&& (state.getHatred() < 50.0) && !state.getBuffs().isActive(Buff.Seethe)) {

			nextHealthHpGlobe -= healthGlobeHpInterval;

			double actual = state.addHatred(hatred);

			Damage d = new Damage();
			d.shooter = "Health Globe";
			d.hatred = actual;
			d.time = state.getTime();
			d.currentHatred = state.getHatred();
			d.disc = state.getDisc();
			log.add(d);
		}

		this.time = queue.nextTime(this.time);

		queue.push(this);
	}

}
