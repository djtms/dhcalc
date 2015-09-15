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
package com.dawg6.web.dhcalc.shared.calculator;

public enum LeaderboardType {
	
	Solo("dh","Solo"),
	Team2("team-2","2 Players"),
	Team3("team-3","3 Players"),
	Team4("team-4","4 Players"),
	;
	
	private String key;
	private String name;
	
	private LeaderboardType(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getKey(boolean hardcore) {
		return "rift-" + (hardcore?"hardcore-":"") + key;
	}
}