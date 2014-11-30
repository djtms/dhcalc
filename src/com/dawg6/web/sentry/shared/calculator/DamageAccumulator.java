package com.dawg6.web.sentry.shared.calculator;

public enum DamageAccumulator {
	Additive("Additive"),
	Multiplicative("Multiplicative"),
	ElementalAdditive("Elemental Additive"),
	Special("Special");
	
	private String description;
	
	private DamageAccumulator(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
}
