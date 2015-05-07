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
package com.dawg6.web.dhcalc.shared.calculator;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation.D3Set;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;

public class CharacterData implements Serializable {

	private static final long serialVersionUID = -7542480040314924139L;

	private int heroLevel;
	private Realm realm;
	private String profile;
	private int tag;
	private int hero;
	private String name;
	private int paragonIAS;
	private int paragonCDR;
	private int paragonCC;
	private int paragonCHD;
	private int paragonHatred;
	private int paragonRCR;
	private double sheetDps;
	private double aps;
	private double offHand_aps;
	private double offHand_dps;
	private double critChance;
	private double critHitDamage;
	private double eliteDamage;
	private boolean wolf;
	private boolean hexingPants;
	private boolean charmed;
	private boolean strongarms;
	private int strongArmsPct;
	private boolean harringtons;
	private int harringtonsPercent;
	private boolean bigBadVoodo;
	private boolean massConfusion;
	private boolean piranhas;
	private boolean innerSanctuary;
	private boolean conviction;
	private boolean overawe;
	private boolean cripplingWave;
	private boolean retribution;
	private boolean transgression;
	private boolean retaliation;
	private boolean calamityMdf;
	private int numTargets;
	private int numAoeTargets;
	private int weaponMin;
	private int weaponMax;
	private int jewelryMin;
	private int jewelryMax;
	private BowType bow;
	private int numAdditional;
	private int distanceToTarget;
	private double weaponDamage;
	private double offHand_weaponDamage;
	private int targetSpacing;
	private double cdr;
	private double rcr;
	private boolean focusedMind;
	private boolean hysteria;
	private boolean anatomy;
	private double wolfUptime;
	private boolean bbv;
	private double bbvUptime;
	private double massConfusionUptime;
	private double percentSlowedChilled;
	private double percentControlled;
	private double percentAtLeast10Yards;
	private double piranhasUptime;
	private double innerSanctuaryUptime;
	private double cripplingWaveUptime;
	private double convictionPassiveUptime;
	private double convictionActiveUptime;
	private boolean meticulousBolts;
	private double meticulousBoltsPercent;
	private boolean tnt;
	private double tntPercent;
	private TargetSize targetSize;
	private double mfdUptime;
	private double mfdAddUptime;
	private double calamityUptime;
	private TargetType targetType;
	private boolean slamDance;
	private boolean valor;
	private double valorUptime;
	private double retributionUptime;
	private boolean harrington;
	private double harringtonPercent;
	private double harringtonUptime;
	private boolean strongarm;
	private double strongarmPercent;
	private double strongarmUptime;
	private double hexingPantsPercent;
	private double hexingPantsUptime;
	private double caltropsUptime;
	private Map<ActiveSkill, Rune> skills;
	private Map<String, Integer> cdrData;
	private Map<String, Integer> rcrData;
	private boolean bornsCdr;
	private boolean crimsonCdr;
	private boolean crimsonRcr;
	private boolean leorics;
	private double leoricsPercent;
	private boolean pridesFall;
	private GemLevel diamond;
	private WeaponType weaponType;
	private double baseMin;
	private double baseMax;
	private double addMin;
	private double addMax;
	private double weaponDamagePercent;
	private double weaponIas;
	private WeaponType offHand_weaponType;
	private double offHand_baseMin;
	private double offHand_baseMax;
	private double offHand_addMin;
	private double offHand_addMax;
	private double offHand_weaponDamagePercent;
	private double offHand_weaponIas;
	private double jewelMin;
	private double jewelMax;
	private boolean royalRing;
	private Map<String, Integer> setCounts;
	private Map<String, D3Set> sets;
	private double equipIas;
	private int paragon;
	private String heroName;
	private boolean seasonal;
	private boolean hardcore;
	private boolean dead;
	private int level;
	private double weaponDps;
	private double weaponAps;
	private double offHand_weaponDps;
	private double offHand_weaponAps;
	private int bp;
	private double sentryDps;
	private double equipCritChance;
	private double equipCritDamage;
	private boolean hasBombardiers;
	private double hatredPerSecond;
	private int numMarauders;
	private int numUe;
	private boolean kridershot;
	private boolean spines;
	private int kridershotHatred;
	private int spinesHatred;
	private boolean reapersWraps;
	private double reapersWrapsPercent;
	private int numHealthGlobes;
	private boolean cindercoat;
	private double cindercoatRCR;
	private boolean inspire;
	private int delay;
	private boolean odysseysEnd;
	private double odysseysEndPercent;
	private double odysseysEndUptime;
	private int equipmentDexterity;
	private int paragonDexterity;
	private boolean helltrapper;
	private double helltrapperPercent;
	private boolean vaxo;
	private double areaDamageEquipment;
	private int paragonAD;
	// private int rovKilled;
	private int numNats;
	private int equipmentDiscipline;
	private boolean bastions;
	private boolean crashingRain;
	private Set<Passive> passives;
	private Map<DamageType, Double> elementalDamage;
	private Map<ActiveSkill, Double> skillDamage;
	private double crashingRainPercent;
	private boolean dml;
	private double dmlPercent;
	private boolean coe;
	private double coePercent;
	private double vaxoUptime;
	private MonsterType primaryTargetType;
	private MonsterType additionalTargetType;
	private int riftLevel;
	private long primaryTargetHealth;
	private long additionalTargetHealth;
	private int numPlayers;
	private int timeLimit;
	private boolean timeWarp;
	private boolean stretchTime;
	private double timeWarpUptime;
	private double stretchTimeUptime;
	private Map<GemSkill, GemData> gems;

	public CharacterData copy() {
		return new CharacterData(this);
	}

	public int getNumMarauders() {
		return numMarauders;
	}

	public void setNumMarauders(int numMarauders) {
		this.numMarauders = numMarauders;
	}

	public void setDefaults() {
		mfdUptime = 1.0;
		mfdAddUptime = 1.0;
		numAdditional = 5;
		percentSlowedChilled = 1.0;
		percentControlled = 1.0;
		percentAtLeast10Yards = 1.0;
		distanceToTarget = 25;
		targetSize = TargetSize.Medium;
		double wolfCd = 30.0 * (1.0 - cdr);
		wolfUptime = Math.min(10.0 / wolfCd, 1.0);
		bbv = false;
		massConfusion = false;
		piranhas = false;
		valor = false;
		innerSanctuary = false;
		cripplingWave = false;
		retribution = false;
		conviction = false;
		harringtonUptime = 1.0;
		strongarmUptime = 1.0;
		hexingPantsUptime = 1.0;
		calamityUptime = 1.0;
		focusedMind = false;
		anatomy = false;
		hysteria = false;
		inspire = false;
		caltropsUptime = 1.0;
		numHealthGlobes = 1;
		delay = 50;
		odysseysEndUptime = 1.0;
		// rovKilled = 0;
		// duration = BreakPoint.DURATION;
		vaxoUptime = 1.0;
		primaryTargetType = MonsterType.RiftGuardian;
		additionalTargetType = MonsterType.NonElite;
		riftLevel = 25;
		numPlayers = 1;
		primaryTargetHealth = MonsterHealth.getHealth(riftLevel, numPlayers,
				primaryTargetType);
		additionalTargetHealth = MonsterHealth.getHealth(riftLevel, numPlayers,
				additionalTargetType);
		timeLimit = 2 * 60;
		stretchTime = false;
		timeWarp = false;
		stretchTimeUptime = 0.0;
		timeWarpUptime = 0.0;
	}

