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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class DamageFunction {

	public static final DamageRow[] ALL = {
			new DamageRow(ActiveSkill.CA, Rune.None, 6.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.CA, Rune.None, 2.2, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.CA, Rune.Dazzling_Arrow, 6.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Lightning),
			new DamageRow(ActiveSkill.CA, Rune.Dazzling_Arrow, 2.5, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades",
					DamageType.Lightning, DamageMultiplier.Grenades,
					DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.CA, Rune.Shooting_Stars, 6.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Physical),
			new DamageRow(ActiveSkill.CA, Rune.Shooting_Stars, 6.0, true, 1, 2,
					"Rockets", DamageType.Physical, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.CA, Rune.Maelstrom, 6.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Cold),
			new DamageRow(ActiveSkill.CA, Rune.Maelstrom, 4.5, true, 2, 3,
					"Rockets", DamageType.Cold, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.CA, Rune.Cluster_Bombs, 6.5, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.CA, Rune.Loaded_For_Bear, 8.5, true,
					Integer.MAX_VALUE, 1, 8, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.CA, Rune.Loaded_For_Bear, 2.5, true,
					Integer.MAX_VALUE, 4, 4, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),

			new DamageRow(ActiveSkill.MS, Rune.None, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Fire_at_Will, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Lightning),
			new DamageRow(ActiveSkill.MS, Rune.Wind_Chill, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Cold), // @TODO
																			// 8%
																			// Crit
																			// Chance
																			// on
																			// chilled
																			// for
																			// 3
																			// secs
			new DamageRow(ActiveSkill.MS, Rune.Suppression_Fire, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Full_Broadside, 5.0, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Physical),
			new DamageRow(ActiveSkill.MS, Rune.Arsenal, 3.6, true,
					Integer.MAX_VALUE, Integer.MAX_VALUE, DamageType.Fire),
			new DamageRow(ActiveSkill.MS, Rune.Arsenal, 3.0, true, 2, 3,
					"Rockets", DamageType.Fire, DamageMultiplier.Rockets),

			new DamageRow(ActiveSkill.EA, Rune.None, 3.0, true,
					Integer.MAX_VALUE, DamageType.Fire),
			new DamageRow(ActiveSkill.EA, Rune.Ball_Lightning, 3.0, true,
					Integer.MAX_VALUE, "Special", DamageType.Lightning,
					DamageMultiplier.BL),
			new DamageRow(ActiveSkill.EA, Rune.Frost_Arrow, 3.3, true, 0,
					DamageType.Cold, DamageMultiplier.Augustines),
			new DamageRow(ActiveSkill.EA, Rune.Frost_Arrow, 3.3, false, 10, 10,
					"Chill", DamageType.Cold, DamageMultiplier.Augustines),
			new DamageRow(ActiveSkill.EA, Rune.Immolation_Arrow, 3.0, true, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.EA, Rune.Immolation_Arrow, 3.15, 2.0,
					true, Integer.MAX_VALUE, 1, 10, "Burning DoT",
					DamageType.Fire, DamageMultiplier.DoT,
					DamageMultiplier.Augustines),
			new DamageRow(ActiveSkill.EA, Rune.Lightning_Bolts, 3.0, true,
					Integer.MAX_VALUE, DamageType.Lightning,
					DamageMultiplier.Augustines),
			new DamageRow(ActiveSkill.EA, Rune.Nether_Tentacles, 3.0, true,
					Integer.MAX_VALUE, DamageType.Physical,
					DamageMultiplier.Augustines),

			new DamageRow(ActiveSkill.IMP, Rune.None, 7.5, true, 0,
					DamageType.Physical, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Impact, 7.5, true, 0,
					DamageType.Physical, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Chemical_Burn, 7.5, true, 0,
					DamageType.Fire, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Chemical_Burn, 5.0, 2.0, true,
					0, "Burning DoT", DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.IMP, Rune.Overpenetration, 7.5, true, 0,
					DamageType.Cold, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Overpenetration, 7.5, false,
					Integer.MAX_VALUE, "Pierce", DamageType.Cold),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, true, 0,
					"Initial", DamageType.Lightning, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, false, 2, 2,
					"Ricochet", DamageType.Lightning),
			new DamageRow(ActiveSkill.IMP, Rune.Grievous_Wounds, 7.5, true, 0,
					"Initial", DamageType.Physical, DamageMultiplier.S6),
			new DamageRow(ActiveSkill.IMP, Rune.Grievous_Wounds, 3.3, true, 0,
					"On Crit", DamageType.Physical, DamageMultiplier.OnCrit),

			new DamageRow(ActiveSkill.CHAK, Rune.None, 3.8, true,
					Integer.MAX_VALUE, 0, DamageType.Physical),
			new DamageRow(ActiveSkill.CHAK, Rune.Twin_Chakrams, 4.4, true,
					Integer.MAX_VALUE, 0, DamageType.Fire),
			new DamageRow(ActiveSkill.CHAK, Rune.Serpentine, 5.0, true,
					Integer.MAX_VALUE, 0, DamageType.Cold),
			new DamageRow(ActiveSkill.CHAK, Rune.Razor_Disk, 3.8, true,
					Integer.MAX_VALUE, 0, DamageType.Physical),
			new DamageRow(ActiveSkill.CHAK, Rune.Boomerang, 4.0, true,
					Integer.MAX_VALUE, 0, DamageType.Lightning),
			new DamageRow(ActiveSkill.CHAK, Rune.Shuriken_Cloud, 2.0, 600.0,
					true, Integer.MAX_VALUE, 0, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.HA, Rune.None, 1.55, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.None, 1.55 * 0.35, false, 1, 0,
					"Pierce", DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.Puncturing_Arrow, 1.55, true, 0,
					0, DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.Puncturing_Arrow, 1.55 * 0.5,
					false, 1, 0, "Pierce", DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.Serrated_Arrow, 1.55, true, 0,
					0, DamageType.Fire),
			new DamageRow(ActiveSkill.HA, Rune.Serrated_Arrow, 1.55 * 0.35,
					false, 1, 0, "Pierce", DamageType.Fire),
			new DamageRow(ActiveSkill.HA, Rune.Shatter_Shot, 1.55, true, 0, 0,
					DamageType.Lightning),
			new DamageRow(ActiveSkill.HA, Rune.Shatter_Shot, 1.55 * 0.35, true,
					3, 0, "Pierce", DamageType.Lightning),
			new DamageRow(ActiveSkill.HA, Rune.Devouring_Arrow, 1.55, true, 0,
					0, DamageType.Cold),
			new DamageRow(ActiveSkill.HA, Rune.Devouring_Arrow,
					1.55 * 0.35 * 1.7, true, 1, 0, "Pierce", DamageType.Cold),
			new DamageRow(ActiveSkill.HA, Rune.Spray_of_Teeth, 1.55, true, 0,
					0, DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.Spray_of_Teeth, 1.55 * 0.35,
					true, 1, 0, "Pierce", DamageType.Physical),
			new DamageRow(ActiveSkill.HA, Rune.Spray_of_Teeth, 0.6, true,
					Integer.MAX_VALUE, 0, 10, "On Crit", DamageType.Physical,
					DamageMultiplier.OnCrit),

			new DamageRow(ActiveSkill.ES, Rune.None, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.ES, Rune.Chain_Gang, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.ES, Rune.Shock_Collar, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.ES, Rune.Shock_Collar, 0.8, 2.0, true, 2,
					0, "DoT", DamageType.Lightning, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.ES, Rune.Heavy_Burden, 2.0, true, 0, 0,
					DamageType.Cold),
			new DamageRow(ActiveSkill.ES, Rune.Justice_is_Served, 2.0, true, 0,
					0, DamageType.Fire),
			new DamageRow(ActiveSkill.ES, Rune.Bounty_Hunter, 2.0, true, 0, 0,
					DamageType.Physical),

			new DamageRow(ActiveSkill.BOLAS, Rune.None, 1.6, true, 0, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.None, 1.6, false,
					Integer.MAX_VALUE, 0, 14, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Volatile_Exolosives, 1.6,
					true, 0, 0, DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Volatile_Exolosives, 1.6,
					false, Integer.MAX_VALUE, 0, 20, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Thunder_Ball, 1.6, true, 0,
					0, DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Thunder_Ball, 1.6, false,
					Integer.MAX_VALUE, 0, 14, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Freezing_Strike, 1.6, true,
					2, 3, DamageType.Cold),
			new DamageRow(ActiveSkill.BOLAS, Rune.Bitter_Pill, 1.6, true, 0, 0,
					DamageType.Lightning),
			new DamageRow(ActiveSkill.BOLAS, Rune.Bitter_Pill, 1.6, false,
					Integer.MAX_VALUE, 0, 14, "AoE", DamageType.Lightning),
			new DamageRow(ActiveSkill.BOLAS, Rune.Imminent_Doom, 2.16, true, 0,
					0, DamageType.Fire),
			new DamageRow(ActiveSkill.BOLAS, Rune.Imminent_Doom, 1.49, false,
					Integer.MAX_VALUE, 0, 14, "AoE", DamageType.Fire),

			new DamageRow(ActiveSkill.EF, Rune.None, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.None, 1.0, false, 2, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.Hardened, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.Hardened, 1.0, false, 2, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.Parting_Gift, 2.0, true, 0, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.Parting_Gift, 1.0, false, 2, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.EF, Rune.Parting_Gift, 1.5, false,
					Integer.MAX_VALUE, 0, 12, "Bomb", DamageType.Physical), // TODO
																			// Bomb
																			// frequency/targeting
			new DamageRow(ActiveSkill.EF, Rune.Covering_Fire, 2.0, true, 0, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.EF, Rune.Covering_Fire, 2.0, false, 2, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.EF, Rune.Focus, 2.0, true, 0, 0,
					DamageType.Cold),
			new DamageRow(ActiveSkill.EF, Rune.Focus, 1.0, false, 2, 0,
					DamageType.Cold),
			new DamageRow(ActiveSkill.EF, Rune.Surge, 2.0, true, 0, 0,
					DamageType.Lightning),
			new DamageRow(ActiveSkill.EF, Rune.Surge, 1.0, false, 2, 0,
					DamageType.Lightning),

			new DamageRow(ActiveSkill.GRENADE, Rune.None, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Tinkerer, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cluster_Grenades, 2.0,
					true, Integer.MAX_VALUE, 0, 9, DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Grenade_Cache, 1.6, true,
					Integer.MAX_VALUE, 3, 6, DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Stun_Grenade, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Lightning,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cold_Grenade, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Lightning,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cold_Grenade, 1.2, 3.0,
					true, Integer.MAX_VALUE, 0, 6, "DoT", DamageType.Cold,
					DamageMultiplier.Grenades, DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.BOLT, Rune.None, 2.8, true, 0, "Bolt",
					DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Spitfire_Turret, 2.8, true, 0,
					"Bolt", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLT, Rune.Spitfire_Turret, 1.2, true, 0,
					"Rockets", DamageType.Fire, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.BOLT, Rune.Impaling_Bolt, 2.8, true, 0,
					"Bolt", DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Polar_Station, 2.8, true, 0,
					"Bolt Chill", DamageType.Cold),
			new DamageRow(ActiveSkill.BOLT, Rune.Guardian_Turret, 2.8, true, 0,
					"Bolt", DamageType.Physical),

			new DamageRow(ActiveSkill.SENTRY, Rune.Chain_of_Torment, 3.0, 1.0,
					true, 9, 0, "Chain DoT", DamageType.Fire,
					DamageMultiplier.DoT),

			new DamageRow(new DamageSource(DamageProc.Thunderfury), 1.0, true,
					5, 1, 10, "", DamageType.Lightning),
			new DamageRow(new DamageSource(DamageProc.Mirinae), 1.0, true,
					Integer.MAX_VALUE, 1, 25, "", DamageType.Holy),

			new DamageRow(new DamageSource(DamageProc.Fulminator), 1.0, 6.0,
					true, Integer.MAX_VALUE, 1, 10, "DoT", DamageType.Lightning),

			new DamageRow(new DamageSource(DamageProc.WreathOfLightning), 1.0,
					3.0, true, Integer.MAX_VALUE, 1, 40, "DoT",
					DamageType.Lightning),
			new DamageRow(new DamageSource(DamageProc.MirinaeTick), 1.0, 3.0,
					true, Integer.MAX_VALUE, 1, 25, "Periodic", DamageType.Holy),

			new DamageRow(new DamageSource(GemSkill.Toxin), 20.0, 10.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Poison,
					DamageMultiplier.DoT),
			new DamageRow(new DamageSource(GemSkill.PainEnhancer), 12.0, 3.0,
					true, Integer.MAX_VALUE, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.Companion, Rune.None, 1.0, true, 0,
					DamageType.Physical, DamageMultiplier.Raven),
			new DamageRow(ActiveSkill.Companion, Rune.Spider, 1.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Companion, Rune.Bat, 1.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Companion, Rune.Boar, 1.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Companion, Rune.Ferret, 2.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Companion, Rune.Wolf, 1.0, true, 0,
					DamageType.Physical),

			new DamageRow(ActiveSkill.ST, Rune.None, 11.6, true,
					Integer.MAX_VALUE, 0, 8, "AoE", DamageType.Physical),
			new DamageRow(ActiveSkill.ST, Rune.Echoing_Blast, 20.2, true,
					Integer.MAX_VALUE, 0, 8, "AoE", DamageType.Cold),
			new DamageRow(ActiveSkill.ST, Rune.Custom_Trigger, 19.0, true,
					Integer.MAX_VALUE, 0, 16, "AoE", DamageType.Fire),
			new DamageRow(ActiveSkill.ST, Rune.Impaling_Spines, 19.3, true,
					Integer.MAX_VALUE, 0, 8, "AoE", DamageType.Physical),
			new DamageRow(ActiveSkill.ST, Rune.Lightning_Rod, 20.1, true,
					Integer.MAX_VALUE, 0, 10, DamageType.Lightning),
			new DamageRow(ActiveSkill.ST, Rune.Scatter, 9.6, true,
					Integer.MAX_VALUE, 0, 8, "AoE Trap-A", DamageType.Fire),
			new DamageRow(ActiveSkill.ST, Rune.Scatter, 9.6, true,
					Integer.MAX_VALUE, 0, 8, "AoE Trap-B", DamageType.Fire),

			new DamageRow(ActiveSkill.Caltrops, Rune.Jagged_Spikes, 2.7, 6.0,
					true, Integer.MAX_VALUE, 0, 12, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.RoV, Rune.None, 15.0, 5.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Dark_Cloud, 35.0, 8.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Shade, 28.0, 5.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Lightning,
					DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Stampede, 46.0, 3.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Fire,
					DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Anathema, 58.0, 2.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Fire,
					DamageMultiplier.DoT, DamageMultiplier.Grenades,
					DamageMultiplier.Hellcat),
			new DamageRow(ActiveSkill.RoV, Rune.Flying_Strike, 38.0, 4.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Cold,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.CR, Rune.None, 1.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.CR, Rune.Dark_Cloud, 1.0, true, 0,
					DamageType.Physical),
			new DamageRow(ActiveSkill.CR, Rune.Shade, 1.0, true, 0,
					DamageType.Lightning),
			new DamageRow(ActiveSkill.CR, Rune.Stampede, 1.0, true, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.CR, Rune.Anathema, 1.0, true, 0,
					DamageType.Fire),
			new DamageRow(ActiveSkill.CR, Rune.Flying_Strike, 1.0, true, 0,
					DamageType.Cold),

			new DamageRow(ActiveSkill.SoK, Rune.None, 1.0, true, 0,
					DamageType.Physical),

			new DamageRow(ActiveSkill.Strafe, Rune.None, 6.75, true, 3,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Strafe, Rune.Icy_Trail, 6.75, true, 3,
					DamageType.Cold),
			new DamageRow(ActiveSkill.Strafe, Rune.Icy_Trail, 3.0, 3.0, true,
					3, "DoT", DamageType.Cold, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.Strafe, Rune.Drifting_Shadow, 6.75, true,
					3, DamageType.Lightning),
			new DamageRow(ActiveSkill.Strafe, Rune.Stinging_Steel, 6.75, true,
					3, DamageType.Physical),
			new DamageRow(ActiveSkill.Strafe, Rune.Stinging_Steel, 1.4, true,
					3, "On Crit", DamageType.Physical, DamageMultiplier.OnCrit),
			new DamageRow(ActiveSkill.Strafe, Rune.Rocket_Storm, 6.75, true, 3,
					DamageType.Fire),
			new DamageRow(ActiveSkill.Strafe, Rune.Rocket_Storm, 1.3, true, 3,
					"Rockets", DamageType.Fire, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Strafe, Rune.Demolition, 4.6, true, 3, 4,
					9, "AoE Grenades", DamageType.Fire,
					DamageMultiplier.Grenades, DamageMultiplier.Hellcat),

			new DamageRow(ActiveSkill.FoK, Rune.None, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical,
					DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Pinpoint_Accuracy, 16.0, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Lightning,
					DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Bladed_Armor, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical,
					DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Knives_Expert, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical,
					DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Fan_of_Daggers, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Fire,
					DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Assassins_Knives, 6.2, true,
					Integer.MAX_VALUE, 1, 20, "Short Range",
					DamageType.Physical, DamageMultiplier.LGF),
			new DamageRow(ActiveSkill.FoK, Rune.Assassins_Knives, 6.2, true, 4,
					1, Integer.MAX_VALUE, "Long Range", DamageType.Physical,
					DamageMultiplier.LGF),

			new DamageRow(ActiveSkill.Vengeance, Rune.None, 0.6, true, 3,
					"Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.None, 0.8, true, 1,
					"Homing Rockets", DamageType.Physical,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.Personal_Mortar, 0.6,
					true, 3, "Side Guns", DamageType.Fire),
			new DamageRow(ActiveSkill.Vengeance, Rune.Personal_Mortar, 1.5,
					true, Integer.MAX_VALUE, 2, 8, "Grenade AoE",
					DamageType.Physical, DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 0.6, true, 3,
					"Side Guns", DamageType.Lightning),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 0.8, true, 1,
					"Homing Rockets", DamageType.Lightning,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 3.25, 15.0,
					true, Integer.MAX_VALUE, "Lightning AoE DoT",
					DamageType.Lightning, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.Vengeance, Rune.Side_Cannons, 2.25, true,
					3, "Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.Seethe, 0.6, true, 3,
					"Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.Seethe, 0.8, true, 1,
					"Homing Rockets", DamageType.Physical,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.From_the_Shadows, 0.6,
					true, 3, "Side Guns", DamageType.Cold),
			new DamageRow(ActiveSkill.Vengeance, Rune.From_the_Shadows, 1.2,
					true, 3, "Allies", DamageType.Cold), };

	public static List<Damage> getDamages(boolean isPlayer, boolean isSentry,
			String shooter, DamageSource source, SimulationState state) {
		return getDamages(isPlayer, isSentry, shooter, source, state, state
				.getTargets().toList());
	}

	public static List<Damage> getDamages(boolean isPlayer, boolean isSentry,
			String shooter, DamageSource source, SimulationState state,
			Collection<TargetType> targetTypes) {
		List<Damage> list = new Vector<Damage>();

		int index = 0;
		int numShooters = isSentry ? state.getData().getNumSentries() : 1;
		boolean first = true;
		WeaponType offHand_type = state.getData().getOffHand_weaponType();
		DamageMultiplier wDMult = DamageMultiplier.WD;

		if ((source == null)
				|| ((source.skill != ActiveSkill.RoV)
						&& (source.skill != ActiveSkill.FoK)
						&& (source.skill != ActiveSkill.Vengeance) && (source.skill != ActiveSkill.CR))) {
			if ((offHand_type != null)
					&& (state.getHand() == Hand.OffHand)
					&& (isPlayer
							|| shooter.equals(ActiveSkill.Companion
									.getLongName()) || ((source != null) && ((source.skill == ActiveSkill.SENTRY) || (source.skill == ActiveSkill.BOLT))))) {

				wDMult = DamageMultiplier.OHWD;
			}
		}

		double baseWd = wDMult.getValue(state);

		for (DamageRow dr : ALL) {
			if (dr.source.test(source, state, dr.radius)) {

				double spacing = state.getData().getTargetSpacing();
				double aoeRange = dr.radius;

				if (dr.multipliers.contains(DamageMultiplier.Grenades)
						&& state.getData().isGrenadier()) {
					aoeRange *= 1.2;
				}

				int maxAdd = Math.min(dr.maxAdditional, state.getData()
						.getNumAdditional());

				if (dr.multipliers.contains(DamageMultiplier.Grenades)
						&& (dr.numProjectiles > 0) && (spacing > aoeRange)) {
					int numP = dr.numProjectiles;

					if (dr.primary)
						numP--;

					maxAdd = Math.min(maxAdd, numP);
				} else if ((aoeRange > 0) && (spacing > aoeRange)) {
					int max = dr.numProjectiles;

					if (dr.primary)
						max--;

					maxAdd = Math.min(max, maxAdd);
				}

				int addNum = 0;

				Collection<TargetType> tlist = targetTypes;

				if (dr.source.proc != null) {
					tlist = state.getTargets().toList();
				}

				for (TargetType target : tlist) {

					if (state.getTargets().getTarget(target).isAlive()) {

						state.getData().setTargetType(target);

						if (!target.isPrimary())
							addNum++;

						if ((target.isPrimary() && dr.primary)
								|| (!target.isPrimary() && (addNum <= maxAdd))) {

							double wd = baseWd;

							double m = dr.getScalar(state.getData());

							if (dr.source.skill == ActiveSkill.CR) {
								m = state.getData().getCrashingRainPercent();
							} else if (dr.source.skill == ActiveSkill.SoK) {
								m = state.getData().getSashOfKnivesPercent();
							} else if (dr.source.proc != null) {
								m = dr.source.proc.getScalar(state);

								if (dr.dot) {
									m /= dr.dotDuration;
								}

							} else if (dr.source.gem != null) {
								m = dr.source.gem.getScalar(state);
							} else if (dr.dot) {
								m /= dr.dotDuration;
							}

							double scalar = m;
							double a = 0.0;
							double ea = 0.0;

							StringBuffer addBuf = new StringBuffer();
							StringBuffer multBuf = new StringBuffer();
							StringBuffer eaBuf = new StringBuffer();

							if (numShooters > 1)
								multBuf.append(numShooters + " x ");

							if (dr.multipliers
									.contains(DamageMultiplier.Grenades)
									&& (dr.numProjectiles > 0)) {
								double numP = dr.numProjectiles;
								int num = (dr.primary ? 1 : 0) + maxAdd;

								if (spacing > aoeRange) {
									if (num < numP) {

										if (target == TargetType.Primary) {
											if (maxAdd > 0) {
												numP = 1;
											}
										} else
											numP = (numP - 1) / maxAdd;
									} else {
										numP = 1;
									}
								}

								if (numP > 1) {
									m *= numP;

									multBuf.append("#Grenades("
											+ Util.format(numP) + ") x ");
								}

							}

							if (dr.multipliers.contains(DamageMultiplier.S6)) {
								double s6 = DamageMultiplier.S6.getValue(state);

								if (s6 > 0) {
									multBuf.append("(" + scalar + " + S6(" + s6
											+ ")) x ");
									scalar += s6;
									m = scalar;
								} else {
									multBuf.append(Util.format(scalar) + " x ");

								}
							} else {
								multBuf.append(Util.format(scalar) + " x ");
							}

							if ((dr.source.proc != null) && !dr.dot) {
								if (m > 0) {
									double pc = dr.source.proc.getProc()
											* state.getLastAttack().getProc();
									double max = dr.source.proc.getIcd();

									if ((max > 0) && (pc > max)) {
										m *= max;
										multBuf.append("PC(" + Util.format(max)
												+ ") x ");
									} else {
										m *= pc;
										multBuf.append("PC("
												+ Util.format(dr.source.proc
														.getProc()) + ") x ");
										multBuf.append("PC("
												+ Util.format(state
														.getLastAttack()
														.getProc()) + ") x ");
									}
								}
							}

							multBuf.append(wDMult.getAbbreviation());

							// if (dr.source.skill != ActiveSkill.Companion) {
							double cc = DamageMultiplier.CC.getValue(state);
							double iced = DamageMultiplier.Iced.getValue(state);
							double cd = DamageMultiplier.CHD.getValue(state);
							double ss = DamageMultiplier.SharpShooter
									.getValue(state);
							double caltrops = DamageMultiplier.CaltropsBT
									.getValue(state);
							double singleOut = DamageMultiplier.SingleOut
									.getValue(state);

							int ccCount = ((cc > 0.0) ? 1 : 0)
									+ ((iced > 0.0) ? 1 : 0)
									+ ((ss > 0.0) ? 1 : 0)
									+ ((singleOut > 0.0) ? 1 : 0)
									+ ((caltrops > 0.0) ? 1 : 0);

							if (dr.multipliers
									.contains(DamageMultiplier.OnCrit)
									&& (((cc > 0.0) || (singleOut > 0.0)
											|| (caltrops > 0.0) || (iced > 0.0) || (ss > 0.0)))) {
								StringBuffer ccStr = new StringBuffer();
								boolean isFirst = true;

								if (ccCount > 1) {
									ccStr.append("(");
								}

								if (cc > 0.0) {
									ccStr.append("CC(" + Util.format(cc) + ")");
									isFirst = false;
								}

								if (ss > 0.0) {

									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.SharpShooter
											.getAbbreviation() + "(" + ss + ")");

									isFirst = false;
								}

								if (singleOut > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.SingleOut
											.getAbbreviation()
											+ "("
											+ singleOut + ")");

									isFirst = false;
								}

								if (caltrops > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.CaltropsBT
											.getAbbreviation()
											+ "("
											+ Util.format(caltrops) + ")");

									isFirst = false;
								}

								if (iced > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.Iced
											.getAbbreviation()
											+ "("
											+ Util.format(iced) + ")");

									isFirst = false;
								}

								if (ccCount > 1)
									ccStr.append(")");

								multBuf.append(" x " + ccStr.toString());

								m *= Math.min(1.0, cc + singleOut + caltrops
										+ iced + ss);

							} else if (((cc > 0.0) || (singleOut > 0.0)
									|| (caltrops > 0.0) || (iced > 0.0) || (ss > 0.0))
									&& (cd > 0.0)) {
								StringBuffer ccStr = new StringBuffer();
								boolean isFirst = true;

								if (ccCount > 1) {
									ccStr.append("(");
								}

								if (cc > 0.0) {
									ccStr.append("CC(" + Util.format(cc) + ")");
									isFirst = false;
								}

								if (ss > 0.0) {

									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.SharpShooter
											.getAbbreviation() + "(" + ss + ")");

									isFirst = false;
								}

								if (singleOut > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.SingleOut
											.getAbbreviation()
											+ "("
											+ singleOut + ")");

									isFirst = false;
								}

								if (caltrops > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.CaltropsBT
											.getAbbreviation()
											+ "("
											+ Util.format(caltrops) + ")");

									isFirst = false;
								}

								if (iced > 0.0) {
									if (!isFirst)
										ccStr.append(" + ");

									ccStr.append(DamageMultiplier.Iced
											.getAbbreviation()
											+ "("
											+ Util.format(iced) + ")");

									isFirst = false;
								}

								if (ccCount > 1)
									ccStr.append(")");

								multBuf.append(" x (1 + " + ccStr.toString()
										+ " x CHD(" + Util.format(cd) + "))");

								m *= (1.0 + (Math.min(1.0, cc + singleOut
										+ caltrops + iced + ss) * cd));
							}
							// }

							addBuf.append("(1");
							eaBuf.append("(1");

							DamageMultiplierList dlist = new DamageMultiplierList(
									dr.multipliers);
							ActiveSkill skill = dr.source.skill;
							SkillType skillType = (skill != null) ? skill
									.getSkillType() : null;

							if (isPlayer
									&& ((skillType == SkillType.Spender) || (skillType == SkillType.Primary))) {
								dlist.add(DamageMultiplier.M6);
							} else if ((skill == ActiveSkill.Companion)
									|| (skill == ActiveSkill.Vengeance)) {
								dlist.add(DamageMultiplier.M6);
							}

							if (isPlayer) {
								dlist.add(DamageMultiplier.S2);
								dlist.add(DamageMultiplier.VenBuff);
								dlist.add(DamageMultiplier.LoN);
							} else if (isSentry) {
								dlist.add(DamageMultiplier.LoN);
								dlist.add(DamageMultiplier.M4);
								dlist.add(DamageMultiplier.VenBuff);
								dlist.remove(DamageMultiplier.Hellcat);
							}

							if ((skill != ActiveSkill.Companion)) {
								dlist.add(DamageMultiplier.Ambush);
							} else {
								dlist.add(DamageMultiplier.VenBuff);
								dlist.add(DamageMultiplier.LoN);
							}

							if (skill != null) {

								if ((state.getData().getNumUe() >= 6)
										&& ((source.skill == ActiveSkill.MS)
												|| (source.skill == ActiveSkill.Vengeance) || (new SkillAndRune(
												skill, dr.source.rune)
												.getHatred(state.getData()) > 0))) {
									dlist.add(DamageMultiplier.UE6);
								}
							}

							for (DamageMultiplier dw : dlist) {

								if ((isSentry || ((dw != DamageMultiplier.Sentry) && (dw != DamageMultiplier.SentryM4)))
										&& (!isPlayer || (dw != DamageMultiplier.Enforcer))
										&& (dw.getAccumulator() != DamageAccumulator.Special)
										&& (isPlayer
												|| (dw != DamageMultiplier.N6)
												|| (dr.source.gem != null) || ((dr.source.skill == ActiveSkill.SENTRY) && (dr.source.rune == Rune.Chain_of_Torment)))) {

									double v = dw.getValue(state);

									// Check for CoE Damage Type and Buff
									if ((dw == DamageMultiplier.COE)
											&& (v > 0.0)) {
										int i = Util.indexOf(
												CoEBuffEvent.TYPES, dr.type);

										if ((i < 0)
												|| !state.getBuffs().isActive(
														CoEBuffEvent.BUFFS[i]))
											v = 0.0;
									}

									if (Math.abs(v) > 0.0) {

										if (dw.getAccumulator() == DamageAccumulator.Multiplicative) {

											if (dw == DamageMultiplier.Hellcat) {
												double pc = 0.5;

												multBuf.append(" X (1 + ("
														+ "PC("
														+ Util.format(pc) + ") X "
														+ dw.getAbbreviation()
														+ "(" + Util.format(v)
														+ ")))");

												m *= (1.0 + (v * pc));

											} else {
												m *= (1.0 + v);
												multBuf.append(" X (1 + "
														+ dw.getAbbreviation()
														+ "(" + Util.format(v)
														+ "))");
											}

										} else if (dw.getAccumulator() == DamageAccumulator.ElementalAdditive) {
											ea += v;

											eaBuf.append(" + "
													+ dw.getAbbreviation()
													+ "(" + Util.format(v)
													+ ")");
										} else {

											if (dw != DamageMultiplier.AD) {
												a += v;

												addBuf.append(" + "
														+ dw.getAbbreviation()
														+ "(" + Util.format(v)
														+ ")");
											} else {
												int n = Math.min(maxAdd, state
														.getTargets()
														.getNumAlive() - 1);

												if (n > 0) {
													a += (v * n);

													addBuf.append(" + "
															+ dw.getAbbreviation()
															+ "(" + n + " X "
															+ Util.format(v)
															+ ")");
												}
											}
										}
									}
								}
							}

							addBuf.append(")");
							eaBuf.append(")");

							Damage d = new Damage();
							d.shooter = shooter;
							d.damage = numShooters * wd * m * (1.0 + a)
									* (1.0 + ea);
							d.index = index++;
							d.note = dr.note;
							d.source = dr.source;
							d.type = dr.type;
							d.target = target;
							// d.numAdd = add;
							d.nonStacking = dr.multipliers
									.contains(DamageMultiplier.DoT);

							if (isPlayer && first) {
								SkillAndRune skr = new SkillAndRune(skill,
										dr.source.rune);
								d.hatred = skr.getHatred(state.getData());
								first = false;
							} else {
								d.hatred = 0;
							}

							if (dr.dot) {
								d.duration = dr.dotDuration;
							} else {
								d.duration = 0.0;
							}

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
		}

		return list;
	}

	private static Map<SkillAndRune, DamageType> runeDamageType = null;

	public static synchronized DamageType getDamageType(SkillAndRune skr) {

		if (runeDamageType == null) {
			runeDamageType = new TreeMap<SkillAndRune, DamageType>();

			for (DamageRow row : ALL) {

				if (row.source != null) {
					SkillAndRune r = new SkillAndRune(row.source.skill,
							row.source.rune);
					DamageType t = runeDamageType.get(r);

					if (t == null)
						runeDamageType.put(r, row.type);
				}
			}
		}

		return runeDamageType.get(skr);

	}

	public static boolean hasDamage(ActiveSkill s) {

		for (DamageRow row : ALL)
			if ((row.source != null) && (row.source.skill == s))
				return true;

		return false;
	}

	public static boolean hasDamage(GemSkill g) {
		for (DamageRow row : ALL)
			if ((row.source != null) && (row.source.gem == g))
				return true;

		return false;
	}

	public static List<MaxHit> calculateMinMax(CharacterData data) {
		List<MaxHit> list = new Vector<MaxHit>();

		for (Map.Entry<ActiveSkill, Rune> e : data.getSkills().entrySet()) {
			list.addAll(calculateMinMax(false, e.getKey(), e.getValue(), data));

			if (data.getNumMarauders() >= 2) {
				if (e.getKey().getSkillType() == SkillType.Spender) {
					list.addAll(calculateMinMax(true, e.getKey(), e.getValue(),
							data));
				}
			}
		}

		if (data.getSkills().containsKey(ActiveSkill.SENTRY)) {
			list.addAll(calculateMinMax(true, ActiveSkill.BOLT, data
					.getSkills().get(ActiveSkill.SENTRY), data));
		}

		return list;
	}

	public static List<MaxHit> calculateMinMax(boolean sentry,
			ActiveSkill skill, Rune rune, CharacterData data) {
		List<MaxHit> list = new Vector<MaxHit>();
		DamageSource source = new DamageSource(skill, rune);

		SimulationState state = new SimulationState();
		state.setData(data);

		Set<DamageMultiplier> ignored = new TreeSet<DamageMultiplier>();
		ignored.add(DamageMultiplier.WD);
		ignored.add(DamageMultiplier.MAXWD);
		ignored.add(DamageMultiplier.OHWD);
		ignored.add(DamageMultiplier.CC);
		ignored.add(DamageMultiplier.CHD);
		ignored.add(DamageMultiplier.PC);
		ignored.add(DamageMultiplier.Traps);

		for (DamageRow dr : ALL) {
			if ((dr.source.skill != null)
					&& dr.primary
					&& (dr.source.test(source, state, dr.radius) || ((skill == ActiveSkill.Companion) && (dr.source.skill == (ActiveSkill.Companion)
							&& (data.getNumMarauders() >= 2) && (dr.source.rune != Rune.None))))) {
				MaxHit row = new MaxHit();
				row.source = dr.source;
				row.shooter = "Player";
				row.type = dr.type;

				double value = DamageMultiplier.MAXWD.getMax(sentry, dr, data);

				StringBuffer notes = new StringBuffer();
				StringBuffer log = new StringBuffer();
				StringBuffer multLog = new StringBuffer();
				StringBuffer addLog = new StringBuffer();
				StringBuffer elemAddLog = new StringBuffer();
				StringBuffer critLog = new StringBuffer();

				double crit = 1.0;
				double mult = 1.0;
				double add = 0.0;
				double elemAdd = 0.0;

				double scalar = dr.getScalar(data);

				double chd = DamageMultiplier.CHD.getMax(sentry, dr, data);

				log.append(Util.format(scalar) + " X "
						+ DamageMultiplier.MAXWD.getAbbreviation() + "("
						+ Util.format(value) + ")");

				if (dr.note != null)
					notes.append(dr.note);

				if (chd > 0) {
					crit = chd + 1.0;
					critLog.append(" X CHD(1 + " + Util.format(chd) + ")");
				}

				for (DamageMultiplier dm : DamageMultiplier.values()) {

					if (dm.hasTest() && !ignored.contains(dm)) {
						DamageAccumulator a = dm.getAccumulator();
						double v = dm.getMax(sentry, dr, data);

						if (v > 0) {
							if (a == DamageAccumulator.Additive) {
								if (add == 0.0)
									addLog.append(" X (1");

								add += v;
								addLog.append(" + " + dm.getAbbreviation()
										+ "(" + Util.format(v) + ")");
							} else if (a == DamageAccumulator.ElementalAdditive) {
								if (elemAdd == 0.0)
									elemAddLog.append(" X (1");

								elemAdd += v;
								elemAddLog.append(" + " + dm.getAbbreviation()
										+ "(" + Util.format(v) + ")");
							} else if (a == DamageAccumulator.Multiplicative) {
								mult *= (1.0 + v);
								multLog.append(" X (1 + "
										+ dm.getAbbreviation() + "("
										+ Util.format(v) + "))");
							}
						}
					}
				}

				if (add > 0)
					addLog.append(")");

				if (elemAdd > 0)
					elemAddLog.append(")");

				row.notes = notes.toString();

				row.log = log.toString() + critLog.toString()
						+ multLog.toString() + addLog.toString()
						+ elemAddLog.toString();
				row.noCrit = Math.round(value * scalar * mult * (1.0 + add)
						* (1.0 + elemAdd));
				row.maxCrit = Math.round(value * scalar * crit * mult
						* (1.0 + add) * (1.0 + elemAdd));

				if (sentry)
					row.shooter = "Sentry";
				else if (skill == ActiveSkill.Companion)
					row.shooter = "Companion";
				else
					row.shooter = "Player";

				list.add(row);
			}
		}

		return list;
	}
}