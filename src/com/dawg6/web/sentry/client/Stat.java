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

	new Stat("Dexterity", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 100.0;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getEquipmentDexterity();
			data.setEquipmentDexterity(value + (int)Math.round(inc));

		}
	}),

	new Stat("% Crit Chance", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getCritChance();
			data.setCritChance(value + (inc / 100.0));

		}
	}),

	new Stat("% Crit Hit Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 5.0;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getCritHitDamage();
			data.setCritHitDamage(value + (inc / 100.0));

		}
	}),

	new Stat("% Raw RCR", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 5.0;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getRcr();
			data.setRcr(1.0 - ((1.0 - value) * (1.0 - (inc/100.0))));

		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.hasSpender();
		}
	}),

	new Stat("% Raw CDR", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 5.0;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getCdr();
			data.setCdr(1.0 - ((1.0 - value) * (1.0 - (inc/100.0))));

		}
		
	}),

	new Stat("% IAS", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getEquipIas();
			data.setEquipIas(value + (inc / 100.0));
			ProfileHelper.updateWeaponDamage(data);

		}
	}),

	new Stat("Discipline", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getEquipmentDiscipline();
			data.setEquipmentDiscipline(value + (int)Math.round(inc));
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.getNumUe() >= 6;
		}
	}),

	new Stat("Hatred per Second", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getHatredPerSecond();
			data.setHatredPerSecond(value + inc);

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.hasSpender();
		}
	}),

	new Stat("% Area Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 10.0;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getAreaDamageEquipment();
			data.setAreaDamageEquipment(value + (inc / 100.0));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return (data.getNumAdditional() > 0)
					&& (data.getTargetSpacing() <= 10);
		}
	}),

	new Stat("Health Globe", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getNumHealthGlobes();
			data.setNumHealthGlobes(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return (data.isReapersWraps() || data.isBloodVengeance()) && data.hasSpender();
		}
	}),

	new Stat("BoT Gem Level", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getBaneOfTheTrappedLevel();
			data.setBaneOfTheTrappedLevel(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.isUseBaneOfTheTrapped();
		}
	}),

	new Stat("Enforcer Gem Level", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getEnforcerLevel();
			data.setEnforcerLevel(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.isUseEnforcer()
					&& (data.isSentry() || data.isCompanion());
		}
	}),

	new Stat("Zei's Gem Level", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getZeisLevel();
			data.setZeisLevel(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.isZeis();
		}
	}),

	new Stat("Toxin Gem Level", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getToxinLevel();
			data.setToxinLevel(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.isToxin();
		}
	}),

	new Stat("Pain Enhancer Gem Level", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getPainEnhancerLevel();
			data.setPainEnhancerLevel(value + (int)Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return data.isPainEnhancer();
		}
	}), }

	;

	public static Stat ELITE = 	new Stat("% Elite Damage", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getEliteDamage();
			data.setEliteDamage(value + (inc / 100.0));

		}
	});


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
					+ ActiveSkill.values().length + DamageType.values().length + 1);

			for (Stat s : fixed)
				values.add(s);

			values.add(ELITE);
			
			for (DamageType t : DamageType.values()) {
				values.add(new Stat("% " + t.getLongName() + " Damage",
						new StatAdapter.ElementalDamage(t)));
			}

			for (ActiveSkill s : ActiveSkill.values()) {

				if (s.doesDamage() && (s != ActiveSkill.BOLT)) {
					values.add(new Stat("% " + s.getLongName() + " Damage",
							new StatAdapter.SkillDamage(s)));
				}
			}
		}

		return values;
	}

}
