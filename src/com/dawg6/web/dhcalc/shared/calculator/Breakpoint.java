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
import java.util.List;
import java.util.Vector;

public class Breakpoint implements Serializable {

	private static final long serialVersionUID = -1270467883361387233L;

	private int frames;
	private double fpa;
	private Data[] data;
	
	protected Breakpoint() { 
		
	}
	
	public Breakpoint(int frames) {
		this.frames = frames;
		this.fpa = Math.round(60.0 * 10000.0 * (frames - 1) / frames) / 10000.0;
		
		int f = (int)Math.floor(fpa);
		
		List<Data> list = new Vector<Data>(f);
		
		int bp = 0;
		
		while (f > 0) {
			list.add(new Data(bp++, fpa, f--));
		}
		
		this.data = list.toArray(new Data[0]);
	}

	public static class Data implements Serializable {

		public Data(int bp, double baseFps, int fpa) {
			this.bp = bp;
			this.fpa = fpa;
			this.minAps = Math.round(baseFps * 10000.0 / fpa) / 10000.0;
			this.actualAps = Math.round(60.0 * 10000.0 / fpa) / 10000.0;
			this.interval = 1.0 / actualAps;
		}
		
		private static final long serialVersionUID = -2699075881100161224L;

		public int bp;
		public int fpa;
		public double minAps;
		public double actualAps;
		public double interval;
	}

	public double getFpa() {
		return fpa;
	}

	public Data[] getData() {
		return data;
	}

	public int getFrames() {
		return frames;
	}

	public Data get(double aps) {
		Data prev = data[0];
		
		for (Data d : data) {
			if (aps >= d.minAps)
				prev = d;
			else
				break;
		}
		
		return prev;
	}

	public Data next(Data d) {
		
		if ((d.bp + 1) < data.length)
			return data[d.bp + 1];
		else
			return d;
	}

	public Data prev(Data d) {
		if (d.bp > 0)
			return data[d.bp - 1];
		else
			return d;
	}
}
