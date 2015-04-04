package com.dawg6.web.sentry.shared.calculator;

import java.util.Map;

public class Build extends JsonObject  implements Comparable<Build> {

	private static final long serialVersionUID = 4903862888670789419L;

	private Map<ActiveSkill, Rune> skills;
	
	public Build() { }

	public Map<ActiveSkill, Rune> getSkills() {
		return skills;
	}

	public void setSkills(Map<ActiveSkill, Rune> skills) {
		this.skills = skills;
	}

	@Override
	public int compareTo(Build o) {

		if (skills.equals(o.skills)) {
			return 0;
		} else {
			return toString().compareTo(o.toString());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
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
		Build other = (Build) obj;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}
}
