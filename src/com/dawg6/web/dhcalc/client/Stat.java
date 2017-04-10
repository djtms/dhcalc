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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.client;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.DamageType;
import com.dawg6.web.dhcalc.shared.calculator.GemSkill;
import com.dawg6.web.dhcalc.shared.calculator.ProfileHelper;

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
			data.setEquipmentDexterity(value + (int) Math.round(inc));

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
			data.setRcr(1.0 - ((1.0 - value) * (1.0 - (inc / 100.0))));

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
			data.setCdr(1.0 - ((1.0 - value) * (1.0 - (inc / 100.0))));

		}

	}),

	new Stat("% IAS (Equipment)", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getEquipIas();
			data.setEquipIas(value + (inc / 100.0));
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("% IAS (MH Weapon)", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getWeaponIas();
			data.setWeaponIas(value + (inc / 100.0));
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("% IAS (OH Weapon)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getOffHand_weaponType() != null;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getOffHand_weaponIas();
			data.setOffHand_weaponIas(value + (inc / 100.0));
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("IAS (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonIAS() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonIAS() + (int)Math.round(inc)));
			data.setParagonIAS(value);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("CHD (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonCHD() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonCHD() + (int)Math.round(inc)));
			data.setParagonCHD(value);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("CC (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonCC() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonCC() + (int)Math.round(inc)));
			data.setParagonCC(value);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("CDR (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonCDR() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonCDR() + (int)Math.round(inc)));
			data.setParagonCDR(value);
			ProfileHelper.updateCdr(data);
		}
	}),

	new Stat("RCR (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonCDR() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonRCR() + (int)Math.round(inc)));
			data.setParagonRCR(value);
			ProfileHelper.updateRcr(data);
		}
	}),

	new Stat("Dex (Paragon Points)", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0,data.getParagonDexterity() + (int)Math.round(inc));
			data.setParagonDexterity(value);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),


	new Stat("Hatred (Paragon Points)", new StatAdapter() {

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getParagonHatred() < 50;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			int value = Math.max(0, Math.min(50, data.getParagonHatred() + (int)Math.round(inc)));
			data.setParagonHatred(value);
		}
	}),

	new Stat("Main Hand Min Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getAddMin();
			data.setAddMin(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Main Hand Max Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getAddMax();
			data.setAddMax(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Jewelry Min Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getJewelryMin();
			data.setJewelryMin(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Jewelry Max Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}

		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getJewelryMax();
			data.setJewelryMax(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Off Hand Min Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getOffHand_weaponType() != null;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getOffHand_addMin();
			data.setOffHand_addMin(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Off Hand Max Damage", new StatAdapter() {

		@Override
		public double getDefaultValue() {
			return 50.0;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {
			return data.getOffHand_weaponType() != null;
		}
		
		@Override
		public void apply(double inc, CharacterData data) {
			double value = data.getOffHand_addMax();
			data.setOffHand_addMax(value + inc);
			ProfileHelper.updateWeaponDamage(data);
		}
	}),

	new Stat("Discipline", new StatAdapter() {

		@Override
		public void apply(double inc, CharacterData data) {
			int value = data.getEquipmentDiscipline();
			data.setEquipmentDiscipline(value + (int) Math.round(inc));
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
			data.setNumHealthGlobes(value + (int) Math.round(inc));

		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types) {

			return (data.isReapersWraps() || data.isBloodVengeance())
					&& data.hasSpender();
		}
	}),

	}

	;

	public static Stat ELITE = new Stat("% Elite Damage", new StatAdapter() {

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
					+ ActiveSkill.values().length + DamageType.values().length + GemSkill.values().length
					+ 1);

			for (Stat s : fixed)
				values.add(s);

			values.add(ELITE);

			for (GemSkill gem : GemSkill.values()) {
				values.add(new Stat(gem.getDisplayName() + " Gem Level",
						new StatAdapter.GemLevel(gem)));
			}
			
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