	public CharacterData() {

	}

	public CharacterData(CharacterData other) {
		updateFrom(other);
	}

	public void updateFrom(CharacterData other) {
		this.additionalTargetHealth = other.additionalTargetHealth;
		this.additionalTargetType = other.additionalTargetType;
		this.addMax = other.addMax;
		this.addMin = other.addMin;
		this.anatomy = other.anatomy;
		this.aps = other.aps;
		this.areaDamageEquipment = other.areaDamageEquipment;
		this.baseMax = other.baseMax;
		this.baseMin = other.baseMin;
		this.bastions = other.bastions;
		this.bbv = other.bbv;
		this.bbvUptime = other.bbvUptime;
		this.bigBadVoodo = other.bigBadVoodo;
		this.bornsCdr = other.bornsCdr;
		this.bow = other.bow;
		this.bp = other.bp;
		this.calamityMdf = other.calamityMdf;
		this.calamityUptime = other.calamityUptime;
		this.caltropsUptime = other.caltropsUptime;
		this.cdr = other.cdr;
		this.cdrData = Util.copy(other.cdrData);
		this.charmed = other.charmed;
		this.cindercoat = other.cindercoat;
		this.cindercoatRCR = other.cindercoatRCR;
		this.coe = other.coe;
		this.coePercent = other.coePercent;
		this.conviction = other.conviction;
		this.convictionActiveUptime = other.convictionActiveUptime;
		this.convictionPassiveUptime = other.convictionPassiveUptime;
		this.crashingRain = other.crashingRain;
		this.crashingRainPercent = other.crashingRainPercent;
		this.crimsonCdr = other.crimsonCdr;
		this.crimsonRcr = other.crimsonRcr;
		this.cripplingWave = other.cripplingWave;
		this.cripplingWaveUptime = other.cripplingWaveUptime;
		this.critChance = other.critChance;
		this.critHitDamage = other.critHitDamage;
		this.dead = other.dead;
		this.delay = other.delay;
		this.dml = other.dml;
		this.dmlPercent = other.dmlPercent;
		this.diamond = other.diamond;
		this.distanceToTarget = other.distanceToTarget;
		// this.duration = other.duration;
		this.elementalDamage = Util.copy(other.elementalDamage);
		this.eliteDamage = other.eliteDamage;
		this.equipCritChance = other.equipCritChance;
		this.equipCritDamage = other.equipCritDamage;
		this.equipIas = other.equipIas;
		this.equipmentDexterity = other.equipmentDexterity;
		this.equipmentDiscipline = other.equipmentDiscipline;
		this.focusedMind = other.focusedMind;
		this.gems = copy(other.gems);
		this.hardcore = other.hardcore;
		this.harrington = other.harrington;
		this.harringtonPercent = other.harringtonPercent;
		this.harringtons = other.harringtons;
		this.harringtonsPercent = other.harringtonsPercent;
		this.harringtonUptime = other.harringtonUptime;
		this.hasBombardiers = other.hasBombardiers;
		this.hatredPerSecond = other.hatredPerSecond;
		this.helltrapper = other.helltrapper;
		this.helltrapperPercent = other.helltrapperPercent;
		this.hero = other.hero;
		this.heroLevel = other.heroLevel;
		this.heroName = other.heroName;
		this.hexingPants = other.hexingPants;
		this.hexingPantsPercent = other.hexingPantsPercent;
		this.hexingPantsUptime = other.hexingPantsUptime;
		this.hysteria = other.hysteria;
		this.innerSanctuary = other.innerSanctuary;
		this.innerSanctuaryUptime = other.innerSanctuaryUptime;
		this.inspire = other.inspire;
		this.jewelMax = other.jewelMax;
		this.jewelMin = other.jewelMin;
		this.jewelryMax = other.jewelryMax;
		this.jewelryMin = other.jewelryMin;
		this.kridershot = other.kridershot;
		this.kridershotHatred = other.kridershotHatred;
		this.leorics = other.leorics;
		this.leoricsPercent = other.leoricsPercent;
		this.level = other.level;
		this.massConfusion = other.massConfusion;
		this.massConfusionUptime = other.massConfusionUptime;
		this.meticulousBolts = other.meticulousBolts;
		this.meticulousBoltsPercent = other.meticulousBoltsPercent;
		this.mfdAddUptime = other.mfdAddUptime;
		this.mfdUptime = other.mfdUptime;
		this.name = other.name;
		this.numAdditional = other.numAdditional;
		this.numAoeTargets = other.numAoeTargets;
		this.numHealthGlobes = other.numHealthGlobes;
		this.numMarauders = other.numMarauders;
		this.numNats = other.numNats;
		this.numPlayers = other.numPlayers;
		this.numTargets = other.numTargets;
		this.numUe = other.numUe;
		this.odysseysEnd = other.odysseysEnd;
		this.odysseysEndPercent = other.odysseysEndPercent;
		this.odysseysEndUptime = other.odysseysEndUptime;
		this.offHand_addMax = other.offHand_addMax;
		this.offHand_addMin = other.offHand_addMin;
		this.offHand_aps = other.offHand_aps;
		this.offHand_baseMax = other.offHand_baseMax;
		this.offHand_baseMin = other.offHand_baseMin;
		this.offHand_dps = other.offHand_dps;
		this.offHand_weaponAps = other.offHand_weaponAps;
		this.offHand_weaponDamage = other.offHand_weaponDamage;
		this.offHand_weaponDamagePercent = other.offHand_weaponDamagePercent;
		this.offHand_weaponDps = other.offHand_weaponDps;
		this.offHand_weaponIas = other.offHand_weaponIas;
		this.offHand_weaponType = other.offHand_weaponType;
		this.overawe = other.overawe;
		this.paragon = other.paragon;
		this.paragonAD = other.paragonAD;
		this.paragonCC = other.paragonCC;
		this.paragonCDR = other.paragonCDR;
		this.paragonCHD = other.paragonCHD;
		this.paragonDexterity = other.paragonDexterity;
		this.paragonHatred = other.paragonHatred;
		this.paragonIAS = other.paragonIAS;
		this.paragonRCR = other.paragonRCR;
		this.passives = Util.copy(other.passives);
		this.percentAtLeast10Yards = other.percentAtLeast10Yards;
		this.percentControlled = other.percentControlled;
		this.percentSlowedChilled = other.percentSlowedChilled;
		this.piranhas = other.piranhas;
		this.piranhasUptime = other.piranhasUptime;
		this.pridesFall = other.pridesFall;
		this.primaryTargetHealth = other.primaryTargetHealth;
		this.primaryTargetType = other.primaryTargetType;
		this.profile = other.profile;
		this.rcr = other.rcr;
		this.rcrData = Util.copy(other.rcrData);
		this.realm = other.realm;
		this.reapersWraps = other.reapersWraps;
		this.reapersWrapsPercent = other.reapersWrapsPercent;
		this.retaliation = other.retaliation;
		this.retribution = other.retribution;
		this.retributionUptime = other.retributionUptime;
		this.riftLevel = other.riftLevel;
		this.royalRing = other.royalRing;
		this.seasonal = other.seasonal;
		this.sentryDps = other.sentryDps;
		this.setCounts = Util.copy(other.setCounts);
		this.sets = Util.copy(other.sets);
		this.sheetDps = other.sheetDps;
		this.skillDamage = Util.copy(other.skillDamage);
		this.skills = Util.copy(other.skills);
		this.slamDance = other.slamDance;
		this.spines = other.spines;
		this.spinesHatred = other.spinesHatred;
		this.stretchTime = other.stretchTime;
		this.stretchTimeUptime = other.stretchTimeUptime;
		this.strongarm = other.strongarm;
		this.strongarmPercent = other.strongarmPercent;
		this.strongarms = other.strongarms;
		this.strongArmsPct = other.strongArmsPct;
		this.strongarmUptime = other.strongarmUptime;
		this.tag = other.tag;
		this.targetSize = other.targetSize;
		this.targetSpacing = other.targetSpacing;
		this.targetType = other.targetType;
		this.timeLimit = other.timeLimit;
		this.timeWarp = other.timeWarp;
		this.timeWarpUptime = other.timeWarpUptime;
		this.tnt = other.tnt;
		this.tntPercent = other.tntPercent;
		this.transgression = other.transgression;
		this.valor = other.valor;
		this.valorUptime = other.valorUptime;
		this.vaxo = other.vaxo;
		this.vaxoUptime = other.vaxoUptime;
		this.weaponAps = other.weaponAps;
		this.weaponDamage = other.weaponDamage;
		this.weaponDamagePercent = other.weaponDamagePercent;
		this.weaponDps = other.weaponDps;
		this.weaponIas = other.weaponIas;
		this.weaponMax = other.weaponMax;
		this.weaponMin = other.weaponMin;
		this.weaponType = other.weaponType;
		this.wolf = other.wolf;
		this.wolfUptime = other.wolfUptime;
	}

