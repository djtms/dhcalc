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

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.dawg6.d3api.shared.ApiData;
import com.dawg6.d3api.shared.CareerProfile;
import com.dawg6.d3api.shared.HeroProfile;

public class FormData extends ApiData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Version version;
	public Map<String, String> main = new TreeMap<String, String>();
	public Map<String, String> calculator = new TreeMap<String, String>();
	public Map<String, String> items = new TreeMap<String, String>();
	public Map<String, String> skills = new TreeMap<String, String>();
	public Map<String, String> passives = new TreeMap<String, String>();
	public Map<String, String> gems = new TreeMap<String, String>();
	public Map<String, String> elementalDamage = new TreeMap<String, String>();
	public Map<String, String> skillDamage = new TreeMap<String, String>();
	public Map<String, String> specialItems = new TreeMap<String, String>();
	
	public CareerProfile career;

	public HeroProfile hero;

}