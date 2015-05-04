package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimePlayed implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public float barbarian;
	public float crusader;
	@JsonProperty("demon-hunter")
	public float demonhunter;
	public float monk;
	@JsonProperty("witch-doctor")
	public float witchdoctor;
	public float wizard;

}
