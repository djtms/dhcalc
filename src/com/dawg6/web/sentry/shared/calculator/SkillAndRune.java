package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SkillAndRune implements Serializable, Comparable<SkillAndRune> {

	private static final long serialVersionUID = -2598597723188510677L;

	public static class HatredSorter implements Comparator<SkillAndRune> {

		private final CharacterData data;

		public HatredSorter(CharacterData data) {
			this.data = data;
		}
		
		@Override
		public int compare(SkillAndRune o1, SkillAndRune o2) {
			return new Integer(o1.getHatred(data)).compareTo(o2.getHatred(data));
		}
	}

	private ActiveSkill skill;
	private Rune rune;
	@JsonIgnore
	private String text;

	public SkillAndRune() {
	}

	public int getHatred(CharacterData data) {
		
		if ((skill == ActiveSkill.CHAK) && data.isSpines())
			return data.getSpinesHatred();
		if ((skill == ActiveSkill.EA) && data.isKridershot())
			return data.getKridershotHatred();
		else
			return skill.getHatred() + rune.getHatred();
	}
	
	public SkillAndRune(ActiveSkill skill, Rune rune) {
		this.skill = skill;
		this.rune = rune;
	}

	public ActiveSkill getSkill() {
		return skill;
	}

	public void setSkill(ActiveSkill skill) {
		this.skill = skill;
	}

	public Rune getRune() {
		return rune;
	}

	public void setRune(Rune rune) {
		this.rune = rune;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rune == null) ? 0 : rune.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
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
		SkillAndRune other = (SkillAndRune) obj;
		if (rune != other.rune)
			return false;
		if (skill != other.skill)
			return false;
		return true;
	}

	@Override
	public synchronized String toString() {

		if (text == null) {
			StringBuffer buf = new StringBuffer();

			if (skill != null) {
				buf.append(skill.getShortName());

				if (rune != null) {
					buf.append("/" + rune.getLongName());
				}
			}

			text = buf.toString();
		}

		return text;
	}

	@Override
	public int compareTo(SkillAndRune o) {
		return toString().compareTo(o.toString());
	}

	public static int compare(SkillAndRune s1, SkillAndRune s2) {
		if (s1 == s2)
			return 0;
		else if (s1 == null)
			return -1;
		else if (s2 == null)
			return 1;
		else {
			int d = s1.skill.compareTo(s2.skill);

			if (d != 0)
				return d;
			else
				return s1.rune.compareTo(s2.rune);
		}
	}
}
