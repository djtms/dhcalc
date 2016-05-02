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
package com.dawg6.web.dhcalc.shared.calculator;

public class DamageRow {

	public final DamageMultiplierList multipliers = new DamageMultiplierList();
	private final double scalar;
	public final boolean primary;
	public final int maxAdditional;
	public final int numProjectiles;
	public final DamageSource source;
	public final String note;
	public final DamageType type;
	public final int radius;
	public final double dotDuration;
	public final boolean dot;
	
	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, 0, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles,  String note,
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar, double dotDuration,
			boolean primary, int maxAdditional, int numProjectiles,  String note,
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, dotDuration, primary, maxAdditional, numProjectiles, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, int radius,
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, numProjectiles, radius, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, 1, 0, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar, double duration,
			boolean primary, int maxAdditional, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, duration, primary, maxAdditional, 1, 0, "", type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar, double dotDuration,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(skill, rune, scalar, dotDuration, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(source, scalar, 0.0, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, int radius,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(source, scalar, 0.0, primary, maxAdditional, numProjectiles, radius, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar, double dotDuration,
			boolean primary, int maxAdditional,  String note, 
			DamageType type, DamageMultiplier... multipliers) {
		this(source, scalar, dotDuration, primary, maxAdditional, 1, 0, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar,
			boolean primary, int maxAdditional, int numProjectiles, int radius, String note, DamageType type,
			DamageMultiplier... multipliers) {
		this(new DamageSource(skill, rune), scalar, 0.0, primary, maxAdditional, numProjectiles, radius, note, type, multipliers);
	}

	public DamageRow(ActiveSkill skill, Rune rune, double scalar, double dotDuration,
			boolean primary, int maxAdditional, int numProjectiles, int radius, String note, DamageType type,
			DamageMultiplier... multipliers) {
		this(new DamageSource(skill, rune), scalar, dotDuration, primary, maxAdditional, numProjectiles, radius, note, type, multipliers);
	}

	public DamageRow(DamageSource source, double scalar, double dotDuration,
				boolean primary, int maxAdditional, int numProjectiles, int radius, String note, DamageType type,
				DamageMultiplier... multipliers) {
		this.source = source;
		this.numProjectiles = numProjectiles;
		this.radius = radius;
		this.dotDuration = dotDuration;
		this.dot = dotDuration > 0.0;

		// Sentry damage and enforcer won't effect gem procs
		if (source.skill != null) {

			if (source.skill == ActiveSkill.BOLT) {
				// Sentry Skill damage is additive for sentry bolts and rockets
				this.multipliers.add(DamageMultiplier.Sentry);
			} else if (source.skill != ActiveSkill.Companion) {
				// Sentry Skill damage is multiplicative for skills fired by M4
				this.multipliers.add(DamageMultiplier.SentryM4);
			}
			
			// Spitfire Turret's rockets don't get Enforce bonus?
			if ((source.skill != ActiveSkill.SENTRY) || (source.rune != Rune.Spitfire_Turret)
					|| !new DamageMultiplierList(multipliers).contains(DamageMultiplier.Rockets)) {
				this.multipliers.add(DamageMultiplier.Enforcer);
			}
		}
		
		this.multipliers.add(DamageMultiplier.CtW);
		this.multipliers.add(DamageMultiplier.BoT);
		this.multipliers.add(DamageMultiplier.BotP);
		this.multipliers.add(DamageMultiplier.BotS);
		this.multipliers.add(DamageMultiplier.BotS25);
		this.multipliers.add(DamageMultiplier.OdysseysEnd);
		this.multipliers.add(DamageMultiplier.Wolf);
		this.multipliers.add(DamageMultiplier.Bbv);
		this.multipliers.add(DamageMultiplier.TW);
		this.multipliers.add(DamageMultiplier.Paranoia);
		this.multipliers.add(DamageMultiplier.Piranhas);
		this.multipliers.add(DamageMultiplier.InnerSanctuary);
		this.multipliers.add(DamageMultiplier.CripplingWave);
		this.multipliers.add(DamageMultiplier.Conviction);
		this.multipliers.add(type.getMultiplier());
		this.multipliers.add(DamageMultiplier.Dexterity);
		this.multipliers.add(DamageMultiplier.MfD);
		this.multipliers.add(DamageMultiplier.Vaxo);
		this.multipliers.add(DamageMultiplier.Calamity);
		this.multipliers.add(DamageMultiplier.Toxicity);
		this.multipliers.add(DamageMultiplier.BW1);
		this.multipliers.add(DamageMultiplier.BW2);
		this.multipliers.add(DamageMultiplier.UE4);
		this.multipliers.add(DamageMultiplier.COE);
		this.multipliers.add(DamageMultiplier.EW);

		if ((source.skill != null) && (source.skill.getSkillType() == SkillType.Primary)) {
			this.multipliers.add(DamageMultiplier.Simplicity);
			this.multipliers.add(DamageMultiplier.HW);
			this.multipliers.add(DamageMultiplier.DD);
		}
		
		if ((source.skill == ActiveSkill.RoV) || (source.skill == ActiveSkill.CR)) {
			this.multipliers.add(DamageMultiplier.N4);
			this.multipliers.add(DamageMultiplier.RoVN6);
		} else {
			this.multipliers.add(DamageMultiplier.N6);
		}
		
		if (source.skill == ActiveSkill.CHAK) {
			this.multipliers.add(DamageMultiplier.ILLWILL);
		}
		
		if (source.skill != ActiveSkill.Companion) {
			this.multipliers.add(DamageMultiplier.Taeguk);
			this.multipliers.add(DamageMultiplier.Harrington);
			this.multipliers.add(DamageMultiplier.Strongarm);
			this.multipliers.add(DamageMultiplier.ArcheryDamage);
			this.multipliers.add(DamageMultiplier.Hysteria);
			this.multipliers.add(DamageMultiplier.HexingPants);

			if (source.skill != ActiveSkill.ST) {
				this.multipliers.add(DamageMultiplier.Zeis);
				this.multipliers.add(DamageMultiplier.SteadyAim);
			}
			
		} else {
			this.multipliers.add(DamageMultiplier.Enforcer);
			this.multipliers.add(DamageMultiplier.APS);
			this.multipliers.add(DamageMultiplier.IAS);
			this.multipliers.add(DamageMultiplier.Companion);
		}
		
		if (source.skill != null) {
			if (source.skill.getDamageMultiplier() != null)
				this.multipliers.add(source.skill.getDamageMultiplier());
		}

		this.multipliers.add(DamageMultiplier.Elite);
		this.multipliers.addAll(multipliers);

		this.type = type;
		this.scalar = scalar;
		this.primary = primary;
		this.maxAdditional = maxAdditional;
		this.note = note;
	}
	
	public double getScalar(CharacterData data) {
		return this.scalar;
	}
}