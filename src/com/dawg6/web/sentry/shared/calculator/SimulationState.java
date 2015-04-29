package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class SimulationState implements Serializable {

	private static final long serialVersionUID = -6201606085704392742L;

	private CharacterData data;
	private TargetList targets;
	private double time;
	private BuffList buffs;

	public SimulationState() {
		this.time = 0.0;
		this.buffs = new BuffList();
	}

	public SimulationState(CharacterData data, TargetList targets) {
		this();

		this.data = data;
		this.targets = targets;
	}

	public CharacterData getData() {
		return data;
	}

	public void setData(CharacterData data) {
		this.data = data;
	}

	public TargetList getTargets() {
		return targets;
	}

	public void setTargets(TargetList targets) {
		this.targets = targets;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
		
		buffs.expire(time);
	}

	public BuffList getBuffs() {
		return buffs;
	}

	public void setBuffs(BuffList buffs) {
		this.buffs = buffs;
	}

}
