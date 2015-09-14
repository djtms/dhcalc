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

import java.util.List;

public class RegenEvent extends Event {

	private double regen;
	private double venRegenTick;

	public RegenEvent(CharacterData data) {
		this.time = 1.0;
		
		Rune companionRune = data.getCompanionRune();
		
		regen = 5.0
				+ data.getHatredPerSecond()
				+ (data.isInspire() ? 1.0 : 0.0)
				+ (((companionRune == Rune.Bat) || ((companionRune != null) && data
						.getNumMarauders() >= 2)) ? 1.0 : 0.0)
				+ ((data.isArchery()
						&& (data.getWeaponType() == WeaponType.HandCrossbow) && (data
						.getOffHand_weaponType() == WeaponType.HandCrossbow)) ? 1.0
						: 0.0);
		venRegenTick = 10.0;

		if (data.isHexingPants()) {
			venRegenTick = venRegenTick
					+ (venRegenTick * data.getHexingPantsUptime() * .25)
					- (venRegenTick * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
			regen = regen
					+ (regen * data.getHexingPantsUptime() * .25)
					- (regen * (1.0 - data.getHexingPantsUptime()) * data
							.getHexingPantsPercent());
		}
	}
	
	@Override
	public void execute(EventQueue queue, List<Damage> log,
			SimulationState state) {
		
		if (regen > 0.0)
		{
			double h = regen;
		
			double actual = state.addHatred(h);
			
			if (actual > 0) {
				Damage d = new Damage();
				d.time = state.getTime();
				d.shooter = "Player";
				d.hatred = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.note = "Hatred Regen";
				log.add(d);
			}
		}

		if (state.getBuffs().isActive(Buff.Seethe)) 
		{
			double h = venRegenTick;
		
			double actual = state.addHatred(h);
			
			if (actual > 0) {
				Damage d = new Damage();
				d.time = state.getTime();
				d.shooter = "Player";
				d.source = new DamageSource(ActiveSkill.Vengeance, Rune.Seethe);
				d.hatred = actual;
				d.currentHatred = state.getHatred();
				d.currentDisc = state.getDisc();
				d.note = "Seethe Hatred";
				log.add(d);
			}
		}
		
		this.time += 1.0;
		
		queue.push(this);
	}

}
