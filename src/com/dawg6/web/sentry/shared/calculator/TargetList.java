package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TargetList implements Serializable {

	private static final long serialVersionUID = -570917745839246976L;
	
	private final Map<TargetType, TargetHolder> targets = new TreeMap<TargetType, TargetHolder>();
	
	public void setTarget(TargetType type, TargetHolder target) {
		this.targets.put(type, target);
	}

	public TargetHolder getTarget(TargetType type) {
		return targets.get(type);
	}
	
	public int getNumTargets() {
		return targets.size();
	}
	
	public int getNumAdditional() {
		int i = targets.size();
		
		if (targets.containsKey(TargetType.Primary))
			i--;
		
		return i;
	}
	
	public int getNumAlive() {
		int i = 0;

		for (TargetHolder t : targets.values()) {
			if (t.isAlive())
				i++;
		}
		
		return i;
	}

	public Collection<TargetType> toList() {
		return new TreeSet<TargetType>(targets.keySet());
	}
	
}
