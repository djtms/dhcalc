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
package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;

public class HeroStats implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public int life;
	
	public double damage;
	
	public double toughness;
	
	public double healing;
	
	public int armor;
	
	public int strength;
	
	public int dexterity;
	
	public int vitality;
	
	public int intelligence;
	
	public int physicalResist;
	
	public int fireResist;
	
	public int coldResist;
	
	public int lightningResist;
	
	public int poisonResist;
		
	public int arcaneResist;
	
	public double damageIncrease;
	
	public double critChance;
	
	public double damageReduction;
	
	public float attackSpeed;
	public float critDamage;
	public float blockChance;
	public int blockAmountMin;
	public int blockAmountMax;
	public float thorns;
	public float lifeSteal;
	public float lifePerKill;
	public float goldFind;
	public float magicFind;
	public float lifeOnHit;
	public int primaryResource;
	public int secondaryResource;
}
