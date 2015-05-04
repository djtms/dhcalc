package com.dawg6.web.dhcalc.shared.calculator;

public enum TargetSize {

	Small("Small", 2),
	Medium("Medium", 3),
	Large("Large", 4);
	
	private String displayName;
	private int hits;
	
	private TargetSize(String displayName, int hits) {
		this.displayName = displayName;
		this.hits = hits;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getHits() {
		return hits;
	}
}
