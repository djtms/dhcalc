package com.dawg6.web.sentry.shared.calculator;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Build extends JsonObject  implements Comparable<Build> {

	private static final long serialVersionUID = 4903862888670789419L;

	private Rune sentryRune;
	private Set<SkillAndRune> skills;
	@JsonIgnore
	private SkillAndRune[] array;
	@JsonIgnore
	private String text;
	
	public Build() { }

	public Rune getSentryRune() {
		return sentryRune;
	}

	public void setSentryRune(Rune sentryRune) {
		this.sentryRune = sentryRune;
	}

	public Set<SkillAndRune> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillAndRune> skills) {
		this.skills = skills;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sentryRune == null) ? 0 : sentryRune.hashCode());
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
		if (sentryRune != other.sentryRune)
			return false;
		if (skills == null) {
			if (other.skills != null)
				return false;
		} else if (!skills.equals(other.skills))
			return false;
		return true;
	}
	
	@Override
	public synchronized String toString() {
		
		if (text == null) {
			StringBuffer buf = new StringBuffer();
	
			if (sentryRune != null) {
				buf.append(ActiveSkill.SENTRY.getShortName() + "/");
				buf.append(sentryRune.getLongName() + ", ");
			}
			
			boolean first = true;
			
			for (SkillAndRune s : skills) {
				if (first)
					first = false;
				else
					buf.append(", ");
				
				buf.append(s.toString());
			}
			
			text = buf.toString();
		}
		
		return text;
	}

	public SkillAndRune getSkill(int index) {
		
		if (index < 0)
			return null;
		
		SkillAndRune[] array = getSkillsAsArray();
		
		if (index < array.length)
			return array[index];
		else
			return null;
	}

	@JsonIgnore
	public synchronized SkillAndRune[] getSkillsAsArray() {
		
		if (array == null)
			array = new TreeSet<SkillAndRune>(skills).toArray(new SkillAndRune[0]);
		
		return array;
	}
	
	@Override
	public int compareTo(Build o) {
		
		int d = sentryRune.compareTo(o.sentryRune);
		
		if (d != 0)
			return d;
		
		for (int i = 0; i < 3; i++) {
			SkillAndRune s1 = getSkill(i);
			SkillAndRune s2 = o.getSkill(i);
			
			d = SkillAndRune.compare(s1, s2);
			
			if (d != 0)
				return d;
		}
		
		return 0;
	}

	public Rune getRune(ActiveSkill s) {
		for (SkillAndRune sr : skills) {
			if (sr.getSkill() == s)
				return sr.getRune();
		}
		
		return null;
	}
}
