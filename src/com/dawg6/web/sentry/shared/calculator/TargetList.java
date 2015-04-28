package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.List;

public class TargetList implements Serializable {

	private static final long serialVersionUID = -570917745839246976L;
	
	private TargetHolder primary;
	private List<TargetHolder> additional;
	
	public TargetHolder getPrimary() {
		return primary;
	}
	public void setPrimary(TargetHolder primary) {
		this.primary = primary;
	}
	public List<TargetHolder> getAdditional() {
		return additional;
	}
	public void setAdditional(List<TargetHolder> additional) {
		this.additional = additional;
	}
	
	public int getNumTargets() {
		return 1 + additional.size();
	}
	
	public int getNumAlive() {
		int i = 0;
		
		if (primary.isAlive())
			i++;

		i += getNumAdditionalAlive();
		
		return i;
	}
	
	public int getNumAdditionalAlive() {
		int i = 0;
		
		for (TargetHolder h : additional)
			if (h.isAlive())
				i++;
		
		return i;
	}
}
