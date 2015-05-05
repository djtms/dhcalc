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
package com.dawg6.web.dhcalc.shared.calculator;


public class BreakPoint implements Comparable<BreakPoint> {

	public static final int DURATION = 30;
	
	private final double aps;
	private final int qty;
	private final int bp;

	private BreakPoint(int bp, double aps, int qty) {
		this.aps = aps;
		this.qty = qty;
		this.bp = bp;
	}

	public int getQty() {
		return qty;
	}

	public int getBp() {
		return bp;
	}

	public double getAps() {
		return aps;
	}

	@Override
	public String toString() {
		return bp + ": " + Util.format(aps) + "/" + qty;
	}

	public static final BreakPoint[] ALL = { 
			new BreakPoint(1, 1.102, 37),
			new BreakPoint(2, 1.256, 42), new BreakPoint(3, 1.459, 49),
			new BreakPoint(4, 1.742, 60), new BreakPoint(5, 2.160, 74),
			new BreakPoint(6, 2.842, 97), new BreakPoint(7, 4.154, 150) };

	public static BreakPoint get(double aps) {

		BreakPoint cur = ALL[0];

		for (int i = 0; (i < ALL.length) && (aps > cur.aps); i++) {
			if (aps > ALL[i].aps)
				cur = ALL[i];
		}

		return cur;
	}

	public static BreakPoint getBp(int bp) {
		for (BreakPoint b : ALL)
			if (b.getBp() == bp)
				return b;
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(get(1.8));
		System.out.println(get(2.2));
		System.out.println(get(4.2));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(aps);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BreakPoint other = (BreakPoint) obj;
		if (Double.doubleToLongBits(aps) != Double.doubleToLongBits(other.aps))
			return false;
		return true;
	}

	@Override
	public int compareTo(BreakPoint o) {
		return new Double(aps).compareTo(o.aps);
	}

	public BreakPoint next() {
		for (int i = 0; i < ALL.length-1; i++) {
			if (ALL[i].bp == this.bp)
				return ALL[i+1];
		}

		return null;
	}

	public BreakPoint prev() {
		for (int i = 1; i < ALL.length; i++) {
			if (ALL[i].bp == this.bp)
				return ALL[i-1];
		}

		return null;
	}
}
