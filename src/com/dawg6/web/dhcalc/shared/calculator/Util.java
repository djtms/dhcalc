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
package com.dawg6.web.dhcalc.shared.calculator;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Util implements ILogger {

	private static final Util instance = new Util();
	private Formatter formatter;
	private ILogger logger;
	
	private Util() {
	}

	public static Util getInstance() {
		return instance;
	}

	
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public static String format(double d) {

		if (instance.formatter == null) {
			return String.valueOf(d);
		} else {
			return instance.formatter.format(d);
		}
	}

	public interface Formatter {
		String format(double d);
	}

	public static <T extends Enum<T>> Map<T, Double> createMap(Class<T> clazz,
			Map<String, String> smap) {
		Map<T, Double> map = new TreeMap<T, Double>();

		if (smap == null)
			return map;

		for (Map.Entry<String, String> e : smap.entrySet()) {
			T t = Enum.valueOf(clazz, e.getKey());
			Double value = Double.parseDouble(e.getValue());
			map.put(t, value);
		}

		return map;
	}

	public static <T extends Enum<T>> Map<String, String> createMap(
			Map<T, Double> map) {
		Map<String, String> smap = new TreeMap<String, String>();

		if (map == null)
			return smap;

		for (Map.Entry<T, Double> e : map.entrySet()) {
			T t = e.getKey();
			Double value = e.getValue();
			smap.put(t.name(), value.toString());
		}

		return smap;
	}

	public static <T extends Enum<T>> Map<String, String> createMap(Set<T> set) {
		Map<String, String> smap = new TreeMap<String, String>();

		if (set == null)
			return smap;

		for (T t : set) {
			smap.put(t.name(), Boolean.TRUE.toString());
		}

		return smap;
	}

	public static <T extends Enum<T>> Set<T> createSet(Class<T> clazz,
			Map<String, String> smap) {
		Set<T> set = new TreeSet<T>();

		if (smap == null)
			return set;

		for (Map.Entry<String, String> e : smap.entrySet()) {
			T t = Enum.valueOf(clazz, e.getKey());
			Boolean value = Boolean.parseBoolean(e.getValue());

			if (value)
				set.add(t);
		}

		return set;
	}

	public static void putAll(Map<String, String> map, String prefix,
			Map<String, String> values) {

		for (Map.Entry<String, String> e : values.entrySet()) {
			map.put(prefix + e.getKey(), e.getValue());
		}
	}

	public static <K extends Enum<K>, V extends Enum<V>> Map<String, String> createEnumMap(
			Map<K, V> map) {
		Map<String, String> smap = new TreeMap<String, String>();

		if (map == null)
			return smap;

		for (Map.Entry<K, V> e : map.entrySet()) {
			smap.put(e.getKey().name(), e.getValue().name());
		}

		return smap;
	}

	public static <K extends Enum<K>, V extends Enum<V>> Map<K, V> createEnumMap(
			Class<K> keyClass, Class<V> valueClass, Map<String, String> smap) {

		Map<K, V> map = new TreeMap<K, V>();

		for (Map.Entry<String, String> e : smap.entrySet()) {
			K key = Enum.valueOf(keyClass, e.getKey());
			V value = Enum.valueOf(valueClass, e.getValue());
			map.put(key, value);
		}

		return map;
	}

	public static <K, V> Map<K, V> copy(Map<K, V> map) {

		if (map == null)
			return null;

		return new TreeMap<K, V>(map);
	}

	public static <K> Set<K> copy(Set<K> set) {

		if (set == null)
			return null;

		return new TreeSet<K>(set);
	}

	public static final String GEM_LEVEL = ".level";

	public static Map<GemSkill, GemAttributeData> createGems(
			Map<String, String> gems) {
		Map<GemSkill, GemAttributeData> map = new TreeMap<GemSkill, GemAttributeData>();

		for (GemSkill gem : GemSkill.values()) {
			String v = gems.get(gem.name() + GEM_LEVEL);

			try {
				if (v != null) {
					GemAttributeData gd = new GemAttributeData();
					map.put(gem, gd);

					gd.level = Integer.parseInt(v);

					for (GemSkill.Attribute a : gem.getAttributes()) {
						v = gems.get(gem.name() + "." + a.getLabel());

						if (v != null) {
							gd.put(a.getLabel(), Integer.parseInt(v));
						} else {
							gd.put(a.getLabel(), 0);
						}
					}
				}
			} catch (Exception e) {
				// ignore
			}
		}

		return map;
	}

	public static Map<String, String> createGemsMap(
			Map<GemSkill, GemAttributeData> gems) {

		Map<String, String> map = new TreeMap<String, String>();

		for (Map.Entry<GemSkill, GemAttributeData> e : gems.entrySet()) {
			GemSkill gem = e.getKey();
			GemAttributeData gd = e.getValue();

			map.put(gem.name() + GEM_LEVEL, String.valueOf(gd.level));

			for (GemSkill.Attribute a : gem.getAttributes()) {
				Integer v = gd.get(a.getLabel());

				if (v == null)
					v = 0;

				map.put(gem.name() + "." + a.getLabel(), String.valueOf(v));
			}
		}

		return map;
	}

	public static <T> int indexOf(T[] list, T value) {

		if ((list == null) || (list.length == 0))
			return -1;

		int n = list.length;
		int i = 0;

		while (i < n) {
			if (list[i] == value)
				return i;
			else
				i++;
		}

		return -1;
	}

	public static Map<String, String> createSpecialItemsMap(
			Map<SpecialItemType, AttributeData> items,
			Map<String, Integer> setCounts) {

		Map<String, String> map = new TreeMap<String, String>();

		if (items != null) {
			for (Map.Entry<SpecialItemType, AttributeData> e : items.entrySet()) {
				SpecialItemType type = e.getKey();
				AttributeData ad = e.getValue();

				map.put(type.name(), "TRUE");

				for (Map.Entry<String, Integer> a : ad.entrySet()) {
					map.put(type.name() + "." + a.getKey(),
							String.valueOf(a.getValue()));
				}
			}
		}

		if (setCounts != null) {
			for (ItemSet set : ItemSet.values()) {
				Integer n = setCounts.get(set.getSlug());

				if (n != null) {
					map.put(set.name(), String.valueOf(n));
				}
			}
		}

		return map;
	}

	public static Map<SpecialItemType, AttributeData> createSpecialItems(
			Map<String, String> smap) {

		Map<SpecialItemType, AttributeData> map = new TreeMap<SpecialItemType, AttributeData>();

		for (SpecialItemType type : SpecialItemType.values()) {
			String value = smap.get(type.name());

			if ((value != null) && Boolean.parseBoolean(value)) {
				AttributeData ad = new AttributeData();
				map.put(type, ad);

				for (SpecialItemType.Attribute a : type.getAttributes()) {
					String av = smap.get(type.name() + "." + a.getKey());
					Integer v = null;

					if (av == null) {
						v = a.getMin();
					} else {

						try {
							v = Integer.parseInt(av);
						} catch (Exception ex) {
							v = a.getMin();
						}
					}

					ad.put(a.getLabel(), v);
				}
			}
		}

		return map;
	}

	public static Map<String, Integer> createSetCounts(Map<String, String> smap) {

		Map<String, Integer> map = new TreeMap<String, Integer>();

		for (ItemSet set : ItemSet.values()) {
			String value = smap.get(set.name());

			if (value != null) {
				Integer n = 0;

				try {
					n = Integer.parseInt(value);
				} catch (Exception ex) {
					n = 0;
				}

				if (n > 0)
					map.put(set.getSlug(), n);
			}
		}

		return map;
	}

	@Override
	public void log(String message) {
		
		if (logger != null)
			logger.log(message);
	}

	@Override
	public void log(String message, Throwable t) {
		if (logger != null)
			logger.log(message, t);
	}
}
