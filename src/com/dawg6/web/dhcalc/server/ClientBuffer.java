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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.server;

import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class ClientBuffer {

	private static final ClientBuffer instance = new ClientBuffer();

	public static ClientBuffer getInstance() {
		return instance;
	}
	
	private final Map<String, Object> buffer = new Hashtable<String, Object>();
	
	public synchronized void put(String client, Object value) {
		buffer.put(client, value);
	}
	
	public synchronized Object get(String client) {
		Object value = buffer.get(client);
		
		if (value != null) {
			buffer.remove(client);
		}
		
		return value;
	}
	
	public Map<String, Object> inspect() {
		return new TreeMap<String, Object>(buffer);
	}
	
	public synchronized void clear() {
		buffer.clear();
	}
}
