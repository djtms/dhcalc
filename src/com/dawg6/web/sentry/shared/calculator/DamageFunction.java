package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Vector;

public class DamageFunction {

	public static final DamageRow[] ALL = {
			new DamageRow(ActiveSkill.CA, Rune.None, 5.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.CA, Rune.None, 2.2, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.CA, Rune.Dazzling_Arrow, 5.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Lightning),
			new DamageRow(ActiveSkill.CA, Rune.Dazzling_Arrow, 2.2, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades",
					DamageType.Lightning, DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.CA, Rune.Shooting_Stars, 5.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Physical),
			new DamageRow(ActiveSkill.CA, Rune.Shooting_Stars, 6.0, true, 2, 3,
					"Rockets", DamageType.Physical, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.CA, Rune.Maelstrom, 5.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Cold),
			new DamageRow(ActiveSkill.CA, Rune.Maelstrom, 4.5, true, 4, 5,
					"Rockets", DamageType.Cold, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.CA, Rune.Cluster_Bombs, 5.25, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.CA, Rune.Loaded_For_Bear, 7.7, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.CA, Rune.Loaded_For_Bear, 2.2, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades),

			new DamageRow(ActiveSkill.MS, Rune.None, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Fire_at_Will, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Lightning),
			new DamageRow(ActiveSkill.MS, Rune.Burst_Fire, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Cold),
			new DamageRow(ActiveSkill.MS, Rune.Burst_Fire, 2.0, true,
					Integer.MAX_VALUE, 1, "Burst", DamageType.Cold),
			new DamageRow(ActiveSkill.MS, Rune.Suppression_Fire, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Full_Broadside, 4.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Arsenal, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Fire),
			new DamageRow(ActiveSkill.MS, Rune.Arsenal, 3.0, true, 2, 3,
					"Rockets", DamageType.Fire, DamageMultiplier.Rockets),

			new DamageRow(ActiveSkill.EA, Rune.None, 3.0, true,
					Integer.MAX_VALUE, DamageType.Fire),
			new DamageRow(ActiveSkill.EA, Rune.Ball_Lightning, 3.0, true,
					Integer.MAX_VALUE, "Special", DamageType.Lightning),
			new DamageRow(ActiveSkill.EA, Rune.Frost_Arrow, 3.3, true, 0,
					DamageType.Cold),
			new DamageRow(ActiveSkill.EA, Rune.Frost_Arrow, 3.3, false, 10, 10,
					"Chill", DamageType.Cold),
			new DamageRow(ActiveSkill.EA, Rune.Immolation_Arrow, 3.0, true, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.EA, Rune.Immolation_Arrow, 3.15 / 2.0,
					true, Integer.MAX_VALUE, 1, 10, "Burning DoT",
					DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.EA, Rune.Lightning_Bolts, 3.0, true,
					Integer.MAX_VALUE, DamageType.Lightning),
			new DamageRow(ActiveSkill.EA, Rune.Nether_Tentacles, 3.0, true,
					Integer.MAX_VALUE, DamageType.Physical),

			new DamageRow(ActiveSkill.IMP, Rune.None, 7.5, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.IMP, Rune.Impact, 7.5, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.IMP, Rune.Chemical_Burn, 7.5, true, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.IMP, Rune.Chemical_Burn, 5.0 / 2.0, true,
					0, "Burning DoT", DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.IMP, Rune.Overpenetration, 7.5, true,
					Integer.MAX_VALUE, DamageType.Cold),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, true, 0,
					"Initial", DamageType.Lightning),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, false, 2, 2,
					"Ricochet", DamageType.Lightning),
			new DamageRow(ActiveSkill.IMP, Rune.Grievous_Wounds, 7.5, true, 0,
					"Initial", DamageType.Physical),
			new DamageRow(ActiveSkill.IMP, Rune.Grievous_Wounds, 3.3, true, 0,
					"On Crit", DamageType.Physical), // fix Crit

			new DamageRow(ActiveSkill.CHAK, Rune.None, 3.8, true,
					Integer.MAX_VALUE, 0, DamageType.Physical),
			new DamageRow(ActiveSkill.CHAK, Rune.Twin_Chakrams, 4.4, true,
					Integer.MAX_VALUE, 0, DamageType.Fire),
			new DamageRow(ActiveSkill.CHAK, Rune.Serpentine, 5.0, true,
					Integer.MAX_VALUE, 0, DamageType.Poison),
			new DamageRow(ActiveSkill.CHAK, Rune.Razor_Disk, 3.8, true,
					Integer.MAX_VALUE, 0, DamageType.Physical),
			new DamageRow(ActiveSkill.CHAK, Rune.Boomerang, 4.0, true,
					Integer.MAX_VALUE, 0, DamageType.Lightning),
			new DamageRow(ActiveSkill.CHAK, Rune.Shuriken_Cloud, 2.0, true,
					Integer.MAX_VALUE, 0, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.BOLT, Rune.None, 2.8, true, 0, "Bolt",
					DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Spitfire_Turret, 2.8, true, 0,
					"Bolt", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLT, Rune.Impaling_Bolt, 2.8, true, 0,
					"Bolt", DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Polar_Station, 2.8, true, 0,
					"Bolt Chill", DamageType.Cold),
			new DamageRow(ActiveSkill.BOLT, Rune.Guardian_Turret, 2.8, true, 0,
					"Bolt", DamageType.Physical),

			new DamageRow(ActiveSkill.SENTRY, Rune.Spitfire_Turret, 1.2, true,
					0, "Rockets", DamageType.Fire, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.SENTRY, Rune.Chain_of_Torment, 3.0, true,
					9, 0, "Chain DoT", DamageType.Fire, DamageMultiplier.DoT),

			new DamageRow(new DamageSource(GemSkill.Toxin), 20.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Poison,
					DamageMultiplier.DoT),
			new DamageRow(new DamageSource(GemSkill.PainEnhancer), 12.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical,
					DamageMultiplier.DoT), };

	public static List<Damage> getDamages(DamageSource source, int qty,
			CharacterData data) {
		List<Damage> list = new Vector<Damage>();

		int index = 0;

		for (DamageRow dr : ALL) {
			if (dr.source.test(source, data)) {

				double spacing = data.getTargetSpacing();
				double aoeRange = dr.radius;

				if (dr.multipliers.contains(DamageMultiplier.Grenades)
						&& data.isGrenadier()) {
					aoeRange *= 1.2;
				}

				for (Target target : Target.values()) {
					data.setTargetType(target);
					
					int add = Math.min(dr.maxAdditional,
							data.getNumAdditional());

					if (target == Target.Additional) {

						if (dr.multipliers.contains(DamageMultiplier.Grenades)
								&& (dr.numProjectiles > 0)
								&& (spacing > aoeRange)) {
							int numP = dr.numProjectiles;

							if (dr.primary)
								numP--;

							add = Math.min(add, numP);
						} else if ((aoeRange > 0) && (spacing > aoeRange)) {
							int max = dr.numProjectiles;

							if (dr.primary)
								max--;

							add = Math.min(max, add);
						}
					}

					if (((target == Target.Primary) && dr.primary)
							|| ((target == Target.Additional) && (add > 0))) {

						double wd = data.getWeaponDamage();

						double m = dr.scalar;
						
						if ((dr.source.skill == ActiveSkill.EA) && (dr.source.rune == Rune.Ball_Lightning)) {
							m = (dr.scalar / 2) * data.getTargetSize().getHits();
							
							if (data.isMeticulousBolts()) {
								double ratio = 1.0 / data.getMeticulousBoltsPercent();
								m *= ratio;
							}
						} else if (dr.source.gem != null)
							m = dr.source.gem.getScalar(data);

						double scalar = m;
						double a = 0.0;
						double ea = 0.0;

						StringBuffer addBuf = new StringBuffer();
						StringBuffer multBuf = new StringBuffer();
						StringBuffer eaBuf = new StringBuffer();

						if (dr.multipliers.contains(DamageMultiplier.DoT))
							multBuf.append(FiringData.DURATION);
						else
							multBuf.append(qty);

						if (target == Target.Additional) {
							if (add > 1) {
								multBuf.append(" x " + add);
								m *= add;
							}
						}

						if (dr.multipliers.contains(DamageMultiplier.Grenades)
								&& (dr.numProjectiles > 0)) {
							double numP = dr.numProjectiles;
							int num = (dr.primary ? 1 : 0) + add;

							if (spacing > aoeRange) {
								if (num < numP) {

									if (target == Target.Primary) {
										if (add > 0) {
											numP = 1;
										}
									} else
										numP = (numP - 1) / add;
								} else {
									numP = 1;
								}
							}

							if (numP > 1) {
								m *= numP;

								multBuf.append(" x #Grenades("
										+ Util.format(numP) + ")");
							}

						}

						multBuf.append(" x " + Util.format(scalar));
						
						multBuf.append(" x WD");

						double cc = DamageMultiplier.CC.getValue(data);
						double cd = DamageMultiplier.CHD.getValue(data);
						double caltrops = DamageMultiplier.Caltrops.getValue(data);
						double singleOut = DamageMultiplier.SingleOut
								.getValue(data);

						if (((cc > 0.0) || (singleOut > 0.0) || (caltrops > 0.0)) && (cd > 0.0)) {
							StringBuffer ccStr = new StringBuffer();

							if (cc > 0.0) {

								if ((singleOut > 0.0) || (caltrops > 0.0))
									ccStr.append("(");

								ccStr.append("CC(" + Util.format(cc) + ")");
							}

							if (singleOut > 0.0) {
								if (cc > 0.0)
									ccStr.append(" + ");
								else
									ccStr.append("(");

								ccStr.append(DamageMultiplier.SingleOut
										.getAbbreviation()
										+ "("
										+ singleOut
										+ ")");

								if (cc > 0.0)
									ccStr.append(")");
							}

							if (caltrops > 0.0) {
								if ((cc > 0.0) || (singleOut > 0.0))
									ccStr.append(" + ");

								ccStr.append(DamageMultiplier.Caltrops
										.getAbbreviation()
										+ "("
										+ Util.format(caltrops)
										+ ")");

								if ((cc > 0.0) || (singleOut > 0.0))
									ccStr.append(")");
							}

							multBuf.append(" x (1 + (" + ccStr.toString()
									+ " x CHD(" + Util.format(cd) + ")))");

							m *= (1.0 + ((cc + singleOut + caltrops) * cd));
						}

						addBuf.append("(1");
						eaBuf.append("(1");

						for (DamageMultiplier dw : dr.multipliers) {

							if (dw.getAccumulator() != DamageAccumulator.Special) {

								double v = dw.getValue(data);

								if (Math.abs(v) > 0.0) {

									if (dw.getAccumulator() == DamageAccumulator.Multiplicative) {
										m *= (1.0 + v);
										multBuf.append(" X "
												+ dw.getAbbreviation() + "("
												+ Util.format(1.0 + v) + ")");
									} else if (dw.getAccumulator() == DamageAccumulator.ElementalAdditive) {
										ea += v;

										eaBuf.append(" + "
												+ dw.getAbbreviation() + "("
												+ Util.format(v) + ")");
									} else {
										a += v;

										addBuf.append(" + "
												+ dw.getAbbreviation() + "("
												+ Util.format(v) + ")");

									}
								}
							}
						}

						addBuf.append(")");
						eaBuf.append(")");

						Damage d = new Damage();
						d.damage = wd * m * (1.0 + a) * (1.0 + ea);
						d.qty = qty;
						d.index = index++;
						d.note = dr.note;
						d.source = dr.source;
						d.type = dr.type;
						d.target = target;
						d.numAdd = add;
						d.nonStacking = dr.multipliers.contains(DamageMultiplier.DoT);

						if (dr.multipliers.contains(DamageMultiplier.DoT))
							d.totalDamage = d.damage * FiringData.DURATION;
						else
							d.totalDamage = d.damage * d.qty;

						d.log = multBuf.toString();

						if (ea > 0.0)
							d.log = d.log + " x " + eaBuf.toString();

						if (a > 0.0)
							d.log = d.log + " x " + addBuf.toString();

						list.add(d);
					}
				}
			}
		}

		return list;
	}

}