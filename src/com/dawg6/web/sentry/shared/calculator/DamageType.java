package com.dawg6.web.sentry.shared.calculator;

public enum DamageType {
	Fire(DamageMultiplier.Fire),
	Cold(DamageMultiplier.Cold),
	Lightning(DamageMultiplier.Lightning),
	Physical(DamageMultiplier.Physical),
	Poison(DamageMultiplier.Poison);

	private DamageMultiplier multiplier;
	
	private DamageType(DamageMultiplier multiplier) {
		this.multiplier = multiplier;
	}

	public DamageMultiplier getMultiplier() {
		return multiplier;
	}
}
