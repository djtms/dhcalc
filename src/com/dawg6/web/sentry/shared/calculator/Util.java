package com.dawg6.web.sentry.shared.calculator;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



public class Util {

	private static final Util instance = new Util();
	private Formatter formatter;
	
	private Util() { }
	
	public static Util getInstance() { return instance; }
	
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
	
	public static <T extends Enum<T>> Map<T, Double> createMap(Class<T> clazz, Map<String, String> smap) {
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
	
	public static <T extends Enum<T>> Map<String, String> createMap(Map<T, Double> map) {
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
	
	public static <T extends Enum<T>> Set<T> createSet(Class<T> clazz, Map<String, String> smap) {
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

	public static <K extends Enum<K>, V extends Enum<V>> Map<String, String> createEnumMap(Map<K, V> map) {
		Map<String, String> smap = new TreeMap<String, String>();
		
		if (map == null)
			return smap;
		
		for (Map.Entry<K, V> e : map.entrySet()) {
			smap.put(e.getKey().name(), e.getValue().name());
		}
		
		return smap;
	}

	public static <K extends Enum<K>, V extends Enum<V>> Map<K, V> createEnumMap(
			Class<K> keyClass, Class<V> valueClass,
			Map<String, String> smap) {
		
		Map<K, V> map = new TreeMap<K, V>();
		
		for (Map.Entry<String, String> e : smap.entrySet()) {
			K key = Enum.valueOf(keyClass, e.getKey());
			V value = Enum.valueOf(valueClass, e.getValue());
			map.put(key, value);
		}
		
		return map;
	}

	public static <K, V>  Map<K, V> copy(Map<K, V> map) {
		
		if (map == null)
			return null;
		
		return new TreeMap<K, V>(map);
	}
	
	public static <K> Set<K> copy(Set<K> set) {
		
		if (set == null)
			return null;
		
		return new TreeSet<K>(set);
	}
}
