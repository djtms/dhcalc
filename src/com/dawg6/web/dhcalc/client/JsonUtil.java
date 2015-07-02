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
package com.dawg6.web.dhcalc.client;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.dawg6.gwt.client.ApplicationPanel;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.GemAttributeData;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.dawg6.web.dhcalc.shared.calculator.ItemHolder;
import com.dawg6.web.dhcalc.shared.calculator.Slot;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JsonUtil {

	public static JSONObject toJSONObject(FormData data) {
		
		if (data == null)
			return null;
		
		JSONObject obj = new JSONObject();
		
		obj.put("version", JsonUtil.toJSONObject(data.version));
		obj.put("main", JsonUtil.toJSONObject(data.main));
		obj.put("calculator", JsonUtil.toJSONObject(data.calculator));
		obj.put("passives", JsonUtil.toJSONObject(data.passives));
		obj.put("gems", JsonUtil.toJSONObject(data.gems));
		obj.put("equipment", JsonUtil.toJSONObject(data.specialItems));
		obj.put("skills", JsonUtil.toJSONObject(data.skills));
		obj.put("elementalDamage", JsonUtil.toJSONObject(data.elementalDamage));
		obj.put("skillDamage", JsonUtil.toJSONObject(data.skillDamage));
		obj.put("items", JsonUtil.toJSONObject(data.items));
		obj.put("hero", JsonUtil.toJSONObject(data.hero));
		obj.put("career", JsonUtil.toJSONObject(data.career));

		return obj;
	}
	
	public static FormData parseFormData(String text) {
		FormData data = new FormData();
		
		if ((text != null) && (text.trim().length() > 0)) {
		
			try {
				JSONValue value = JSONParser.parseLenient(text);
				
				if (value != null) {
					JSONObject obj = value.isObject();
				
					if (obj != null) {
						data.version = JsonUtil.parseVersion(obj.get("version"));
						data.main = JsonUtil.parseMap(obj.get("main"));
						data.calculator = JsonUtil.parseMap(obj.get("calculator"));
						data.items = JsonUtil.parseMap(obj.get("items"));
						data.passives = JsonUtil.parseMap(obj.get("passives"));
						data.gems = JsonUtil.parseMap(obj.get("gems"));
						data.specialItems = JsonUtil.parseMap(obj.get("equipment"));
						data.skills = JsonUtil.parseMap(obj.get("skills"));
						data.elementalDamage = JsonUtil.parseMap(obj.get("elementalDamage"));
						data.skillDamage = JsonUtil.parseMap(obj.get("skillDamage"));
						data.hero = null;
						data.career = null;
					}
				}
			} catch (Exception e) {
				ApplicationPanel.showErrorDialog("Error Parsing Form Data");
				GWT.log("Error Parsing JSON Data", e);
			}
			
			
		}
		
		return data;
	}

	
	public static <K extends Enum<K>, V extends Enum<V>> Map<String, String> createEnumStringMap(Map<K, V> map) {
		Map<String, String> smap = new TreeMap<String, String>();
		
		for (Map.Entry<K, V> e : map.entrySet()) {
			smap.put(e.getKey().name(), e.getValue().name());
		}
		
		return smap;
	}

	public static <K, V> Map<String, String> createStringMap(Map<K, V> map) {
		Map<String, String> smap = new TreeMap<String, String>();
		
		for (Map.Entry<K, V> e : map.entrySet()) {
			smap.put(e.getKey().toString(), e.getValue().toString());
		}
		
		return smap;
	}
	
	public static Map<String, String> parseMap(JSONValue jsonValue) {
		JSONObject obj = jsonValue.isObject();
		Map<String, String> map = new TreeMap<String, String>();
		
		if (obj != null) {
		
			for (String key : obj.keySet()) {
				JSONValue value = obj.get(key);

				if (value != null) {
					JSONString str = value.isString();
					
					if (str != null) {
						map.put(key, str.stringValue());
					}
				}
			}
		}
		
		return map;
	}

	public static Version parseVersion(JSONValue jsonValue) {
		JSONObject obj = jsonValue.isObject();
		
		Version version = new Version();
		
		if (obj != null) {
			JSONValue value = obj.get("version");
			
			if (value != null) {
				JSONString str = value.isString();
				
				if (str != null) {
					version.version = str.stringValue();
				}
			}
			
		}
		
		return version;
	}

	public static JSONValue toJSONObject(CareerProfile career) {
		return null;
	}

	public static JSONValue toJSONObject(HeroProfile hero) {
		return null;
	}

	public static JSONObject toJSONObject(Map<String, String> map) {
		if (map == null)
			return null;
		
		JSONObject obj = new JSONObject();
		
		for (Map.Entry<String, String> e : map.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();
			
			obj.put(key, new JSONString(value));
		}
		
		return obj;
	}

	public static <T extends Enum<T>> Set<T> parseSet(Class<T> clazz, String text) {
		if ((text == null) || (text.trim().length() == 0))
			return null;
		
		Set<T> set = new TreeSet<T>();

		JSONValue value = JSONParser.parseLenient(text);

		JSONArray array = value.isArray();
		
		if (array == null)
			return null;
		
		for (int i = 0; i < array.size(); i++) {
			JSONValue e = array.get(i);
			
			if (e  != null) {
				JSONString str = e.isString();
				
				if (str != null) {
					String name = str.stringValue();
					
					if (name != null) {
						T elem = Enum.valueOf(clazz, name);
						
						if (elem != null) {
							set.add(elem);
						}
					}
				}
			}
		}
		
		
		return set;
	}
	
	public static <T extends Enum<T>> JSONArray toJSONObject(Set<T> set) {
		if (set == null)
			return null;
		
		JSONArray obj = new JSONArray();
		
		int i = 0;
		
		for (T t : set) {
			obj.set(i++, new JSONString(t.name()));
		}
		
		return obj;
	}

	public static JSONObject toJSONObject(Version version) {
		
		if (version == null)
			return null;

		JSONObject obj = new JSONObject();
		
		obj.put("version", new JSONString(version.version));

		return obj;
	}
	
	public static native String formatJsonText(String text) 
	/*-{
		var object = JSON.parse(text);
		return JSON.stringify(object, null, 4);
	}-*/;

	public static <T extends Enum<T>> Map<T, Double> parseMap(Class<T> clazz,
			String text) {
		
		if (text == null) {
			return new TreeMap<T, Double>();
		} else {
		
			JSONValue v = JSONParser.parseLenient(text);
			Map<String, String> smap = JsonUtil.parseMap(v);

			Map<T, Double> map = new TreeMap<T, Double>();

			for (Map.Entry<String, String> e : smap.entrySet()) {
				T type = Enum.valueOf(clazz, e.getKey());
				Double d = Double.parseDouble(e.getValue());
				
				map.put(type, d);
			}

			return map;
		}
	}

	public static <K extends Enum<K>, V extends Enum<V>> Map<K, V> parseMap(Class<K> keyClass,
			Class<V> valueClass, String text) {
		if (text == null) {
			return new TreeMap<K, V>();
		} else {
		
			JSONValue v = JSONParser.parseLenient(text);
			Map<String, String> smap = JsonUtil.parseMap(v);

			Map<K, V> map = new TreeMap<K, V>();

			for (Map.Entry<String, String> e : smap.entrySet()) {
				K key = Enum.valueOf(keyClass, e.getKey());
				V value = Enum.valueOf(valueClass, e.getValue());
				
				map.put(key, value);
			}

			return map;
		}
	}

	public static Object gemsToJSONObject(Map<GemSkill, GemAttributeData> gems) {
		return toJSONObject(Util.createGemsMap(gems));
	}

	public static Object specialItemsToJSONObject(Map<Slot, ItemHolder> items, Map<String, Integer> setCounts) {
		return toJSONObject(Util.createSpecialItemsMap(items, setCounts));
	}

	public static Map<GemSkill, GemAttributeData> parseGemsMap(String text) {
		if (text == null) {
			return new TreeMap<GemSkill, GemAttributeData>();
		} else {
		
			JSONValue v = JSONParser.parseLenient(text);
			Map<String, String> smap = JsonUtil.parseMap(v);

			return Util.createGems(smap);
		}
	}

	public static Map<Slot, ItemHolder> parseSpecialItemsMap(String text) {
		if (text == null) {
			return new TreeMap<Slot, ItemHolder>();
		} else {
		
			JSONValue v = JSONParser.parseLenient(text);
			Map<String, String> smap = JsonUtil.parseMap(v);

			return Util.createSpecialItems(smap);
		}
	}

	public static Map<String, Integer> parseSetCounts(String text) {
		if (text == null) {
			return new TreeMap<String, Integer>();
		} else {
		
			JSONValue v = JSONParser.parseLenient(text);
			Map<String, String> smap = JsonUtil.parseMap(v);

			return Util.createSetCounts(smap);
		}
	}
}
