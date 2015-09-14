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
package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;
import java.util.Map;

public class ItemInformation extends D3Message implements Serializable {

	private static final long serialVersionUID = 1L;

	public String id;
	public String name;
	public String icon;
	public String displayColor;
	public String tooltipParams;
	public String[] setItemsEquipped;
	public Integer stackSizeMax;
	public ItemInformation transmogItem;
	public Integer requiredLevel;
	public Integer itemLevel;
	public Integer bonusAffixes;
	public Integer bonusAffixesMax;
	public Boolean accountBound;
	public String flavorText;
	public DyeColor dyeColor;
	public String typeName;

	public static class DyeColor implements Serializable {

		private static final long serialVersionUID = 8320529854519293317L;

		public ItemInformation item;
	}
	
	public static class Type implements Serializable {

		private static final long serialVersionUID = 1L;

		public Boolean twoHanded;
		public String id;

	}

	public Type type;

	public String damageRange;
	public Value<Integer> armor;
	public Value<Float> dps;
	public Value<Float> attacksPerSecond;
	public Value<Float> minDamage;
	public Value<Float> maxDamage;
//	public String blockChance;
	public String[] slots;

	public Attributes attributes;

	/*
	 * @gwt.typeArgs<java.lang.String,
	 * com.dawg6.web.dhcalc.shared.calculator.d3api.Value<java.lang.Float>>
	 */
	public Map<String, Value<Float>> attributesRaw;

	public RandomAffixe[] randomAffixes;

	public static class Gem implements Serializable {

		private static final long serialVersionUID = 1L;

		public ItemInformation item;
		public boolean isGem;
		public boolean isJewel;
		public int jewelRank;
		public int jewelSecondaryEffectUnlockRank;
		public String jewelSecondaryEffect;
		public Attributes attributes;

		/*
		 * @gwt.typeArgs<java.lang.String,
		 * com.dawg6.web.dhcalc.shared.calculator.d3api.Value<java.lang.Float>>
		 */
		public Map<String, Value<Float>> attributesRaw;
	}

	public Gem[] gems;

	public SocketEffect[] socketEffects;

	public static class D3Set implements Serializable {

		private static final long serialVersionUID = 1L;

		public String name;
		public ItemInformation[] items;
		public String slug;
		public Rank[] ranks;

		public static class Rank implements Serializable {

			private static final long serialVersionUID = 1L;

			public Integer required;
			public Attributes attributes;

			/*
			 * @gwt.typeArgs<java.lang.String,
			 * com.dawg6.web.dhcalc.shared.calculator
			 * .d3api.Value<java.lang.Float>>
			 */
			public Map<String, Value<Float>> attributesRaw;
		}
	}

	public D3Set set;
	public Recipt recipe;
	public Recipt[] craftedBy;
	public String description;
	public String effectDescription;
	public int seasonRequiredToDrop;
	public boolean isSeasonRequiredToDrop;
	public Integer jewelSecondaryEffectUnlockRank;
	public String jewelSecondaryEffectUnlock;
	
	public static class RandomAffixe implements Serializable {

		private static final long serialVersionUID = 1L;

		public OneOf[] oneOf;

		public static class OneOf implements Serializable {

			private static final long serialVersionUID = 1L;

			public Attributes attributes;

			/*
			 * @gwt.typeArgs<java.lang.String,
			 * com.dawg6.web.dhcalc.shared.calculator
			 * .d3api.Value<java.lang.Float>>
			 */
			public Map<String, Value<Float>> attributesRaw;
		}
	}

	public static class Recipt implements Serializable {

		private static final long serialVersionUID = 1L;

		public String id;
		public String slug;
		public String name;
		public int cost;
		public Reagent[] reagents;

		public static class Reagent implements Serializable {

			private static final long serialVersionUID = 1L;

			public int quantity;
			public ItemInformation item;
		}

		public ItemInformation itemProduced;
	}

	public static class Attributes implements Serializable {

		private static final long serialVersionUID = 1L;

		public Attribute[] primary;
		public Attribute[] secondary;
		public Attribute[] passive;

		public static class Attribute implements Serializable {

			private static final long serialVersionUID = 1L;

			public String text;
			public String affixType;
			public String color;

		}

	}
}
