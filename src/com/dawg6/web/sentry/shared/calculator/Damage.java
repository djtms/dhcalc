package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class Damage implements Serializable {
	
	private static final long serialVersionUID = -8479254378841622164L;
	
	public String shooter;
	public DamageSource source;
	public int index;
	public int qty;
	public double damage;
	public double totalDamage;
	public DamageType type;
	public String note;
	public String log;
	public Target target;
	public int numAdd;
	public boolean nonStacking;
	public double hatred;

}