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

public class BuffState implements Serializable {

	private static final long serialVersionUID = 6150784391766289996L;

	private Buff buff;
	private boolean active;
	private double expires;

	public BuffState() {
	}

	public BuffState(Buff buff) {
		this.buff = buff;
		this.active = false;
		this.expires = 0.0;
	}

	public Buff getBuff() {
		return buff;
	}

	public void setBuff(Buff buff) {
		this.buff = buff;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getExpires() {
		return expires;
	}

	public void setExpires(double expires) {
		this.expires = expires;
	}

	public void expire(double time) {
		if (active && (time > expires))
			active = false;
	}
}
