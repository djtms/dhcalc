package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class DamageSource implements Serializable, Comparable<DamageSource> {

	private static final long serialVersionUID = 1L;

	public ActiveSkill skill;

	public Rune rune;
	public GemSkill gem;

	public DamageSource() { }
	
	public DamageSource(ActiveSkill skill, Rune rune) {
		super();
		this.skill = skill;
		this.rune = rune;
	}

	public DamageSource(GemSkill gem) {
		this.gem = gem;
	}
	
	public boolean test(DamageSource source,
			CharacterData data) {

		if (this.skill != null) {
			return (source != null) && (this.skill == source.skill) && (this.rune == source.rune);
		} else {
			return (source == null) && (gem.getScalar(data) > 0.0);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gem == null) ? 0 : gem.hashCode());
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
		DamageSource other = (DamageSource) obj;
		if (gem != other.gem)
			return false;
		if (rune != other.rune)
			return false;
		if (skill != other.skill)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (skill != null) {
			return skill.name() + ((rune != null) ? ("." + rune.name()) : "");
		} else {
			return gem.name();
		}
	}

	@Override
	public int compareTo(DamageSource o) {
		return toString().compareTo(o.toString());
	}
}
