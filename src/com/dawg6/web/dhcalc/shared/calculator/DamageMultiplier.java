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
			}), OHWD("OHWD", DamageAccumulator.Multiplicative,
			"Average Off-Hand Weapon Damage",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getOffHand_weaponDamage();
				}
			}), HW("HW", DamageAccumulator.Multiplicative,
			"Hunter's Wrath (45%-60% for primary skills)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHuntersWrath() ? state.getData()
							.getHuntersWrathPercent() : 0.0;
				}
			}), PC("PC", DamageAccumulator.Multiplicative,
			"Proc Coefficient (depends on skill)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getLastAttack().getProc();
				}
			}), DD("DD", DamageAccumulator.Multiplicative,
			"Depth Diggers (80%-100% for primary skills)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isDepthDiggers() ? state.getData()
							.getDepthDiggersPercent() : 0.0;
				}
			}), NumGrenades("#Grenades", DamageAccumulator.Multiplicative,
			"# of Grenades per Target", null), Fire("Fire",
			DamageAccumulator.ElementalAdditive, "Fire Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getFireDamage();
				}
			}), Cold("Cold", DamageAccumulator.ElementalAdditive,
			"Cold Elemental Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getColdDamage();
				}
			}), Physical("Physical", DamageAccumulator.ElementalAdditive,
			"Physical Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getPhysDamage();
				}
			}), SharpShooter("SS", DamageAccumulator.Special,
			"SharpShooter Bonus (+4% crit chance/second after last crit)",
			new Test<SimulationState, Double>() {

				@Override
				public Double getValue(SimulationState data) {
					return data.getData().isSharpshooter() ? data.getData()
							.getSharpshooterCC() : 0.0;
				}
			}), Lightning("Lightning", DamageAccumulator.ElementalAdditive,
			"Lightning Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getLightDamage();
				}
			}), Poison("Poison", DamageAccumulator.ElementalAdditive,
			"Poison Elemental Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getPoisonDamage();
				}
			}), Holy("Holy", DamageAccumulator.ElementalAdditive,
			"Holy Elemental Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getHolyDamage();
				}
			}), Enforcer(
			"Enforcer",
			DamageAccumulator.ElementalAdditive,
			"Enforcer gem bonus (15% + 3%/rank pet damage). Does not apply to Sentry Spitfire rockets.",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isUseEnforcer() ? (0.15 + (state
							.getData().getEnforcerLevel() * 0.003)) : 0.0;
				}
			}), Zeis(
			"Zei's",
			DamageAccumulator.Multiplicative,
			"Zei's stone of vengeance gem bonus (4% + .05%/rank per 10 yards up to 50 yards).",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					if (!state.getData().isZeis())
						return 0.0;

					int d = Math.min(
							state.getData().getDistanceToTarget() / 10, 5);

					return d
							* (0.04 + (state.getData().getZeisLevel() * 0.0005));
				}
			}), SentryM4("SentryM4", DamageAccumulator.Multiplicative,
			"Sentry Skill Damage Bonus for skills fired by M4",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSentryDamage();
				}
			}), Sentry("Sentry", DamageAccumulator.Additive,
			"Sentry Skill Damage Bonus for sentry bolts/rockets",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSentryDamage();
				}
			}), COE("CoE", DamageAccumulator.Multiplicative,
			"Convention of Elements Bonus (150-200% while element is active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {

					return state.getData().isCoe() ? state.getData()
							.getCoePercent() : 0.0;
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
			}), Rockets("Ballistics", DamageAccumulator.Multiplicative,
			"Ballistics passive bonus (100% Rocket damage)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isBallistics() ? 1.0 : 0.0;
				}
			}), Grenades("Grenadier", DamageAccumulator.Multiplicative,
			"Grenadier passive bonus (10% to grenade damage)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isGrenadier() ? 0.1 : 0.0;
				}
			}), SteadyAim("SteadyAim", DamageAccumulator.Additive,
			"Steady Aim passive bonus (20% when no enemies within 10 yards)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isSteadyAim()) ? (0.2 * state
							.getData().getPercentAtLeast10Yards()) : 0.0;
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
			}), Harrington("Harrington", DamageAccumulator.Additive,
			"Harrington Waistguard bonus (100-135% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isHarrington()) ? (state.getData()
							.getHarringtonPercent() * state.getData()
							.getHarringtonUptime()) : 0.0;
				}
			}), HexingPants(
			"HexingPants",
			DamageAccumulator.Additive,
			"Hexing Pants of Mr. Yan bonus (+ while moving, - while stationary)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHexingPants() ? ((0.25 * state
							.getData().getHexingPantsUptime()) - (state
							.getData().getHexingPantsPercent() * (1.0 - state
							.getData().getHexingPantsUptime()))) : 0.0;
				}
			}), ArcheryDamage("Archery", DamageAccumulator.Additive,
			"Archery damage bonus (8% when using 2H Bow)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isArchery() && (state.getData()
							.getWeaponType() == WeaponType.Bow)) ? 0.08 : 0.0;
				}
			}), UE4(
			"UE4",
			DamageAccumulator.Multiplicative,
			"Unhallowed Essence 4 item set bonus (20% if no enemies within 10 yards)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumUe() >= 4) ? (0.2 * state
							.getData().getPercentAtLeast10Yards()) : 0.0;
				}
			}), UE6(
			"UE6",
			DamageAccumulator.Multiplicative,
			"Unhallowed Essence 6 item set bonus (15% per point of discipline)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumUe() >= 6) ? (0.15 * state
							.getDisc()) : 0.0;
				}
			}), BW1("BWg", DamageAccumulator.Multiplicative,
			"Bastions of Will Generator Bonus (50%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BwGen) ? 0.5 : 0.0;
				}
			}), BW2("BWs", DamageAccumulator.Multiplicative,
			"Bastions of Will Spender Bonus (50%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BwSpend) ? 0.5 : 0.0;
				}
			}), Dexterity("Dex", DamageAccumulator.Multiplicative,
			"Dexterity bonus (1% damage per dex)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getDexterity() / 100.0;
				}
			}), CC("CC", DamageAccumulator.Special, "Chrit Chance bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCritChance();
				}
			}), CHD("CHD", DamageAccumulator.Special, "Chrit Hit Damage bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCritHitDamage();
				}
			}), OnCrit("OnCrit", DamageAccumulator.Special,
			"On Crit Only Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCritHitDamage();
				}
			}), Hysteria("Hysteria", DamageAccumulator.Additive,
			"Scoundrel Hysteria Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isHysteria() ? 0.03 : 0.0;
				}
			}), OdysseysEnd("OE", DamageAccumulator.Additive,
			"Odyssey's End Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.OdysseysEnd) ? state
							.getData().getOdysseysEndPercent() : 0.0;
				}
			}), EA("EA", DamageAccumulator.Additive,
			"Elemental Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEaDamage();
				}
			}), CA("CA", DamageAccumulator.Additive,
			"Cluster Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCaDamage();
				}
			}), MS("MS", DamageAccumulator.Additive,
			"Multishot Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getMsDamage();
				}
			}), IMP("Imp", DamageAccumulator.Additive,
			"Impale Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getImpDamage();
				}
			}), CHAK("Chak", DamageAccumulator.Additive,
			"Chakram Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getChakDamage();
				}
			}), HA("HA", DamageAccumulator.Additive,
			"Hungering Arrow Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getHaDamage();
				}
			}), Companion("Companion", DamageAccumulator.Additive,
			"Companion Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getCompanionDamage();
				}
			}), Raven("Raven", DamageAccumulator.Multiplicative,
			"Companion/Raven Active Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.CompanionActive) ? 5.0
							: 0.0;
				}
			}), ES("ES", DamageAccumulator.Additive,
			"Entangling Shot Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEsDamage();
				}
			}), BOLAS("BOLAS", DamageAccumulator.Additive,
			"Bolas Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getBolasDamage();
				}
			}), EF("EF", DamageAccumulator.Additive,
			"Evasive Fire Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getEfDamage();
				}
			}), GRENADE("GRENADE", DamageAccumulator.Additive,
			"Grenade Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getGrenadeDamage();
				}
			}), ST("ST", DamageAccumulator.Additive, "Spike Trap Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getSpikeTrapDamage();
				}
			}), FoK("FoK", DamageAccumulator.Additive,
			"Fan of Knives Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getFokDamage();
				}
			}), Vengeance("Ven", DamageAccumulator.Additive,
			"Vengeance Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getVengeanceDamage();
				}
			}), Strafe("Strafe", DamageAccumulator.Additive,
			"Strafe Skill Damage Bonus", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getStrafeDamage();
				}
			}), RF("RF", DamageAccumulator.Additive,
			"Rapid Fire Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getRFDamage();
				}
			}), RoV("RoV", DamageAccumulator.Additive,
			"Rain of Vengeance Skill Damage Bonus",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().getRovDamage();
				}
			}), CtW("CtW", DamageAccumulator.Multiplicative,
			"Cull the Weak passive bonus (20% to chilled/slowed)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isCullTheWeak() ? (0.2 * state
							.getData().getPercentSlowedChilled()) : 0.0;
				}
			}), M6("M6", DamageAccumulator.Multiplicative,
			"Marauder's 6 piece bonus (+100% per Sentry)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumMarauders() >= 6) ? (double) (state
							.getData().getNumSentries()) : 0;
				}
			}), N4("N4", DamageAccumulator.Multiplicative,
			"Nat's 4 piece bonus (+100% to RoV)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().getNumNats() >= 4) ? 1.0 : 0;
				}
			}), RoVN6(
			"N6RoV",
			DamageAccumulator.Multiplicative,
			"Nat's 6 piece bonus to RoV (+400% damage for 5 seconds after RoV)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.N6) ? 4.0 : 0.0;
				}
			}), N6(
			"N6",
			DamageAccumulator.Multiplicative,
			"Nat's 6 piece bonus to other skills(+400% damage for 5 seconds after RoV)",
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
						return 4.0;
					else
						return Math.round(400.0 * (5.0 / rovCD)) / 100.0;
				}
			}), Elite("Elite", DamageAccumulator.Multiplicative,
			"Elite damage bonus (includes BotP if rank 25+)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					// TODO Apply Target Type
					return state.getData().getTotalEliteDamage();
				}
			}), DoT("DoT", DamageAccumulator.Multiplicative,
			"Damage over Time (damage per second, not per attack)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
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
			}), BotP("BotP", DamageAccumulator.Additive,
			"Bane of the Powerful active gem bonus (20% while active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.BotP) ? 0.2 : 0.0;
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
			}), CaltropsBT(
			"Caltrops",
			DamageAccumulator.Special,
			"Caltrops/Bait the Trap active bonus (10% Crit Chance while active)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Caltrops) ? 0.1 : 0.0;
				}
			}), Taeguk("Taeguk", DamageAccumulator.Additive,
			"Taeguk active gem bonus (0.5% per stack)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isTaeguk() ? (0.005 * state
							.getData().getTaegukStacks()) : 0.0;
				}
			}), Simplicity(
			"Simplicity",
			DamageAccumulator.Additive,
			"Simplicity's Strenght gem bonus (25% + 0.5 % per level to Primary skills)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getData().isSimplicity() ? (0.25 + (state
							.getData().getSimplicityLevel() * .005)) : 0.0;
				}
			}), Wolf("Wolf", DamageAccumulator.Additive,
			"Wolf Companion active bonus (30% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getBuffs().isActive(Buff.Wolf) || state
							.getBuffs().isActive(Buff.OtherWolf)) ? 0.3 : 0.0;
				}
			}), Bbv("Bbv", DamageAccumulator.Additive,
			"Big Bad Voodoo/Slam Dance active bonus (30% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Bbv) ? 0.3 : 0.0;
				}
			}), TW("TW", DamageAccumulator.Additive,
			"Slow Time/Time Warp (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.TimeWarp) ? 0.15
							: 0.0;
				}
			}), Piranhas("Piranhas", DamageAccumulator.Additive,
			"Piranhas active bonus (15% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Piranhas) ? 0.15
							: 0.0;
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
			}), CripplingWave("CripplingWave", DamageAccumulator.Additive,
			"Crippling Wave/Breaking Wave active bonus (10% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.CripWave) ? 0.1 : 0.0;
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
			}), Paranoia("Paranoia", DamageAccumulator.Additive,
			"Mass Confusion/Paranoia active bonus (20% during uptime)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Paranoia) ? 0.2 : 0.0;
				}
			}), MfD(
			"MfD",
			DamageAccumulator.Additive,
			"Marked for Death active skill bonus (20%+ while applied, depending on selected Rune)",
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

					double scalar = 0.2;
					double aoe = 0.0;

					switch (state.getData().getMfdRune()) {
					case Valley_Of_Death:
						scalar = 0.15;
						break;

					case Grim_Reaper:

						if (state.getData().getNumAdditional() <= 0)
							break;

						if (state.getData().getTargetSpacing() <= 20) {
							aoe = (0.20 / state.getData().getNumAdditional());
						}

						break;

					case Contagion:
						break;

					case Mortal_Enemy:
					case None:
					default:
						break;
					}

					return scalar + aoe;
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
			}), AD("Area", DamageAccumulator.Special,
			"Area Damage (20% chance)", new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return 0.0;
					// return ((state.getData().getNumAdditional() > 0) &&
					// (state
					// .getData().getTargetSpacing() <= 10)) ? (0.2 * state
					// .getData().getAreaDamage()) : 0.0;
				}
			}), Calamity("Calamity", DamageAccumulator.Additive,
			"Calamity Marked for Death bonus (20% while applied)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return state.getBuffs().isActive(Buff.Calamity) ? 0.2 : 0.0;
				}
			}), Toxicity("Toxicity", DamageAccumulator.Additive,
			"Gem of Efficacious Toxin rank 25+ bonus (10%)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return (state.getData().isToxin() && state.getData()
							.getToxinLevel() >= 25) ? 0.1 : 0.0;
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
			}), DML(
			"DML",
			DamageAccumulator.Special,
			"Dead Man's Legacy (Multishot hits twice when target is below 50-60% health)",
			new Test<SimulationState, Double>() {
				@Override
				public Double getValue(SimulationState state) {
					return 0.0;
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

	public double getValue(SimulationState state) {
		return test.getValue(state);
	}
}