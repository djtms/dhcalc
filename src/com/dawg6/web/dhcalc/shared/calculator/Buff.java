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

public enum Buff {

	BwGen("Bastions Will Generator"),
	BwSpend("Bastions Will Spender"),
	Wolf("Wolf Howl"),
	OtherWolf("Other Player Wolf Howl"),
	Bbv("Big Bad Voodo"),
	BotP("Bane of the Powerful"),
	Paranoia("Paranoia"),
	Piranhas("Prianhas"),
	Valor("Laws of Valor"),
	InnerSanct("Inner Sanctuary"),
	CripWave("Crippling Wave"),
	Retribution("Retribution"),
	ConvictionPassive("Conviction Passive"),
	ConvictionActive("Conviction Active"),
	Caltrops("Caltrops/BtT"),
	MfdPrimary("MfD Primary"),
	MfdAdditional("MfD Additional"),
	Calamity("Calamity MfD"),
	Hysteria("Hysteria"),
	N6("Nat's 6 pc bonus"),
	Seethe("Vengeance/Seethe"),
	FocusedMind("Preparation/Focused Mind"),
	CompanionActive("Companion Active"),
	Vengeance("Vengeance"),
	TimeWarp("Slow Time/Time Warp");
	
	private String name;
	
	private Buff(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
