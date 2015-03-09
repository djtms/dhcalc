package com.dawg6.web.sentry.shared.calculator;

public enum DamageMultiplier {
	WD("WD", DamageAccumulator.Multiplicative, "Average Weapon Damage", null), NumGrenades(
			"#Grenades", DamageAccumulator.Multiplicative,
			"# of Grenades per Target", null), Fire("Fire",
			DamageAccumulator.ElementalAdditive, "Fire Elemental Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getFireDamage();
				}
			}), Cold("Cold", DamageAccumulator.ElementalAdditive,
			"Cold Elemental Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getColdDamage();
				}
			}), Physical("Physical", DamageAccumulator.ElementalAdditive,
			"Physical Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getPhysDamage();
				}
			}), Lightning("Lightning", DamageAccumulator.ElementalAdditive,
			"Lightning Elemental Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getLightDamage();
				}
			}), Poison("Poison", DamageAccumulator.ElementalAdditive,
			"Poison Elemental Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getPoisonDamage();
				}
			}), Enforcer(
			"Enforcer",
			DamageAccumulator.ElementalAdditive,
			"Enforcer gem bonus (15% + 3%/rank pet damage). Does not apply to Sentry Spitfire rockets.",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isUseEnforcer() ? (0.15 + (data
							.getEnforcerLevel() * 0.003)) : 0.0;
				}
			}), Zeis(
			"Zei's",
			DamageAccumulator.Multiplicative,
			"Zei's stone of vengeance gem bonus (4% + .05%/rank per 10 yards up to 50 yards).",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {

					if (!data.isZeis())
						return 0.0;

					int d = Math.min(data.getDistanceToTarget() / 10, 5);

					return d * (0.04 + (data.getZeisLevel() * 0.0005));
				}
			}), Sentry("Sentry", DamageAccumulator.Multiplicative,
			"Sentry Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getSentryDamage();
				}
			}), Rockets("Ballistics", DamageAccumulator.Multiplicative,
			"Ballistics passive bonus (100% Rocket damage)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isBallistics() ? 1.0 : 0.0;
				}
			}), Grenades("Grenadier", DamageAccumulator.Multiplicative,
			"Grenadier passive bonus (10% to grenade damage)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isGrenadier() ? 0.1 : 0.0;
				}
			}), SteadyAim("SteadyAim", DamageAccumulator.Additive,
			"Steady Aim passive bonus (20% when no enemies within 10 yards)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isSteadyAim()) ? (0.2 * data
							.getPercentAtLeast10Yards()) : 0.0;
				}
			}), Strongarm("Strongarm", DamageAccumulator.Additive,
			"Strongarm Bracers bonus (20-30% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isStrongarm()) ? (data.getStrongarmPercent() * data
							.getStrongarmUptime()) : 0.0;
				}
			}), Harrington("Harrington", DamageAccumulator.Additive,
			"Harrington Waistguard bonus (100-135% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isHarrington()) ? (data.getHarringtonPercent() * data
							.getHarringtonUptime()) : 0.0;
				}
			}), HexingPants(
			"HexingPants",
			DamageAccumulator.Additive,
			"Hexing Pants of Mr. Yan bonus (+ while moving, - while stationary)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isHexingPants() ? ((0.25 * data
							.getHexingPantsUptime()) - (data
							.getHexingPantsPercent() * (1.0 - data
							.getHexingPantsUptime()))) : 0.0;
				}
			}), ArcheryDamage("Archery", DamageAccumulator.Additive,
			"Archery damage bonus (8% when using 2H Bow)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isArchery() && (data.getWeaponType() == WeaponType.Bow)) ? 0.08
							: 0.0;
				}
			}), Dexterity("Dex", DamageAccumulator.Multiplicative,
			"Dexterity bonus (1% damage per dex)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getDexterity() / 100.0;
				}
			}), CC("CC", DamageAccumulator.Special, "Chrit Chance bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getCritChance();
				}
			}), CHD("CHD", DamageAccumulator.Special, "Chrit Hit Damage bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getCritHitDamage();
				}
			}), Hysteria("Hysteria", DamageAccumulator.Additive,
			"Scoundrel Hysteria Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isHysteria() ? 0.03 : 0.0;
				}
			}), EA("EA", DamageAccumulator.Additive,
			"Elemental Arrow Skill Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getEaDamage();
				}
			}), CA("CA", DamageAccumulator.Additive,
			"Cluster Arrow Skill Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getCaDamage();
				}
			}), MS("MS", DamageAccumulator.Additive,
			"Multishot Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getMsDamage();
				}
			}), IMP("Imp", DamageAccumulator.Additive,
			"Impale Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getImpDamage();
				}
			}), CHAK("Chak", DamageAccumulator.Additive,
			"Chakram Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getChakDamage();
				}
			}), HA("HA", DamageAccumulator.Additive,
			"Hungering Arrow Skill Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getHaDamage();
				}
			}), ES("ES", DamageAccumulator.Additive,
			"Entangling Shot Skill Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getEsDamage();
				}
			}), BOLAS("BOLAS", DamageAccumulator.Additive,
			"Bolas Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getBolasDamage();
				}
			}), EF("EF", DamageAccumulator.Additive,
			"Evasive Fire Skill Damage Bonus",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getEfDamage();
				}
			}), GRENADE("GRENADE", DamageAccumulator.Additive,
			"Grenade Skill Damage Bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.getGrenadeDamage();
				}
			}), CtW("CtW", DamageAccumulator.Multiplicative,
			"Cull the Weak passive bonus (20% to chilled/frozen)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isCullTheWeak() ? (0.2 * data
							.getPercentSlowedChilled()) : 0.0;
				}
			}), M6("M6", DamageAccumulator.Multiplicative,
			"Marauder's 6 piece bonus", new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.getNumMarauders() >= 6) ? (double) (data
							.getNumSentries()) : 0;
				}
			}), Elite("Elite", DamageAccumulator.Multiplicative,
			"Elite damage bonus (includes BotP if rank 25+)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return 0.0;
				}
			}), DoT("DoT", DamageAccumulator.Multiplicative,
			"Damage over Time (damage per second, not per attack)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return 0.0;
				}
			}), BoT("BoT", DamageAccumulator.Multiplicative,
			"Bane of the Trapped bonus (15% + 3%/rank to control impaired)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isUseBaneOfTheTrapped() ? ((0.15 + (data
							.getBaneOfTheTrappedLevel() * 0.003)) * data
							.getPercentControlled()) : 0.0;
				}
			}), BotP("BotP", DamageAccumulator.Additive,
			"Bane of the Powerful active gem bonus (20% while active)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isBotp() ? (0.2 * data.getBotpUptime()) : 0.0;
				}
			}), Caltrops(
			"Caltrops",
			DamageAccumulator.Special,
			"Caltrops/Bait the Trap active bonus (10% Crit Chance while active)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isCaltrops() ? (0.1 * data.getCaltropsUptime())
							: 0.0;
				}
			}), Taeguk("Taeguk", DamageAccumulator.Additive,
			"Taeguk active gem bonus (0.5% per stack)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isTaeguk() ? (0.005 * data.getTaegukStacks())
							: 0.0;
				}
			}), Wolf("Wolf", DamageAccumulator.Additive,
			"Wolf Companion active bonus (30% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isWolf() ? (data.getWolfUptime() * 0.3) : 0.0;
				}
			}), Bbv("Bbv", DamageAccumulator.Additive,
			"Big Bad Voodoo/Slam Dance active bonus (30% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isBbv() && data.isSlamDance()) ? (data
							.getBbvUptime() * 0.3) : 0.0;
				}
			}), Piranhas("Piranhas", DamageAccumulator.Additive,
			"Piranhas active bonus (15% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isPiranhas() ? (data.getPiranhasUptime() * 0.15)
							: 0.0;
				}
			}), InnerSanctuary(
			"InnerSanctuary",
			DamageAccumulator.Additive,
			"Inner Sanctuary/Forbidden Palace active bonus (30% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isInnerSanctuary() ? (data
							.getInnerSanctuaryUptime() * 0.3) : 0.0;
				}
			}), CripplingWave("CripplingWave", DamageAccumulator.Additive,
			"Crippling Wave/Breaking Wave active bonus (10% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isCripplingWave() ? (data
							.getCripplingWaveUptime() * 0.1) : 0.0;
				}
			}), Conviction("Conviction", DamageAccumulator.Additive,
			"Conviction bonus (10-24% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {

					if (!data.isConviction())
						return 0.0;

					double passive = 0.10;
					double active = 0.20;

					if (data.isOverawe()) {
						passive = 0.16;
						active = 0.24;
					}

					return (passive * data.getConvictionPassiveUptime())
							+ (active * data.getConvictionActiveUptime());
				}
			}), Paranoia("Paranoia", DamageAccumulator.Additive,
			"Mass Confusion/Paranoia active bonus (20% during uptime)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isMassConfusion() ? (data
							.getMassConfusionUptime() * 0.2) : 0.0;
				}
			}), MfD(
			"MfD",
			DamageAccumulator.Additive,
			"Marked for Death active skill bonus (20%+ while applied, depending on selected Rune)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {

					if (!data.isMarked())
						return 0.0;

					double upTime = (data.getTargetType() == Target.Primary) ? data
							.getMfdUptime() : data.getMfdAddUptime();
					double scalar = 0.2;
					double aoe = 0.0;
					double aoeUpTime = Math.max(data.getMfdUptime(),
							data.getMfdAddUptime());

					switch (data.getMfdRune()) {
					case Valley_Of_Death:
						scalar = 0.15;
						break;

					case Grim_Reaper:
						if (data.getTargetSpacing() <= 20) {
							aoe = (0.20 / data.getNumAdditional());

							if (data.getTargetType() == Target.Primary) {
								aoeUpTime = data.getMfdAddUptime();
							}
						}

						break;

					case Contagion:
						upTime = Math.max(data.getMfdUptime(),
								data.getMfdAddUptime());
						break;

					case Mortal_Enemy:
					case None:
					default:
						break;
					}

					return (scalar * upTime) + (aoe * aoeUpTime);
				}
			}), Calamity("Calamity", DamageAccumulator.Additive,
			"Calamity Marked for Death bonus (20% while applied)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isCalamityMdf() ? (0.2 * data
							.getCalamityUptime()) : 0.0;
				}
			}), Toxicity("Toxicity", DamageAccumulator.Additive,
			"Gem of Efficacious Toxin rank 25+ bonus (10%)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isToxin() && data.getToxinLevel() >= 25) ? 0.1
							: 0.0;
				}
			}), Ambush("Ambush", DamageAccumulator.Multiplicative,
			"Ambush (40% to enemies above 75% health)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return data.isAmbush() ? (0.4 * data.getPercentAbove75())
							: 0.0;
				}
			}), SingleOut(
			"SingleOut",
			DamageAccumulator.Special,
			"Single Out (25% Crit Chance to enemies more than 20 yards away from other enemies)",
			new Test<CharacterData, Double>() {
				@Override
				public Double getValue(CharacterData data) {
					return (data.isSingleOut() && (data.getTargetSpacing() >= 20)) ? 0.25
							: 0.0;
				}
			});

	private String abbrev;
	private Test<CharacterData, Double> test;
	private DamageAccumulator accumulator;
	private String description;

	private DamageMultiplier(String abbrev, DamageAccumulator accumulator,
			String description, Test<CharacterData, Double> test) {
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

	public double getValue(CharacterData data) {
		return test.getValue(data);
	}
}