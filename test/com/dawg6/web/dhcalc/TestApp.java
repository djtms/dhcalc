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
package com.dawg6.web.dhcalc;

import com.dawg6.d3api.shared.Leaderboard;
import com.dawg6.d3api.shared.Realm;
import com.dawg6.web.dhcalc.server.IO;
import com.google.gson.Gson;


public class TestApp {

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
//		SeasonProfile s = IO.getInstance().readSeason(Realm.US.getApiHost(), 4);
//		SeasonIndex s = IO.getInstance().readSeasonIndex(Realm.US.getApiHost());
		Leaderboard s = IO.getInstance().readSeasonLeaderboard(Realm.US.getApiHost(), 4, "rift-hardcore-team-2");
		
		System.out.println("s = " + gson.toJson(s));
	}

}
