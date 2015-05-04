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