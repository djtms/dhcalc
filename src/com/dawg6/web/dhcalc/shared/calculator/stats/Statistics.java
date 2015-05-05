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
package com.dawg6.web.dhcalc.shared.calculator.stats;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Statistics implements Serializable {

	private static final long serialVersionUID = 3696769404960522163L;

	public long total = 0;
	public Map<StatCategory, DpsTableEntry> max = new TreeMap<StatCategory, DpsTableEntry>();
	public Map<StatCategory, Double> average = new TreeMap<StatCategory, Double>();
}
