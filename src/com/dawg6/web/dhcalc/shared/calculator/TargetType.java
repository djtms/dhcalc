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

public class TargetType implements Serializable, Comparable<TargetType> {
	
	private static final long serialVersionUID = 319119745151783609L;

	public static final TargetType Primary = new TargetType(true);
	
	public static TargetType Additional(int targetNumber) {
		return new TargetType(false, targetNumber);
	}

	private boolean primary;
	private int targetNumber;

	public TargetType() {
		this(true, 0);
	}
	
	public TargetType(boolean primary) {
		this(primary, 0);
	}
	
	public TargetType(boolean primary, int targetNumber) {
		this.primary = primary;
		this.targetNumber = targetNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (primary ? 1231 : 1237);
		result = prime * result + targetNumber;
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
		TargetType other = (TargetType) obj;
		if (primary != other.primary)
			return false;
		if (targetNumber != other.targetNumber)
			return false;
		return true;
	}

	public boolean isPrimary() {
		return primary;
	}

	public int getTargetNumber() {
		return targetNumber;
	}
	
	@Override
	public String toString() {
		return primary ? "Primary" :
			("Add#" + targetNumber);
	}

	protected void setPrimary(boolean primary) {
		this.primary = primary;
	}

	protected void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}

	@Override
	public int compareTo(TargetType o) {
		if (this.primary)
			if (o.primary)
				return 0;
			else
				return -1;
		else if (o.primary)
			return 1;
		else
			return Integer.compare(this.targetNumber, o.targetNumber);
	}
}
