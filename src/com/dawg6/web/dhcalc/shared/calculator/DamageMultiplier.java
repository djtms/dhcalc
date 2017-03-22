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

public enum DamageMultiplier {
	WD("WD", DamageAccumulator.Multiplicative,
			"Average Main Hand Weapon Damage",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getWeaponDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getWeaponMaximumDamage();
				}
			}), MAXWD("MaxWD", DamageAccumulator.Multiplicative,
			"Maximum Main Hand Weapon Damage",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getWeaponMaximumDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getWeaponMaximumDamage();
				}
			}), OHWD("OHWD", DamageAccumulator.Multiplicative,
			"Average Off-Hand Weapon Damage",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getOffHand_weaponDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getOffHand_maxDamage();
				}
			}), HW("HW", DamageAccumulator.Multiplicative,
			"Hunter's Wrath (45%-60% for primary skills)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHuntersWrath() ? state.getData()
							.getHuntersWrathPercent() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill.getSkillType() == SkillType.Primary) && data
							.isHuntersWrath()) ? data.getHuntersWrathPercent()
							: 0.0;
				}

			}), Hellcat("HCB", DamageAccumulator.Multiplicative,
			"Hellcat Bounces (2-4 x 50% + 1 x 800%)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getHellcatDamageMultiplier();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return (!sentry && row.multipliers
							.contains(DamageMultiplier.Grenades)) ? data
							.getHellcatDamageMultiplier() : 0.0;
				}

			}), PC("PC", DamageAccumulator.Multiplicative,
			"Proc Coefficient (depends on skill)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getLastAttack().getProc();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.rune == Rune.None) ? row.source.skill
							.getProc() : row.source.rune.getProc();
				}

			}), LGF(
			"LGF",
			DamageAccumulator.Multiplicative,
			"Lord Greenstone's Fan bonus to Fan of Knives (+160-200%, per stack)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isLGF() ? (state.getLGFStacks() * state
							.getData().getLGFPercent()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill == ActiveSkill.FoK) && data
							.isLGF()) ? (data.getLGFPercent() * 30.0) : 0.0;
				}
			}), DD("DD", DamageAccumulator.Multiplicative,
			"Depth Diggers (80%-100% for primary skills)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isDepthDiggers() ? state.getData()
							.getDepthDiggersPercent() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill.getSkillType() == SkillType.Primary) && data
							.isDepthDiggers()) ? data.getDepthDiggersPercent()
							: 0.0;
				}

			}), NumGrenades("#G", DamageAccumulator.Multiplicative,
			"# of Grenades per Target", null), Fire("Fire",
			DamageAccumulator.ElementalAdditive, "Fire Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getFireDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Fire) ? data.getFireDamage()
							: 0.0;
				}

			}), Augustines("AP", DamageAccumulator.Multiplicative,
			"Augustine's Panacea Damage Bonus (200%-250%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isAugustinesPanacea() ? state
							.getData().getAugustinesPanaceaPercent() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return ((row.source.skill != ActiveSkill.EA)
							|| (row.source.rune == Rune.None) || (row.source.rune == Rune.Ball_Lightning)) ? 0.0
							: data.getAugustinesPanaceaPercent();
				}

			}), Cold("Cold", DamageAccumulator.ElementalAdditive,
			"Cold Elemental Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getColdDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Cold) ? data.getColdDamage()
							: 0.0;
				}

			}), Physical("Physical", DamageAccumulator.ElementalAdditive,
			"Physical Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getPhysDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Physical) ? data
							.getPhysDamage() : 0.0;
				}
			}), SharpShooter(
			"CCX",
			DamageAccumulator.Special,
			"Effective Crit Chance Bonus from Sharpshooter and/or Broken Promises",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState data) {
					return (data.getData().isSharpshooter() || data.getData()
							.isBrokenPromises()) ? data.getData()
							.getSharpshooterCC() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isSharpshooter() || data.isBrokenPromises()) ? 1.0
							: 0.0;
				}
			}), Lightning("Lightning", DamageAccumulator.ElementalAdditive,
			"Lightning Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getLightDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Lightning) ? data
							.getLightDamage() : 0.0;
				}
			}), Poison("Poison", DamageAccumulator.ElementalAdditive,
			"Poison Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getPoisonDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Poison) ? data
							.getPoisonDamage() : 0.0;
				}
			}), Holy("Holy", DamageAccumulator.ElementalAdditive,
			"Holy Elemental Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getHolyDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.type == DamageType.Holy) ? data.getHolyDamage()
							: 0.0;
				}
			}), Enforcer(
			"Enforcer",
			DamageAccumulator.Multiplicative,
			"Enforcer gem bonus (15% + 3%/rank pet damage). Does not apply to Sentry Spitfire rockets.",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isUseEnforcer() ? (0.15 + (state
							.getData().getEnforcerLevel() * 0.003)) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (((row.source.skill == ActiveSkill.Companion)
							|| (row.source.skill == ActiveSkill.SENTRY) || (row.source.skill == ActiveSkill.BOLT)) && data
							.isUseEnforcer()) ? (0.15 + (data
							.getEnforcerLevel() * 0.003)) : 0.0;
				}
			}), Zeis(
			"Zei's",
			DamageAccumulator.Multiplicative,
			"Zei's stone of vengeance gem bonus (4% + .08%/rank per 10 yards up to 50 yards).",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					if (!state.getData().isZeis())
						return 0.0;

					int d = Math.min(
							state.getData().getDistanceToTarget() / 10, 5);

					return d
							* (0.04 + (state.getData().getZeisLevel() * 0.0008));
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isZeis() && (row.source.skill != ActiveSkill.Companion)) ? (5 * (0.04 + (data
							.getZeisLevel() * 0.0008))) : 0.0;
				}
			}), Traps("Traps", DamageAccumulator.Multiplicative,
			"# of Other Traps (Spike Trap arcs)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					double n = state.getNumSpikeTraps();

					return (n > 1) ? (n - 2) : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return 0.0;
				}
			}), M4("M4", DamageAccumulator.Multiplicative,
			"M4 Damage Bonus for Sentries (400%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumMarauders() >= 4) ? 4.0 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (sentry && (data.getNumMarauders() >= 4)) ? 4.0
							: 0.0;
				}
			}), SentryM4("SentryM4", DamageAccumulator.Multiplicative,
			"Sentry Skill Damage Bonus for skills fired by M4",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSentryDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (sentry && (row.source.skill.getSkillType() == SkillType.Spender)) ? data
							.getSentryDamage() : 0.0;
				}
			}), Sentry("Sentry", DamageAccumulator.Additive,
			"Sentry Skill Damage Bonus for sentry bolts/rockets",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSentryDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (sentry && ((row.source.skill == ActiveSkill.SENTRY) || (row.source.skill == ActiveSkill.BOLT))) ? data
							.getSentryDamage() : 0.0;
				}
			}), COE("CoE", DamageAccumulator.Multiplicative,
			"Convention of Elements Bonus (150-200% while element is active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					return state.getData().isCoe() ? state.getData()
							.getCoePercent() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isCoe() ? data.getCoePercent() : 0.0;
				}
			}), BL(
			"BL",
			DamageAccumulator.Multiplicative,
			"Ball Lightning Damage Multiplier (varies based on target size and Meticulous Bolts %)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					double m = state.getData().getTargetSize().getHits() / 2.0;

					if (state.getData().isMeticulousBolts()) {
						double ratio = 1.0 / state.getData()
								.getMeticulousBoltsPercent();
						m *= ratio;
					}

					return m - 1.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					if ((row.source.skill != ActiveSkill.EA)
							|| (row.source.rune != Rune.Ball_Lightning)
							|| !data.isMeticulousBolts())
						return 0.0;

					double m = TargetSize.Large.getHits() / 2.0;
					double ratio = 1.0 / data.getMeticulousBoltsPercent();

					return (m * ratio) - 1.0;
				}
			}), Rockets("Ballistics", DamageAccumulator.Multiplicative,
			"Ballistics passive bonus (100% Rocket damage)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isBallistics() ? 1.0 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.multipliers.contains(Rockets) && data
							.isBallistics()) ? 1.0 : 0.0;
				}
			}), Grenades("Grenadier", DamageAccumulator.Multiplicative,
			"Grenadier passive bonus (10% to grenade damage)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isGrenadier() ? 0.1 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.multipliers.contains(Grenades) && data
							.isGrenadier()) ? 0.1 : 0.0;
				}
			}), SteadyAim("SteadyAim", DamageAccumulator.Multiplicative,
			"Steady Aim passive bonus (20% when no enemies within 10 yards)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isSteadyAim()) ? (0.2 * state
							.getData().getPercentAtLeast10Yards()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isSteadyAim() ? 0.2 : 0.0;
				}
			}), Strongarm("Strongarm", DamageAccumulator.Additive,
			"Strongarm Bracers bonus (20-30% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isStrongarm()) ? (state.getData()
							.getStrongarmPercent() * state.getData()
							.getStrongarmUptime()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isStrongarm() ? data.getStrongarmPercent()
							: 0.0;
				}
			}), Harrington("Harrington", DamageAccumulator.Additive,
			"Harrington Waistguard bonus (100-135% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isHarrington()) ? (state.getData()
							.getHarringtonPercent() * state.getData()
							.getHarringtonUptime()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isHarrington() ? data.getHarringtonPercent()
							: 0.0;
				}
			}), HexingPants(
			"HexingPants",
			DamageAccumulator.Additive,
			"Hexing Pants of Mr. Yan bonus (+ while moving, - while stationary)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHexingPants() ? ((0.25 * state
							.getData().getPercentMoving()) - (state.getData()
							.getHexingPantsPercent() * (1.0 - state.getData()
							.getPercentMoving()))) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isHexingPants() ? 0.25 : 0.0;
				}
			}), EW("EW", DamageAccumulator.Multiplicative,
			"Endless Walk (up to +100% while stationary)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isEndlessWalk() ? (1.0 * (1.0 - state
							.getData().getPercentMoving())) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isEndlessWalk() ? 1.0 : 0.0;
				}
			}), ArcheryDamage("Archery", DamageAccumulator.Additive,
			"Archery damage bonus (8% when using 2H Bow)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isArchery() && (state.getData()
							.getWeaponType() == WeaponType.Bow)) ? 0.08 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isArchery() && (data.getWeaponType() == WeaponType.Bow)) ? 0.08
							: 0.0;
				}
			}), UE4(
			"UE4",
			DamageAccumulator.Multiplicative,
			"Unhallowed Essence 4 item set bonus (60% if no enemies within 10 yards)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumUe() >= 4) ? (0.6 * state
							.getData().getPercentAtLeast10Yards()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.getNumUe() >= 4) ? 0.6 : 0.0;
				}
			}), UE6(
			"UE6",
			DamageAccumulator.Multiplicative,
			"Unhallowed Essence 6 item set bonus (40% per point of discipline)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumUe() >= 6) ? (0.4 * state
							.getDisc()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return ((data.getNumUe() >= 6)
							&& ((row.source.skill == ActiveSkill.MS) || ((row.source.skill == ActiveSkill.Vengeance) || (new SkillAndRune(
									row.source.skill, row.source.rune)
									.getHatred(data) > 0))) && !sentry) ? (0.4 * data
							.getMaxDiscipline()) : 0.0;
				}
			}), BW1("BWg", DamageAccumulator.Multiplicative,
			"Bastions of Will Generator Bonus (50%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BwGen) ? 0.5 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isBastions() ? 0.5 : 0.0;
				}
			}), BW2("BWs", DamageAccumulator.Multiplicative,
			"Bastions of Will Spender Bonus (50%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BwSpend) ? 0.5 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isBastions() ? 0.5 : 0.0;
				}
			}), Occulus("Oculus", DamageAccumulator.Multiplicative,
			"Oculus Ring Bonus(70-85% while Active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					boolean a = state.getData().isOculus();
					boolean b = state.getData().isFollowerOculus();
					boolean c = state.getData().isPartyOculus();

					if (!a && !b && !c)
						return 0.0;

					double t = 0.0;
					int n = 0;

					if (a) {
						t += (state.getData().getOculusPercent() * state
								.getData().getOculusUptime());
						n++;
					}

					if (b) {
						t += (state.getData().getFollowerOculusPercent() * state
								.getData().getFollowerOculusUptime());
						n++;
					}

					if (c) {
						t += (state.getData().getPartyOculusPercent() * state
								.getData().getPartyOculusUptime());
						n++;
					}

					return t / n;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return Math.max(data.isOculus() ? data.getOculusPercent()
							: 0.0, Math.max(
							data.isFollowerOculus() ? data
									.getFollowerOculusPercent() : 0.0,
							data.isPartyOculus() ? data.getPartyOculusPercent()
									: 0.0));

				}
			}), Dexterity("Dex", DamageAccumulator.Multiplicative,
			"Dexterity bonus (1% damage per dex)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getDexterity() / 100.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getDexterity() / 100.0;
				}
			}), CC("CC", DamageAccumulator.Special, "Chrit Chance bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCritChance();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getCritChance();
				}
			}), CHD("CHD", DamageAccumulator.Special, "Chrit Hit Damage bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCritHitDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getCritHitDamage();
				}
			}), OnCrit("OnCrit", DamageAccumulator.Special,
			"On Crit Only Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					// TODO
					return state.getData().getCritHitDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					// TODO
					return 0.0;
				}
			}), Hysteria("Hysteria", DamageAccumulator.Additive,
			"Scoundrel Hysteria Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHysteria() ? 0.03 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isHysteria() ? 0.03 : 0.0;
				}
			}), OdysseysEnd("OE", DamageAccumulator.Additive,
			"Odyssey's End Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.OdysseysEnd) ? state
							.getData().getOdysseysEndPercent() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isOdysseysEnd() ? data.getOdysseysEndPercent()
							: 0.0;
				}
			}), EA("EA", DamageAccumulator.Additive,
			"Elemental Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEaDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.EA) ? data
							.getEaDamage() : 0.0;
				}
			}), CA("CA", DamageAccumulator.Additive,
			"Cluster Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCaDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.CA) ? data
							.getCaDamage() : 0.0;
				}
			}), MS("MS", DamageAccumulator.Additive,
			"Multishot Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getMsDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.MS) ? data
							.getMsDamage() : 0.0;
				}
			}), IMP("Imp", DamageAccumulator.Additive,
			"Impale Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getImpDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.IMP) ? data
							.getImpDamage() : 0.0;
				}
			}), CHAK("Chak", DamageAccumulator.Additive,
			"Chakram Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getChakDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.CHAK) ? data
							.getChakDamage() : 0.0;
				}
			}), ILLWILL("IW", DamageAccumulator.Additive,
			"Sword of Ill Will Chakram Bonus (1.0-1.4% per point of Hatred)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isIllWill() ? (state.getData()
							.getIllWillPercent() * state.getHatred()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return ((row.source.skill == ActiveSkill.CHAK) && data
							.isIllWill()) ? (data.getIllWillPercent() * data
							.getMaxHatred()) : 0.0;
				}
			}), HA("HA", DamageAccumulator.Additive,
			"Hungering Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getHaDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.HA) ? data
							.getHaDamage() : 0.0;
				}
			}), Companion("Companion", DamageAccumulator.Additive,
			"Companion Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCompanionDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.Companion) ? data
							.getCompanionDamage() : 0.0;
				}
			}), Raven("Raven", DamageAccumulator.Multiplicative,
			"Companion/Raven Active Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.CompanionActive) ? 5.0
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return 0.0;
				}
			}), ES("ES", DamageAccumulator.Additive,
			"Entangling Shot Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEsDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.ES) ? data
							.getEsDamage() : 0.0;
				}
			}), BOLAS("Bolas", DamageAccumulator.Additive,
			"Bolas Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getBolasDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.BOLAS) ? data
							.getBolasDamage() : 0.0;
				}
			}), EF("EF", DamageAccumulator.Additive,
			"Evasive Fire Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEfDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.EF) ? data
							.getEfDamage() : 0.0;
				}
			}), GRENADE("Grenade", DamageAccumulator.Additive,
			"Grenade Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getGrenadeDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.GRENADE) ? data
							.getGrenadeDamage() : 0.0;
				}
			}), ST("ST", DamageAccumulator.Additive, "Spike Trap Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSpikeTrapDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.ST) ? data
							.getSpikeTrapDamage() : 0.0;
				}
			}), FoK("FoK", DamageAccumulator.Additive,
			"Fan of Knives Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getFokDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.FoK) ? data
							.getFokDamage() : 0.0;
				}
			}), Vengeance("Ven", DamageAccumulator.Additive,
			"Vengeance Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getVengeanceDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.Vengeance) ? data
							.getVengeanceDamage() : 0.0;
				}
			}), Strafe("Strafe", DamageAccumulator.Additive,
			"Strafe Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getStrafeDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.Strafe) ? data
							.getStrafeDamage() : 0.0;
				}
			}), RF("RF", DamageAccumulator.Additive,
			"Rapid Fire Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getRFDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.RF) ? data
							.getRFDamage() : 0.0;
				}
			}), RoV("RoV", DamageAccumulator.Additive,
			"Rain of Vengeance Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getRovDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (row.source.skill == ActiveSkill.RoV) ? data
							.getRovDamage() : 0.0;
				}
			}), CtW("CtW", DamageAccumulator.Multiplicative,
			"Cull the Weak passive bonus (20% to chilled/slowed)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isCullTheWeak() ? (0.2 * state
							.getData().getPercentSlowedChilled()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isCullTheWeak() ? 0.2 : 0.0;
				}
			}), M6("M6", DamageAccumulator.Multiplicative,
			"Marauder's 6 piece bonus (+1200% per Sentry)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumMarauders() >= 6) ? (state
							.getData().getNumSentries() * 12.0) : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (!sentry && (data.getNumMarauders() >= 6)) ? (data
							.getNumSentries() * 12.0) : 0.0;
				}
			}), S2("S2", DamageAccumulator.Multiplicative,
			"Shadow 2 piece bonus (+1200% if using Melee)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return ((state.getData().getNumShadow() >= 2)
							&& (state.getData().getWeaponType() != null) && state
							.getData().getWeaponType().isMelee()) ? 12.0 : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (!sentry
							&& (row.source.skill != ActiveSkill.Companion)
							&& (data.getNumShadow() >= 2)
							&& (data.getWeaponType() != null) && data
							.getWeaponType().isMelee()) ? 12.0 : 0.0;
				}
			}), S6(
			"S6",
			DamageAccumulator.Special,
			"Shadow 6 piece Impale damage bonus (+40,000% to Impale's first target)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumShadow() >= 6) ? 400.0 : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (!sentry && (row.source.skill == ActiveSkill.IMP) && (data
							.getNumShadow() >= 6)) ? 400.0 : 0.0;
				}
			}), VenBuff("VenBuff", DamageAccumulator.Multiplicative,
			"Vengeance damage buff (+40%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Vengeance) ? 0.4 : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return data.getSkills().containsKey(ActiveSkill.Vengeance) ? 0.4
							: 0.0;
				}
			}), LoN("LoN", DamageAccumulator.Multiplicative,
			"Legacy of Nightmares buff (+100% per ancient item)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return ((state.getData().getNumLoN() >= 2)
							&& (state.getData().getSetCounts().size() == 1) && !state
							.getData().isOtherSets()) ? (1.0 * state.getData()
							.getNumAncients()) : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((data.getNumLoN() >= 2)
							&& (data.getSetCounts().size() == 1) && !data
							.isOtherSets()) ? (1.0 * data.getNumAncients()) : 0;
				}
			}), N4("N4", DamageAccumulator.Multiplicative,
			"Nat's 4 piece bonus (+100% to RoV)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumNats() >= 4) ? 1.0 : 0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill == ActiveSkill.RoV) && (data
							.getNumNats() >= 4)) ? 1.0 : 0.0;
				}
			}), RoVN6(
			"N6RoV",
			DamageAccumulator.Multiplicative,
			"Nat's 6 piece bonus to RoV (+500% damage for 10 seconds after RoV)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.N6) ? 5.0 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill == ActiveSkill.RoV) && (data
							.getNumNats() >= 6)) ? 5.0 : 0.0;
				}
			}), N6(
			"N6",
			DamageAccumulator.Multiplicative,
			"Nat's 6 piece bonus to other skills(+500% damage for 5 seconds after RoV)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					// TODO do we need to calculate uptime?

					if (state.getData().getNumNats() < 6)
						return 0.0;

					double interval = (1.0 / state.getData().getAps())
							+ (state.getData().getDelay() / 1000.0);
					double cdr = state.getData().getCdr();
					double rovCD = 30.0 * (1.0 - cdr);
					double numAttacks = rovCD / (interval + 2.0);
					rovCD = numAttacks * interval;

					if (rovCD <= 5.0)
						return 5.0;
					else
						return Math.round(500.0 * (5.0 / rovCD)) / 100.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill != ActiveSkill.RoV) && (data
							.getNumNats() >= 6)) ? 5.0 : 0.0;
				}
			}), Elite("Elite", DamageAccumulator.Multiplicative,
			"Elite damage bonus (includes BotP if rank 25+)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					// TODO Apply Target Type
					return state.getData().getTotalEliteDamage();
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.getTotalEliteDamage();
				}
			}), DoT("DoT", DamageAccumulator.Multiplicative,
			"Damage over Time (damage per second, not per attack)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return 0.0;
				}
			}), BoT("BotT", DamageAccumulator.Multiplicative,
			"Bane of the Trapped bonus (15% + 3%/rank to control impaired)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isUseBaneOfTheTrapped() ? ((0.15 + (state
							.getData().getBaneOfTheTrappedLevel() * 0.003)) * state
							.getData().getPercentControlled())
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isUseBaneOfTheTrapped() ? (0.15 + (data
							.getBaneOfTheTrappedLevel() * 0.003)) : 0.0;
				}
			}), BotP("BotP", DamageAccumulator.Multiplicative,
			"Bane of the Powerful active gem bonus (20% while active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BotP) ? 0.2 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isBotp() ? 0.2 : 0.0;
				}
			}), BotS(
			"BotS",
			DamageAccumulator.Multiplicative,
			"Bane of the Stricken gem per stack bonus (0.80% + 0.01 % per level, per stack)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isBotS() && (state.getData()
							.getTargetType() == TargetType.Primary)) ? ((0.008 + (state
							.getData().getBotSLevel() * 0.0001)) * state
							.getTargets().getTarget(TargetType.Primary)
							.getBotsStacks()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return data.isBotS() ? ((0.008 + (data.getBotSLevel() * 0.0001)) * 100.0)
							: 0.0;
				}
			}), BotS25(
			"BotS25",
			DamageAccumulator.Multiplicative,
			"Bane of the Stricken rank 25 bonus (25% against bosses and rift guardians)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isBotS()
							&& (state.getData().getBotSLevel() >= 25)
							&& (state.getData().getPrimaryTargetType() == MonsterType.RiftGuardian) && (state
							.getData().getTargetType() == TargetType.Primary)) ? 0.25
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isBotS() && (data.getBotSLevel() >= 25)) ? 0.25
							: 0.0;
				}
			}), IAS("IAS", DamageAccumulator.Multiplicative,
			"Character IAS bonus for Companions",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getEquipIas()
							+ (state.getData().getParagonIAS() * 0.002)
							+ (state.getData().isGogok() ? (state.getData()
									.getGogokStacks() * 0.01) : 0.0)
							+ (state.getData().isFocusedMind() ? 0.03 : 0.0) + (state
							.getData().isRetribution() ? (0.1 * state.getData()
							.getRetributionUptime()) : 0.0));
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return (row.source.skill == ActiveSkill.Companion) ? (data
							.getEquipIas()
							+ (data.getParagonIAS() * 0.002)
							+ (data.isGogok() ? 0.15 : 0.0)
							+ (data.isFocusedMind() ? 0.03 : 0.0) + (data
							.isRetribution() ? 0.1 : 0.0)) : 0.0;
				}
			}), APS("APS", DamageAccumulator.Multiplicative,
			"Weapon APS bonus for Companions",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					WeaponType type = state.getData().getWeaponType();

					if (type == null)
						type = WeaponType.Bow;

					return (type.getAps() * (1.0 + state.getData()
							.getWeaponIas())) - 1.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					if (row.source.skill != ActiveSkill.Companion)
						return 0.0;

					WeaponType type = data.getWeaponType();

					if (type == null)
						type = WeaponType.Bow;

					return (type.getAps() * (1.0 + data.getWeaponIas())) - 1.0;
				}
			}), CaltropsBT(
			"Caltrops",
			DamageAccumulator.Special,
			"Caltrops/Bait the Trap active bonus (10% Crit Chance while active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Caltrops) ? 0.1 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.getSkills().get(ActiveSkill.Caltrops) == Rune.Bait_the_Trap) ? 0.1
							: 0.0;
				}
			}), Taeguk("Taeguk", DamageAccumulator.Multiplicative,
			"Taeguk active gem bonus (2% + 0.04% per stack)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isTaeguk() ? ((0.02 + (0.0004 * state
							.getData().getTaegukLevel())) * state.getData()
							.getTaegukStacks()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return ((data.getSkills().containsKey(ActiveSkill.Strafe) || (row.source.skill == ActiveSkill.RF)) && data
							.isTaeguk()) ? ((0.02 + (0.0004 * data
							.getTaegukLevel())) * 10.0) : 0.0;
				}
			}), Simplicity(
			"Simplicity",
			DamageAccumulator.Multiplicative,
			"Simplicity's Strenght gem bonus (25% + 0.5 % per level to Primary skills)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isSimplicity() ? (0.25 + (state
							.getData().getSimplicityLevel() * .005)) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((row.source.skill.getSkillType() == SkillType.Primary) && data
							.isSimplicity()) ? (0.25 + (data
							.getSimplicityLevel() * .005)) : 0.0;
				}
			}), Wolf("Wolf", DamageAccumulator.Multiplicative,
			"Wolf Companion active bonus (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getBuffs().isActive(Buff.Wolf) || state
							.getBuffs().isActive(Buff.OtherWolf)) ? 0.15 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return ((data.getSkills()
							.containsKey(ActiveSkill.Companion) && ((data
							.getNumMarauders() >= 2) || (data.getSkills().get(
							ActiveSkill.Companion) == Rune.Wolf))) ||

					data.isWolf()) ? 0.15 : 0.0;
				}
			}), Bbv("Bbv", DamageAccumulator.Additive,
			"Big Bad Voodoo/Slam Dance active bonus (30% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Bbv) ? 0.3 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isBbv() ? 0.3 : 0.0;
				}
			}), TW("TW", DamageAccumulator.Additive,
			"Slow Time/Time Warp (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.TimeWarp) ? 0.15
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isTimeWarp() ? 0.15 : 0.0;
				}
			}), Piranhas("Piranhas", DamageAccumulator.Additive,
			"Piranhas active bonus (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Piranhas) ? 0.15
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isPiranhas() ? 0.15 : 0.0;
				}
			}), InnerSanctuary(
			"InnerSanctuary",
			DamageAccumulator.Additive,
			"Inner Sanctuary/Forbidden Palace active bonus (30% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.InnerSanct) ? 0.3
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isInnerSanctuary() ? 0.3 : 0.0;
				}
			}), CripplingWave("CripplingWave", DamageAccumulator.Additive,
			"Crippling Wave/Breaking Wave active bonus (10% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.CripWave) ? 0.1 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isCripplingWave() ? 0.1 : 0.0;
				}
			}), Conviction("Conviction", DamageAccumulator.Additive,
			"Conviction bonus (10-20% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					if (state.getBuffs().isActive(Buff.ConvictionActive)) {
						return 0.2;
					} else if (state.getBuffs()
							.isActive(Buff.ConvictionPassive)) {
						return state.getData().isOverawe() ? 0.16 : 0.10;
					} else {
						return 0.0;
					}
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isConviction() ? 0.2 : 0.0;
				}

			}), Paranoia("Paranoia", DamageAccumulator.Additive,
			"Mass Confusion/Paranoia active bonus (20% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Paranoia) ? 0.2 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isMassConfusion() ? 0.2 : 0.0;
				}
			}), MfD("MfD", DamageAccumulator.Additive,
			"Marked for Death active skill bonus (15%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					TargetType type = state.getData().getTargetType();

					if (type.isPrimary()
							&& !state.getBuffs().isActive(Buff.MfdPrimary))
						return 0.0;
					if (!type.isPrimary()
							&& !state.getBuffs().isActive(Buff.MfdAdditional))
						return 0.0;

					double scalar = 0.15;
					double aoe = 0.0;

					// TODO fix
					if ((state.getData().getMfdRune() == Rune.Grim_Reaper)
							&& (state.getData().getNumAdditional() > 0)
							&& (state.getData().getTargetSpacing() <= 20)) {
						aoe = (0.15 / state.getData().getNumAdditional());
					}

					return scalar + aoe;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					if ((data.getMfdRune() == Rune.Grim_Reaper)
							&& (data.getNumAdditional() > 0)
							&& (data.getTargetSpacing() <= 20))
						return 0.3;
					else
						return data.isMarked() ? 0.15 : 0.0;
				}
			}), Vaxo("Vaxo", DamageAccumulator.Additive,
			"Haunt of Vaxo Marked for Death bonus (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					TargetType type = state.getData().getTargetType();

					if (type == TargetType.Primary) {
						return (state.getBuffs().isActive(Buff.Vaxo)
								&& !state.getBuffs().isActive(Buff.Calamity) && !state
								.getBuffs().isActive(Buff.MfdPrimary)) ? 0.15
								: 0.0;
					} else {
						return (state.getBuffs().isActive(Buff.Vaxo)
								&& !state.getBuffs().isActive(Buff.Calamity)
								&& !state.getBuffs().isActive(
										Buff.MfdAdditional) && (state.getData()
								.getTargetSpacing() <= 15)) ? 0.15 : 0.0;
					}

				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isVaxo() && !data.isCalamityMdf() && !data
							.getSkills().containsKey(ActiveSkill.MFD)) ? 0.15
							: 0.0;
				}
			}), AD("Area", DamageAccumulator.Special,
			"Area Damage (20% chance)", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return ((state.getTargets().getNumAlive() > 1) && (state
							.getData().getTargetSpacing() <= 10)) ? state
							.getData().getAreaDamage() : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {

					return ((data.getNumAdditional() > 0) && (data
							.getTargetSpacing() <= 10)) ? (data.getAreaDamage() * data
							.getNumAdditional()) : 0.0;
				}
			}), Calamity("Calamity", DamageAccumulator.Additive,
			"Calamity Marked for Death bonus (15% while applied)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					TargetType type = state.getData().getTargetType();
					Rune rune = Rune.None;

					if (state.getData().isMarked()) {
						rune = state.getData().getMfdRune();
					}

					double scalar = 0.15;
					double aoe = 0.0;

					if ((rune == Rune.Grim_Reaper)
							&& (state.getData().getNumAdditional() > 0)
							&& (state.getData().getTargetSpacing() <= 20)) {
						aoe = (0.15 / state.getData().getNumAdditional());

					}

					if (state.getBuffs().isActive(Buff.Calamity)
							&& !state.getBuffs().isActive(Buff.Vaxo)) {
						if (type == TargetType.Primary) {
							return !state.getBuffs().isActive(Buff.MfdPrimary) ? (scalar + aoe)
									: 0.0;
						} else {
							return !state.getBuffs().isActive(
									Buff.MfdAdditional) ? (scalar + aoe) : 0.0;
						}
					} else {
						return 0.0;
					}
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isCalamityMdf() && !data.getSkills()
							.containsKey(ActiveSkill.MFD)) ? 0.15 : 0.0;
				}
			}), Toxicity("Toxicity", DamageAccumulator.Additive,
			"Gem of Efficacious Toxin rank 25+ bonus (10%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isToxin() && state.getData()
							.getToxinLevel() >= 25) ? 0.1 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isToxin() && data.getToxinLevel() >= 25) ? 0.1
							: 0.0;
				}
			}), Ambush("Ambush", DamageAccumulator.Multiplicative,
			"Ambush (40% to enemies above 75% health)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					TargetType type = state.getData().getTargetType();

					return (state.getData().isAmbush() && state.getTargets()
							.getTarget(type).getPercentHealth() >= 0.75) ? 0.4
							: 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isAmbush() ? 0.4 : 0.0;
				}
			}), Iced(
			"Iced",
			DamageAccumulator.Special,
			"Iceblink rank 25 bonus (10% Crit Chance to enemies chilled/frozen)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isIceblink() && (state.getData()
							.getIceblinkLevel() >= 25)) ? (0.1 * state
							.getData().getPercentSlowedChilled()) : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isIceblink() && (data.getIceblinkLevel() >= 25)) ? 0.1
							: 0.0;
				}
			}), DML(
			"DML",
			DamageAccumulator.Special,
			"Dead Man's Legacy (Multishot hits twice when target is below 50-60% health)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return data.isDml() ? 1.0 : 0.0;
				}
			}), SingleOut(
			"SingleOut",
			DamageAccumulator.Special,
			"Single Out (25% Crit Chance to enemies more than 20 yards away from other enemies)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isSingleOut() && (state.getData()
							.getTargetSpacing() >= 20)) ? 0.25 : 0.0;
				}

				@Override
				public Double getMax(boolean sentry, DamageRow row,
						CharacterData data) {
					return (data.isSingleOut() && (data.getTargetSpacing() >= 20)) ? 0.25
							: 0.0;
				}
			});

	private String abbrev;
	private Test<SimulationState, Double> test;
	private DamageAccumulator accumulator;
	private String description;

	private DamageMultiplier(String abbrev, DamageAccumulator accumulator,
			String description, Test<SimulationState, Double> test) {
		this.abbrev = abbrev;
		this.test = test;
		this.accumulator = accumulator;
		this.description = description;
	}

	public DamageAccumulator getAccumulator() {
		return this.accumulator;
	}

	public String getDescription() {
		return this.description;
	}

	public String getAbbreviation() {
		return abbrev;
	}

	public boolean hasTest() {
		return test != null;
	}

	public double getValue(SimulationState state) {
		return test.getValue(state);
	}

	public double getMax(boolean sentry, DamageRow row, CharacterData data) {
		return test.getMax(sentry, row, data);
	}
}