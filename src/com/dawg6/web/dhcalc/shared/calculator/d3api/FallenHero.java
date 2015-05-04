package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FallenHero implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String name;
	
	public int level;
	
	public HeroStats stats;

	public HeroProfile.Items items;
	
	public Kill kills;
	
	public boolean hardcore;
	
	public Death death;
	
	public static class Death implements Serializable {

		private static final long serialVersionUID = 1L;

		public int killer;
		
		public long time;
		
		public int location;
	}

	public int heroId;
	
	public int gender;
	
	@JsonProperty("class")
	public String clazz;}
