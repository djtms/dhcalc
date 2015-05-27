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


public class OldSentryBreakPoint implements Comparable<OldSentryBreakPoint> {

	public static final int DURATION = 30;
	
	private final double aps;
	private final int qty;
	private final int bp;

	private OldSentryBreakPoint(int bp, double aps, int qty) {
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

	public static final OldSentryBreakPoint[] ALL = { 
			new OldSentryBreakPoint(1, 1.102, 37),
			new OldSentryBreakPoint(2, 1.256, 42), new OldSentryBreakPoint(3, 1.459, 49),
			new OldSentryBreakPoint(4, 1.742, 60), new OldSentryBreakPoint(5, 2.160, 74),
			new OldSentryBreakPoint(6, 2.842, 97), new OldSentryBreakPoint(7, 4.154, 150) };

	public static OldSentryBreakPoint get(double aps) {

		OldSentryBreakPoint cur = ALL[0];

		for (int i = 0; (i < ALL.length) && (aps > cur.aps); i++) {
			if (aps > ALL[i].aps)
				cur = ALL[i];
		}

		return cur;
	}

	public static OldSentryBreakPoint getBp(int bp) {
		for (OldSentryBreakPoint b : ALL)
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
		OldSentryBreakPoint other = (OldSentryBreakPoint) obj;
		if (Double.doubleToLongBits(aps) != Double.doubleToLongBits(other.aps))
			return false;
		return true;
	}

	@Override
	public int compareTo(OldSentryBreakPoint o) {
		return new Double(aps).compareTo(o.aps);
	}

	public OldSentryBreakPoint next() {
		for (int i = 0; i < ALL.length-1; i++) {
			if (ALL[i].bp == this.bp)
				return ALL[i+1];
		}

		return null;
	}

	public OldSentryBreakPoint prev() {
		for (int i = 1; i < ALL.length; i++) {
			if (ALL[i].bp == this.bp)
				return ALL[i-1];
		}

		return null;
	}
}
