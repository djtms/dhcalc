package com.dawg6.web.sentry.client;

import java.util.Set;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.DamageType;

public abstract class StatAdapter {

	public abstract Object apply(CharacterData data);
	
	public abstract void unapply(CharacterData data, Object token);
	
	public boolean test(CharacterData data, Set<DamageType> types) {
		return true;
	}
	
	
	public static class ElementalDamage extends StatAdapter {

		private final DamageType type;

		public ElementalDamage(DamageType type) {
			this.type = type;
		}
		
		@Override
		public Object apply(CharacterData data) {
			double value = data.getElementalDamage(type);
			data.getElementalDamage().put(type, value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return types.contains(type);
		}
		
		@Override
		public void unapply(CharacterData data, Object token) {
			data.getElementalDamage().put(type, (Double)token);
		}
		
	}
	
	public static class SkillDamage extends StatAdapter {

		private final ActiveSkill type;

		public SkillDamage(ActiveSkill type) {
			this.type = type;
		}
		
		@Override
		public Object apply(CharacterData data) {
			double value = data.getSkillDamage(type);
			data.getSkillDamage().put(type, value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getSkills().containsKey(type);
		}
		
		@Override
		public void unapply(CharacterData data, Object token) {
			data.getSkillDamage().put(type, (Double)token);
		}
		
	}}
