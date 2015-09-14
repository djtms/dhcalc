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
package com.dawg6.web.dhcalc.server;

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
