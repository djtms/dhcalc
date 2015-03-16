package com.dawg6.web.sentry.shared.calculator;

public enum WeaponType {

	HandCrossbow("Hand Crossbow", 1.6, 126, 714), Bow("2H Bow", 1.4, 143, 815), Crossbow("2H Crossbow",
			1.1, 779, 945);

	String name;
	double aps;
	int min;
	int max;

	private WeaponType(String name, double aps, int min, int max) {
		this.name = name;
		this.aps = aps;
		this.min = min;
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public double getAps() {
		return aps;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
