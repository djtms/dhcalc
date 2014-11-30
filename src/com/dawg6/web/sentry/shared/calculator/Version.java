package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Version  implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PREFIX = "Version:";
	
	public static int VERSION_MAJOR = 1;
	public static int VERSION_MINOR = 3;
	public static String VERSION_BUILD = ".r";
	public static String VERSION_DATE = "11/29/2014";
	
	public int major;
	public int minor;
	public String build;
	
	public Version() { }
	
	public Version(int major, int minor, String build) {
		this.major = major;
		this.minor = minor;
		this.build = build;
	}

	public static String getVersionString() {
		return VERSION_MAJOR + "." + VERSION_MINOR + VERSION_BUILD + " (" + VERSION_DATE + ")";
	}

	public static String getShortVersionString() {
		return VERSION_MAJOR + "." + VERSION_MINOR + VERSION_BUILD;
	}
	
	public static Version getVersion() {
		return new Version(VERSION_MAJOR, VERSION_MINOR, VERSION_BUILD);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((build == null) ? 0 : build.hashCode());
		result = prime * result + major;
		result = prime * result + minor;
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
		Version other = (Version) obj;
		if (build == null) {
			if (other.build != null)
				return false;
		} else if (!build.equals(other.build))
			return false;
		if (major != other.major)
			return false;
		if (minor != other.minor)
			return false;
		return true;
	}

	@JsonIgnore 
	public String getPrefixString() {
		return PREFIX + toString();
	}
	
	@Override
	public String toString() {
		return major + "." + minor + build;
	}
}
