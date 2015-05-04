package com.dawg6.web.dhcalc.shared.calculator;

public class MonsterHealth {

	public static long getHealth(int rift, int numPlayers, MonsterType type) {
		
		double mult = Math.pow(1.17, rift) * 2.0;
		double hp = type.getHealth() * mult * (0.5 + (numPlayers * 0.5));
		return Math.round(hp);
	}
}
