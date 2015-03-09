package com.dawg6.web.sentry.shared.calculator;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation.D3Set;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;

public class CharacterData implements Serializable {

	private static final long serialVersionUID = -7542480040314924139L;
	
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
	private double coldDamage;
	private double fireDamage;
	private double lightDamage;
	private double physDamage;
	private double poisonDamage;
	private double sentryDamage;
	private double eaDamage;
	private double msDamage;
	private double caDamage;
	private double impDamage;
	private double haDamage;
	private double esDamage;
	private double bolasDamage;
	private double efDamage;
	private double grenadeDamage;
	private double chakDamage;
	private double chillDamage;
	private double critChance;
	private double critHitDamage;
	private double dexterity;
	private double eliteDamage;
	private boolean useEnforcer;
	private int enforcerLevel;
	private boolean steadyAim;
	private boolean mfdSkill;
	private boolean wolf;
	private boolean hexingPants;
	private boolean charmed;
	private boolean botp;
	private int botpLevel;
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
	private boolean zeis;
	private int zeisLevel;
	private boolean cullTheWeak;
	private boolean Ballistics;
	private boolean grenadier;
	private boolean singleOut;
	private boolean caltrops;
	private int numTargets;
	private int numAoeTargets;
	private int weaponMin;
	private int weaponMax;
	private int jewelryMin;
	private int jewelryMax;
	private BowType bow;
	private int numAdditional;
	private boolean useBaneOfTheTrapped;
	private int baneOfTheTrappedLevel;
	private boolean marked;
	private int distanceToTarget;
	private boolean ambush;
	private boolean archery;
	private WeaponType weaponType;
	private double weaponDamage;
	private int targetSpacing;
	private boolean gogok;
	private int gogokLevel;
	private int gogokStacks;
	private double cdr;
	private double rcr;
	private boolean focusedMind;
	private boolean hysteria;
	private boolean anatomy;
	private boolean toxin;
	private int toxinLevel;
	private boolean painEnhancer;
	private int painEnhancerLevel;
	private int painEnhancerStacks;
	private boolean taeguk;
	private int taegukLevel;
	private int taegukStacks;
	private double wolfUptime;
	private boolean bbv;
	private double bbvUptime;
	private double massConfusionUptime;
	private double botpUptime;
	private double percentSlowedChilled;
	private double percentControlled;
	private double percentAtLeast10Yards;
	private double percentAbove75;
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
	private Rune mfdRune;
	private double mfdUptime;
	private double mfdAddUptime;
	private double calamityUptime;
	private Target targetType;
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
	private Set<SkillAndRune> skills;
	private Rune sentryRune;
	private Map<String, Integer> cdrData;
	private Map<String, Integer> rcrData;
	private boolean bornsCdr;
	private boolean crimsonCdr;
	private boolean crimsonRcr;
	private boolean leorics;
	private double leoricsPercent;
	private boolean pridesFall;
	private GemLevel diamond;
	private double baseMin;
	private double baseMax;
	private double addMin;
	private double addMax;
	private double jewelMin;
	private double jewelMax;
	private boolean royalRing;
	private Map<String, Integer> setCounts;
	private Map<String, D3Set> sets;
	private double weaponIas;
	private double equipIas;
	private double weaponDamagePercent;
	private int paragon;
	private String heroName;
	private boolean seasonal;
	private boolean hardcore;
	private boolean dead;
	private int level;
	private double weaponDps;
	private double weaponAps;
	private int bp;
	private double sentryDps;
	private double equipCritChance;
	private double equipCritDamage;
	private boolean hasBombardiers;
	private boolean customEngineering;
	private double hatredPerSecond;
	private double maxHatred;
	private int numMarauders;
	private boolean kridershot;
	private boolean spines;
	private int kridershotHatred;
	private int spinesHatred;
	
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
		percentAbove75 = 0.25;
		distanceToTarget = 25;
		targetSize = TargetSize.Medium;
		botpUptime = 1.0;
		painEnhancerStacks = 1;
		gogokStacks = 1;
		taegukStacks = 1;
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
		caltropsUptime = 1.0;
	}
	
	public CharacterData() {
		
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
		return coldDamage;
	}

	public void setColdDamage(double coldDamage) {
		this.coldDamage = coldDamage;
	}

	public double getSentryDamage() {
		return sentryDamage;
	}

	public void setSentryDamage(double sentryDamage) {
		this.sentryDamage = sentryDamage;
	}

	public double getEaDamage() {
		return eaDamage;
	}

	public void setEaDamage(double eaDamage) {
		this.eaDamage = eaDamage;
	}

	public double getMsDamage() {
		return msDamage;
	}

	public void setMsDamage(double msDamage) {
		this.msDamage = msDamage;
	}

	public double getCaDamage() {
		return caDamage;
	}

	public void setCaDamage(double caDamage) {
		this.caDamage = caDamage;
	}

	public double getImpDamage() {
		return impDamage;
	}

	public void setImpDamage(double impDamage) {
		this.impDamage = impDamage;
	}

	public double getChakDamage() {
		return chakDamage;
	}

	public void setChakDamage(double chakDamage) {
		this.chakDamage = chakDamage;
	}
	
	public double getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(double fireDamage) {
		this.fireDamage = fireDamage;
	}

	public double getLightDamage() {
		return lightDamage;
	}

	public void setLightDamage(double lightDamage) {
		this.lightDamage = lightDamage;
	}

	public double getPhysDamage() {
		return physDamage;
	}

	public void setPhysDamage(double physDamage) {
		this.physDamage = physDamage;
	}

	public double getSentryAps() {
		double gogokIas = gogok ? (gogokStacks * .01) : 0.0;
		
		return this.aps * (this.tnt ? (1.0 + this.tntPercent) : 1.0) * (1.0 + gogokIas);
	}
	
	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public double getPoisonDamage() {
		return poisonDamage;
	}

	public void setPoisonDamage(double poisonDamage) {
		this.poisonDamage = poisonDamage;
	}

	public double getCritHitDamage() {
		return critHitDamage;
	}

	public void setCritHitDamage(double critHitDamage) {
		this.critHitDamage = critHitDamage;
	}

	public double getDexterity() {
		return dexterity;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getEliteDamage() {
		return eliteDamage;
	}

	public void setEliteDamage(double eliteDamage) {
		this.eliteDamage = eliteDamage;
	}

	public boolean isUseEnforcer() {
		return useEnforcer;
	}

	public void setUseEnforcer(boolean useEnforcer) {
		this.useEnforcer = useEnforcer;
	}

	public int getEnforcerLevel() {
		return enforcerLevel;
	}

	public void setEnforcerLevel(int enforcerLevel) {
		this.enforcerLevel = enforcerLevel;
	}

	public boolean isSteadyAim() {
		return steadyAim;
	}

	public void setSteadyAim(boolean steadyAim) {
		this.steadyAim = steadyAim;
	}

	public boolean isMfdSkill() {
		return mfdSkill;
	}

	public void setMfdSkill(boolean mfdSkill) {
		this.mfdSkill = mfdSkill;
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
		return botp;
	}

	public void setBotp(boolean botp) {
		this.botp = botp;
	}

	public int getBotpLevel() {
		return botpLevel;
	}

	public void setBotpLevel(int botpLevel) {
		this.botpLevel = botpLevel;
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
		return zeis;
	}

	public void setZeis(boolean zeis) {
		this.zeis = zeis;
	}

	public boolean isCullTheWeak() {
		return cullTheWeak;
	}

	public void setCullTheWeak(boolean cullOfTheWeak) {
		this.cullTheWeak = cullOfTheWeak;
	}

	public boolean isBallistics() {
		return Ballistics;
	}

	public void setBallistics(boolean ballistics) {
		Ballistics = ballistics;
	}

	public boolean isGrenadier() {
		return grenadier;
	}

	public void setGrenadier(boolean grenadier) {
		this.grenadier = grenadier;
	}

	public boolean isSingleOut() {
		return singleOut;
	}

	public void setSingleOut(boolean singleOut) {
		this.singleOut = singleOut;
	}

	public boolean isCaltrops() {
		return caltrops;
	}

	public void setCaltrops(boolean caltrops) {
		this.caltrops = caltrops;
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

	public double getChillDamage() {
		return chillDamage;
	}

	public void setChillDamage(double chillDamage) {
		this.chillDamage = chillDamage;
	}

	public boolean isUseBaneOfTheTrapped() {
		return useBaneOfTheTrapped;
	}

	public void setUseBaneOfTheTrapped(boolean useBaneOfTheTrapped) {
		this.useBaneOfTheTrapped = useBaneOfTheTrapped;
	}

	public int getBaneOfTheTrappedLevel() {
		return baneOfTheTrappedLevel;
	}

	public void setBaneOfTheTrappedLevel(int baneOfTheTrappedLevel) {
		this.baneOfTheTrappedLevel = baneOfTheTrappedLevel;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public int getDistanceToTarget() {
		return distanceToTarget;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	public int getZeisLevel() {
		return zeisLevel;
	}

	public void setZeisLevel(int zeisLevel) {
		this.zeisLevel = zeisLevel;
	}

	public boolean isAmbush() {
		return ambush;
	}

	public void setAmbush(boolean ambush) {
		this.ambush = ambush;
	}

	public boolean isArchery() {
		return archery;
	}

	public void setArchery(boolean archery) {
		this.archery = archery;
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
		return gogok;
	}

	public void setGogok(boolean gogok) {
		this.gogok = gogok;
	}

	public int getGogokLevel() {
		return gogokLevel;
	}

	public void setGogokLevel(int gogokLevel) {
		this.gogokLevel = gogokLevel;
	}

	public int getGogokStacks() {
		return gogokStacks;
	}

	public void setGogokStacks(int gogokStacks) {
		this.gogokStacks = gogokStacks;
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
		return toxin;
	}

	public void setToxin(boolean toxin) {
		this.toxin = toxin;
	}

	public int getToxinLevel() {
		return toxinLevel;
	}

	public void setToxinLevel(int toxinLevel) {
		this.toxinLevel = toxinLevel;
	}

	public boolean isPainEnhancer() {
		return painEnhancer;
	}

	public void setPainEnhancer(boolean painEnhancer) {
		this.painEnhancer = painEnhancer;
	}

	public int getPainEnhancerLevel() {
		return painEnhancerLevel;
	}

	public void setPainEnhancerLevel(int painEnhancerLevel) {
		this.painEnhancerLevel = painEnhancerLevel;
	}

	public int getPainEnhancerStacks() {
		return painEnhancerStacks;
	}

	public void setPainEnhancerStacks(int painEnhancerStacks) {
		this.painEnhancerStacks = painEnhancerStacks;
	}

	public boolean isTaeguk() {
		return taeguk;
	}

	public void setTaeguk(boolean taeguk) {
		this.taeguk = taeguk;
	}

	public int getTaegukLevel() {
		return taegukLevel;
	}

	public void setTaegukLevel(int taegukLevel) {
		this.taegukLevel = taegukLevel;
	}

	public int getTaegukStacks() {
		return taegukStacks;
	}

	public void setTaegukStacks(int taegukStacks) {
		this.taegukStacks = taegukStacks;
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

	public double getBotpUptime() {
		return botpUptime;
	}

	public void setBotpUptime(double botpUptime) {
		this.botpUptime = botpUptime;
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

	public double getPercentAbove75() {
		return percentAbove75;
	}

	public void setPercentAbove75(double percentAbove75) {
		this.percentAbove75 = percentAbove75;
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
		return eliteDamage + ((botp && (botpLevel >= 25)) ? 0.15 : 0.0);
	}

	public Rune getMfdRune() {
		return mfdRune;
	}

	public void setMfdRune(Rune mfdRune) {
		this.mfdRune = mfdRune;
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

	public Target getTargetType() {
		return targetType;
	}

	public void setTargetType(Target targetType) {
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

	public Set<SkillAndRune> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillAndRune> skills) {
		this.skills = skills;
	}

	public Rune getSentryRune() {
		return sentryRune;
	}

	public void setSentryRune(Rune sentryRune) {
		this.sentryRune = sentryRune;
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
		return 2 + (hasBombardiers ? 2 : 0) + (customEngineering ? 1 : 0);
	}

	public boolean isHasBombardiers() {
		return hasBombardiers;
	}

	public void setHasBombardiers(boolean hasBombardiers) {
		this.hasBombardiers = hasBombardiers;
	}

	public boolean isCustomEngineering() {
		return customEngineering;
	}

	public void setCustomEngineering(boolean customEngineering) {
		this.customEngineering = customEngineering;
	}

	public double getHatredPerSecond() {
		return hatredPerSecond;
	}

	public void setHatredPerSecond(double hatredPerSecond) {
		this.hatredPerSecond = hatredPerSecond;
	}

	public double getMaxHatred() {
		return maxHatred;
	}

	public void setMaxHatred(double maxHatred) {
		this.maxHatred = maxHatred;
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
		return haDamage;
	}

	public void setHaDamage(double haDamage) {
		this.haDamage = haDamage;
	}

	public double getEsDamage() {
		return esDamage;
	}

	public void setEsDamage(double esDamage) {
		this.esDamage = esDamage;
	}

	public double getBolasDamage() {
		return bolasDamage;
	}

	public void setBolasDamage(double bolasDamage) {
		this.bolasDamage = bolasDamage;
	}

	public double getEfDamage() {
		return efDamage;
	}

	public void setEfDamage(double efDamage) {
		this.efDamage = efDamage;
	}

	public double getGrenadeDamage() {
		return grenadeDamage;
	}

	public void setGrenadeDamage(double grenadeDamage) {
		this.grenadeDamage = grenadeDamage;
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
	
}
