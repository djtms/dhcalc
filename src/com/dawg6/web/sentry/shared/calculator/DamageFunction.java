package com.dawg6.web.sentry.shared.calculator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
			new DamageRow(ActiveSkill.EA, Rune.Immolation_Arrow, 3.15, 2.0, true,
					Integer.MAX_VALUE, 1, 10, "Burning DoT", DamageType.Fire,
					DamageMultiplier.DoT),
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
			new DamageRow(ActiveSkill.IMP, Rune.Chemical_Burn, 5.0, 2.0, true, 0,
					"Burning DoT", DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.IMP, Rune.Overpenetration, 7.5, true,
					Integer.MAX_VALUE, DamageType.Cold),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, true, 0,
					"Initial", DamageType.Lightning),
			new DamageRow(ActiveSkill.IMP, Rune.Ricochet, 7.5, false, 2, 2,
					"Ricochet", DamageType.Lightning),
			new DamageRow(ActiveSkill.IMP, Rune.Grievous_Wounds, 7.5, true, 0,
					"Initial", DamageType.Physical),
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
			new DamageRow(ActiveSkill.CHAK, Rune.Shuriken_Cloud, 2.0, 120.0, true,
					Integer.MAX_VALUE, 0, "DoT", DamageType.Physical,
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
			new DamageRow(ActiveSkill.ES, Rune.Shock_Collar, 0.8, 2.0, true, 2, 0,
					"DoT", DamageType.Lightning, DamageMultiplier.DoT),
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
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Tinkerer, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Fire,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cluster_Grenades, 2.0,
					true, Integer.MAX_VALUE, 0, 9, DamageType.Fire,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Grenade_Cache, 1.6, true,
					Integer.MAX_VALUE, 3, 6, DamageType.Fire,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Stun_Grenade, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Lightning,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cold_Grenade, 1.6, true,
					Integer.MAX_VALUE, 0, 6, DamageType.Lightning,
					DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.GRENADE, Rune.Cold_Grenade, 1.2, 3.0, true,
					Integer.MAX_VALUE, 0, 6, "DoT", DamageType.Cold,
					DamageMultiplier.Grenades, DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.BOLT, Rune.None, 2.8, true, 0, "Bolt",
					DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Spitfire_Turret, 2.8, true, 0,
					"Bolt", DamageType.Fire),
			new DamageRow(ActiveSkill.BOLT, Rune.Spitfire_Turret, 1.2, true,
					0, "Rockets", DamageType.Fire, DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.BOLT, Rune.Impaling_Bolt, 2.8, true, 0,
					"Bolt", DamageType.Physical),
			new DamageRow(ActiveSkill.BOLT, Rune.Polar_Station, 2.8, true, 0,
					"Bolt Chill", DamageType.Cold),
			new DamageRow(ActiveSkill.BOLT, Rune.Guardian_Turret, 2.8, true, 0,
					"Bolt", DamageType.Physical),

			new DamageRow(ActiveSkill.SENTRY, Rune.Chain_of_Torment, 3.0, 1.0, true,
					9, 0, "Chain DoT", DamageType.Fire, DamageMultiplier.DoT),

			new DamageRow(new DamageSource(GemSkill.Toxin), 20.0, 10.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Poison,
					DamageMultiplier.DoT),
			new DamageRow(new DamageSource(GemSkill.PainEnhancer), 12.0, 3.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical,
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

			new DamageRow(ActiveSkill.ST, Rune.None, 3.4, true,
					Integer.MAX_VALUE, 0, 8, DamageType.Fire),
			new DamageRow(ActiveSkill.ST, Rune.Echoing_Blast, 4.2, true,
					Integer.MAX_VALUE, 0, 8, DamageType.Cold),
			new DamageRow(ActiveSkill.ST, Rune.Sticky_Trap, 8.0, true,
					Integer.MAX_VALUE, 0, 16, DamageType.Fire),
			new DamageRow(ActiveSkill.ST, Rune.Long_Fuse, 5.2, true,
					Integer.MAX_VALUE, 0, 8, DamageType.Fire),
			new DamageRow(ActiveSkill.ST, Rune.Lightning_Rod, 5, true, 2,
					DamageType.Lightning),
			new DamageRow(ActiveSkill.ST, Rune.Scatter, 3.4, true,
					Integer.MAX_VALUE, 0, 8, DamageType.Fire),

			new DamageRow(ActiveSkill.Caltrops, Rune.Jagged_Spikes, 2.7, 6.0, true,
					Integer.MAX_VALUE, 0, 12, "DoT", DamageType.Physical,
					DamageMultiplier.DoT),

			new DamageRow(ActiveSkill.RoV, Rune.None, 15.0, 5.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Dark_Cloud, 35.0, 8.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Physical, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Shade, 28.0, 5.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Lightning, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Stampede, 46.0, 3.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Anathema, 58.0, 2.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Fire, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.RoV, Rune.Flying_Strike, 38.0, 4.0, true,
					Integer.MAX_VALUE, "DoT", DamageType.Cold, DamageMultiplier.DoT),

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

			new DamageRow(ActiveSkill.Strafe, Rune.None, 6.75, true, 3,
					DamageType.Physical),
			new DamageRow(ActiveSkill.Strafe, Rune.Icy_Trail, 6.75, true, 3,
					DamageType.Cold),
			new DamageRow(ActiveSkill.Strafe, Rune.Icy_Trail, 3.0, 3.0, true, 3,
					"DoT", DamageType.Cold, DamageMultiplier.DoT),
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
					DamageMultiplier.Grenades),

			new DamageRow(ActiveSkill.FoK, Rune.None, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical),
			new DamageRow(ActiveSkill.FoK, Rune.Pinpoint_Accuracy, 16.0, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Lightning),
			new DamageRow(ActiveSkill.FoK, Rune.Bladed_Armor, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical),
			new DamageRow(ActiveSkill.FoK, Rune.Knives_Expert, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Physical),
			new DamageRow(ActiveSkill.FoK, Rune.Fan_of_Daggers, 6.2, true,
					Integer.MAX_VALUE, 1, 20, DamageType.Fire),
			new DamageRow(ActiveSkill.FoK, Rune.Assassins_Knives, 6.2, true,
					Integer.MAX_VALUE, 1, 20, "Short Range",
					DamageType.Physical),
			new DamageRow(ActiveSkill.FoK, Rune.Assassins_Knives, 6.2, true, 4,
					1, Integer.MAX_VALUE, "Long Range", DamageType.Physical),

			new DamageRow(ActiveSkill.Vengeance, Rune.None, 0.6, true, 3,
					"Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.None, 0.4, true, 3,
					"Homing Rockets", DamageType.Physical,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.Personal_Mortar, 0.6,
					true, 3, "Side Guns", DamageType.Fire),
			new DamageRow(ActiveSkill.Vengeance, Rune.Personal_Mortar, 1.5,
					true, Integer.MAX_VALUE, 2, 8, "Grenade AoE",
					DamageType.Physical, DamageMultiplier.Grenades),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 0.6, true, 3,
					"Side Guns", DamageType.Lightning),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 0.4, true, 3,
					"Homing Rockets", DamageType.Lightning,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.Dark_Heart, 3.25, 15.0, true,
					Integer.MAX_VALUE, "Lightning AoE DoT",
					DamageType.Lightning, DamageMultiplier.DoT),
			new DamageRow(ActiveSkill.Vengeance, Rune.Side_Cannons, 2.25, true,
					3, "Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.Seethe, 0.6, true, 3,
					"Side Guns", DamageType.Physical),
			new DamageRow(ActiveSkill.Vengeance, Rune.Seethe, 0.4, true, 3,
					"Homing Rockets", DamageType.Physical,
					DamageMultiplier.Rockets),
			new DamageRow(ActiveSkill.Vengeance, Rune.From_the_Shadows, 0.6,
					true, 3, "Side Guns", DamageType.Cold),
			new DamageRow(ActiveSkill.Vengeance, Rune.From_the_Shadows, 1.2,
					true, 3, "Allies", DamageType.Cold), };

	public static List<Damage> getDamages(boolean isPlayer, boolean isSentry,
			String shooter, DamageSource source, SimulationState state) {
		List<Damage> list = new Vector<Damage>();

		int index = 0;
		int numShooters = isSentry ? state.getData().getNumSentries() : 1;
		boolean first = true;
		WeaponType offHand_type = state.getData().getOffHand_weaponType();
		DamageMultiplier wDMult = DamageMultiplier.WD;
		
		// TODO re-enable Additional targets
		TargetType[] targetTypes = { TargetType.Primary }; //, TargetType.Additional }; 
		
		if ((source == null)
				|| ((source.skill != ActiveSkill.RoV)
						&& (source.skill != ActiveSkill.FoK)
						&& (source.skill != ActiveSkill.Vengeance) && (source.skill != ActiveSkill.CR))) {
			if ((offHand_type != null) && (state.getHand() == Hand.OffHand)
					&& (isPlayer
							|| shooter.equals(ActiveSkill.Companion
									.getLongName()) || ((source != null) && ((source.skill == ActiveSkill.SENTRY) || (source.skill == ActiveSkill.BOLT))))) {

				wDMult = DamageMultiplier.OHWD;
			}
		}

		double baseWd = wDMult.getValue(state);

		for (DamageRow dr : ALL) {
			if (dr.source.test(source, state.getData(), dr.radius)) {

				double spacing = state.getData().getTargetSpacing();
				double aoeRange = dr.radius;

				if (dr.multipliers.contains(DamageMultiplier.Grenades)
						&& state.getData().isGrenadier()) {
					aoeRange *= 1.2;
				}

				for (TargetType target : targetTypes) {
					state.getData().setTargetType(target);

					int add = Math.min(dr.maxAdditional,
							state.getData().getNumAdditional());

					if (target == TargetType.Additional) {

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

					if (((target == TargetType.Primary) && dr.primary)
							|| ((target == TargetType.Additional) && (add > 0))) {

						double wd = baseWd;

						double m = dr.scalar;

						if (dr.source.skill == ActiveSkill.CR) {
							m = state.getData().getCrashingRainPercent();
						} else if ((dr.source.skill == ActiveSkill.EA)
								&& (dr.source.rune == Rune.Ball_Lightning)) {
							m = (dr.scalar / 2)
									* state.getData().getTargetSize().getHits();

							if (state.getData().isMeticulousBolts()) {
								double ratio = 1.0 / state.getData()
										.getMeticulousBoltsPercent();
								m *= ratio;
							}
						} else if (dr.source.gem != null) {
							m = dr.source.gem.getScalar(state.getData());
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

						if (target == TargetType.Additional) {
							if (add > 1) {
								multBuf.append(add + " x ");
								m *= add;
							}
						}

						if (dr.multipliers.contains(DamageMultiplier.Grenades)
								&& (dr.numProjectiles > 0)) {
							double numP = dr.numProjectiles;
							int num = (dr.primary ? 1 : 0) + add;

							if (spacing > aoeRange) {
								if (num < numP) {

									if (target == TargetType.Primary) {
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

								multBuf.append("#Grenades(" + Util.format(numP)
										+ ") x ");
							}

						}

						multBuf.append(Util.format(scalar) + " x ");

						multBuf.append(wDMult.getAbbreviation());

						// if (dr.source.skill != ActiveSkill.Companion) {
						double cc = DamageMultiplier.CC.getValue(state);
						double iced = DamageMultiplier.Iced.getValue(state);
						double cd = DamageMultiplier.CHD.getValue(state);
						double caltrops = DamageMultiplier.CaltropsBT
								.getValue(state);
						double singleOut = DamageMultiplier.SingleOut
								.getValue(state);

						if (dr.multipliers.contains(DamageMultiplier.OnCrit)
								&& (((cc > 0.0) || (singleOut > 0.0)
										|| (caltrops > 0.0) || (iced > 0.0)))) {
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

								ccStr.append(DamageMultiplier.CaltropsBT
										.getAbbreviation()
										+ "("
										+ Util.format(caltrops) + ")");

								if ((cc > 0.0) || (singleOut > 0.0))
									ccStr.append(")");
							}

							if (iced > 0.0) {
								if ((cc > 0.0) || (singleOut > 0.0)
										|| (caltrops > 0.0))
									ccStr.append(" + ");

								ccStr.append(DamageMultiplier.Iced
										.getAbbreviation()
										+ "("
										+ Util.format(iced) + ")");

								if ((cc > 0.0) || (singleOut > 0.0)
										|| (caltrops > 0.0))
									ccStr.append(")");
							}

							multBuf.append(" x " + ccStr.toString());

							m *= Math
									.min(1.0, cc + singleOut + caltrops + iced);

						} else if (((cc > 0.0) || (singleOut > 0.0)
								|| (caltrops > 0.0) || (iced > 0.0))
								&& (cd > 0.0)) {
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

								ccStr.append(DamageMultiplier.CaltropsBT
										.getAbbreviation()
										+ "("
										+ Util.format(caltrops) + ")");

								if ((cc > 0.0) || (singleOut > 0.0))
									ccStr.append(")");
							}

							if (iced > 0.0) {
								if ((cc > 0.0) || (singleOut > 0.0)
										|| (caltrops > 0.0))
									ccStr.append(" + ");

								ccStr.append(DamageMultiplier.Iced
										.getAbbreviation()
										+ "("
										+ Util.format(iced) + ")");

								if ((cc > 0.0) || (singleOut > 0.0)
										|| (caltrops > 0.0))
									ccStr.append(")");
							}

							multBuf.append(" x (1 + (" + ccStr.toString()
									+ " x CHD(" + Util.format(cd) + ")))");

							m *= (1.0 + (Math.min(1.0, cc + singleOut
									+ caltrops + iced) * cd));
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
						}

						if (isPlayer)
							dlist.add(DamageMultiplier.AD);

						if ((skill != ActiveSkill.Companion)) {
							dlist.add(DamageMultiplier.Ambush);
						}

						if (skill != null) {

							if ((state.getData().getNumUe() >= 6)
									&& ((source.skill == ActiveSkill.MS) || (new SkillAndRune(
											skill, dr.source.rune)
											.getHatred(state.getData()) > 0))) {
								dlist.add(DamageMultiplier.UE6);
							}
						}

						for (DamageMultiplier dw : dlist) {

							if ((isSentry || (dw != DamageMultiplier.Sentry))
									&& (!isPlayer || (dw != DamageMultiplier.Enforcer))
									&& (isPlayer || (dw != DamageMultiplier.N6)
											|| (dr.source.gem != null) || ((dr.source.skill == ActiveSkill.SENTRY) && (dr.source.rune == Rune.Chain_of_Torment)))) {

								if (dw.getAccumulator() != DamageAccumulator.Special) {

									double v = dw.getValue(state);

									if (Math.abs(v) > 0.0) {

										if (dw.getAccumulator() == DamageAccumulator.Multiplicative) {
											m *= (1.0 + v);
											multBuf.append(" X "
													+ dw.getAbbreviation()
													+ "("
													+ Util.format(1.0 + v)
													+ ")");
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
												int n = state.getData().getNumAdditional();

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
						d.numAdd = add;
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

						d.totalDamage = d.damage;
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

		if ((source != null) && (source.skill == ActiveSkill.MS)
				&& state.getData().isDml()
				&& (state.getTargets().getPrimary().getPercentHealth() <= state.getData()
						.getDmlPercent())) {
			List<Damage> copy = new Vector<Damage>(list);

			for (Damage d : copy) {
				Damage dc = d.copy();

				dc.hatred = 0;

				if ((d.note == null) || (d.note.length() == 0)) {
					dc.note = "DML";
				} else {
					dc.note += " DML";
				}

				list.add(dc);
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
}