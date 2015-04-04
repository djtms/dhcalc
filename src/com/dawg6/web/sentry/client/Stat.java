package com.dawg6.web.sentry.client;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.DamageType;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;

public class Stat {

	private static final Stat[] fixed = {

	new Stat("+10 Dexterity", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getEquipmentDexterity();
			data.setEquipmentDexterity(value + 10);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEquipmentDexterity((Integer) token);
		}
	}),

	new Stat("+1% Crit Chance", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getCritChance();
			data.setCritChance(value + 0.01);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setCritChance((Double) token);
		}
	}),

	new Stat("+10% Crit Hit Damage", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getCritHitDamage();
			data.setCritHitDamage(value + 0.1);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setCritHitDamage((Double) token);
		}
	}),

	new Stat("+1% Elite Damage", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEliteDamage();
			data.setEliteDamage(value + 0.01);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEliteDamage((Double) token);
		}
	}),

	new Stat("+8% Raw RCR", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getRcr();
			data.setRcr(1.0 - ((1.0 - value) * 0.92));

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setRcr((Double) token);
		}
	}),

	new Stat("+1% IAS", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEquipIas();
			data.setEquipIas(value + 0.01);
			ProfileHelper.updateWeaponDamage(data);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEquipIas((Double) token);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("+1 Hatred per Second", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getHatredPerSecond();
			data.setHatredPerSecond(value + 1.0);

			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setHatredPerSecond((Double) token);
		}
	}),

	new Stat("+1% Area Damage", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			double value = data.getAreaDamageEquipment();
			data.setAreaDamageEquipment(value + 0.01);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return (data.getNumAdditional() > 0)
					&& (data.getTargetSpacing() <= 10);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setAreaDamageEquipment((Double) token);
		}
	}),

	new Stat("+1 Health Globe", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getNumHealthGlobes();
			data.setNumHealthGlobes(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isReapersWraps() || data.isBloodVengeance();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setNumHealthGlobes((Integer) token);
		}
	}),

	new Stat("+1 BoT Gem Level", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getBaneOfTheTrappedLevel();
			data.setBaneOfTheTrappedLevel(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isUseBaneOfTheTrapped();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setBaneOfTheTrappedLevel((Integer) token);
		}
	}),

	new Stat("+1 Enforcer Gem Level", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getEnforcerLevel();
			data.setEnforcerLevel(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isUseEnforcer()
					&& (data.isSentry() || data.isCompanion());
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEnforcerLevel((Integer) token);
		}
	}),

	new Stat("+1 Zei's Gem Level", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getZeisLevel();
			data.setZeisLevel(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isZeis();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setZeisLevel((Integer) token);
		}
	}),

	new Stat("+1 Toxin Gem Level", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getToxinLevel();
			data.setToxinLevel(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isToxin();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setToxinLevel((Integer) token);
		}
	}),

	new Stat("+1 Pain Enhancer Gem Level", new StatAdapter() {

		@Override
		public Object apply(CharacterData data) {
			int value = data.getPainEnhancerLevel();
			data.setPainEnhancerLevel(value + 1);

			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
				
			return data.isPainEnhancer();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setPainEnhancerLevel((Integer) token);
		}
	}), }

	;

	private final String label;
	private final StatAdapter adapter;

	private Stat(String label, StatAdapter adapter) {
		this.label = label;
		this.adapter = adapter;
	}

	public String getLabel() {
		return label;
	}

	public StatAdapter getAdapter() {
		return adapter;
	}

	@Override
	public String toString() {
		return label;
	}

	private static List<Stat> values;

	public static List<Stat> values() {

		if (values == null) {
			values = new Vector<Stat>(fixed.length
					+ ActiveSkill.values().length + DamageType.values().length);

			for (Stat s : fixed)
				values.add(s);

			for (DamageType t : DamageType.values()) {
				values.add(new Stat("+1% " + t.getLongName() + " Damage",
						new StatAdapter.ElementalDamage(t)));
			}

			for (ActiveSkill s : ActiveSkill.values()) {

				if (s.doesDamage() && (s != ActiveSkill.BOLT)) {
					values.add(new Stat("+1% " + s.getLongName() + " Damage",
							new StatAdapter.SkillDamage(s)));
				}
			}
		}

		return values;
	}

}
