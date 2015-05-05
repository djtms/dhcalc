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

import java.io.Serializable;

public class BuffHolder implements Serializable {

	private static final long serialVersionUID = -2853766306122780821L;

	private final Buff buff;
	private final double uptime;
	private double avail;
	private final double cd;
	private final double duration;

	public BuffHolder(Buff buff, double duration, double uptime) {
		this.buff = buff;
		this.duration = duration;
		this.uptime = uptime;

		if (uptime < 0) {
			this.cd = Double.MAX_VALUE;
			this.avail = 0;
		} else if (uptime > 0) {
			this.cd = duration / uptime;
			this.avail = 0;
		} else {
			this.cd = Double.MAX_VALUE;
			this.avail = Double.MAX_VALUE;
		}

	}

	public double getAvail() {
		return avail;
	}

	public void setAvail(double avail) {
		this.avail = avail;
	}

	public double getCd() {
		return cd;
	}

	public Buff getBuff() {
		return buff;
	}

	public double getUptime() {
		return uptime;
	}

	public double getDuration() {
		return duration;
	}

}
