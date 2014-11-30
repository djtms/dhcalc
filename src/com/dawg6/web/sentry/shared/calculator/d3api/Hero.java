package com.dawg6.web.sentry.shared.calculator.d3api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hero implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public int paragonLevel;
	
	public boolean seasonal;
	
	public String name;
	
	public int id;
	
	public int level;
	
	public boolean hardcore;
	
	public int gender;
	
	public boolean dead;
	
	@JsonProperty("class")
	public String clazz;
	
	@JsonProperty("last-updated")
	public long lastUpdated;
	
}
