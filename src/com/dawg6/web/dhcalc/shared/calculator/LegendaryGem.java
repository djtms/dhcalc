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

public enum LegendaryGem {

	BotP("Bane of the Powerful","bane-of-the-powerful"),
	BotT("Bane of the Trapped","bane-of-the-trapped"),
	Boon("Boon of the Hoarder","boon-of-the-hoarder"),
	Ease("Gem of Ease", "gem-of-ease"),
	Enforcer("Enforcer","enforcer"),
	Esoteric("Esoteric Alteration","esoteric-alteration"),
	Toxin("Gem of Efficacious Toxin", "gem-of-efficacious-toxin"),
	Gogok("Gogok of Swiftness", "gogok-of-swiftness"),
	Iceblink("Iceblink", "iceblink"),
	Invigorating("Invigorating Gemstone", "invigorating-gemstone"),
	Mirinae("Mirinae, Teardrop of the Starweaver", "mirinae-teardrop-of-the-starweaver"),
	Gizzard("Molten Wildebeest's Gizzard","molten-wildebeests-gizzard"),
	Moratorium("Moratorium", "moratorium"),
	Mutilation("Mutilation Guard", "mutilation-guard"),
	Pain("Pain Enhancer","pain-enhancer"),
	Simplicity("Simplicity's Strength","simplicitys-strength"),
	Taeguk("Taeguk", "taeguk"),
	Wreath("Wreath of Lightning", "wreath-of-lightning"),
	Zeis("Zei's Stone of Vengeance", "zeis-stone-of-vengeance"),
	
	;
	
	private String name;
	private String slug;

	private LegendaryGem(String name, String slug) {
		this.name = name;
		this.slug = slug;
	}
	
	public String getLongName() {
		return name;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public String getUrl() {
		return "http://us.battle.net/d3/en/item/" + slug;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
