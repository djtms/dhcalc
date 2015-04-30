package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class DotList {

	private final Map<Object, Dot> dots = new TreeMap<Object, Dot>();
	
	public boolean isActive(Damage damage) {
		
		// RoV Stacks
		if (damage.source.skill == ActiveSkill.RoV)
			return false;
		
		return dots.containsKey(damage.source);
	}
	
	public void addDot(SimulationState state, Damage damage) {
		DamageSource source = damage.source;
		Object key = source;
		
		// RoV Stacks
		if (source.skill == ActiveSkill.RoV)
			key = new Double(Math.random());
		
		Dot dot = dots.get(key);
		
		if (dot == null) {
			dot = new Dot();
			dot.time = damage.time + 1.0;
			dot.expires = damage.time + damage.duration;

			if (damage.duration > 1.0)
				dots.put(key, dot);
		} else {
			dot.expires = damage.time + damage.duration;
		}
		
		dot.damage = damage.copy();
	}

	public double tick(SimulationState state, List<Damage> log) {

		double time = state.getTime();
		List<Object> expired = new Vector<Object>();
		List<Damage> toApply = new Vector<Damage>();
		
		double next = time + 1.0;
		
		for (Map.Entry<Object, Dot> e : dots.entrySet()) {
			Dot dot = e.getValue();
			
			if (dot.expires <= time) {
				expired.add(e.getKey());
			} else if (dot.time <= time) {
				dot.time += 1.0;
				Damage d = dot.damage.copy();
				d.duration = 0.0;
				toApply.add(d);
			} else if (dot.time < next) {
				next = dot.time;
			}
		}
		
		Event.applyDamages(state, log, toApply, "Tick", false);

		for (Object s : expired)
			dots.remove(s);
		
		return next;
	}
}
