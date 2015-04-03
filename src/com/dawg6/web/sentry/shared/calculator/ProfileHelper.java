package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import com.dawg6.web.sentry.shared.calculator.d3api.Const;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation.Attributes.Attribute;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation.D3Set;
import com.dawg6.web.sentry.shared.calculator.d3api.Value;

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
		else
			list.add(data.getDiamond().getCdr());

		String[] gear = { Const.SHOULDERS, Const.BELT, Const.RING1,
				Const.RING2, Const.AMULET, Const.GLOVES, Const.QUIVER,
				Const.WEAPON };

		if (data.getCdrData() != null) {
			for (String slot : gear) {
				Integer value = data.getCdrData().get(slot);

				if (value != null)
					list.add(value / 100.0);
			}
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

		min += data.getJewelMin();
		max += data.getJewelMax();
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

		double critChance = data.getEquipCritChance() + pCC + aCC;
		double critDamage = data.getEquipCritDamage() + pCD + aCD;

		double eIas = data.getEquipIas();
		double wIas = data.getWeaponIas();
		double pIas = data.getParagonIAS() * 0.002;

		double gogokIas = data.isGogok() ? (data.getGogokStacks() / 100.0)
				: 0.0;
		double painEnhancerIas = (data.isPainEnhancer() && data
				.getPainEnhancerLevel() >= 25) ? (data.getPainEnhancerStacks() * 0.03)
				: 0.0;

		double dwIas = (offHand_type != null) ? 0.15 : 0.0;

		double aps = type.getAps() * (1.0 + wIas)
				* (1.0 + eIas + pIas + gogokIas + painEnhancerIas + dwIas);

		double averageWeaponDamage = ((min + max) / 2.0);

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

		double offHand_wIas = data.getOffHand_weaponIas();
		double offHand_aps = (offHand_type == null) ? 0.0 : (offHand_type
				.getAps() * (1.0 + offHand_wIas) * (1.0 + eIas + pIas
				+ gogokIas + painEnhancerIas + dwIas));
		data.setOffHand_aps(offHand_aps);

		double offHand_averageWeaponDamage = ((min + max) / 2.0);

		double offHand_dps = offHand_averageWeaponDamage * offHand_aps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);

		double offHand_sheetDps = Math.round(offHand_dps * 10.0) / 10.0;

		data.setOffHand_dps(offHand_sheetDps);

		BreakPoint bp = BreakPoint.get(petApsValue);
		data.setBp(bp.getBp());

		double sentryAps = bp.getQty() / FiringData.DURATION;
		double sentryDpsValue = averageWeaponDamage * sentryAps
				* (1.0 + critChance * critDamage) * (1.0 + (dex / 100.0))
				* (1.0 + aDam);
		data.setSentryDps(sentryDpsValue);
	}

	public static void setHeroSkills(HeroProfile hero, CharacterData data) {

		boolean mfd = false;
		Rune mfdRune = Rune.None;
		boolean caltrops = false;
		boolean sentry = false;
		Rune sentryRune = Rune.None;
		boolean preparation = false;
		Rune preparationRune = Rune.None;
		Set<SkillAndRune> skills = new TreeSet<SkillAndRune>();
		boolean companion = false;
		Rune companionRune = Rune.None;
		Rune caltropsRune = Rune.None;
		Rune spikeTrapRune = Rune.None;
		boolean spikeTrap = false;
		boolean rov = false;
		Rune rovRune = Rune.None;

		for (HeroProfile.Skills.Active s : hero.skills.active) {

			if ((s != null) && (s.skill != null) && (s.skill.name != null)) {

				if (s.skill.name.equals(Const.COMPANION)) {
					companion = true;

					if (s.rune == null) {
						companionRune = Rune.None;
					} else {
						String type = s.rune.type;

						for (Rune r : ActiveSkill.Companion.getRunes()) {
							if (r.getSlug().equals(type)) {
								companionRune = r;
								break;
							}
						}
					}
				} else if (s.skill.name.equals(Const.CALTROPS)) {
					caltrops = true;

					if (s.rune == null) {
						caltropsRune = Rune.None;
					} else {
						String type = s.rune.type;

						for (Rune r : ActiveSkill.Caltrops.getRunes()) {
							if (r.getSlug().equals(type)) {
								caltropsRune = r;
								break;
							}
						}
					}
				} else if (s.skill.name.equals(Const.SPIKE_TRAP)) {
					spikeTrap = true;

					if (s.rune == null) {
						spikeTrapRune = Rune.None;
					} else {
						String type = s.rune.type;

						for (Rune r : ActiveSkill.ST.getRunes()) {
							if (r.getSlug().equals(type)) {
								spikeTrapRune = r;
								break;
							}
						}
					}
				} else if (s.skill.name.equals(Const.PREPARATION)) {
					preparation = true;

					if (s.rune == null) {
						preparationRune = Rune.None;
					} else {
						String type = s.rune.type;
	
						for (Rune r : ActiveSkill.Preparation.getRunes()) {
							if (r.getSlug().equals(type)) {
								preparationRune = r;
								break;
							}
						}
					}
				} else if (s.skill.name

				.equals(ActiveSkill.SENTRY.getLongName())) {

					sentry = true;

					if (s.rune != null) {
						sentryRune = lookupRune(ActiveSkill.SENTRY, s.rune.name);
					}

				} else if (s.skill.name.equals(Const.MARKED_FOR_DEATH)) {
					mfd = true;

					if ((s.rune != null) && (s.rune.type != null)) {
						for (Rune r : MarkedForDeath.RUNES) {
							if (r.getSlug().equals(s.rune.type)) {
								mfdRune = r;
								break;
							}
						}
					}

				} else if (s.skill.name.equals(Const.RAIN_OF_VENGEANCE)) {
					rov = true;

					if ((s.rune != null) && (s.rune.type != null)) {
						for (Rune r : ActiveSkill.RoV.getRunes()) {
							if (r.getSlug().equals(s.rune.type)) {
								rovRune = r;
								break;
							}
						}
					}

				} else {
					for (ActiveSkill sk : ActiveSkill.values()) {

						if (s.skill.name.equals(sk.getLongName())) {
							Rune rune = Rune.None;
							// String runeName = Rune.None.getLongName();

							if ((s.rune != null) && (s.rune.name != null))
								rune = lookupRune(sk, s.rune.name);

							SkillAndRune sr = new SkillAndRune(sk, rune);
							skills.add(sr);

							if (skills.size() >= 2)
								break;
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
		data.setMfdSkill(mfd);
		data.setMfdRune(mfdRune);
		data.setCompanion(companion);
		data.setCompanionRune(companionRune);
		data.setCaltrops(caltrops);
		data.setCaltropsRune(caltropsRune);
		data.setSpikeTrap(spikeTrap);
		data.setSpikeTrapRune(spikeTrapRune);
		data.setSentry(sentry);
		data.setSentryRune(sentryRune);
		data.setSkills(skills);
		data.setPreparation(preparation);
		data.setPreparationRune(preparationRune);
		data.setHeroLevel(hero.level);
		data.setRov(rov);
		data.setRovRune(rovRune);
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

		boolean enforcer = false;
		boolean iceblink = false;
		boolean bot = false;
		boolean botp = false;
		int enforcerLevel = 0;
		int iceblinkLevel = 0;
		int botLevel = 0;
		int botpLevel = 0;
		boolean zeis = false;
		int zeisLevel = 0;

		boolean gogok = false;
		int gogokLevel = 0;
		boolean toxin = false;
		int toxinLevel = 0;
		boolean painEnhancer = false;
		int painEnhancerLevel = 0;

		for (ItemInformation i : hero.items.values()) {

			if (i.gems != null) {
				for (ItemInformation.Gem g : i.gems) {
					if (g.item != null) {
						if (g.item.name.equals(Const.BANE_OF_THE_TRAPPED)) {
							bot = true;
							float rank = g.jewelRank;
							botLevel = (int) rank;
						} else if (g.item.name.equals(Const.ENFORCER)) {
							enforcer = true;
							float rank = g.jewelRank;
							enforcerLevel = (int) rank;
						} else if (g.item.name.equals(Const.ICEBLINK)) {
							iceblink = true;
							float rank = g.jewelRank;
							iceblinkLevel = (int) rank;
						} else if (g.item.name.equals(Const.BOTP)) {
							botp = true;
							float rank = g.jewelRank;
							botpLevel = (int) rank;
						} else if (g.item.name.equals(Const.ZEI)) {
							zeis = true;
							float rank = g.jewelRank;
							zeisLevel = (int) rank;
						} else if (g.item.name.equals(Const.GOGOK)) {
							gogok = true;
							float rank = g.jewelRank;
							gogokLevel = (int) rank;
						} else if (g.item.name.equals(Const.TOXIN)) {
							toxin = true;
							float rank = g.jewelRank;
							toxinLevel = (int) rank;
						} else if (g.item.name.equals(Const.PAIN_ENHANCER)) {
							painEnhancer = true;
							float rank = g.jewelRank;
							painEnhancerLevel = (int) rank;
						}
					}
				}
			}
		}

		data.setUseBaneOfTheTrapped(bot);
		data.setBaneOfTheTrappedLevel(botLevel);

		data.setUseEnforcer(enforcer);
		data.setEnforcerLevel(enforcerLevel);

		data.setIceblink(iceblink);
		data.setIceblinkLevel(iceblinkLevel);

		data.setBotp(botp);
		data.setBotpLevel(botpLevel);

		data.setZeis(zeis);
		data.setZeisLevel(zeisLevel);

		data.setGogok(gogok);
		data.setGogokLevel(gogokLevel);

		data.setToxin(toxin);
		data.setToxinLevel(toxinLevel);

		data.setPainEnhancer(painEnhancer);
		data.setPainEnhancerLevel(painEnhancerLevel);
	}

	public static void importWeaponData(HeroProfile hero, CharacterData data,
			Integer paragonDexterity) {

		double critChance = 0.05;
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
			data.setParagonDexterity(hero.stats.dexterity
					- (equipmentDexterity + 7 + (hero.level * 3)));
		}

		data.setAreaDamageEquipment(areaDamage);
		data.setJewelMin(minJewelry);
		data.setJewelMax(maxJewelry);

	}

	public static void setSkillDamage(HeroProfile hero, CharacterData data) {

		Map<ActiveSkill, Double> damage = new TreeMap<ActiveSkill, Double>();
		
		int petSpeed = 0;
		boolean tnt = false;
		boolean calamity = false;
		boolean meticulousBolts = false;
		int meticulousBoltsPercent = 0;
		double elite = 0;
		Map<String, Integer> setCounts = new TreeMap<String, Integer>();
		Map<String, D3Set> sets = new TreeMap<String, D3Set>();
		data.setSets(sets);
		boolean royalRing = false;
		boolean strongarm = false;
		double strongarmPercent = 0.0;
		boolean odysseysEnd = false;
		double odysseysEndPercent = 0.0;
		boolean harrington = false;
		double harringtonPercent = 0.0;
		boolean hexingPants = false;
		double hexingPantsPercent = 0.0;
		boolean bombadiers = false;
		boolean crashingRain = false;
		boolean helltrapper = false;
		double helltrapperPercent = 0.0;
		boolean reapersWraps = false;
		double reapersWrapsPercent = 0.0;
		boolean kridershot = false;
		boolean spines = false;
		int kridershotHatred = 0;
		int spinesHatred = 0;
		double hatredPerSecond = 0.0;
		int discipline = 0;
		boolean cindercoat = false;
		double cindercoatPercent = 0.0;
		boolean vaxo = false;

		for (ItemInformation i : hero.items.values()) {

			if (i.attributesRaw != null) {
				Value<Float> v = i.attributesRaw.get(Const.ROYAL_RING);

				if (v != null) {
					royalRing = true;
				}

				v = i.attributesRaw.get(Const.ELITE_DAMAGE_RAW);

				if (v != null) {
					elite += Math.round(v.min * 100.0);
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

			if (i.name.equals(Const.CALAMITY)) {
				calamity = true;
			} else if (i.name.equals(Const.KRIDERSHOT)) {
				kridershot = true;
				Value<Float> value = i.attributesRaw
						.get(Const.KRIDERSHOT_HATRED);
				kridershotHatred = Math.round(value.min);
			} else if (i.name.equals(Const.ODYSSEYS_END)) {
				odysseysEnd = true;
				Value<Float> value = i.attributesRaw
						.get(Const.ODYSSEYS_END_PERCENT);
				odysseysEndPercent = Math.round(value.min);
			} else if (i.name.equals(Const.SPINES)) {
				spines = true;
				Value<Float> value = i.attributesRaw.get(Const.SPINES_HATRED);
				spinesHatred = Math.round(value.min);
			} else if (i.name.equals(Const.REAPERS_WRAPS)) {
				reapersWraps = true;
				Value<Float> value = i.attributesRaw
						.get(Const.REAPERS_WRAPS_PERCENT);
				reapersWrapsPercent = value.min;
			} else if (i.name.equals(Const.CINDERCOAT)) {
				cindercoat = true;
				Value<Float> value = i.attributesRaw.get(Const.CINDERCOAT_RCR);
				cindercoatPercent = value.min;
			} else if (i.name.equals(Const.METICULOUS_BOLTS)) {
				meticulousBolts = true;
				Value<Float> value = i.attributesRaw
						.get(Const.METICULOUS_BOLTS_PERCENT);

				if (value != null)
					meticulousBoltsPercent = (int) Math
							.round(value.min * 100.0);
				else
					meticulousBoltsPercent = 30;
			} else if (i.name.equals(Const.STRONGARM)) {
				Value<Float> value = i.attributesRaw
						.get(Const.STRONGARM_PERCENT);
				strongarm = true;

				if (value != null)
					strongarmPercent = value.min;
				else
					strongarmPercent = 0.20;
			} else if (i.name.equals(Const.BOMBADIERS)) {
				bombadiers = true;
			} else if (i.name.equals(Const.CRASHING_RAIN)) {
				crashingRain = true;
			} else if (i.name.equals(Const.VAXO)) {
				vaxo = true;
			} else if (i.name.equals(Const.HELLTRAPPER)) {
				helltrapper = true;
				Value<Float> value = i.attributesRaw
						.get(Const.HELLTRAPPER_PERCENT);

				if (value != null) {
					helltrapperPercent = value.min;
				} else {
					helltrapperPercent = 0.07;
				}
			} else if (i.name.equals(Const.HARRINGTON)) {
				Value<Float> value = i.attributesRaw
						.get(Const.HARRINGTON_PERCENT);
				harrington = true;

				if (value != null)
					harringtonPercent = value.min;
				else
					harringtonPercent = 1.0;
			} else if (i.name.equals(Const.HEXING_PANTS)) {
				Value<Float> value = i.attributesRaw
						.get(Const.HEXING_PANTS_PERCENT);
				hexingPants = true;

				if (value != null)
					hexingPantsPercent = value.min;
				else
					hexingPantsPercent = 0.20;
			}

			for (ItemInformation.Attributes.Attribute a : i.attributes.passive) {
				if (a.text.startsWith(Const.PET_ATTACK_SPEED)) {
					int j = a.text.indexOf('%');
					String value = a.text.substring(
							Const.PET_ATTACK_SPEED.length(), j);
					double d = Double.valueOf(value);
					tnt = true;
					petSpeed = (int) d;
				}
			}
		}

		for (Map.Entry<String, Integer> e : setCounts.entrySet()) {
			int count = e.getValue();

			if ((count > 1) && royalRing)
				count++;

			D3Set set = sets.get(e.getKey());

			for (D3Set.Rank r : set.ranks) {
				if (r.required <= count) {
					if (r.attributesRaw != null) {
						Value<Float> v = r.attributesRaw
								.get(Const.ELITE_DAMAGE_RAW);

						if (v != null) {
							elite += Math.round(v.min * 100.0);
						}
					}
				}
			}
		}

		data.setRoyalRing(royalRing);
		data.setSetCounts(setCounts);

		for (ActiveSkill skill : ActiveSkill.values()) {
			String slug = skill.getSlug();
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

		boolean leorics = false;
		boolean pridesFall = false;
		int leoricsLevel = 0;
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

		data.setBornsCdr(hasSet(hero, setCounts, royalRing, Const.BORNS, 2));
		data.setCrimsonCdr(hasSet(hero, setCounts, royalRing,
				Const.CAPTAIN_CRIMSON, 2));
		data.setCrimsonRcr(hasSet(hero, setCounts, royalRing,
				Const.CAPTAIN_CRIMSON, 3));

		int m = getSetCount(data, Const.MARAUDERS);
		int nats = getSetCount(data, Const.NATS);
		int bastions = getSetCount(data, Const.BASTIONS_OF_WILL);
		
		data.setNumMarauders(m);
		data.setNumNats(nats);
		data.setBastions(bastions >= 2);
		
		data.setCdrData(cdrData);
		data.setRcrData(rcrData);

		if (helm != null) {
			if ((helm.name != null) && helm.name.equals(Const.PRIDES_FALL)) {
				pridesFall = true;
			}

			if ((helm.name != null) && helm.name.equals(Const.LEORICS_CROWN)) {
				leorics = true;
				Value<Float> value = helm.attributesRaw
						.get(Const.GEM_MULTIPLIER);

				if (value != null)
					leoricsLevel = (int) Math.round(value.min * 100.0);
			}

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

		data.setPridesFall(pridesFall);

		data.setLeorics(leorics);
		data.setLeoricsPercent(leoricsLevel / 100.0);
		data.setDiamond(diamond);

		data.setTnt(tnt);
		data.setTntPercent(petSpeed / 100.0);

		data.setCalamityMdf(calamity);
		data.setEliteDamage(elite / 100.0);

		data.setMeticulousBolts(meticulousBolts);
		data.setMeticulousBoltsPercent(meticulousBoltsPercent / 100.0);

		data.setStrongarm(strongarm);
		data.setStrongarmPercent(strongarmPercent / 100.0);

		data.setHexingPants(hexingPants);
		data.setHexingPantsPercent(hexingPantsPercent);

		data.setHarrington(harrington);
		data.setHarringtonPercent(harringtonPercent);

		data.setHasBombardiers(bombadiers);
		data.setCrashingRain(crashingRain);
		data.setVaxo(vaxo);
		data.setHelltrapper(helltrapper);
		data.setHelltrapperPercent(helltrapperPercent);

		data.setKridershot(kridershot);
		data.setKridershotHatred(kridershotHatred);
		data.setSpines(spines);
		data.setSpinesHatred(spinesHatred);

		data.setHatredPerSecond(hatredPerSecond);
		data.setEquipmentDiscipline(discipline);

		data.setReapersWraps(reapersWraps);
		data.setReapersWrapsPercent(reapersWrapsPercent);

		data.setCindercoat(cindercoat);
		data.setCindercoatRCR(cindercoatPercent);

		data.setOdysseysEnd(odysseysEnd);
		data.setOdysseysEndPercent(odysseysEndPercent);

		data.setWolf(data.isCompanion()
				&& ((data.getCompanionRune() == Rune.Wolf) || (data
						.getNumMarauders() >= 2)));

	}

	private static Boolean hasSet(HeroProfile hero,
			Map<String, Integer> setCounts, boolean royalRing, String name,
			int count) {

		boolean hasSet = false;

		Integer i = setCounts.get(name);

		if (i != null) {

			if ((i >= 2) && (royalRing))
				i++;

			hasSet = (i >= count);
		}

		return hasSet;
	}

	public static int getSetCount(CharacterData data, String name) {
		Integer i = data.getSetCounts().get(name);

		if (i != null) {

			if ((i >= 2) && (data.isRoyalRing()))
				i++;
		} else {
			i = 0;
		}

		return i;
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
}
