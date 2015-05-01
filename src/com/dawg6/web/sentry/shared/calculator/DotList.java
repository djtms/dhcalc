package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class DotList {

	private final Map<TargetType, Map<DamageSource, Dot>> targets = new TreeMap<TargetType, Map<DamageSource, Dot>>();
	
	public boolean isActive(TargetType target, Damage damage) {
		
		// RoV Stacks
		if (damage.source.skill == ActiveSkill.RoV)
			return false;
		
		return getDots(target).containsKey(damage.source);
	}
	
	private Map<DamageSource, Dot> getDots(TargetType target) {
		Map<DamageSource, Dot> map = targets.get(target);
		
		if (map == null) {
			map = new TreeMap<DamageSource, Dot>();
			targets.put(target, map);
		}
		
		
		return map;
	}
	
	public void addDot(SimulationState state, Damage damage) {
		DamageSource source = damage.source;
		DamageSource key = source;
		TargetType target = damage.target;

		if (target != null) {
			// RoV Stacks
			if (source.skill == ActiveSkill.RoV)
				key = DamageSource.Random();
			
			Map<DamageSource, Dot> dots = getDots(target);
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
	}

	public double tick(SimulationState state, List<Damage> log) {

		double time = state.getTime();
		List<Damage> toApply = new Vector<Damage>();
		
		double next = time + 1.0;
		
		for (Map<DamageSource, Dot> dots : targets.values()) {
			
			List<DamageSource> expired = new Vector<DamageSource>();

			for (Map.Entry<DamageSource, Dot> e : dots.entrySet()) {
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

			for (DamageSource s : expired)
				dots.remove(s);
		}
		
		Event.applyDamages(state, log, toApply, "Tick", false);
		
		return next;
	}
}
