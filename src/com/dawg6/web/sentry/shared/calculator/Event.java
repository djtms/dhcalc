package com.dawg6.web.sentry.shared.calculator;

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

	public static void applyDamages(SimulationState state, List<Damage> log, List<Damage> source) {
		applyDamages(state, log, source, null);
	}
	
	public static void applyDamages(SimulationState state, List<Damage> log, List<Damage> source, String notes) {
		applyDamages(state, log, source, notes, true);
	}
	
	public static void applyDamages(SimulationState state, List<Damage> log, List<Damage> source, String notes, boolean refreshDots) {
		for (Damage dr : source) {
			dr.time = state.getTime();
			
			// TODO Handle Additional
			if (dr.target == TargetType.Primary) {
				
				if ((dr.duration <= 0.0) || !state.getDots().isActive(dr)) {
					dr.actualDamage = state.getTargets().getPrimary().applyDamage(dr.damage);
					dr.targetHp = state.getTargets().getPrimary().getCurrentHp();
					dr.targetHpPercent = state.getTargets().getPrimary().getPercentHealth();
					dr.currentHatred = state.getHatred();
					dr.currentDisc = state.getDisc();
					
					if (notes != null) {
						if ((dr.note == null) || (dr.note.length() == 0)) {
							dr.note = notes;
						} else {
							dr.note = dr.note + " " + notes;
						}
					}
	
					if (dr.actualDamage > 0) {
						log.add(dr);
					}
				}
				
				if ((dr.duration > 0.0) && refreshDots)
					state.getDots().addDot(state, dr);
			}
		}
	}
	
	public abstract void execute(EventQueue queue, List<Damage> log, SimulationState state);
}
