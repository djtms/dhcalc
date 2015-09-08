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

import java.util.Map;
import java.util.TreeMap;

public enum GemSkill {

	Toxin("Gem of Efficacious Toxin", "gem-of-efficacious-toxin",
			new GemAdapter() {

				@Override
				public double getValue(SimulationState state) {
					return state.getData().isToxin() ? (2.0 + (state.getData()
							.getToxinLevel() * 0.05)) : 0.0;
				}
			}),

	PainEnhancer("Pain Enhancer", "pain-enhancer", new GemAdapter() {

		@Override
		public double getValue(SimulationState state) {
			return state.getData().isPainEnhancer() ? (4.0 + (state.getData()
					.getPainEnhancerLevel() * 0.1)) : 0.0;
		}
	}, new Attribute() {

		@Override
		public String getLabel() {
			return GemSkill.BLEEDING;
		}

		@Override
		public int getMaxValue(int level) {
			return 100;
		}
	}),

	Gogok("Gogok of Swiftness", "gogok-of-swiftness", null, new Attribute(){

		@Override
		public String getLabel() {
			return GemSkill.STACKS;
		}

		@Override
		public int getMaxValue(int level) {
			return 15;
		}
	}),

	Taeguk("Taeguk", "taeguk", null, new Attribute(){

		@Override
		public String getLabel() {
			return GemSkill.STACKS;
		}

		@Override
		public int getMaxValue(int level) {
			return 20 + level;
		}
	}),

	Mirinae("Mirinae, Teardrop of the Starweaver", "mirinae-teardrop-of-the-starweaver", null),
	Wreath("Wreath of Lightning", "wreath-of-lightning", null),

	BotP("Bane of the Powerful","bane-of-the-powerful", null),
	BotS("Bane of the Stricken","bane-of-the-stricken", null),
	BotT("Bane of the Trapped","bane-of-the-trapped", null),
	Boon("Boon of the Hoarder","boon-of-the-hoarder", null),
//	Ease("Gem of Ease", "gem-of-ease", null),
	Enforcer("Enforcer","enforcer", null),
	Esoteric("Esoteric Alteration","esoteric-alteration", null),
	Iceblink("Iceblink", "iceblink", null),
	Invigorating("Invigorating Gemstone", "invigorating-gemstone", null),
	Gizzard("Molten Wildebeest's Gizzard","molten-wildebeests-gizzard", null),
	Moratorium("Moratorium", "moratorium", null),
	Mutilation("Mutilation Guard", "mutilation-guard", null),
	Simplicity("Simplicity's Strength","simplicitys-strength", null),
	Zeis("Zei's Stone of Vengeance", "zeis-stone-of-vengeance", null),
	
	;

	public static final String STACKS = "Stacks";
	public static final String BLEEDING = "Bleeding";
	
	private final String displayName;
	private final String slug;
	private final GemAdapter adapter;
	private final Attribute[] attributes;

	public interface Attribute {
		public String getLabel();

		public int getMaxValue(int level);
	}

	public interface GemAdapter {
		double getValue(SimulationState state);
	}

	private GemSkill(String displayName, String slug, GemAdapter adapter,
			Attribute... attributes) {
		this.displayName = displayName;
		this.slug = slug;
		this.adapter = adapter;
		this.attributes = attributes;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getUrl() {
		return "http://us.battle.net/d3/en/item/" + slug;
	}

	public double getScalar(SimulationState state) {
		return adapter.getValue(state);
	}

	public Attribute[] getAttributes() {
		return this.attributes;
	}

	private static Map<String, GemSkill> gemsByName;
	
	public static GemSkill getGemByName(String name) {
		if (gemsByName == null) {
			gemsByName = new TreeMap<String, GemSkill>();
			
			for (GemSkill gem : values()) {
				gemsByName.put(gem.getDisplayName(), gem);
			}
		}
		
		return gemsByName.get(name);
	}
	
	@Override
	public String toString() {
		return displayName;
	}
}
