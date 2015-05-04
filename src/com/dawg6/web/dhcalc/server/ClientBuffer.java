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
