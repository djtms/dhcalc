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
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface SentryServiceAsync {

	void getProfile(Realm realm, String profile, int tag,
			AsyncCallback<CareerProfile> callback);

	void getHero(Realm realm, String profile, int tag, int id,
			AsyncCallback<HeroProfile> callback);

	void serializeFormData(FormData data, AsyncCallback<String> callback);

	void getClientData(String client, AsyncCallback<FormData> callback);

	void exportData(ExportData data, AsyncCallback<String> callback);

	void getVersion(AsyncCallback<Version> callback);

	void toJson(JsonObject object, AsyncCallback<String> callback);

	void fromJson(String json, String type, AsyncCallback<JsonObject> callback);

	void logData(CharacterData data,
			AsyncCallback<Void> callback);

	void getStats(Rune sentryRune, ActiveSkill[] skills, Rune[] runes,
			AsyncCallback<DBStatistics> callback);
	
	
}