	public static Map<GemSkill, GemData> copy(Map<GemSkill, GemData> gems) {
		Map<GemSkill, GemData> map = new TreeMap<GemSkill, GemData>();
		
		for (Map.Entry<GemSkill, GemData> e : gems.entrySet()) {
			map.put(e.getKey(), e.getValue().copy());
		}
		
		return map;
	}

	public double getSheetDps() {
		return sheetDps;
	}

	public void setSheetDps(double sheetDps) {
		this.sheetDps = sheetDps;
	}

	public double getAps() {
		return aps;
	}

	public void setAps(double aps) {
		this.aps = aps;
	}

	public double getColdDamage() {
		return getElementalDamage(DamageType.Cold);
	}

	public double getElementalDamage(DamageType type) {
		Double d = elementalDamage.get(type);

		return (d == null) ? 0.0 : d;
	}

	public double getSentryDamage() {
		return getSkillDamage(ActiveSkill.SENTRY);
	}

	public double getSkillDamage(ActiveSkill skill) {
		Double d = skillDamage.get(skill);

		return (d == null) ? 0.0 : d;
	}

	public double getEaDamage() {
		return getSkillDamage(ActiveSkill.EA);
	}

	public double getMsDamage() {
		return getSkillDamage(ActiveSkill.MS);
	}

	public double getCaDamage() {
		return getSkillDamage(ActiveSkill.CA);
	}

	public double getImpDamage() {
		return getSkillDamage(ActiveSkill.IMP);
	}

	public double getChakDamage() {
		return getSkillDamage(ActiveSkill.CHAK);
	}

	public double getFireDamage() {
		return getElementalDamage(DamageType.Fire);
	}

	public double getLightDamage() {
		return getElementalDamage(DamageType.Lightning);
	}

	public double getPhysDamage() {
		return getElementalDamage(DamageType.Physical);
	}

