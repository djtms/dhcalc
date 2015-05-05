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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

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
