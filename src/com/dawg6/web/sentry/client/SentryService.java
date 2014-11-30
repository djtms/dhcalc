package com.dawg6.web.sentry.client;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.ExportData;
import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.JsonObject;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("sentry")
public interface SentryService extends RemoteService {

	CareerProfile getProfile(Realm realm, String profile, int tag);
	HeroProfile getHero(Realm realm, String profile, int tag, int id);
	
	String serializeFormData(FormData data);
	String exportData(ExportData data);
	
	FormData getClientData(String client);
	
	Version getVersion();
	
	String toJson(JsonObject object);
	JsonObject fromJson(String json, String type);
	
	void logData(CharacterData data);
	
	DBStatistics getStats(Rune sentryRune, ActiveSkill[] skills, Rune[] runes);
}
