package com.dawg6.web.sentry.shared.calculator;

public enum BowType {
	HandCrossbow("1H Crossbow"),
	Crossbow("2H Crossbow"),
	Bow("2h Bow");
	
	private String name;
	
	private BowType(String name) {
		this.name = name;
	}
	
	public String getLongName() {
		return name;
	}
	
}
