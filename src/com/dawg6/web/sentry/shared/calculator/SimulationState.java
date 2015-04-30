package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class SimulationState implements Serializable {

	private static final long serialVersionUID = -6201606085704392742L;

	private CharacterData data;
	private TargetList targets;
	private BuffList buffs;
	private double time;
	private double hatred;
	private double maxHatred;
	private double lastSpenderTime;
	private Hand hand;
	private double disc;
	private double maxDisc;

	public SimulationState() {
		this.time = 0.0;
		this.buffs = new BuffList();
		this.lastSpenderTime = 0.0;
	}

	public SimulationState(CharacterData data, TargetList targets) {
		this();

		this.data = data;
		this.targets = targets;
		this.hatred = data.getMaxHatred();
		this.maxHatred = data.getMaxHatred();
		this.disc = data.getMaxDiscipline();
		this.maxDisc = data.getMaxDiscipline();
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

	public double getHatred() {
		return hatred;
	}

	public void setHatred(double hatred) {
		this.hatred = hatred;
	}

	public double getMaxHatred() {
		return maxHatred;
	}

	public void setMaxHatred(double maxHatred) {
		this.maxHatred = maxHatred;
	}

	public double addHatred(double h) {
		
		double actual = Math.min(maxHatred - hatred, h);
		this.hatred += actual;
		
		return actual;
	}

	public double addDisc(double h) {
		
		double actual = Math.min(maxDisc - disc, h);
		this.disc += actual;
		
		return actual;
	}

	public double getLastSpenderTime() {
		return lastSpenderTime;
	}

	public void setLastSpenderTime(double lastSpenderTime) {
		this.lastSpenderTime = lastSpenderTime;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public double getMaxDisc() {
		return maxDisc;
	}

	public void setMaxDisc(double maxDisc) {
		this.maxDisc = maxDisc;
	}


}
