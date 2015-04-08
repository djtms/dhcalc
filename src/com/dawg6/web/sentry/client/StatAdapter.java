package com.dawg6.web.sentry.client;

import java.util.Set;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.DamageType;

public abstract class StatAdapter {

	public abstract void apply(CharacterData data);

	public boolean test(CharacterData data, Set<DamageType> types) {
		return true;
	}

	public static class ElementalDamage extends StatAdapter {

		private final DamageType type;

		public ElementalDamage(DamageType type) {
			this.type = type;
		}

		@Override
		public void apply(CharacterData data) {
			double value = data.getElementalDamage(type);
			data.getElementalDamage().put(type, value + 0.01);

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return types.contains(type);
		}
	}

	public static class SkillDamage extends StatAdapter {

		private final ActiveSkill type;

		public SkillDamage(ActiveSkill type) {
			this.type = type;
		}

		@Override
		public void apply(CharacterData data) {
			double value = data.getSkillDamage(type);
			data.getSkillDamage().put(type, value + 0.01);

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getSkills().containsKey(type);
		}
	}
}
