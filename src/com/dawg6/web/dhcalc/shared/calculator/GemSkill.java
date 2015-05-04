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
