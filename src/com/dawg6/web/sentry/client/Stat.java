package com.dawg6.web.sentry.client;

import java.util.Set;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.DamageType;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;

public enum Stat {

	Dexterity("+10 Dexterity", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getEquipmentDexterity();
			data.setEquipmentDexterity(value + 10);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEquipmentDexterity((Integer)token);
		}}),
		
	CC("+1% Crit Chance", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getCritChance();
			data.setCritChance(value + 0.01);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setCritChance((Double)token);
		}}),
		
	CHD("+10% Crit Hit Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getCritHitDamage();
			data.setCritHitDamage(value + 0.1);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setCritHitDamage((Double)token);
		}}),
		
	Elite("+1% Elite Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEliteDamage();
			data.setEliteDamage(value + 0.01);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEliteDamage((Double)token);
		}}),		
		
	RCR("+8% Raw RCR", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getRcr();
			data.setRcr( 1.0 - ( ( 1.0 - value ) * 0.92 ));
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setRcr((Double)token);
		}}),		
		
	IAS("+1% IAS", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEquipIas();
			data.setEquipIas( value + 0.01);
			ProfileHelper.updateWeaponDamage(data);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEquipIas((Double)token);
			ProfileHelper.updateWeaponDamage(data);
		}}),		
			
	HPS("+1 Hatred per Second", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getHatredPerSecond();
			data.setHatredPerSecond(value + 1.0);
			
			return value;
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setHatredPerSecond((Double)token);
		}}),			
	
	
	Fire("+1% Fire Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getFireDamage();
			data.setFireDamage(value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return types.contains(DamageType.Fire);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setFireDamage((Double)token);
		}}),
		
	Cold("+1% Cold Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getColdDamage();
			data.setColdDamage(value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return types.contains(DamageType.Cold);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setColdDamage((Double)token);
		}}),
		
	Physical("+1% Physical Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getPhysDamage();
			data.setPhysDamage(value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return types.contains(DamageType.Physical);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setPhysDamage((Double)token);
		}}),
		
	Lightning("+1% Lightning Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getLightDamage();
			data.setLightDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return types.contains(DamageType.Lightning);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setLightDamage((Double)token);
		}}),
		
	Sentry("+1% Sentry Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getSentryDamage();
			data.setSentryDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isSentry();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setSentryDamage((Double)token);
		}}),
		
	RoV("+1% RoV Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getRovDamage();
			data.setRovDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isRov();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setRovDamage((Double)token);
		}}),
			
	Area("+1% Area Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getAreaDamageEquipment();
			data.setAreaDamageEquipment(value + 0.01);
			
			return value;
		}

		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return (data.getNumAdditional() > 0) && (data.getTargetSpacing() <= 10);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setAreaDamageEquipment((Double)token);
		}}),	
			
//	Companion("+1% Companion Damage", new StatAdapter(){
//
//		@Override
//		public Object apply(CharacterData data) {
//			double value = data.getCompanionDamage();
//			data.setCompanionDamage(value + 0.01);
//			
//			return value;
//		}
//		
//		@Override
//		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
//			return data.isCompanion();
//		}
//
//		@Override
//		public void unapply(CharacterData data, Object token) {
//			data.setCompanionDamage((Double)token);
//		}}),

	CA("+1% CA Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getCaDamage();
			data.setCaDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.CA);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setCaDamage((Double)token);
		}}),
		
	MS("+1% MS Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getMsDamage();
			data.setMsDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.MS);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setMsDamage((Double)token);
		}}),
		
	EA("+1% EA Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEaDamage();
			data.setEaDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.EA);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEaDamage((Double)token);
		}}),
				
	CHAK("+1% CHAK Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getChakDamage();
			data.setChakDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.CHAK);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setChakDamage((Double)token);
		}}),

	IMP("+1% IMP Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getImpDamage();
			data.setImpDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.IMP);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setImpDamage((Double)token);
		}}),
	
	EF("+1% EF Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEfDamage();
			data.setEfDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.EF);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEfDamage((Double)token);
		}}),
				
	HA("+1% HA Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getHaDamage();
			data.setHaDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.HA);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setHaDamage((Double)token);
		}}),

	ES("+1% ES Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getEsDamage();
			data.setEsDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.ES);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEsDamage((Double)token);
		}}),
		
	Bolas("+1% Bolas Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getBolasDamage();
			data.setBolasDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.BOLAS);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setBolasDamage((Double)token);
		}}),
		
	Grenade("+1% Grenade Damage", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			double value = data.getGrenadeDamage();
			data.setGrenadeDamage(value + 0.01);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return skills.contains(ActiveSkill.GRENADE);
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setGrenadeDamage((Double)token);
		}}),
		
	HG("+1 Health Globe", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getNumHealthGlobes();
			data.setNumHealthGlobes(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isReapersWraps() || data.isBloodVengeance();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setNumHealthGlobes((Integer)token);
		}}),
		
	BoT("+1 BoT Gem Level", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getBaneOfTheTrappedLevel();
			data.setBaneOfTheTrappedLevel(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isUseBaneOfTheTrapped();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setBaneOfTheTrappedLevel((Integer)token);
		}}),
		
	Enforcer("+1 Enforcer Gem Level", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getEnforcerLevel();
			data.setEnforcerLevel(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isUseEnforcer() && (data.isSentry() || data.isCompanion());
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setEnforcerLevel((Integer)token);
		}}),

	Zeis("+1 Zei's Gem Level", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getZeisLevel();
			data.setZeisLevel(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isZeis();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setZeisLevel((Integer)token);
		}}),

	Toxin("+1 Toxin Gem Level", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getToxinLevel();
			data.setToxinLevel(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isToxin();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setToxinLevel((Integer)token);
		}}),
			
	PainEnhancer("+1 Pain Enhancer Gem Level", new StatAdapter(){

		@Override
		public Object apply(CharacterData data) {
			int value = data.getPainEnhancerLevel();
			data.setPainEnhancerLevel(value + 1);
			
			return value;
		}
		
		@Override
		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
			return data.isPainEnhancer();
		}

		@Override
		public void unapply(CharacterData data, Object token) {
			data.setPainEnhancerLevel((Integer)token);
		}}),
		
//	Taeguk("+1 Taeguk Gem Level", new StatAdapter(){
//
//		@Override
//		public Object apply(CharacterData data) {
//			int value = data.getTaegukLevel();
//			data.setTaegukLevel(value + 1);
//			
//			return value;
//		}
//		
//		@Override
//		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
//			return data.isTaeguk();
//		}
//
//		@Override
//		public void unapply(CharacterData data, Object token) {
//			data.setTaegukLevel((Integer)token);
//		}}),
//		
//	Gogok("+1 Gogok Gem Level", new StatAdapter(){
//
//		@Override
//		public Object apply(CharacterData data) {
//			int value = data.getGogokLevel();
//			data.setGogokLevel(value + 1);
//			ProfileHelper.updateWeaponDamage(data);
//			
//			return value;
//		}
//		
//		@Override
//		public boolean test(CharacterData data, Set<DamageType> types, Set<ActiveSkill> skills) {
//			return data.isGogok();
//		}
//
//		@Override
//		public void unapply(CharacterData data, Object token) {
//			data.setGogokLevel((Integer)token);
//			ProfileHelper.updateWeaponDamage(data);
//		}}),
	;
	
	private String label;
	private StatAdapter adapter;

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
	
}