	public double getSentryAps() {
		double gogokIas = isGogok() ? (getGogokStacks() * .01) : 0.0;

		return this.aps * (this.tnt ? (1.0 + this.tntPercent) : 1.0)
				* (1.0 + gogokIas);
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public double getPoisonDamage() {
		return getElementalDamage(DamageType.Poison);
	}

	public double getHolyDamage() {
		return getElementalDamage(DamageType.Holy);
	}

	public double getCritHitDamage() {
		return critHitDamage;
	}

	public void setCritHitDamage(double critHitDamage) {
		this.critHitDamage = critHitDamage;
	}

	public int getDexterity() {
		return equipmentDexterity + paragonDexterity + 7 + (heroLevel * 3);
	}

	public double getEliteDamage() {
		return eliteDamage;
	}

	public void setEliteDamage(double eliteDamage) {
		this.eliteDamage = eliteDamage;
	}

	public boolean isUseEnforcer() {
		return gems.containsKey(GemSkill.Enforcer);
	}

	public int getGemLevel(GemSkill gem) {
		int level = 0;
		
		GemData data = gems.get(gem);
		
		if (data != null)
			level = data.level;
		
		return level;
	}
	
	public int getEnforcerLevel() {
		return getGemLevel(GemSkill.Enforcer);
	}

	public boolean isSteadyAim() {
		return passives.contains(Passive.Steady_Aim);
	}

	public boolean isWolf() {
		return wolf;
	}

	public void setWolf(boolean wolf) {
		this.wolf = wolf;
	}

	public boolean isHexingPants() {
		return hexingPants;
	}

	public void setHexingPants(boolean hexingPants) {
		this.hexingPants = hexingPants;
	}

	public boolean isCharmed() {
		return charmed;
	}

	public void setCharmed(boolean charmed) {
		this.charmed = charmed;
	}

	public boolean isBotp() {
		return gems.containsKey(GemSkill.BotP);
	}

	public int getBotpLevel() {
		return getGemLevel(GemSkill.BotP);
	}

	public boolean isStrongarms() {
		return strongarms;
	}

	public void setStrongarms(boolean strongarms) {
		this.strongarms = strongarms;
	}

	public int getStrongArmsPct() {
		return strongArmsPct;
	}

	public void setStrongArmsPct(int strongArmsPct) {
		this.strongArmsPct = strongArmsPct;
	}

	public boolean isHarringtons() {
		return harringtons;
	}

	public void setHarringtons(boolean harringtons) {
		this.harringtons = harringtons;
	}

	public int getHarringtonsPercent() {
		return harringtonsPercent;
	}

	public void setHarringtonsPercent(int harringtonsPercent) {
		this.harringtonsPercent = harringtonsPercent;
	}

	public boolean isBigBadVoodo() {
		return bigBadVoodo;
	}

	public void setBigBadVoodo(boolean bigBadVoodo) {
		this.bigBadVoodo = bigBadVoodo;
	}

	public boolean isMassConfusion() {
		return massConfusion;
	}

	public void setMassConfusion(boolean massConfusion) {
		this.massConfusion = massConfusion;
	}

	public boolean isPiranhas() {
		return piranhas;
	}

	public void setPiranhas(boolean piranhas) {
		this.piranhas = piranhas;
	}

	public boolean isInnerSanctuary() {
		return innerSanctuary;
	}

	public void setInnerSanctuary(boolean innerSanctuary) {
		this.innerSanctuary = innerSanctuary;
	}

	public boolean isConviction() {
		return conviction;
	}

	public void setConviction(boolean conviction) {
		this.conviction = conviction;
	}

	public boolean isOverawe() {
		return overawe;
	}

	public void setOverawe(boolean overawe) {
		this.overawe = overawe;
	}

	public boolean isCripplingWave() {
		return cripplingWave;
	}

	public void setCripplingWave(boolean cripplingWave) {
		this.cripplingWave = cripplingWave;
	}

	public boolean isRetribution() {
		return retribution;
	}

	public void setRetribution(boolean retribution) {
		this.retribution = retribution;
	}

	public boolean isTransgression() {
		return transgression;
	}

	public void setTransgression(boolean transgression) {
		this.transgression = transgression;
	}

	public boolean isRetaliation() {
		return retaliation;
	}

	public void setRetaliation(boolean retaliation) {
		this.retaliation = retaliation;
	}

	public boolean isCalamityMdf() {
		return calamityMdf;
	}

	public void setCalamityMdf(boolean calamityMdf) {
		this.calamityMdf = calamityMdf;
	}

	public boolean isZeis() {
		return gems.containsKey(GemSkill.Zeis);
	}

	public boolean isCullTheWeak() {
		return passives.contains(Passive.Cull_the_Weak);
	}

	public boolean isBallistics() {
		return passives.contains(Passive.Ballistics);
	}

	public boolean isGrenadier() {
		return passives.contains(Passive.Grenadier);
	}

	public boolean isSingleOut() {
		return passives.contains(Passive.Single_Out);
	}

	public int getNumTargets() {
		return numTargets;
	}

	public void setNumTargets(int numTargets) {
		this.numTargets = numTargets;
	}

	public int getNumAoeTargets() {
		return numAoeTargets;
	}

	public void setNumAoeTargets(int numAoeTargets) {
		this.numAoeTargets = numAoeTargets;
	}

	public int getWeaponMin() {
		return weaponMin;
	}

	public void setWeaponMin(int weaponMin) {
		this.weaponMin = weaponMin;
	}

	public int getWeaponMax() {
		return weaponMax;
	}

	public void setWeaponMax(int weaponMax) {
		this.weaponMax = weaponMax;
	}

	public int getJewelryMin() {
		return jewelryMin;
	}

	public void setJewelryMin(int jewelryMin) {
		this.jewelryMin = jewelryMin;
	}

	public int getJewelryMax() {
		return jewelryMax;
	}

	public void setJewelryMax(int jewelryMax) {
		this.jewelryMax = jewelryMax;
	}

	public BowType getBow() {
		return bow;
	}

	public void setBow(BowType bow) {
		this.bow = bow;
	}

	public int getNumAdditional() {
		return numAdditional;
	}

	public void setNumAdditional(int numAdditional) {
		this.numAdditional = numAdditional;
	}

	public boolean isUseBaneOfTheTrapped() {
		return gems.containsKey(GemSkill.BotT);
	}

	public int getBaneOfTheTrappedLevel() {
		return getGemLevel(GemSkill.BotT);
	}

	public int getDistanceToTarget() {
		return distanceToTarget;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	public int getZeisLevel() {
		return getGemLevel(GemSkill.Zeis);
	}

	public boolean isAmbush() {
		return passives.contains(Passive.Ambush);
	}

	public boolean isArchery() {
		return passives.contains(Passive.Archery);
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public double getWeaponDamage() {
		return this.weaponDamage;
	}

	public void setWeaponDamage(double weaponDamage) {
		this.weaponDamage = weaponDamage;
	}

	public int getTargetSpacing() {
		return targetSpacing;
	}

	public void setTargetSpacing(int targetSpacing) {
		this.targetSpacing = targetSpacing;
	}

	public boolean isGogok() {
		return gems.containsKey(GemSkill.Gogok);
	}

	public int getGogokLevel() {
		return getGemLevel(GemSkill.Gogok);
	}

	public int getGemAttribute(GemSkill gem, String attribute) {
		int value = 0;
		
		GemData data = gems.get(gem);
		
		if ((data != null) && (data.attributes != null)) {
			Integer v = data.attributes.get(attribute);
			
			if (v != null)
				value = v;
		}
		
		return value;
	}
	
	public int getGogokStacks() {
		return getGemAttribute(GemSkill.Gogok, GemSkill.STACKS);
	}

	public double getCdr() {
		return cdr;
	}

	public void setCdr(double cdr) {
		this.cdr = cdr;
	}

	public boolean isFocusedMind() {
		return focusedMind;
	}

	public void setFocusedMind(boolean focusedMind) {
		this.focusedMind = focusedMind;
	}

	public boolean isHysteria() {
		return hysteria;
	}

	public void setHysteria(boolean hysteria) {
		this.hysteria = hysteria;
	}

	public boolean isAnatomy() {
		return anatomy;
	}

	public void setAnatomy(boolean anatomy) {
		this.anatomy = anatomy;
	}

	public boolean isToxin() {
		return gems.containsKey(GemSkill.Toxin);
	}

	public int getToxinLevel() {
		return getGemLevel(GemSkill.Toxin);
	}

	public boolean isPainEnhancer() {
		return gems.containsKey(GemSkill.PainEnhancer);
	}

	public int getPainEnhancerLevel() {
		return getGemLevel(GemSkill.PainEnhancer);
	}

	public int getPainEnhancerStacks() {
		return getGemAttribute(GemSkill.PainEnhancer, GemSkill.BLEEDING);
	}

	public boolean isTaeguk() {
		return gems.containsKey(GemSkill.Taeguk);
	}

	public int getTaegukLevel() {
		return getGemLevel(GemSkill.Taeguk);
	}

	public int getTaegukStacks() {
		return getGemAttribute(GemSkill.Taeguk, GemSkill.STACKS);
	}

	public double getWolfUptime() {
		return wolfUptime;
	}

	public void setWolfUptime(double wolfUptime) {
		this.wolfUptime = wolfUptime;
	}

	public boolean isBbv() {
		return bbv;
	}

	public void setBbv(boolean bbv) {
		this.bbv = bbv;
	}

	public double getBbvUptime() {
		return bbvUptime;
	}

	public void setBbvUptime(double bbvUptime) {
		this.bbvUptime = bbvUptime;
	}

	public double getPercentSlowedChilled() {
		return percentSlowedChilled;
	}

	public void setPercentSlowedChilled(double percentSlowedChilled) {
		this.percentSlowedChilled = percentSlowedChilled;
	}

	public double getPercentControlled() {
		return percentControlled;
	}

	public void setPercentControlled(double percentControlled) {
		this.percentControlled = percentControlled;
	}

	public double getPercentAtLeast10Yards() {
		return percentAtLeast10Yards;
	}

	public void setPercentAtLeast10Yards(double percentAtLeast10Yards) {
		this.percentAtLeast10Yards = percentAtLeast10Yards;
	}

	public double getMassConfusionUptime() {
		return massConfusionUptime;
	}

	public void setMassConfusionUptime(double massConfusionUptime) {
		this.massConfusionUptime = massConfusionUptime;
	}

	public double getPiranhasUptime() {
		return piranhasUptime;
	}

	public void setPiranhasUptime(double piranhasUptime) {
		this.piranhasUptime = piranhasUptime;
	}

	public double getInnerSanctuaryUptime() {
		return innerSanctuaryUptime;
	}

	public void setInnerSanctuaryUptime(double innerSanctuaryUptime) {
		this.innerSanctuaryUptime = innerSanctuaryUptime;
	}

	public double getCripplingWaveUptime() {
		return cripplingWaveUptime;
	}

	public void setCripplingWaveUptime(double cripplingWaveUptime) {
		this.cripplingWaveUptime = cripplingWaveUptime;
	}

	public double getConvictionPassiveUptime() {
		return convictionPassiveUptime;
	}

	public void setConvictionPassiveUptime(double convictionPassiveUptime) {
		this.convictionPassiveUptime = convictionPassiveUptime;
	}

	public double getConvictionActiveUptime() {
		return convictionActiveUptime;
	}

	public void setConvictionActiveUptime(double convictionActiveUptime) {
		this.convictionActiveUptime = convictionActiveUptime;
	}

	public boolean isMeticulousBolts() {
		return meticulousBolts;
	}

	public void setMeticulousBolts(boolean meticulousBolts) {
		this.meticulousBolts = meticulousBolts;
	}

	public double getMeticulousBoltsPercent() {
		return meticulousBoltsPercent;
	}

	public void setMeticulousBoltsPercent(double meticulousBoltsPercent) {
		this.meticulousBoltsPercent = meticulousBoltsPercent;
	}

	public boolean isTnt() {
		return tnt;
	}

	public void setTnt(boolean tnt) {
		this.tnt = tnt;
	}

	public double getTntPercent() {
		return tntPercent;
	}

	public void setTntPercent(double tntPercent) {
		this.tntPercent = tntPercent;
	}

	public TargetSize getTargetSize() {
		return targetSize;
	}

	public void setTargetSize(TargetSize targetSize) {
		this.targetSize = targetSize;
	}

	public double getTotalEliteDamage() {
		return eliteDamage + ((this.isBotp() && (getBotpLevel() >= 25)) ? 0.15 : 0.0);
	}

	public double getMfdUptime() {
		return mfdUptime;
	}

	public void setMfdUptime(double mfdUptime) {
		this.mfdUptime = mfdUptime;
	}

	public double getCalamityUptime() {
		return calamityUptime;
	}

	public void setCalamityUptime(double calamityUptime) {
		this.calamityUptime = calamityUptime;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public double getMfdAddUptime() {
		return mfdAddUptime;
	}

	public void setMfdAddUptime(double mfdAddUptime) {
		this.mfdAddUptime = mfdAddUptime;
	}

	public boolean isSlamDance() {
		return slamDance;
	}

	public void setSlamDance(boolean slamDance) {
		this.slamDance = slamDance;
	}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}

	public double getValorUptime() {
		return valorUptime;
	}

	public void setValorUptime(double valorUptime) {
		this.valorUptime = valorUptime;
	}

	public double getRetributionUptime() {
		return retributionUptime;
	}

	public void setRetributionUptime(double retributionUptime) {
		this.retributionUptime = retributionUptime;
	}

	public boolean isHarrington() {
		return harrington;
	}

	public void setHarrington(boolean harrington) {
		this.harrington = harrington;
	}

	public double getHarringtonPercent() {
		return harringtonPercent;
	}

	public void setHarringtonPercent(double harringtonPercent) {
		this.harringtonPercent = harringtonPercent;
	}

	public double getHarringtonUptime() {
		return harringtonUptime;
	}

	public void setHarringtonUptime(double harringtonUptime) {
		this.harringtonUptime = harringtonUptime;
	}

	public boolean isStrongarm() {
		return strongarm;
	}

	public void setStrongarm(boolean strongarm) {
		this.strongarm = strongarm;
	}

	public double getStrongarmPercent() {
		return strongarmPercent;
	}

	public void setStrongarmPercent(double strongarmPercent) {
		this.strongarmPercent = strongarmPercent;
	}

	public double getStrongarmUptime() {
		return strongarmUptime;
	}

	public void setStrongarmUptime(double strongarmUptime) {
		this.strongarmUptime = strongarmUptime;
	}

	public double getHexingPantsPercent() {
		return hexingPantsPercent;
	}

	public void setHexingPantsPercent(double hexingPantsPercent) {
		this.hexingPantsPercent = hexingPantsPercent;
	}

	public double getHexingPantsUptime() {
		return hexingPantsUptime;
	}

	public void setHexingPantsUptime(double hexingPantsUptime) {
		this.hexingPantsUptime = hexingPantsUptime;
	}

	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getHero() {
		return hero;
	}

	public void setHero(int hero) {
		this.hero = hero;
	}

	public int getParagonIAS() {
		return paragonIAS;
	}

	public void setParagonIAS(int paragonIAS) {
		this.paragonIAS = paragonIAS;
	}

	public int getParagonCDR() {
		return paragonCDR;
	}

	public void setParagonCDR(int paragonCDR) {
		this.paragonCDR = paragonCDR;
	}

	public int getParagonCC() {
		return paragonCC;
	}

	public void setParagonCC(int paragonCC) {
		this.paragonCC = paragonCC;
	}

	public int getParagonCHD() {
		return paragonCHD;
	}

	public void setParagonCHD(int paragonCHD) {
		this.paragonCHD = paragonCHD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCaltropsUptime() {
		return caltropsUptime;
	}

	public void setCaltropsUptime(double caltropsUptime) {
		this.caltropsUptime = caltropsUptime;
	}

	public Map<ActiveSkill, Rune> getSkills() {
		return skills;
	}

	public void setSkills(Map<ActiveSkill, Rune> skills) {
		this.skills = skills;
	}

	public Map<String, Integer> getCdrData() {
		return cdrData;
	}

	public void setCdrData(Map<String, Integer> cdrData) {
		this.cdrData = cdrData;
	}

	public boolean isBornsCdr() {
		return bornsCdr;
	}

	public void setBornsCdr(boolean bornsCdr) {
		this.bornsCdr = bornsCdr;
	}

	public boolean isCrimsonCdr() {
		return crimsonCdr;
	}

	public void setCrimsonCdr(boolean crimsonCdr) {
		this.crimsonCdr = crimsonCdr;
	}

	public boolean isLeorics() {
		return leorics;
	}

	public void setLeorics(boolean leorics) {
		this.leorics = leorics;
	}

	public double getLeoricsPercent() {
		return leoricsPercent;
	}

	public void setLeoricsPercent(double leoricsPercent) {
		this.leoricsPercent = leoricsPercent;
	}

	public GemLevel getDiamond() {
		return diamond;
	}

	public void setDiamond(GemLevel diamond) {
		this.diamond = diamond;
	}

	public double getBaseMin() {
		return baseMin;
	}

	public void setBaseMin(double baseMin) {
		this.baseMin = baseMin;
	}

	public double getBaseMax() {
		return baseMax;
	}

	public void setBaseMax(double baseMax) {
		this.baseMax = baseMax;
	}

	public double getAddMin() {
		return addMin;
	}

	public void setAddMin(double addMin) {
		this.addMin = addMin;
	}

	public double getAddMax() {
		return addMax;
	}

	public void setAddMax(double addMax) {
		this.addMax = addMax;
	}

	public double getJewelMin() {
		return jewelMin;
	}

	public void setJewelMin(double jewelMin) {
		this.jewelMin = jewelMin;
	}

	public double getJewelMax() {
		return jewelMax;
	}

	public void setJewelMax(double jewelMax) {
		this.jewelMax = jewelMax;
	}

	public boolean isRoyalRing() {
		return royalRing;
	}

	public void setRoyalRing(boolean royalRing) {
		this.royalRing = royalRing;
	}

	public Map<String, Integer> getSetCounts() {
		return setCounts;
	}

	public void setSetCounts(Map<String, Integer> setCounts) {
		this.setCounts = setCounts;
	}

	public Map<String, D3Set> getSets() {
		return sets;
	}

	public void setSets(Map<String, D3Set> sets) {
		this.sets = sets;
	}

	public double getWeaponIas() {
		return weaponIas;
	}

	public void setWeaponIas(double weaponIas) {
		this.weaponIas = weaponIas;
	}

	public double getEquipIas() {
		return equipIas;
	}

	public void setEquipIas(double equipIas) {
		this.equipIas = equipIas;
	}

	public double getWeaponDamagePercent() {
		return weaponDamagePercent;
	}

	public void setWeaponDamagePercent(double weaponDamagePercent) {
		this.weaponDamagePercent = weaponDamagePercent;
	}

	public int getParagon() {
		return paragon;
	}

	public void setParagon(int paragon) {
		this.paragon = paragon;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public boolean isSeasonal() {
		return seasonal;
	}

	public void setSeasonal(boolean seasonal) {
		this.seasonal = seasonal;
	}

	public boolean isHardcore() {
		return hardcore;
	}

	public void setHardcore(boolean hardcore) {
		this.hardcore = hardcore;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getWeaponAps() {
		return weaponAps;
	}

	public void setWeaponAps(double weaponAps) {
		this.weaponAps = weaponAps;
	}

	public double getWeaponDps() {
		return weaponDps;
	}

	public void setWeaponDps(double weaponDps) {
		this.weaponDps = weaponDps;
	}

	public int getBp() {
		return bp;
	}

	public void setBp(int bp) {
		this.bp = bp;
	}

	public double getSentryDps() {
		return sentryDps;
	}

	public void setSentryDps(double sentryDps) {
		this.sentryDps = sentryDps;
	}

	public double getEquipCritChance() {
		return equipCritChance;
	}

	public void setEquipCritChance(double equipCritChance) {
		this.equipCritChance = equipCritChance;
	}

	public double getEquipCritDamage() {
		return equipCritDamage;
	}

	public void setEquipCritDamage(double equipCritDamage) {
		this.equipCritDamage = equipCritDamage;
	}

	public int getNumSentries() {
		return 2 + (hasBombardiers ? 2 : 0) + (isCustomEngineering() ? 1 : 0);
	}

	public boolean isHasBombardiers() {
		return hasBombardiers;
	}

	public void setHasBombardiers(boolean hasBombardiers) {
		this.hasBombardiers = hasBombardiers;
	}

	public boolean isCustomEngineering() {
		return passives.contains(Passive.Custom_Engineering);
	}

	public double getHatredPerSecond() {
		return hatredPerSecond;
	}

	public void setHatredPerSecond(double hatredPerSecond) {
		this.hatredPerSecond = hatredPerSecond;
	}

	public double getMaxHatred() {
		return 125 + (this.paragonHatred * 0.5)
				+ (this.isBloodVengeance() ? 25 : 0);
	}

	public boolean isKridershot() {
		return kridershot;
	}

	public void setKridershot(boolean kridershot) {
		this.kridershot = kridershot;
	}

	public boolean isSpines() {
		return spines;
	}

	public void setSpines(boolean spines) {
		this.spines = spines;
	}

	public int getKridershotHatred() {
		return kridershotHatred;
	}

	public void setKridershotHatred(int kridershotHatred) {
		this.kridershotHatred = kridershotHatred;
	}

	public int getSpinesHatred() {
		return spinesHatred;
	}

	public void setSpinesHatred(int spinesHatred) {
		this.spinesHatred = spinesHatred;
	}

	public double getHaDamage() {
		return getSkillDamage(ActiveSkill.HA);
	}

	public double getEsDamage() {
		return getSkillDamage(ActiveSkill.ES);
	}

	public double getBolasDamage() {
		return getSkillDamage(ActiveSkill.BOLAS);
	}

	public double getEfDamage() {
		return getSkillDamage(ActiveSkill.EF);
	}

	public double getGrenadeDamage() {
		return getSkillDamage(ActiveSkill.GRENADE);
	}

	public int getParagonHatred() {
		return paragonHatred;
	}

	public void setParagonHatred(int paragonHatred) {
		this.paragonHatred = paragonHatred;
	}

	public int getParagonRCR() {
		return paragonRCR;
	}

	public void setParagonRCR(int paragonRCR) {
		this.paragonRCR = paragonRCR;
	}

	public Map<String, Integer> getRcrData() {
		return rcrData;
	}

	public void setRcrData(Map<String, Integer> rcrData) {
		this.rcrData = rcrData;
	}

	public boolean isCrimsonRcr() {
		return crimsonRcr;
	}

	public void setCrimsonRcr(boolean crimsonRcr) {
		this.crimsonRcr = crimsonRcr;
	}

	public boolean isPridesFall() {
		return pridesFall;
	}

	public void setPridesFall(boolean pridesFall) {
		this.pridesFall = pridesFall;
	}

	public double getRcr() {
		return rcr;
	}

	public void setRcr(double rcr) {
		this.rcr = rcr;
	}

	public boolean isBloodVengeance() {
		return passives.contains(Passive.Blood_Vengeance);
	}

	public boolean isNightStalker() {
		return passives.contains(Passive.Night_Stalker);
	}

	public boolean isReapersWraps() {
		return reapersWraps;
	}

	public void setReapersWraps(boolean reapersWraps) {
		this.reapersWraps = reapersWraps;
	}

	public double getReapersWrapsPercent() {
		return reapersWrapsPercent;
	}

	public void setReapersWrapsPercent(double reapersWrapsPercent) {
		this.reapersWrapsPercent = reapersWrapsPercent;
	}

	public int getNumHealthGlobes() {
		return numHealthGlobes;
	}

	public void setNumHealthGlobes(int numHealthGlobes) {
		this.numHealthGlobes = numHealthGlobes;
	}

	public boolean isCindercoat() {
		return cindercoat;
	}

	public void setCindercoat(boolean cindercoat) {
		this.cindercoat = cindercoat;
	}

	public double getCindercoatRCR() {
		return cindercoatRCR;
	}

	public void setCindercoatRCR(double cindercoatRCR) {
		this.cindercoatRCR = cindercoatRCR;
	}

	public boolean isInspire() {
		return inspire;
	}

	public void setInspire(boolean inspire) {
		this.inspire = inspire;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isOdysseysEnd() {
		return odysseysEnd;
	}

	public void setOdysseysEnd(boolean odysseysEnd) {
		this.odysseysEnd = odysseysEnd;
	}

	public double getOdysseysEndPercent() {
		return odysseysEndPercent;
	}

	public void setOdysseysEndPercent(double odysseysEndPercent) {
		this.odysseysEndPercent = odysseysEndPercent;
	}

	public double getOdysseysEndUptime() {
		return odysseysEndUptime;
	}

	public void setOdysseysEndUptime(double odysseysEndUptime) {
		this.odysseysEndUptime = odysseysEndUptime;
	}

	public double getCompanionDamage() {
		return getSkillDamage(ActiveSkill.Companion);
	}

	public int getParagonDexterity() {
		return paragonDexterity;
	}

	public void setParagonDexterity(int paragonDexterity) {
		this.paragonDexterity = paragonDexterity;
	}

	public int getEquipmentDexterity() {
		return equipmentDexterity;
	}

	public void setEquipmentDexterity(int equipmentDexterity) {
		this.equipmentDexterity = equipmentDexterity;
	}

	public int getHeroLevel() {
		return heroLevel;
	}

	public void setHeroLevel(int heroLevel) {
		this.heroLevel = heroLevel;
	}

	public boolean isHelltrapper() {
		return helltrapper;
	}

	public void setHelltrapper(boolean helltrapper) {
		this.helltrapper = helltrapper;
	}

	public WeaponType getOffHand_weaponType() {
		return offHand_weaponType;
	}

	public void setOffHand_weaponType(WeaponType offHand_weaponType) {
		this.offHand_weaponType = offHand_weaponType;
	}

	public double getOffHand_baseMin() {
		return offHand_baseMin;
	}

	public void setOffHand_baseMin(double offHand_baseMin) {
		this.offHand_baseMin = offHand_baseMin;
	}

	public double getOffHand_baseMax() {
		return offHand_baseMax;
	}

	public void setOffHand_baseMax(double offHand_baseMax) {
		this.offHand_baseMax = offHand_baseMax;
	}

	public double getOffHand_addMin() {
		return offHand_addMin;
	}

	public void setOffHand_addMin(double offHand_addMin) {
		this.offHand_addMin = offHand_addMin;
	}

	public double getOffHand_addMax() {
		return offHand_addMax;
	}

	public void setOffHand_addMax(double offHand_addMax) {
		this.offHand_addMax = offHand_addMax;
	}

	public double getOffHand_weaponDamagePercent() {
		return offHand_weaponDamagePercent;
	}

	public void setOffHand_weaponDamagePercent(
			double offHand_weaponDamagePercent) {
		this.offHand_weaponDamagePercent = offHand_weaponDamagePercent;
	}

	public double getOffHand_weaponIas() {
		return offHand_weaponIas;
	}

	public void setOffHand_weaponIas(double offHand_weaponIas) {
		this.offHand_weaponIas = offHand_weaponIas;
	}

	public double getOffHand_weaponDamage() {
		return offHand_weaponDamage;
	}

	public void setOffHand_weaponDamage(double offHand_weaponDamage) {
		this.offHand_weaponDamage = offHand_weaponDamage;
	}

	public double getOffHand_weaponAps() {
		return offHand_weaponAps;
	}

	public void setOffHand_weaponAps(double offHand_weaponAps) {
		this.offHand_weaponAps = offHand_weaponAps;
	}

	public double getOffHand_weaponDps() {
		return offHand_weaponDps;
	}

	public void setOffHand_weaponDps(double offHand_weaponDps) {
		this.offHand_weaponDps = offHand_weaponDps;
	}

	public double getOffHand_aps() {
		return offHand_aps;
	}

	public void setOffHand_aps(double offHand_aps) {
		this.offHand_aps = offHand_aps;
	}

	public double getOffHand_dps() {
		return offHand_dps;
	}

	public void setOffHand_dps(double offHand_dps) {
		this.offHand_dps = offHand_dps;
	}

	public double getSpikeTrapDamage() {
		return getSkillDamage(ActiveSkill.ST);
	}

	public double getHelltrapperPercent() {
		return helltrapperPercent;
	}

	public void setHelltrapperPercent(double helltrapperPercent) {
		this.helltrapperPercent = helltrapperPercent;
	}

	public boolean isVaxo() {
		return vaxo;
	}

	public void setVaxo(boolean vaxo) {
		this.vaxo = vaxo;
	}

	public double getAreaDamageEquipment() {
		return areaDamageEquipment;
	}

	public void setAreaDamageEquipment(double areaDamageEquipment) {
		this.areaDamageEquipment = areaDamageEquipment;
	}

	public int getParagonAD() {
		return paragonAD;
	}

	public void setParagonAD(int paragonAD) {
		this.paragonAD = paragonAD;
	}

	public double getAreaDamage() {
		return areaDamageEquipment + (paragonAD * .01);
	}

	public double getRovDamage() {
		return getSkillDamage(ActiveSkill.RoV);
	}

	// public int getRovKilled() {
	// return rovKilled;
	// }
	//
	// public void setRovKilled(int rovKilled) {
	// this.rovKilled = rovKilled;
	// }
	//
	public int getNumNats() {
		return numNats;
	}

	public void setNumNats(int numNats) {
		this.numNats = numNats;
	}

	public int getEquipmentDiscipline() {
		return equipmentDiscipline;
	}

	public void setEquipmentDiscipline(int equipmentDiscipline) {
		this.equipmentDiscipline = equipmentDiscipline;
	}

	public boolean isBastions() {
		return bastions;
	}

	public void setBastions(boolean bastions) {
		this.bastions = bastions;
	}

	public boolean hasGenerator() {

		for (Map.Entry<ActiveSkill, Rune> e : this.getSkills().entrySet()) {
			ActiveSkill s = e.getKey();

			if (s.getSkillType() == SkillType.Primary)
				return true;

			SkillAndRune skr = new SkillAndRune(s, e.getValue());

			if (skr.getHatred(this) > 0)
				return true;
		}

		return false;
	}

	public boolean hasSpender() {
		for (Map.Entry<ActiveSkill, Rune> e : this.getSkills().entrySet()) {
			ActiveSkill s = e.getKey();
			SkillAndRune skr = new SkillAndRune(s, e.getValue());

			if ((s.getSkillType() == SkillType.Channeled)
					|| ((s.getSkillType() == SkillType.Spender) && (skr
							.getHatred(this) < 0)))
				return true;
		}

		return false;
	}

	public int getIceblinkLevel() {
		return getGemLevel(GemSkill.Iceblink);
	}

	public boolean isIceblink() {
		return gems.containsKey(GemSkill.Iceblink);
	}

	public boolean isCrashingRain() {
		return crashingRain;
	}

	public void setCrashingRain(boolean crashingRain) {
		this.crashingRain = crashingRain;
	}

	public Set<Passive> getPassives() {
		return passives;
	}

	public void setPassives(Set<Passive> passives) {
		this.passives = passives;
	}

	public Map<DamageType, Double> getElementalDamage() {
		return elementalDamage;
	}

	public void setElementalDamage(Map<DamageType, Double> elementalDamage) {
		this.elementalDamage = elementalDamage;
	}

	public Map<ActiveSkill, Double> getSkillDamage() {
		return skillDamage;
	}

	public void setSkillDamage(Map<ActiveSkill, Double> skillDamage) {
		this.skillDamage = skillDamage;
	}

	public Double getFokDamage() {
		return this.getSkillDamage(ActiveSkill.FoK);
	}

	public Double getRFDamage() {
		return this.getSkillDamage(ActiveSkill.RF);
	}

	public Double getStrafeDamage() {
		return this.getSkillDamage(ActiveSkill.Strafe);
	}

	public Double getVengeanceDamage() {
		return this.getSkillDamage(ActiveSkill.Vengeance);
	}

	public boolean isSentry() {
		return skills.containsKey(ActiveSkill.SENTRY);
	}

	public Rune getSentryRune() {
		return skills.get(ActiveSkill.SENTRY);
	}

	public boolean isRov() {
		return skills.containsKey(ActiveSkill.RoV);
	}

	public boolean isPreparation() {
		return skills.containsKey(ActiveSkill.Preparation);
	}

	public Rune getPreparationRune() {
		return skills.get(ActiveSkill.Preparation);
	}

	public boolean isCompanion() {
		return skills.containsKey(ActiveSkill.Companion);
	}

	public Rune getCompanionRune() {
		return skills.get(ActiveSkill.Companion);
	}

	public boolean isMarked() {
		return skills.containsKey(ActiveSkill.MFD);
	}

	public Rune getMfdRune() {
		return skills.get(ActiveSkill.MFD);
	}

	public Rune getRovRune() {
		return skills.get(ActiveSkill.RoV);
	}

	public boolean isCaltrops() {
		return skills.containsKey(ActiveSkill.Caltrops);
	}

	public Rune getCaltropsRune() {
		return skills.get(ActiveSkill.Caltrops);
	}

	public boolean isSpikeTrap() {
		return skills.containsKey(ActiveSkill.ST);
	}

	public Rune getSpikeTrapRune() {
		return skills.get(ActiveSkill.ST);
	}

	public int getNumUe() {
		return numUe;
	}

	public void setNumUe(int numUe) {
		this.numUe = numUe;
	}

	public double getMaxDiscipline() {
		double d = this.equipmentDiscipline + 30.0;

		Rune r = skills.get(ActiveSkill.Preparation);

		if (r == Rune.Invigoration)
			d += 15.0;

		return d;
	}

	public double getCrashingRainPercent() {
		return crashingRainPercent;
	}

	public void setCrashingRainPercent(double crashingRainPercent) {
		this.crashingRainPercent = crashingRainPercent;
	}

	public boolean isDml() {
		return dml;
	}

	public void setDml(boolean dml) {
		this.dml = dml;
	}

	public double getDmlPercent() {
		return dmlPercent;
	}

	public void setDmlPercent(double dmlPercent) {
		this.dmlPercent = dmlPercent;
	}

	public boolean isCoe() {
		return coe;
	}

	public void setCoe(boolean coe) {
		this.coe = coe;
	}

	public double getCoePercent() {
		return coePercent;
	}

	public void setCoePercent(double coePercent) {
		this.coePercent = coePercent;
	}

	public double getVaxoUptime() {
		return vaxoUptime;
	}

	public void setVaxoUptime(double vaxoUptime) {
		this.vaxoUptime = vaxoUptime;
	}

	public MonsterType getPrimaryTargetType() {
		return primaryTargetType;
	}

	public void setPrimaryTargetType(MonsterType primaryTargetType) {
		this.primaryTargetType = primaryTargetType;
	}

	public MonsterType getAdditionalTargetType() {
		return additionalTargetType;
	}

	public void setAdditionalTargetType(MonsterType additionalTargetType) {
		this.additionalTargetType = additionalTargetType;
	}

	public int getRiftLevel() {
		return riftLevel;
	}

	public void setRiftLevel(int riftLevel) {
		this.riftLevel = riftLevel;
	}

	public long getPrimaryTargetHealth() {
		return primaryTargetHealth;
	}

	public void setPrimaryTargetHealth(long primaryTargetHealth) {
		this.primaryTargetHealth = primaryTargetHealth;
	}

	public long getAdditionalTargetHealth() {
		return additionalTargetHealth;
	}

	public void setAdditionalTargetHealth(long additionalTargetHealth) {
		this.additionalTargetHealth = additionalTargetHealth;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public boolean isTimeWarp() {
		return timeWarp;
	}

	public void setTimeWarp(boolean timeWarp) {
		this.timeWarp = timeWarp;
	}

	public boolean isStretchTime() {
		return stretchTime;
	}

	public void setStretchTime(boolean stretchTime) {
		this.stretchTime = stretchTime;
	}

	public double getTimeWarpUptime() {
		return timeWarpUptime;
	}

	public void setTimeWarpUptime(double timeWarpUptime) {
		this.timeWarpUptime = timeWarpUptime;
	}

	public double getStretchTimeUptime() {
		return stretchTimeUptime;
	}

	public void setStretchTimeUptime(double strecthTimeUptime) {
		this.stretchTimeUptime = strecthTimeUptime;
	}

	public boolean isSimplicity() {
		return gems.containsKey(GemSkill.Simplicity);
	}
	
	public int getSimplicityLevel() {
		return getGemLevel(GemSkill.Simplicity);
	}

	public Map<GemSkill, GemData> getGems() {
		return gems;
	}

	public void setGems(Map<GemSkill, GemData> gems) {
		this.gems = gems;
	}
}
