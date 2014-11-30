package com.dawg6.web.sentry.shared.calculator.d3api;

import java.io.Serializable;
import java.util.Map;

public class CareerProfile extends D3Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Hero[] heroes;
	
	public int lastHeroPlayed;

	public long lastUpdated;

	public Kill kills;

	public int highestHardcoreLevel;
	
	public TimePlayed timePlayed;
	
	public FallenHero[] fallenHeroes;

	public int paragonLevel;

	public int paragonLevelHardcore;

	public int paragonLevelSeason;

	public int paragonLevelSeasonHardcore;

	public String battleTag;

	public Progression progression;

	public Artisan blacksmith;
	public Artisan jeweler;
	public Artisan mystic;
	public Artisan blacksmithHardcore;
	public Artisan jewelerHardcore;
	public Artisan mysticHardcore;
	public Artisan blacksmithSeason;
	public Artisan jewelerSeason;
	public Artisan mysticSeason;
	public Artisan blacksmithSeasonHardcore;
	public Artisan jewelerSeasonHardcore;
	public Artisan mysticSeasonHardcore;
	
	public static class Artisan implements Serializable {

		private static final long serialVersionUID = 1L;
		
		public String slug;
		
		public int level;
		
		public int stepCurrent;
		
		public int stepMax;
	}
	
	public Map<String, Season> seasonalProfiles;
	
	public static class Season implements Serializable {

		private static final long serialVersionUID = 1L;

		public int seasonId;
		
		public int paragonLevel;
		
		public int paragonLevelHardcore;
		
		public Kill kills;
		
		public TimePlayed timePlayed;
		
		public int highestHardcoreLevel;
		
		public Progression progression;
	}
}
