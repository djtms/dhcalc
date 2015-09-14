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

public class Damage implements Serializable {
	
	private static final long serialVersionUID = -8479254378841622164L;
	
	public String shooter;
	public double time;
	public DamageSource source;
	public int index;
	public double damage;
	public DamageType type;
	public String note;
	public String log;
	public TargetType target;
	public long targetHp;
	public double targetHpPercent;
	public double actualDamage;
	public boolean nonStacking;
	public double hatred;
	public int seq;
	public double currentHatred;
	public double disc;
	public double currentDisc;
	public double duration;
	
	public Damage copy() {
		Damage d = new Damage();
		d.shooter = shooter;
		d.time = time;
		d.source = source;
		d.index = index;
		d.disc = disc;
		d.currentDisc = currentDisc;
		d.damage = damage;
		d.type = type;
		d.note = note;
		d.log = log;
		d.target = target;
		d.targetHp = targetHp;
		d.targetHpPercent = targetHpPercent;
		d.actualDamage = actualDamage;
		d.nonStacking = nonStacking;
		d.hatred = hatred;
		d.seq = seq;
		d.currentHatred = currentHatred;
		d.duration = duration;
		
		return d;
	}
}