package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.stats.DBStatistics;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("dhcalc")
public interface DHCalcService extends RemoteService {

	CareerProfile getProfile(Realm realm, String profile, int tag);
	HeroProfile getHero(Realm realm, String profile, int tag, int id);
	ItemInformation getItem(Realm realm, String item);
	
	String exportData(ExportData data);
	
	FormData getClientData(String client);
	
	Version getVersion();
	
	void logData(CharacterData data);
	
	DBStatistics getStats(Rune sentryRune, ActiveSkill[] skills, Rune[] runes);
}
