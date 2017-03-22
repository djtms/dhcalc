/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

import java.io.Serializable;

public class TargetHolder implements Serializable {

	private static final long serialVersionUID = -2384448071033486526L;

	private TargetType targetType;
	private MonsterType monsterType;
	private long currentHp;
	private long maxHp;
	private int botsStacks;
	private double nextBots;
	private boolean impaled;
	
	public TargetHolder() {
		botsStacks = 0;
		nextBots = 0.0;
	}
	
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
	public int getBotsStacks() {
		return botsStacks;
	}
	public void setBotsStacks(int botsStacks) {
		this.botsStacks = botsStacks;
	}
	public double getNextBots() {
		return nextBots;
	}
	public void setNextBots(double nextBots) {
		this.nextBots = nextBots;
	}

	public boolean isImpaled() {
		return impaled;
	}

	public void setImpaled(boolean impaled) {
		this.impaled = impaled;
	}
}
