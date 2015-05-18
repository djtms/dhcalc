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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.dawg6.web.dhcalc.shared.calculator.d3api.Const;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation.Attributes.Attribute;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation.D3Set;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Value;

public class ProfileHelper {

	public static CharacterData importHero(HeroProfile hero,
			Integer paragonDexterity) {
		CharacterData data = new CharacterData();

		data.setHeroName(hero.name);
		data.setParagon(hero.paragonLevel);
		data.setSeasonal(hero.seasonal);
		data.setHardcore(hero.hardcore);
		data.setDead(hero.dead);
		data.setLevel(hero.level);

		setHeroSkills(hero, data);
		setElementalDamage(hero, data);
		setSkillDamage(hero, data);
		setGemDamage(hero, data);
		importWeaponData(hero, data, paragonDexterity);

		data.setDefaults();
		updateCdr(data);
		data.setDefaults();

		return data;
	}

	public static void updateCdr(CharacterData data) {

		List<Double> list = new Vector<Double>();

		list.add(data.getParagonCDR() * .002);

		if (data.isGogok() && data.getGogokLevel() >= 25)
			list.add(data.getGogokStacks() * .01);

		if (data.isLeorics())
			list.add(data.getDiamond().getCdr()
					* (1 + (data.getLeoricsPercent() / 100.0)));
		else {
			GemLevel gem = data.getDiamond();
			
			if (gem != null)
				list.add(gem.getCdr());
		}

		for (Integer i : data.getCdrData().values()) {
			if (i != null)
				list.add(i / 100.0);
		}

		if (data.isCrimsonCdr())
			list.add(0.10);

		if (data.isBornsCdr())
			list.add(0.10);

		double effCdr = 0.0;

		boolean first = true;

		for (Double c : list) {

			if (first) {
				first = false;
				effCdr = 1.0 - c;
			} else {
				effCdr *= (1.0 - c);
			}
		}

		effCdr = 1.0 - effCdr;

		data.setCdr(effCdr);
	}

	public static void updateWeaponDamage(CharacterData data) {

		double wpnDamage = data.getWeaponDamagePercent();

		double min = (data.getBaseMin() + data.getAddMin()) * (1.0 + wpnDamage);

		double max = (data.getBaseMax() + data.getAddMax()) * (1.0 + wpnDamage);

		WeaponType type = data.getWeaponType();

		if (type == null)
			type = WeaponType.Bow;
		
		double weaponAps = type.getAps() * (1.0 + data.getWeaponIas());
		double weaponDps = Math.round(((min + max) / 2.0) * weaponAps * 10.0) / 10.0;
		data.setWeaponAps(weaponAps);
		data.setWeaponDps(weaponDps);

		WeaponType offHand_type = data.getOffHand_weaponType();

		double offHand_wpnDamage = data.getOffHand_weaponDamagePercent();
		double offHand_min = (data.getOffHand_baseMin() + data
				.getOffHand_addMin()) * (1.0 + offHand_wpnDamage);
		double offHand_max = (data.getOffHand_baseMax() + data
				.getOffHand_addMax()) * (1.0 + offHand_wpnDamage);
		double offHand_weaponAps = (offHand_type == null) ? 0.0 : (offHand_type
				.getAps() * (1.0 + data.getOffHand_weaponIas()));
		double offHand_weaponDps = (offHand_type == null) ? 0.0 : (Math
				.round(((offHand_min + offHand_max) / 2.0) * offHand_weaponAps
						* 10.0) / 10.0);
		data.setOffHand_weaponAps(offHand_weaponAps);
		data.setOffHand_weaponDps(offHand_weaponDps);

		min += data.getJewelryMin();
		max += data.getJewelryMax();
		double dex = data.getDexterity();
		double pCC = (data.getParagonCC() * 0.1) / 100.0;
		double pCD = (data.getParagonCHD() * 1.0) / 100.0;
		double aCC = 0.0;
		double aDam = 0.0;
		double aCD = 0.0;

		if (data.isArchery()) {
			if (type == WeaponType.HandCrossbow) {
				aCC += 0.05;
			} else if (type == WeaponType.Bow) {
				aDam += 0.08;
			} else if (type == WeaponType.Crossbow) {
				aCD += 0.5;
			}
		}

		if (data.isSteadyAim()) {
			aDam += .2;
		}

		double critChance = Math.min(1.0, 0.05 + data.getEquipCritChance() + pCC + aCC);
		double critDamage = data.getEquipCritDamage() + pCD + aCD;

		double eIas = data.getEquipIas();
		double wIas = data.getWeaponIas();
		double pIas = data.getParagonIAS() * 0.002;
		double fIas = data.isFocusedMind() ? 0.03 : 0.0;
		double bbvIas = (data.isBbv() && (data.getBbvUptime() >= 1.0)) ? 0.2 : 0.0;
		double lovIas = 0.0;
		
		if (data.isValor()) {
			if (data.getValorActiveUptime() >= 1.0) {
				lovIas = 0.15;
			} else if ((data.getValorActiveUptime() + data.getValorPassiveUptime()) >= 1.0) {
				lovIas = 0.08;
			}
		}
		
		double retIas = (data.isRetribution() && (data.getRetributionUptime() >= 1.0)) ? 0.1 : 0.0;
		double stIas = (data.isStretchTime() && (data.getStretchTimeUptime() >= 1.0)) ? 0.1 : 0.0;
		
		double gogokIas = data.isGogok() ? (data.getGogokStacks() / 100.0)
				: 0.0;
		double painEnhancerIas = (data.isPainEnhancer() && data
				.getPainEnhancerLevel() >= 25) ? (data.getPainEnhancerStacks() * 0.03)
				: 0.0;
				

		double dwIas = (offHand_type != null) ? 0.15 : 0.0;

		double aps = type.getAps() * (1.0 + wIas)
				* (1.0 + eIas + fIas + pIas + gogokIas + painEnhancerIas + dwIas + bbvIas + lovIas + retIas + stIas);

		double averageWeaponDamage = ((min + max) / 2.0);
		double offHand_averageWeaponDamage = ((offHand_min + offHand_max) / 2.0);
		
		double dps = averageWeaponDamage * aps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);

