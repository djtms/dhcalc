package com.dawg6.web.dhcalc.shared.calculator;

public enum DamageType {
	Cold(DamageMultiplier.Cold),
	Fire(DamageMultiplier.Fire),
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
	
	public String getLongName() {
		return name();
	}
	
	public String getSlug() {
		return name();
	}
	
	@Override
	public String toString() {
		return name();
	}
}
