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

public enum GemSkill {

	Toxin("Gem of Efficacious Toxin",
			"http://us.battle.net/d3/en/item/gem-of-efficacious-toxin",
			new Test() {

				@Override
				public double getValue(CharacterData data) {
					return data.isToxin() ? (2.0 + (data.getToxinLevel() * 0.05))
							: 0.0;
				}
			}),

	PainEnhancer("Pain Enhancer",
			"http://us.battle.net/d3/en/item/pain-enhancer", new Test() {

				@Override
				public double getValue(CharacterData data) {
					return data.isPainEnhancer() ? (4.0 + (data
							.getPainEnhancerLevel() * 0.1)) : 0.0;
				}
			});

	private String displayName;
	private String url;
	private Test test;

	public interface Test {
		double getValue(CharacterData data);
	}

	private GemSkill(String displayName, String url, Test test) {
		this.displayName = displayName;
		this.url = url;
		this.test = test;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getUrl() {
		return url;
	}

	public double getScalar(CharacterData data) {
		return test.getValue(data);
	}
}