		double sheetDps = Math.round(dps * 10.0) / 10.0;

		data.setSheetDps(sheetDps);
		data.setAps(aps);
		double petIasValue = data.isTnt() ? data.getTntPercent() : 0.0;
		double petApsValue = aps * (1.0 + petIasValue) * (1.0 + gogokIas);
		data.setCritChance(critChance);
		data.setCritHitDamage(critDamage);
		
		data.setWeaponDamage(averageWeaponDamage);
		data.setOffHand_weaponDamage(offHand_averageWeaponDamage);

		double offHand_wIas = data.getOffHand_weaponIas();
		double offHand_aps = (offHand_type == null) ? 0.0 : (offHand_type
				.getAps() * (1.0 + offHand_wIas) * (1.0 + eIas + pIas
				+ gogokIas + painEnhancerIas + dwIas + bbvIas + lovIas + retIas + stIas));
		data.setOffHand_aps(offHand_aps);

		double offHand_dps = offHand_averageWeaponDamage * offHand_aps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);

		double offHand_sheetDps = Math.round(offHand_dps * 10.0) / 10.0;

		data.setOffHand_dps(offHand_sheetDps);

		BreakPoint bp = BreakPoint.get(petApsValue);
		data.setBp(bp.getBp());

		double sentryAps = bp.getQty() / BreakPoint.DURATION;
		double sentryDpsValue = averageWeaponDamage * sentryAps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);
		data.setSentryDps(sentryDpsValue);
	}

	public static void setHeroSkills(HeroProfile hero, CharacterData data) {

		Map<ActiveSkill, Rune> skills = new TreeMap<ActiveSkill, Rune>();

		if ((hero.skills != null) && (hero.skills.active != null)) {
			for (HeroProfile.Skills.Active s : hero.skills.active) {
				if ((s != null) && (s.skill != null) && (s.skill.slug != null)) {

					for (ActiveSkill sk : ActiveSkill.values()) {
					
						SkillType type = sk.getSkillType();
						
						if (type != SkillType.NA) {
							if (s.skill.slug.equals(sk.getSlug())) {
								Rune rune = Rune.None;
								
								if ((s.rune != null) && (s.rune.type != null)) {
									for (Rune r : sk.getRunes()) {
										if (r.getSlug().equals(s.rune.type)) {
											rune = r;
											break;
										}
									}
								}
								
								skills.put(sk, rune);
							}
						}
					}
				}
			}
		}

		Set<Passive> passives = new TreeSet<Passive>();
		
		for (HeroProfile.Skills.Passive p : hero.skills.passive) {

			if ((p != null) && (p.skill != null) && (p.skill.slug != null)) {
				Passive passive = Passive.fromSlug(p.skill.slug);
				
				if (passive != null)
					passives.add(passive);
			}
		}

		for (ItemInformation item : hero.items.values()) {

			if ((item.attributes != null) && (item.attributes.passive != null)) {

				for (Attribute a : item.attributes.passive) {

					if ((a != null) && (a.text != null)
							&& (a.text.length() > 0)) {
						if (a.text.startsWith(Const.HELLFIRE_PASSIVE)) {
							String pname = a.text
									.substring(Const.HELLFIRE_PASSIVE.length());
							pname = pname.substring(0, pname.length()
									- Const.PASSIVE.length());

							Passive passive = Passive.fromName(pname);
							
							if (passive != null) {
								passives.add(passive);
							}
						}

					}
				}
			}
		}

		data.setPassives(passives);
		data.setHeroLevel(hero.level);
		data.setSkills(skills);
	}

	public static Rune lookupRune(ActiveSkill skill, String name) {

		for (Rune r : skill.getRunes()) {
			if (name.equalsIgnoreCase(r.getLongName())) {
				return r;
			}
		}

		return Rune.None;
	}

	public static void setElementalDamage(HeroProfile hero, CharacterData data) {

		Map<DamageType, Double> damage = new TreeMap<DamageType, Double>();
		
		for (DamageType t : DamageType.values()) {
			double d = 0.0;
			String e = t.getSlug();
			String attr = Const.ELEMENTAL_DAMAGE_BONUS + e;

			for (ItemInformation i : hero.items.values()) {

				Value<Float> f = i.attributesRaw.get(attr);
				
				if (f != null) {
					
					d += f.min;
				}
				
			}

			if (d > 0.0) {
				damage.put(t, d);
			}
		}

		data.setElementalDamage(damage);
	}

	public static void setGemDamage(HeroProfile hero, CharacterData data) {

		Map<GemSkill, GemAttributeData> gems = new TreeMap<GemSkill, GemAttributeData>();
		
		for (ItemInformation i : hero.items.values()) {

			if (i.gems != null) {
				for (ItemInformation.Gem g : i.gems) {

					if (g.item.name != null) {
						GemSkill gem = GemSkill.getGemByName(g.item.name);
						
						if (gem != null) {
							GemAttributeData gd = new GemAttributeData();
							gd.level = g.jewelRank;
							
							for (GemSkill.Attribute a : gem.getAttributes()) {
								gd.put(a.getLabel(), new Integer(0));
							}
							
							gems.put(gem,  gd);
						}
					}
				}
			}
		}
		
		data.setGems(gems);
	}

	public static void importWeaponData(HeroProfile hero, CharacterData data,
			Integer paragonDexterity) {

		double critChance = 0.0;
		double critDamage = 0.5;
		WeaponType type = null;
		int weaponIas = 0;
		WeaponType offHand_type = null;
		int offHand_weaponIas = 0;
		int equipIas = 0;
		double wpnDamage = 0.0;
		double offHand_wpnDamage = 0.0;
		double minJewelry = 0.0;
		double maxJewelry = 0.0;
		double baseMin = 0.0;
		double baseDelta = 0.0;
		double addMin = 0.0;
		double addDelta = 0.0;
		double offHand_baseMin = 0.0;
		double offHand_baseDelta = 0.0;
		double offHand_addMin = 0.0;
		double offHand_addDelta = 0.0;
		int equipmentDexterity = 0;
		double areaDamage = 0.0;

		ItemInformation bow = hero.items.get(Slot.MainHand.getSlot());

		if (bow != null) {
			String bowType = bow.type.id;

			if (bowType.equalsIgnoreCase(Const.HANDXBOW)) {
				type = WeaponType.HandCrossbow;
			} else if (bowType.equalsIgnoreCase(Const.BOW)) {
				type = WeaponType.Bow;
			} else if (bowType.equalsIgnoreCase(Const.CROSSBOW)) {
				type = WeaponType.Crossbow;
			} else {
				type = null;
			}
		}

		data.setWeaponType(type);

		ItemInformation offHand = hero.items.get(Slot.OffHand.getSlot());

		if (offHand != null) {
			String bowType = offHand.type.id;

			if (bowType.equalsIgnoreCase(Const.HANDXBOW)) {
				offHand_type = WeaponType.HandCrossbow;
			} else if (bowType.equalsIgnoreCase(Const.BOW)) {
				offHand_type = WeaponType.Bow;
			} else if (bowType.equalsIgnoreCase(Const.CROSSBOW)) {
				offHand_type = WeaponType.Crossbow;
			} else {
				offHand_type = null;
			}
		}

		data.setOffHand_weaponType(offHand_type);

		if ((bow != null) && (type != null)) {

			for (Map.Entry<String, Value<Float>> e : bow.attributesRaw
					.entrySet()) {
				String name = e.getKey();
				double value = e.getValue().min;

				if (name.startsWith(Const.DAMAGE_WEAPON_MIN)) {

					if (name.contains(Const.PHYSICAL))
						baseMin = value;
					else
						addMin += value;

				} else if (name.startsWith(Const.DAMAGE_WEAPON_DELTA)) {

					if (name.contains(Const.PHYSICAL))
						baseDelta = value;
					else
						addDelta += value;

				} else if (name.startsWith(Const.DAMAGE_WEAPON_BONUS_MIN)) {
					addMin += value;
				} else if (name.startsWith(Const.DAMAGE_WEAPON_BONUS_DELTA)) {
					addDelta += value;
				} else if (name.startsWith(Const.DAMAGE_WEAPON_PERCENT))
					wpnDamage = value;
				else if (name.equals(Const.WEAPON_IAS)) {
					weaponIas = (int) Math.round(value * 100.0);
				}
			}

			data.setBaseMin(baseMin);
			data.setBaseMax(baseMin + baseDelta);
			data.setAddMin(addMin);
			data.setAddMax(addMin + addDelta);
		}

		if ((offHand != null) && (offHand_type != null)) {

			for (Map.Entry<String, Value<Float>> e : offHand.attributesRaw
					.entrySet()) {
				String name = e.getKey();
				double value = e.getValue().min;

				if (name.startsWith(Const.DAMAGE_WEAPON_MIN)) {

					if (name.contains(Const.PHYSICAL))
						offHand_baseMin = value;
					else
						offHand_addMin += value;

				} else if (name.startsWith(Const.DAMAGE_WEAPON_DELTA)) {

					if (name.contains(Const.PHYSICAL))
						offHand_baseDelta = value;
					else
						offHand_addDelta += value;

				} else if (name.startsWith(Const.DAMAGE_WEAPON_BONUS_MIN)) {
					offHand_addMin += value;
				} else if (name.startsWith(Const.DAMAGE_WEAPON_BONUS_DELTA)) {
					offHand_addDelta += value;
				} else if (name.startsWith(Const.DAMAGE_WEAPON_PERCENT))
					offHand_wpnDamage = value;
				else if (name.equals(Const.WEAPON_IAS)) {
					offHand_weaponIas = (int) Math.round(value * 100.0);
				}
			}

			data.setOffHand_baseMin(offHand_baseMin);
			data.setOffHand_baseMax(offHand_baseMin + offHand_baseDelta);
			data.setOffHand_addMin(offHand_addMin);
			data.setOffHand_addMax(offHand_addMin + offHand_addDelta);
		}

		// StringBuffer log = new StringBuffer();

		for (ItemInformation i : hero.items.values()) {

			Value<Float> v = i.attributesRaw.get(Const.CRIT_CHANCE_RAW);

			if (v != null) {
				critChance += v.min;
			}

			v = i.attributesRaw.get(Const.DEXTERITY);

			if (v != null) {
				equipmentDexterity += v.min;
			}

			v = i.attributesRaw.get(Const.AREA_DAMAGE);

			if (v != null) {
				areaDamage += v.min;
			}

			v = i.attributesRaw.get(Const.CRIT_DAMAGE_RAW);

			if (v != null) {
				critDamage += v.min;
			}

			if (i.gems != null) {
				for (ItemInformation.Gem g : i.gems) {
					v = g.attributesRaw.get(Const.CRIT_DAMAGE_RAW);

					if (v != null) {
						critDamage += v.min;
					}

					v = g.attributesRaw.get(Const.DEXTERITY);

					if (v != null) {
						equipmentDexterity += v.min;
					}

				}
			}

			if ((i != bow) && ((i != offHand) || (offHand_type == null))) {

				if (i.attributesRaw != null) {
					Value<Float> min = i.attributesRaw
							.get(Const.JEWELY_MIN_DAMAGE);

					if (min != null) {
						minJewelry += min.min;

						Value<Float> delta = i.attributesRaw
								.get(Const.JEWELY_DELTA_DAMAGE);

						if (delta != null) {
							maxJewelry += (min.min + delta.min);
						}
					}
				}

				v = i.attributesRaw.get(Const.IAS);

				if (v != null) {

					equipIas += Math.round(v.min * 100.0);
				}

			}

		}

		for (Map.Entry<String, Integer> e : data.getSetCounts().entrySet()) {
			int count = e.getValue();

			if ((count > 1) && data.isRoyalRing())
				count++;

			ItemInformation.D3Set set = data.getSets().get(e.getKey());

			for (ItemInformation.D3Set.Rank r : set.ranks) {
				if (r.required <= count) {
					if (r.attributesRaw != null) {
						Value<Float> v = r.attributesRaw
								.get(Const.CRIT_CHANCE_RAW);

						if (v != null) {
							critChance += v.min;
						}

						v = r.attributesRaw.get(Const.DEXTERITY);

						if (v != null) {
							equipmentDexterity += v.min;
						}

						v = r.attributesRaw.get(Const.AREA_DAMAGE);

						if (v != null) {
							areaDamage += v.min;
						}

						v = r.attributesRaw.get(Const.CRIT_DAMAGE_RAW);

						if (v != null) {
							critDamage += v.min;
						}

						v = r.attributesRaw.get(Const.IAS);

						if (v != null) {
							equipIas += (int) Math.round(v.min * 100.0);
						}
					}
				}
			}
		}

		data.setEquipCritChance(critChance);
		data.setEquipCritDamage(critDamage);
		data.setWeaponIas(weaponIas / 100.0);
		data.setOffHand_weaponIas(offHand_weaponIas / 100.0);
		data.setEquipIas(equipIas / 100.0);
		data.setWeaponDamagePercent(wpnDamage);
		data.setOffHand_weaponDamagePercent(offHand_wpnDamage);
		data.setEquipmentDexterity(equipmentDexterity);

		if (paragonDexterity != null)
			data.setParagonDexterity(paragonDexterity);
		else {
			data.setParagonDexterity((hero.stats.dexterity
					- (equipmentDexterity + 7 + (hero.level * 3))) / 5);
		}

		data.setAreaDamageEquipment(areaDamage);
		data.setJewelryMin(minJewelry);
		data.setJewelryMax(maxJewelry);

	}

	public static void setSkillDamage(HeroProfile hero, CharacterData data) {

		Map<ActiveSkill, Double> damage = new TreeMap<ActiveSkill, Double>();
		Map<SpecialItemType, AttributeData> items = new TreeMap<SpecialItemType, AttributeData>();
		double elite = 0;
		Map<String, Integer> setCounts = new TreeMap<String, Integer>();
		Map<String, D3Set> sets = new TreeMap<String, D3Set>();
		data.setSets(sets);
		double hatredPerSecond = 0.0;
		int discipline = 0;

		for (ItemInformation i : hero.items.values()) {

			if (i.attributesRaw != null) {
				Value<Float> v = i.attributesRaw.get(Const.ELITE_DAMAGE_RAW);

				if (v != null) {
					elite += v.min;
				}

				v = i.attributesRaw.get(Const.HATRED_PER_SECOND);

				if (v != null) {
					hatredPerSecond += v.min;
				}

				v = i.attributesRaw.get(Const.MAX_DISCIPLINE);

				if (v != null) {
					discipline += v.min;
				}
			}

			if ((i.set != null) && (i.set.slug != null)) {
				Integer count = setCounts.get(i.set.slug);

				if (count == null) {
					setCounts.put(i.set.slug, 1);
					sets.put(i.set.slug, i.set);
				} else {
					setCounts.put(i.set.slug, count + 1);
				}
			}
			
			for (SpecialItemType type : SpecialItemType.values()) {
				if (i.name.equals(type.getName())) {
					AttributeData ad = new AttributeData();
					
					for (SpecialItemType.Attribute a : type.getAttributes()) {
						int value = 0;
						Value<Float> v = i.attributesRaw.get(a.getSlug());
						
						if (v == null) {
							value = a.setRawAttributeValue(a.getMin());
						} else {
							value = a.setRawAttributeValue(v.min);
						}
						
						ad.put(a.getLabel(), value);
					}
					
					items.put(type, ad);
				}
			}
		}

		data.setSpecialItems(items);

		for (Map.Entry<String, Integer> e : setCounts.entrySet()) {
			int count = e.getValue();

			if ((count > 1) && data.isRoyalRing())
				count++;

			D3Set set = sets.get(e.getKey());

			for (D3Set.Rank r : set.ranks) {
				if (r.required <= count) {
					if (r.attributesRaw != null) {
						Value<Float> v = r.attributesRaw
								.get(Const.ELITE_DAMAGE_RAW);

						if (v != null) {
							elite += v.min;
						}
					}
				}
			}
		}

		data.setSetCounts(setCounts);

		for (ActiveSkill skill : ActiveSkill.values()) {
			String slug = skill.getDamageAttribute();
			String attr = Const.SKILL_DAMAGE_BONUS + slug;
			double d = 0.0;
			
			for (ItemInformation i : hero.items.values()) {

				Value<Float> f = i.attributesRaw.get(attr);
				
				if (f != null) {
					d += f.min;
				}
				
			}

			if (d > 0.0) {
				damage.put(skill, d);
			}
		}

		data.setSkillDamage(damage);

		ItemInformation helm = hero.items.get(Const.HEAD);

		GemLevel diamond = GemLevel.None;

		Map<String, Integer> cdrData = new TreeMap<String, Integer>();
		addCdr(hero, Const.SHOULDERS, cdrData);
		addCdr(hero, Const.GLOVES, cdrData);
		addCdr(hero, Const.RING1, cdrData);
		addCdr(hero, Const.RING2, cdrData);
		addCdr(hero, Const.BELT, cdrData);
		addCdr(hero, Const.WEAPON, cdrData);
		addCdr(hero, Const.QUIVER, cdrData);
		addCdr(hero, Const.AMULET, cdrData);

		Map<String, Integer> rcrData = new TreeMap<String, Integer>();
		addRcr(hero, Const.SHOULDERS, rcrData);
		addRcr(hero, Const.GLOVES, rcrData);
		addRcr(hero, Const.RING1, rcrData);
		addRcr(hero, Const.RING2, rcrData);
		addRcr(hero, Const.BELT, rcrData);
		addRcr(hero, Const.WEAPON, rcrData);
		addRcr(hero, Const.QUIVER, rcrData);
		addRcr(hero, Const.AMULET, rcrData);

		data.setCdrData(cdrData);
		data.setRcrData(rcrData);

		if (helm != null) {
			if (helm.gems != null) {
				for (ItemInformation.Gem gem : helm.gems) {
					Value<Float> value = gem.attributesRaw.get(Const.CDR);

					if (value != null) {
						int a = (int) Math.round(value.min * 10000.0);

						for (GemLevel l : GemLevel.values()) {
							int b = (int) Math.round(l.getCdr() * 10000.0);

							if (a == b) {
								diamond = l;
								break;
							}
						}
					}
				}
			}
		}


		data.setHatredPerSecond(hatredPerSecond);
		data.setEquipmentDiscipline(discipline);
		data.setEliteDamage(elite);
		data.setDiamond(diamond);

	}

	public static int getSetCount(CharacterData data, String name) {
		return data.getSetCount(name);
	}

	private static void addCdr(HeroProfile hero, String slot,
			Map<String, Integer> cdrData) {

		Integer cdr = 0;

		ItemInformation i = hero.items.get(slot);

		if (i != null) {
			Value<Float> value = i.attributesRaw.get(Const.CDR);

			if (value != null) {
				cdr = (int) Math.round(value.min * 100.0);
			}
		}

		cdrData.put(slot, cdr);
	}

	private static void addRcr(HeroProfile hero, String slot,
			Map<String, Integer> rcrData) {

		Integer rcr = 0;

		ItemInformation i = hero.items.get(slot);

		if (i != null) {
			Value<Float> value = i.attributesRaw.get(Const.RESOURCE_REDUCTION);

			if (value != null) {
				rcr = (int) Math.round(value.min * 100.0);
			}
		}

		rcrData.put(slot, rcr);
	}

	public static void updateRcr(CharacterData data) {
		List<Double> list = new Vector<Double>();

		list.add(data.getParagonRCR() * 0.002);

		if (data.isPridesFall())
			list.add(0.30);

		Map<String, Integer> map = data.getRcrData();
		
		for (Integer i : map.values()) {
			
			if (i != null)
				list.add(i / 100.0);
		}

		if (data.getSetCount(ItemSet.Crimson.getSlug()) >= 3)
			list.add(0.10);

		double effRcr = 0.0;

		boolean first = true;

		for (Double c : list) {
			if (first) {
				first = false;
				effRcr = 1.0 - c;
			} else {
				effRcr *= (1.0 - c);
			}
		}

		effRcr = 1.0 - effRcr;

		data.setRcr(effRcr);
	}
}
