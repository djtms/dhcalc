package com.dawg6.web.sentry.shared.calculator;

import java.util.Map;
import java.util.TreeMap;

public enum Passive {

	Thrill_of_the_Hunt,
	Tactical_Advantage,
	Blood_Vengeance,
	Steady_Aim,
	Cull_the_Weak,
	Night_Stalker,
	Brooding,
	Hot_Pursuit,
	Archery,
	Numbing_Traps,
	Perfectionist,
	Custom_Engineering,
	Grenadier,
	Sharpshooter,
	Ballistics,
	Ambush,
	Awareness,
	Single_Out;
	
	private static final Map<String, Passive> slugs = new TreeMap<String, Passive>();
	private static final Map<String, Passive> names = new TreeMap<String, Passive>();

	private Passive() {
	}
	
	public String getLongName() {
		return name().replaceAll("_", " ");
	}

	public String getSlug() {
		return name().toLowerCase().replaceAll("_", "-");
	}
	
	public String getUrl() {
		return "http://us.battle.net/d3/en/class/demon-hunter/passive/" + getSlug();
	}

	
	public static Passive fromSlug(String slug) {
		
		if (slugs.isEmpty()) {
			for (Passive p : values())
				slugs.put(p.getSlug(), p);
		}

		return slugs.get(slug);
	}

	public static Passive fromName(String name) {
		if (names.isEmpty()) {
			for (Passive p : values())
				names.put(p.getLongName(), p);
		}

		return names.get(name);
	}
}
