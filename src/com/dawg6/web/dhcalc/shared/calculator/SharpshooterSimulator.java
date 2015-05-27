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
