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

public class SharpshooterSimulator {

	public static double simulate(double aps, double delayMs, double baseCC, int count, int num) {
		double total = 0;
		
		for (int i = 0; i < num; i++) {
			total += simulate(aps, delayMs, baseCC, count);
		}
		
		return total/num;
	}
	
	public static double simulate(double aps, double delayMs, double baseCC, int count) {
		double cc = baseCC;
		double interval = (1.0 / aps) + (delayMs / 1000.0);
		double t = 0;
		double next = 1.0;
		double reset = Integer.MAX_VALUE;
		double total = 0;

		for (int i = 0; i < count; i++) {

			if (t >= reset) {
				cc = baseCC;
				reset = Integer.MAX_VALUE;
			}

			if (t >= next) {
				cc += 0.04;

				next += 1.0;
			}

			cc = Math.min(cc,  1.0);

			total += cc;

			if (Math.random() <= cc) {
				reset = t + 1.0;
				next = reset + 1.0;
			}

			t += interval;
		}

		double avg = ((total / count) - baseCC);

		return avg;
	}

	public static double getValue(CharacterData data) {
		
		if (!data.isSharpshooter())
			return 0.0;
		
		double mainHandAps = data.getAps();
		double aps = mainHandAps;
		double cc = data.getCritChance();
		
		if (data.isSingleOut() && (data.getTargetSpacing() >= 20))
			cc += 0.25;
		
		if (data.getGems().containsKey(GemSkill.Iceblink) && (data.getGemLevel(GemSkill.Iceblink) >= 25)) {
			cc += (0.1 * data.getPercentSlowedChilled());
		}
		
		if (data.getSkills().get(ActiveSkill.Caltrops) == Rune.Bait_the_Trap) {
			cc += 0.1;
		}
		
		cc = Math.min(cc,  1.0);
		
		if (data.getOffHand_weaponType() != null) {
			double offHandAps = data.getOffHand_aps();
			
			aps = (mainHandAps + offHandAps) / 2.0;
		}

		double value = simulate(aps, data.getDelay(), cc, 1000, 100);

		return Math.round(value * 1000.0) / 1000.0;
	}
	
}
