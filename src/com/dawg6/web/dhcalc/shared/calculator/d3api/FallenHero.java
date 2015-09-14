/*******************************************************************************
 * Copyright (c) 2014, 2015 Scott Clarke (scott@dawg6.com).
 *
 * This file is part of Dawg6's Demon Hunter DPS Calculator.
 *
 * Dawg6's Demon Hunter DPS Calculator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dawg6's Demon Hunter DPS Calculator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
