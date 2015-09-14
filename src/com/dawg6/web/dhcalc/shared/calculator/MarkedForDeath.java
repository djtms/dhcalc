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
package com.dawg6.web.dhcalc.shared.calculator;

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
