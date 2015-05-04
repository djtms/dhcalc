package com.dawg6.web.dhcalc.shared.calculator;

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
			return Double.compare(o1.getHatred(data), o2.getHatred(data));
		}
	}

	private ActiveSkill skill;
	private Rune rune;
	@JsonIgnore
	private String text;

	public SkillAndRune() {
	}

	public double getHatred(CharacterData data) {
		
		double h = 0.0;
		
		if ((skill == ActiveSkill.CHAK) && data.isSpines()) {
			h = data.getSpinesHatred();
		} else if ((skill == ActiveSkill.FoK) && (rune == Rune.Knives_Expert)) {
			h = -30.0;
		} else if ((skill == ActiveSkill.EA) && data.isKridershot()) {
			h = data.getKridershotHatred();
		} else {
			double hatred = skill.getHatred() + rune.getHatred();
			
			if (hatred < 0) {
				double rcr = data.getRcr();
				
				if (data.isCindercoat()) {
					DamageType type = DamageFunction.getDamageType(this);
					
					if (type == DamageType.Fire) {
						rcr = 1.0 - ((1.0 - rcr) * (1.0 - data.getCindercoatRCR()));
					}
				}
				
				if ((hatred < 0) && (rcr > 0.0)) {
					hatred = hatred * (1.0 - rcr);
				}
				
			}
			
			if ((skill.getSkillType() == SkillType.Primary) && data.isNightStalker()) {
				hatred += 4;
			}

			h = hatred;
		}
		
		if ((h > 0) && data.isHexingPants()) {
			h = h +  (h * data.getHexingPantsUptime() * .25) -
					(h * (1.0 - data.getHexingPantsUptime()) * data.getHexingPantsPercent());
		}
		
		return h;
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
