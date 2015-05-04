package com.dawg6.web.dhcalc.shared.calculator.d3api;

public enum Realm {
	US("Americas","http://us.battle.net","https://us.api.battle.net"),
	EU("Europe","http://eu.battle.net","https://eu.api.battle.net"),
	KR("Korea","http://kr.battle.net","https://kr.api.battle.net"),
	TW("Taiwan","http://tw.battle.net","https://tw.api.battle.net");
	
	private final String displayName;
	private final String host;
	private final String apiHost;
	
	private Realm(String displayName, String host, String apiHost) {
		this.displayName = displayName;
		this.host = host;
		this.apiHost = apiHost;
	}
	
	public String getApiHost() {
		return apiHost;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public String getHost() {
		return this.host;
	}
}
