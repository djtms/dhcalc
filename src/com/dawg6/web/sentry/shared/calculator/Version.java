package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Version  implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PREFIX = "Version:";
	
	public static String VERSION = "2.1.2-h";
	public static String VERSION_DATE = "03/10/2015";
	
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
