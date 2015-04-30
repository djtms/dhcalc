package com.dawg6.web.sentry.shared.calculator;

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
	Caltrops("Caltrops"),
	MfdPrimary("MfD Primary"),
	MfdAdditional("MfD Additional"),
	Calamity("Calamity MfD"),
	Hysteria("Hysteria"),
	N6("Nat's 6 pc bonus"),
	Seethe("Vengeance/Seethe"),
	FocusedMind("Preparation/Focused Mind"),
	CompanionActive("Companion Active"),
	Vengeance("Vengeance");
	
	private String name;
	
	private Buff(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
