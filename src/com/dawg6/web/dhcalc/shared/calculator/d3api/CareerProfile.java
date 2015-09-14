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
package com.dawg6.web.dhcalc.shared.calculator.d3api;

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
