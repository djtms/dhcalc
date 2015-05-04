package com.dawg6.web.dhcalc.shared.calculator;

public class SkillAction {

	private final ActiveSkill skill;
	private final int qty;
	
	public SkillAction(ActiveSkill skill, int qty) {
		this.skill = skill;
		this.qty = qty;
	}
	
	public ActiveSkill getSkill() {
		return skill;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		return skill.getLongName() + " x " + qty;
	}
}
