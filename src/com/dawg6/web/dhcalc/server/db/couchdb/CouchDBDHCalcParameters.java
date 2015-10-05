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
package com.dawg6.web.dhcalc.server.db.couchdb;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.google.gson.Gson;

public class CouchDBDHCalcParameters {

	private static CouchDBDHCalcParameters instance = null;
	
	private final Map<String, String> cache = new HashMap<String, String>();
	private final Map<String, List<Listener>> listeners = new Hashtable<String, List<Listener>>();

	public static final String MAX_REQUESTS = "max_requests";
	public static final String CACHE_SIZE = "cache_size";
	public static final String SCHEMA_VERSION = "schema.version";
	public static final String CONNECT_TIMEOUT = "timeout.connect";
	public static final String READ_TIMEOUT = "timeout.read";
	public static final String ITEM_REALM = "item.realm";
	public static final String TIMEOUT_RETRIES = "timeout.retries";

	private final CachePolicy cachePolicy;
	private final Gson gson;
	
	public enum CachePolicy {
		CacheEnabled, CacheDisabled
	}

	public static synchronized CouchDBDHCalcParameters getInstance() {
		if (instance == null) 
			instance = new CouchDBDHCalcParameters();
		
		return instance;
	}
	
	private CouchDBDHCalcParameters() { 
		this.cachePolicy = CachePolicy.CacheEnabled;
		this.gson = new Gson();
	}

	public String getParameter(String parameter, String defaultValue, Listener listener) {
		String value = null;

		if (cachePolicy == CachePolicy.CacheDisabled) {
			value = CouchDBDHCalcDatabase.getInstance().getParameter(parameter);
		
			if (value == null) {
					value = defaultValue;
					storeParameter(parameter, value);
			}

		} else {

			synchronized (cache) {
				value = cache.get(parameter);

				if (value == null) {
					value = CouchDBDHCalcDatabase.getInstance().getParameter(parameter);

					if (value == null) {
						value = defaultValue;
						storeParameter(parameter, value);
					}

					cache.put(parameter, value);
				}
			}
		}

		if (listener != null) {
			addListener(parameter, listener);
		}

		return value;
	}

	public Integer getParameter(String parameter, Integer defaultValue, Listener listener) {
		String def = (defaultValue != null) ? defaultValue.toString() : "0";

		String value = getParameter(parameter, def, listener);

		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public void storeParameter(String parameter, String value) {

		if (cachePolicy == CachePolicy.CacheEnabled) {

			synchronized (cache) {
				cache.put(parameter, value);
			}
		}

		CouchDBDHCalcDatabase.getInstance().putParameter(parameter, value);
		notifyListeners(parameter, value);
	}

	public void storeParameter(String parameter, Integer value) {
		String str = (value != null) ? value.toString() : null;
		storeParameter(parameter, str);
	}

	public void addListener(String parameter, Listener listener) {
		synchronized (listeners) {
			List<Listener> list = listeners.get(parameter);

			if (list == null) {
				list = new Vector<Listener>();
				listeners.put(parameter, list);
			}

			if (!list.contains(listener))
				list.add(listener);
		}
	}

	public void notifyListeners(final String parameter, final String value) {
		synchronized (listeners) {
			List<Listener> list = listeners.get(parameter);

			if (list != null) {
				for (final Listener l : list) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							l.parameterChanged(parameter, value);
						}
					}).start();
				}
			}
		}
	}

	
	public interface Listener {
		void parameterChanged(String parameter, String value);
	}
	
	public void reloadCache() {
		synchronized (cache) {
			
			Map<String, String> copy = copy();
			
			cache.clear();
			
			for (Map.Entry<String, String> e : copy.entrySet()) {
				String value = this.getParameter(e.getKey(), e.getValue(), null);
				this.notifyListeners(e.getKey(), value);
			}
		}
	}
	
	public Map<String, String> copy() {
		
		synchronized (cache) {
			return new TreeMap<String, String>(cache);
		}
	}
}
