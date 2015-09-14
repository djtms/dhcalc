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


public class UrlHelper {

	public static String careerProfileUrl(String server, String name, int code) {
		return server + "/d3/profile/" + name + "-" + code + "/?locale=" + LOCALE;
	}

	public static String heroProfileUrl(String server, String name, int code,
			int id) {
		return server + "/d3/profile/" + name + "-" + code + "/hero/" + id + "?locale=" + LOCALE;
	}

	public static String itemInformationUrl(String server, String tooltipParams) {
		return server + "/d3/data/" + tooltipParams + "?locale=" + LOCALE;
	}
	
	public static String seasonIndexUrl(String server) {
		return server + "/data/d3/season/";
	}
	
	public static String seasonUrl(String server, int season) {
		return server + "/data/d3/season/" + season;
	}

	public static String seasonLeaderboardUrl(String server, int season, String leaderboard) {
		return server + "/data/d3/season/" + season + "/leaderboard/" + leaderboard;
	}

	public static String eraIndexUrl(String server) {
		return server + "/data/d3/era/";
	}

	public static String eraUrl(String server, int era) {
		return server + "/data/d3/era/" + era;
	}

	public static String eraLeaderboardUrl(String server, int era, String leaderboard) {
		return server + "/data/d3/era/" + era + "/leaderboard/" + leaderboard;
	}

	public static String LOCALE = "en_US";
	
}
