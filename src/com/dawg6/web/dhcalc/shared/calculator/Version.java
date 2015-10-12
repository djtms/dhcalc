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

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Version  implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PREFIX = "Version:";
	
	public static String VERSION = "2.3-151012.1";
	public static String VERSION_DATE = "10/12/15";
	
	public String version;
	
	public Version() { }
	
	public Version(String version) {
		this.version = version;
	}

	public static String getVersionString() {
		return VERSION + " (" + VERSION_DATE + ")";
	}

	public static String getShortVersionString() {
		return VERSION;
	}
	
	public static Version getVersion() {
		return new Version(VERSION);
	}

	@Override
	public int hashCode() {
		return version.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;

		return true;
	}

	@JsonIgnore 
	public String getPrefixString() {
		return PREFIX + toString();
	}
	
	@Override
	public String toString() {
		return version;
	}

}
