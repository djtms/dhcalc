package com.dawg6.web.sentry.shared.calculator;

public enum MonsterType {

	RiftGuardian("Rift Guardian", 44766142, true),
	Elite("Elite", 10533210, true),
	NonElite("Non-Elite", 1053321, false);
	
	private long health;
	private String name;
	private boolean elite;
	
	private MonsterType(String name, long health, boolean elite) {
		this.health = health;
		this.name = name;
		this.elite = elite;
	}
	
	public long getHealth() {
		return health;
	}
	
	public boolean isElite() {
		return elite;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
