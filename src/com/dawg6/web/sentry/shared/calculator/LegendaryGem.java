package com.dawg6.web.sentry.shared.calculator;

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
