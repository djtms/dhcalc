/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
			
			if ((dr.source.proc != null) && (dr.source.proc.getIcd() > 0)) {
				state.getProcAvail().put(dr.source.proc, state.getTime() + (dr.source.proc.getIcd() / state.getLastAps()));
			}
			
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
