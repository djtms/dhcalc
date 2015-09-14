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

public enum Realm {
	US("Americas","http://us.battle.net","https://us.api.battle.net"),
	EU("Europe","http://eu.battle.net","https://eu.api.battle.net"),
	KR("Korea","http://kr.battle.net","https://kr.api.battle.net"),
	TW("Taiwan","http://tw.battle.net","https://tw.api.battle.net");
	
	private final String displayName;
	private final String host;
	private final String apiHost;
	
	private Realm(String displayName, String host, String apiHost) {
		this.displayName = displayName;
		this.host = host;
		this.apiHost = apiHost;
	}
	
	public String getApiHost() {
		return apiHost;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public String getHost() {
		return this.host;
	}
}
