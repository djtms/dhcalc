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
package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.JsonObject;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.stats.DBStatistics;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DHCalcServiceAsync {

	void getProfile(Realm realm, String profile, int tag,
			AsyncCallback<CareerProfile> callback);

	void getHero(Realm realm, String profile, int tag, int id,
			AsyncCallback<HeroProfile> callback);

//	void serializeFormData(FormData data, AsyncCallback<String> callback);

	void getClientData(String client, AsyncCallback<FormData> callback);

	void exportData(ExportData data, AsyncCallback<String> callback);

	void getVersion(AsyncCallback<Version> callback);

//	void toJson(JsonObject object, AsyncCallback<String> callback);

//	void fromJson(String json, String type, AsyncCallback<JsonObject> callback);

	void logData(CharacterData data,
			AsyncCallback<Void> callback);

	void getStats(Rune sentryRune, ActiveSkill[] skills, Rune[] runes,
			AsyncCallback<DBStatistics> callback);

	void getItem(Realm realm, String item, AsyncCallback<ItemInformation> callback);
	
	
}
