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

import com.dawg6.d3api.shared.CareerProfile;
import com.dawg6.d3api.shared.HeroProfile;
import com.dawg6.d3api.shared.ItemInformation;
import com.dawg6.d3api.shared.Leaderboard;
import com.dawg6.d3api.shared.Realm;
import com.dawg6.d3api.shared.SeasonIndex;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.NewsItem;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Version;
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
	
	SeasonIndex getSeasonEraIndex(Realm realm);
	Leaderboard getLeaderboard(Realm realm, int seasonEra, boolean isEra, String which);
	
	NewsItem[] getNews();
}
