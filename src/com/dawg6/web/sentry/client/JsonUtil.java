package com.dawg6.web.sentry.client;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
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
		obj.put("items", JsonUtil.toJSONObject(data.items));
		obj.put("hero", JsonUtil.toJSONObject(data.hero));
		obj.put("career", JsonUtil.toJSONObject(data.career));

		return obj;
	}
	
	public static FormData parseFormData(String text) {
		FormData data = new FormData();
		
		
		JSONValue value = JSONParser.parseLenient(text);
		
		if (value != null) {
			JSONObject obj = value.isObject();
		
			if (obj != null) {
				data.version = JsonUtil.parseVersion(obj.get("version"));
				data.main = JsonUtil.parseMap(obj.get("main"));
				data.calculator = JsonUtil.parseMap(obj.get("calculator"));
				data.items = JsonUtil.parseMap(obj.get("items"));
				data.hero = null;
				data.career = null;
			}
		}
		
		return data;
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
}
