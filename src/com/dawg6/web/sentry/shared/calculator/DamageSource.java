package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;

public class DamageSource implements Serializable, Comparable<DamageSource> {

	private static final long serialVersionUID = 1L;

	public ActiveSkill skill;

	public Rune rune;
	public GemSkill gem;
	private Double random;

	public DamageSource() { }
	
	public static DamageSource Random() {
		return new DamageSource(Math.random());
	}
	
	private DamageSource(double random) {
		this.random = random;
	}
	
	public DamageSource(ActiveSkill skill, Rune rune) {
		super();
		this.skill = skill;
		this.rune = rune;
	}

	public DamageSource(GemSkill gem) {
		this.gem = gem;
	}
	
	public boolean test(DamageSource source,
			CharacterData data, int radius) {

		if (this.skill != null) {
			if ((source != null) && (this.skill == source.skill) && (this.rune == source.rune)) {

				if (this.skill == ActiveSkill.FoK) {
					return data.getDistanceToTarget() <= radius;
				} else {
					return true;
				}
				
			} else {
				return false;
			}
		} else {
			return (source == null) && (gem.getScalar(data) > 0.0);
		}
	}

	@Override
	public String toString() {
		if (skill != null) {
			return skill.name() + ((rune != null) ? ("." + rune.name()) : "");
		} else if (gem != null) {
			return gem.name();
		} else {
			return String.valueOf(random);
		}
	}

	@Override
	public int compareTo(DamageSource o) {
		return toString().compareTo(o.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gem == null) ? 0 : gem.hashCode());
		result = prime * result + ((random == null) ? 0 : random.hashCode());
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
		if (random == null) {
			if (other.random != null)
				return false;
		} else if (!random.equals(other.random))
			return false;
		if (rune != other.rune)
			return false;
		if (skill != other.skill)
			return false;
		return true;
	}
}
