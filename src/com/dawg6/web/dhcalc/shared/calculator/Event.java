package com.dawg6.web.dhcalc.shared.calculator;

import java.util.List;

public abstract class Event implements Comparable<Event> {

	private static int nextId = 1;

	protected double time;
	protected int id;

	protected Event() {
		this.id = nextId++;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	@Override
	public int compareTo(Event o) {
		int i = Double.compare(time, o.time);

		if (i == 0) {
			i = Integer.compare(id, o.id);
		}

		return i;
	}

	public static void applyDamages(SimulationState state, List<Damage> log,
			List<Damage> source) {
		applyDamages(state, log, source, null);
	}

	public static void applyDamages(SimulationState state, List<Damage> log,
			List<Damage> source, String notes) {
		applyDamages(state, log, source, notes, true);
	}

	private static void applyDamage(TargetHolder target, SimulationState state,
			List<Damage> log, Damage dr, String notes) {

		dr.actualDamage = target.applyDamage(dr.damage);
		dr.targetHp = target.getCurrentHp();
		dr.targetHpPercent = target.getPercentHealth();

		if (notes != null) {
			if ((dr.note == null) || (dr.note.length() == 0)) {
				dr.note = notes;
			} else {
				dr.note = dr.note + " " + notes;
			}
		}
	}

	public static void applyDamages(SimulationState state, List<Damage> log,
			List<Damage> source, String notes, boolean refreshDots) {
		for (Damage dr : source) {
			dr.time = state.getTime();
			dr.currentHatred = state.getHatred();
			dr.currentDisc = state.getDisc();

			if (dr.target != null) {
				TargetHolder target = state.getTargets().getTarget(dr.target);

				if (target.isAlive()) {

					if ((dr.duration <= 0.0)
							|| !state.getDots().isActive(dr.target, dr)) {

						applyDamage(target, state, log, dr, notes);

						if (dr.actualDamage > 0) {
							log.add(dr);
						}
					}

					if ((dr.duration > 0.0) && refreshDots)
						state.getDots().addDot(state, dr);
				}
			}
		}

		// DML hits twice
		if (state.getData().isDml()) {

			for (Damage dr : source) {
				if ((dr.source != null) && (dr.source.skill == ActiveSkill.MS)) {

					if (dr.target != null) {
						TargetHolder target = state.getTargets().getTarget(
								dr.target);

						if (target.isAlive()
								&& (target.getPercentHealth() <= state
										.getData().getDmlPercent())) {

							Damage copy = dr.copy();
							applyDamage(target, state, log, copy, "DML");

							if (copy.actualDamage > 0) {
								log.add(copy);
							}
						}
					}
				}
			}
		}
	}

	public abstract void execute(EventQueue queue, List<Damage> log,
			SimulationState state);
}
