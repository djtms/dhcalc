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

public class Leaderboard implements Serializable {

	private static final long serialVersionUID = 1904200828613880858L;

	@JsonProperty("_links")
	public Links links;

	@JsonProperty("row")
	public Row[] rows;
	
	public String last_update_time;
	public String generated_by;
	public Boolean greater_rift;
	public Boolean achievement_points;
	public String greater_rift_solo_class;
	public Integer greater_rift_team_size;
	public Integer season;
	public Integer era;
	
	public static class Row implements Serializable {

		private static final long serialVersionUID = -1330951434091258539L;

		@JsonProperty("player")
		public Player[] players;
		public int order;
		public Data[] data;
		
	}

	public static class Player implements Serializable {

		private static final long serialVersionUID = 1648944336841273500L;

		public String key;
		public long accountId;
		public Data[] data;
	}
	
	public static class Data implements Serializable {

		private static final long serialVersionUID = 4294709576339657374L;

		public String id;
		public Long number;
		public String string;
		public Long timestamp;
	}
	
}
