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
