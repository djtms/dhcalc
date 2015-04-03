package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;

public class FormData extends JsonObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Version version;
	public Map<String, String> main = new TreeMap<String, String>();
	public Map<String, String> calculator = new TreeMap<String, String>();
	public Map<String, String> items = new TreeMap<String, String>();
	public Map<String, String> passives = new TreeMap<String, String>();
	public Map<String, String> elementalDamage = new TreeMap<String, String>();
	public Map<String, String> skillDamage = new TreeMap<String, String>();
	
	public CareerProfile career;

	public HeroProfile hero;

}