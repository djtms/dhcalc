package com.dawg6.web.dhcalc.shared.calculator;

public enum GemLevel {
	None(0, 0),
	Chipped(1, .035),
	Flawed(2, .04),
	Regular(3, .045),
	Flawless(4, .05),
	Perfect(5, .055),
	Radiant(6, .06),
	Square(7, .065),
	Flawless_Square(8, .07),
	Perfect_Square(9, .075),
	Radiant_Square(10, .08),
	Star(11, .085),
	Flawless_Star(12, .09),
	Perfect_Star(13, .095),
	Radiant_Star(14, .10),
	Marquise(15, .105),
	Imperial(16, .11),
	Flawless_Imperial(17, .115),
	Royal(18, .12),
	Flawless_Royal(19, .125);
	
	private double cdr;
	private int rank;
	
	private GemLevel(int rank, double cdr) {
		this.cdr = cdr;
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public double getCdr() {
		return cdr;
	}
	
	public String getDisplayName() {
		return name().replaceAll("_"," ") + " (" + Util.format(cdr * 100.0) + "%)";
	}
}
