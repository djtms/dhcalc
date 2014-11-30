package com.dawg6.web.sentry.shared.calculator;

public class MarkedForDeath {

	public static final Rune[] RUNES = {
		Rune.None,
		Rune.Contagion,
		Rune.Valley_Of_Death,
		Rune.Grim_Reaper,
		Rune.Mortal_Enemy,
		Rune.Death_Toll
	};
	
	public static final String url = "http://us.battle.net/d3/en/class/demon-hunter/active/marked-for-death";
	
	public static final double BASE_DAMAGE = 0.20;
	public static final double VOD_DAMAGE = 0.15;
	public static final double VOD_RADIUS = 15.0;
	public static final double GR_AOE_DAMAGE = 0.20;
	public static final double GR_AOE_RADIUS = 20.0;
}
