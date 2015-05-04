package com.dawg6.web.dhcalc.shared.calculator;

public enum Slot {

	Head("head"),
	Torso("torso"),
	Feet("feet"),
	Hands("hands"),
	Shoulders("shoulders"),
	Legs("legs"),
	Bracers("bracers"),
	MainHand("mainHand"),
	OffHand("offHand"),
	Waist("waist"),
	Ring1("leftFinger"),
	Ring2("rightFinger"),
	Necklace("neck");
	
	private final String slot;

	Slot(String slot) {
		this.slot = slot;
	}
	
	public String getSlot() {
		return slot;
	}
	
	@Override
	public String toString() {
		return name();
	}
}
