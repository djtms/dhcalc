package com.dawg6.web.sentry.server;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = -6887102596733419364L;

	public static final int DEFAULT_MAX_SIZE = 1000;

	private int maxSize;

	public Cache() {
		this(DEFAULT_MAX_SIZE, (float) 0.75, false);
	}

	public Cache(boolean accessOrder) {
		this(DEFAULT_MAX_SIZE, (float) 0.75, accessOrder);
	}

	public Cache(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
		maxSize = initialCapacity;
	}

	public Cache(int initialCapacity, float loadFactor) {
		this(initialCapacity, loadFactor, false);
	}

	public Cache(int initialCapacity) {
		this(initialCapacity, (float) 0.75, false);
	}

	public Cache(Map<? extends K, ? extends V> m) {
		super(m);
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > maxSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;

		purge();
	}

	private void purge() {
		while (size() > maxSize) {
			this.remove(this.keySet().iterator().next());
		}
	}

}
