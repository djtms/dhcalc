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
package com.dawg6.web.dhcalc.client;

import java.util.Set;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.dawg6.web.dhcalc.shared.calculator.GemAttributeData;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;

public abstract class StatAdapter {

	public abstract void apply(double inc, CharacterData data);

	public double getDefaultValue() {
		return 1.0;
	}
	
	public boolean test(CharacterData data, Set<DamageType> types) {
		return true;
	}

	public static class ElementalDamage extends StatAdapter {

		private final DamageType type;

		public ElementalDamage(DamageType type) {
			this.type = type;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getElementalDamage(type);
			data.getElementalDamage().put(type, value + (inc / 100.0));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return types.contains(type);
		}
	}

	public static class GemLevel extends StatAdapter {

		private final GemSkill gem;

		public GemLevel(GemSkill gem) {
			this.gem = gem;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			GemAttributeData gd = data.getGems().get(gem);
			
			if (gd != null) {
				gd.level++;
			}
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getGems().containsKey(gem);
		}
	}

	public static class SkillDamage extends StatAdapter {

		private final ActiveSkill type;

		public SkillDamage(ActiveSkill type) {
			this.type = type;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getSkillDamage(type);
			data.getSkillDamage().put(type, value + (inc / 100.0));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getSkills().containsKey(type);
		}
	}
}
