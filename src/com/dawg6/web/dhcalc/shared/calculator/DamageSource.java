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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.shared.calculator;

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
			SimulationState state, int radius) {

		if (this.skill != null) {
			if ((source != null) && (this.skill == source.skill) && (this.rune == source.rune)) {

				if (this.skill == ActiveSkill.FoK) {
					return state.getData().getDistanceToTarget() <= radius;
				} else {
					return true;
				}
				
			} else {
				return false;
			}
		} else {
			return (source == null) && (gem.getScalar(state) > 0.0);
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
