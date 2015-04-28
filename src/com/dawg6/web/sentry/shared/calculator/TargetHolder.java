package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class TargetHolder implements Serializable {

	private static final long serialVersionUID = -2384448071033486526L;

	private TargetType targetType;
	private MonsterType monsterType;
	private long currentHp;
	private long maxHp;
	
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public MonsterType getMonsterType() {
		return monsterType;
	}
	public void setMonsterType(MonsterType monsterType) {
		this.monsterType = monsterType;
	}
	public long getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(long currentHp) {
		this.currentHp = currentHp;
	}
	public long getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(long maxHp) {
		this.maxHp = maxHp;
	}
	
	public boolean isAlive() {
		return currentHp > 0;
	}
	
	public double getPercentHealth() {
		return (double)currentHp / (double)maxHp;
	}
	public double applyDamage(double damage) {
		double d = Math.min(currentHp, damage);
		currentHp -= d;
		
		return d;
	}
}
