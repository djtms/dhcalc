package com.dawg6.web.sentry.shared.calculator.d3api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributesRaw implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("Armor_Bonus_Item")
	public Value<Float> Armor_Bonus_Item;
	@JsonProperty("Armor_Item")
	public Value<Float> Armor_Item;
	@JsonProperty("Attacks_Per_Second_Item")
	public Value<Float> Attacks_Per_Second_Item;
	@JsonProperty("Attacks_Per_Second_Item_Percent")
	public Value<Float> Attacks_Per_Second_Item_Percent;
	@JsonProperty("Attacks_Per_Second_Percent")
	public Value<Float> Attacks_Per_Second_Percent;
	@JsonProperty("Attribute_Set_Item_Discount")
	public Value<Float> Attribute_Set_Item_Discount;
	@JsonProperty("Block_Amount_Item_Delta")
	public Value<Float> Block_Amount_Item_Delta;
	@JsonProperty("Block_Amount_Item_Min")
	public Value<Float> Block_Amount_Item_Min;
	@JsonProperty("Block_Chance_Bonus_Item")
	public Value<Float> Block_Chance_Bonus_Item;
	@JsonProperty("Block_Chance_Item")
	public Value<Float> Block_Chance_Item;
	@JsonProperty("Bow")
	public Value<Float> Bow;
	@JsonProperty("Crit_Damage_Percent")
	public Value<Float> Crit_Damage_Percent;
	@JsonProperty("Crit_Percent_Bonus_Capped")
	public Value<Float> Crit_Percent_Bonus_Capped;
	@JsonProperty("Crossbow")
	public Value<Float> Crossbow;
	@JsonProperty("CrowdControl_Reduction")
	public Value<Float> CrowdControl_Reduction;
	@JsonProperty("Damage_Bonus_Min#Physical")
	public Value<Float> Damage_Bonus_Min_Physical;
	@JsonProperty("Damage_Dealt_Percent_Bonus#Arcane")
	public Value<Float> Damage_Dealt_Percent_Bonus_Arcane;
	@JsonProperty("Damage_Dealt_Percent_Bonus#Cold")
	public Value<Float> Damage_Dealt_Percent_Bonus_Cold;
	@JsonProperty("Damage_Dealt_Percent_Bonus#Fire")
	public Value<Float> Damage_Dealt_Percent_Bonus_Fire;
	@JsonProperty("Damage_Dealt_Percent_Bonus#Lightning")
	public Value<Float> Damage_Dealt_Percent_Bonus_Lightning;
	@JsonProperty("Damage_Dealt_Percent_Bonus#Physical")
	public Value<Float> Damage_Dealt_Percent_Bonus_Physical;
	@JsonProperty("Damage_Delta#Physical")
	public Value<Float> Damage_Delta_Physical;
	@JsonProperty("Damage_Min#Physical")
	public Value<Float> Damage_Min_Physical;
	@JsonProperty("Damage_Percent_Bonus_Vs_Elites")
	public Value<Float> Damage_Percent_Bonus_Vs_Elites;
	@JsonProperty("Damage_Percent_Bonus_Vs_Monster_Type#Demon")
	public Value<Float> Damage_Percent_Bonus_Vs_Monster_Type_Demon;
	@JsonProperty("Damage_Percent_Reduction_From_Elites")
	public Value<Float> Damage_Percent_Reduction_From_Elites;
	@JsonProperty("Damage_Percent_Reduction_From_Melee")
	public Value<Float> Damage_Percent_Reduction_From_Melee;
	@JsonProperty("Damage_Percent_Reduction_From_Ranged")
	public Value<Float> Damage_Percent_Reduction_From_Ranged;
	@JsonProperty("Damage_Weapon_Bonus_Delta#Physical")
	public Value<Float> Damage_Weapon_Bonus_Delta_Physical;
	@JsonProperty("Damage_Weapon_Bonus_Delta_X1#Physical")
	public Value<Float> Damage_Weapon_Bonus_Delta_X1_Physical;
	@JsonProperty("Damage_Weapon_Bonus_Flat#Physical")
	public Value<Float> Damage_Weapon_Bonus_Flat_Physical;
	@JsonProperty("Damage_Weapon_Bonus_Min#Physical")
	public Value<Float> Damage_Weapon_Bonus_Min_Physical;
	@JsonProperty("Damage_Weapon_Bonus_Min_X1#Physical")
	public Value<Float> Damage_Weapon_Bonus_Min_X1_Physical;
	@JsonProperty("Damage_Weapon_Delta#Arcane")
	public Value<Float> Damage_Weapon_Delta_Arcane;
	@JsonProperty("Damage_Weapon_Delta#Cold")
	public Value<Float> Damage_Weapon_Delta_Cold;
	@JsonProperty("Damage_Weapon_Delta#Fire")
	public Value<Float> Damage_Weapon_Delta_Fire;
	@JsonProperty("Damage_Weapon_Delta#Holy")
	public Value<Float> Damage_Weapon_Delta_Holy;
	@JsonProperty("Damage_Weapon_Delta#Lightning")
	public Value<Float> Damage_Weapon_Delta_Lightning;
	@JsonProperty("Damage_Weapon_Delta#Physical")
	public Value<Float> Damage_Weapon_Delta_Physical;
	@JsonProperty("Damage_Weapon_Delta#Poison")
	public Value<Float> Damage_Weapon_Delta_Poison;
	@JsonProperty("Damage_Weapon_Min#Arcane")
	public Value<Float> Damage_Weapon_Min_Arcane;
	@JsonProperty("Damage_Weapon_Min#Cold")
	public Value<Float> Damage_Weapon_Min_Cold;
	@JsonProperty("Damage_Weapon_Min#Fire")
	public Value<Float> Damage_Weapon_Min_Fire;
	@JsonProperty("Damage_Weapon_Min#Holy")
	public Value<Float> Damage_Weapon_Min_Holy;
	@JsonProperty("Damage_Weapon_Min#Lightning")
	public Value<Float> Damage_Weapon_Min_Lightning;
	@JsonProperty("Damage_Weapon_Min#Physical")
	public Value<Float> Damage_Weapon_Min_Physical;
	@JsonProperty("Damage_Weapon_Min#Poison")
	public Value<Float> Damage_Weapon_Min_Poison;
	@JsonProperty("Damage_Weapon_Percent_Bonus#Physical")
	public Value<Float> Damage_Weapon_Percent_Bonus_Physical;
	@JsonProperty("Dexterity_Item")
	public Value<Float> Dexterity_Item;
	@JsonProperty("Dexterity")
	public Value<Float> Dexterity;
	@JsonProperty("Durability_Cur")
	public Value<Float> Durability_Cur;
	@JsonProperty("Durability_Max")
	public Value<Float> Durability_Max;
	@JsonProperty("Durability_Max_Before_Reforge")
	public Value<Float> Durability_Max_Before_Reforge;
	@JsonProperty("Experience_Bonus")
	public Value<Float> Experience_Bonus;
	@JsonProperty("Experience_Bonus_Percent")
	public Value<Float> Experience_Bonus_Percent;
	@JsonProperty("Gold_Find")
	public Value<Float> Gold_Find;
	@JsonProperty("Gold_PickUp_Radius")
	public Value<Float> Gold_PickUp_Radius;
	@JsonProperty("Health_Globe_Bonus_Health")
	public Value<Float> Health_Globe_Bonus_Health;
	@JsonProperty("Hitpoints_Max_Percent_Bonus_Item")
	public Value<Float> Hitpoints_Max_Percent_Bonus_Item;
	@JsonProperty("Hitpoints_On_Hit")
	public Value<Float> Hitpoints_On_Hit;
	@JsonProperty("Hitpoints_On_Kill")
	public Value<Float> Hitpoints_On_Kill;
	@JsonProperty("Hitpoints_Regen_Per_Second")
	public Value<Float> Hitpoints_Regen_Per_Second;
	@JsonProperty("Intelligence")
	public Value<Float> Intelligence;
	@JsonProperty("Intelligence_Item")
	public Value<Float> Intelligence_Item;
	@JsonProperty("IsCrafted")
	public Value<Float> IsCrafted;
	@JsonProperty("Item_Binding_Level_Override")
	public Value<Float> Item_Binding_Level_Override;
	@JsonProperty("Item_Indestructible")
	public Value<Float> Item_Indestructible;
	@JsonProperty("Item_LegendaryItem_Level_Override")
	public Value<Float> Item_LegendaryItem_Level_Override;
	@JsonProperty("Item_Legendary_Item_Base_Item")
	public Value<Float> Item_Legendary_Item_Base_Item;
	@JsonProperty("Item_Level_Requirement_Reduction")
	public Value<Float> Item_Level_Requirement_Reduction;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Boots_007_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Boots_007_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Bracer_105_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Bracer_105_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_015_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_015_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_020_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_020_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_024")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_024;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_513_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_513_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_515_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_515_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_526_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_526_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_542_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_542_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_581_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_581_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_616_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_616_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_622_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_622_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_624_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_624_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_648_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_648_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_653_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_653_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_664_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_664_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_665_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_665_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_669_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_669_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_681_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_681_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_686_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_686_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_703_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_703_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_710_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_710_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_711_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_711_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_715_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_715_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_727_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_727_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Shoulder_002_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Shoulder_002_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Shoulder_009")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Shoulder_009;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Sword_2H_004_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Sword_2H_004_x1;
	@JsonProperty("Item_Power_Passive#itemPassive_Unique_Mace_1H_002_x1")
	public Value<Float> Item_Power_Passive_itemPassive_Unique_Mace_1H_002_x1;
	@JsonProperty("Item_Power_Passive#itemPassive_Unique_Pants_007_x1")
	public Value<Float> Item_Power_Passive_itemPassive_Unique_Pants_007_x1;
	@JsonProperty("Item_Power_Passive#itemPassive_Unique_Shield_011")
	public Value<Float> Item_Power_Passive_itemPassive_Unique_Shield_011;
	@JsonProperty("Item_Power_Passive#itemPassive_Unique_XBow_002")
	public Value<Float> Item_Power_Passive_itemPassive_Unique_XBow_002;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_524_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_524_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_680_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_680_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_716_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_716_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_717_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_717_x1;
	@JsonProperty("Item_Power_Passive#ItemPassive_Unique_Ring_723_x1")
	public Value<Float> Item_Power_Passive_ItemPassive_Unique_Ring_723_x1;
	@JsonProperty("Loot_2_0_Drop")
	public Value<Float> Loot_2_0_Drop;
	@JsonProperty("Magic_Find")
	public Value<Float> Magic_Find;
	@JsonProperty("Movement_Scalar")
	public Value<Float> Movement_Scalar;
	@JsonProperty("Power_Cooldown_Reduction_Percent_All")
	public Value<Float> Power_Cooldown_Reduction_Percent_All;
	@JsonProperty("Power_Damage_Percent_Bonus#Barbarian_Bash")
	public Value<Float> Power_Damage_Percent_Bonus_Barbarian_Bash;
	@JsonProperty("Power_Damage_Percent_Bonus#DemonHunter_RapidFire")
	public Value<Float> Power_Damage_Percent_Bonus_DemonHunter_RapidFire;
	@JsonProperty("Power_Damage_Percent_Bonus#DemonHunter_Sentry")
	public Value<Float> Power_Damage_Percent_Bonus_DemonHunter_Sentry;
	@JsonProperty("Power_Damage_Percent_Bonus#Monk_CripplingWave")
	public Value<Float> Power_Damage_Percent_Bonus_Monk_CripplingWave;
	@JsonProperty("Power_Damage_Percent_Bonus#Monk_LashingTailKick")
	public Value<Float> Power_Damage_Percent_Bonus_Monk_LashingTailKick;
	@JsonProperty("Power_Damage_Percent_Bonus#Monk_TempestRush")
	public Value<Float> Power_Damage_Percent_Bonus_Monk_TempestRush;
	@JsonProperty("Power_Damage_Percent_Bonus#Trait_Witchdoctor_ZombieDogSpawner_Passive")
	public Value<Float> Power_Damage_Percent_Bonus_Trait_Witchdoctor_ZombieDogSpawner_Passive;
	@JsonProperty("Power_Damage_Percent_Bonus#X1_Crusader_BlessedShield")
	public Value<Float> Power_Damage_Percent_Bonus_X1_Crusader_BlessedShield;
	@JsonProperty("Power_Damage_Percent_Bonus#X1_Crusader_ShieldBash2")
	public Value<Float> Power_Damage_Percent_Bonus_X1_Crusader_ShieldBash2;
	@JsonProperty("Power_Damage_Percent_Bonus#X1_DemonHunter_EntanglingShot")
	public Value<Float> Power_Damage_Percent_Bonus_X1_DemonHunter_EntanglingShot;
	@JsonProperty("Power_Damage_Percent_Bonus#X1_DemonHunter_EvasiveFire")
	public Value<Float> Power_Damage_Percent_Bonus_X1_DemonHunter_EvasiveFire;
	@JsonProperty("Power_Resource_Reduction#Monk_SweepingWind")
	public Value<Float> Power_Resource_Reduction_Monk_SweepingWind;
	@JsonProperty("Quiver")
	public Value<Float> Quiver;
	@JsonProperty("Requirement_When_Equipped#BowAny")
	public Value<Float> Requirement_When_Equipped_BowAny;
	@JsonProperty("Resistance#Arcane")
	public Value<Float> Resistance_Arcane;
	@JsonProperty("Resistance#Cold")
	public Value<Float> Resistance_Cold;
	@JsonProperty("Resistance#Fire")
	public Value<Float> Resistance_Fire;
	@JsonProperty("Resistance#Lightning")
	public Value<Float> Resistance_Lightning;
	@JsonProperty("Resistance#Physical")
	public Value<Float> Resistance_Physical;
	@JsonProperty("Resistance#Poison")
	public Value<Float> Resistance_Poison;
	@JsonProperty("Resistance_All")
	public Value<Float> Resistance_All;
	@JsonProperty("Resource_Cost_Reduction_Percent_All")
	public Value<Float> Resource_Cost_Reduction_Percent_All;
	@JsonProperty("Resource_Max_Bonus#Arcanum")
	public Value<Float> Resource_Max_Bonus_Arcanum;
	@JsonProperty("Resource_Max_Bonus#Discipline")
	public Value<Float> Resource_Max_Bonus_Discipline;
	@JsonProperty("Resource_Max_Bonus#Fury")
	public Value<Float> Resource_Max_Bonus_Fury;
	@JsonProperty("Resource_Max_Bonus#Mana")
	public Value<Float> Resource_Max_Bonus_Mana;
	@JsonProperty("Resource_Max_Bonus#Spirit")
	public Value<Float> Resource_Max_Bonus_Spirit;
	@JsonProperty("Resource_On_Kill#Mana")
	public Value<Float> Resource_On_Kill_Mana;
	@JsonProperty("Resource_Regen_Per_Second#Arcanum")
	public Value<Float> Resource_Regen_Per_Second_Arcanum;
	@JsonProperty("Resource_Regen_Per_Second#Mana")
	public Value<Float> Resource_Regen_Per_Second_Mana;
	@JsonProperty("Resource_Regen_Per_Second#Spirit")
	public Value<Float> Resource_Regen_Per_Second_Spirit;
	@JsonProperty("Season")
	public Value<Float> Season;
	@JsonProperty("Sockets")
	public Value<Float> Sockets;
	@JsonProperty("Spending_Resource_Heals_Percent#Spirit")
	public Value<Float> Spending_Resource_Heals_Percent_Spirit;
	@JsonProperty("Splash_Damage_Effect_Percent")
	public Value<Float> Splash_Damage_Effect_Percent;
	@JsonProperty("Steal_Health_Percent")
	public Value<Float> Steal_Health_Percent;
	@JsonProperty("Strength_Item")
	public Value<Float> Strength_Item;
	@JsonProperty("Thorns_Fixed#Fire")
	public Value<Float> Thorns_Fixed_Fire;
	@JsonProperty("Thorns_Fixed#Physical")
	public Value<Float> Thorns_Fixed_Physical;
	@JsonProperty("Vitality")
	public Value<Float> Vitality;
	@JsonProperty("Vitality_Item")
	public Value<Float> Vitality_Item;
	@JsonProperty("Weapon_On_Hit_Bleed_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Bleed_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Bleed_Proc_Damage_Base")
	public Value<Float> Weapon_On_Hit_Bleed_Proc_Damage_Base;
	@JsonProperty("Weapon_On_Hit_Bleed_Proc_Damage_Delta")
	public Value<Float> Weapon_On_Hit_Bleed_Proc_Damage_Delta;
	@JsonProperty("Weapon_On_Hit_Blind_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Blind_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Fear_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Fear_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Freeze_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Freeze_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Immobilize_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Immobilize_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Knockback_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Knockback_Proc_Chance;
	@JsonProperty("Weapon_On_Hit_Stun_Proc_Chance")
	public Value<Float> Weapon_On_Hit_Stun_Proc_Chance;
}
