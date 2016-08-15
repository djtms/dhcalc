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

public enum GemLevel {
	None(0, 0),
	Regular(1, .035),
	Flawless(4, .05),
	Square(7, .065),
	Flawless_Square(8, .08),
	Star(11, .10),
	Marquise(15, .105),
	Imperial(16, .11),
	Flawless_Imperial(17, .115),
	Royal(18, .12),
	Flawless_Royal(19, .125);
	
	private double cdr;
	private int rank;
	
	private GemLevel(int rank, double cdr) {
		this.cdr = cdr;
		this.rank = rank;
	}
	
	public int getRank() {
		return rank;
	}
	
	public double getCdr() {
		return cdr;
	}
	
	public double getRcr() {
		return cdr;
	}
	
	public String getDisplayName() {
		return name().replaceAll("_"," ") + " (" + Util.format(cdr * 100.0) + "%)";
	}
}
