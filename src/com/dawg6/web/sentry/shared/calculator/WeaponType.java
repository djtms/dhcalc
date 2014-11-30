package com.dawg6.web.sentry.shared.calculator;

public enum WeaponType {

	HandCrossbow("Hand Crossbow", 1.6), Bow("2H Bow", 1.4), Crossbow("2H Crossbow",
			1.1);

	String name;
	double aps;

	private WeaponType(String name, double aps) {
		this.name = name;
		this.aps = aps;
	}

	public String getName() {
		return name;
	}

	public double getAps() {
		return aps;
	}
}
