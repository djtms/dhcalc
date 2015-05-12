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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.dawg6.web.dhcalc.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dawg6.web.dhcalc.client.DHCalcService;
import com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcDatabase;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.Build;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.Damage;
import com.dawg6.web.dhcalc.shared.calculator.DamageResult;
import com.dawg6.web.dhcalc.shared.calculator.ExportData;
import com.dawg6.web.dhcalc.shared.calculator.FiringData;
import com.dawg6.web.dhcalc.shared.calculator.FormData;
import com.dawg6.web.dhcalc.shared.calculator.JsonObject;
import com.dawg6.web.dhcalc.shared.calculator.ProfileHelper;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.Util;
import com.dawg6.web.dhcalc.shared.calculator.Version;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.stats.DBStatistics;
import com.dawg6.web.dhcalc.shared.calculator.stats.DpsTableEntry;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DHCalcServiceImpl extends RemoteServiceServlet implements
		DHCalcService {

	private static final Logger log = Logger.getLogger(DHCalcServiceImpl.class
			.getName());

	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(
			"#,###.####");

	{
		Util.getInstance().setFormatter(new Util.Formatter() {

			@Override
			public String format(double d) {
				return DECIMAL_FORMAT.format(d);
			}
		});
	}

	private final Gson gson = new Gson();
	
	@Override
	public CareerProfile getProfile(Realm realm, String profile, int tag) {

		try {
			String server = realm.getApiHost();
			profile = URLEncoder.encode(profile, "UTF-8");

			log.info("getProfile(" + realm.getDisplayName() + "," + profile
					+ "-" + tag + ")");
			CareerProfile career = IO.getInstance().readCareerProfile(server,
					profile, tag);

//			log.info("Career: " + gson.toJson(career));
			
			if (career == null) {
				career = new CareerProfile();
				career.code = "Timeout";
				career.reason = "Timeout";
				
			}
			if (career.code != null)
				log.info(realm.getDisplayName() + "/" + profile + "-" + tag
						+ " Code: " + career.code + ", Reason: "
						+ career.reason);

			return career;

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Getting Profile", e);
			return null;
		}
	}

	@Override
	public ItemInformation getItem(Realm realm, String item) {

		try {
			String server = realm.getApiHost();

//			log.info("getItem(" + realm.getDisplayName() + "," + item + ")");
	
			ItemInformation result = IO.getInstance().readItemInformation(server,
					item);

//			log.info("Item: " + gson.toJson(result));
			
			if (result == null) {
				result = new ItemInformation();
				result.code = "Timeout";
				result.reason = "Timeout";
				
			}

			if (result.code != null)
				log.info(realm.getDisplayName() + "/" + item + " Code: " + result.code + ", Reason: "
						+ result.reason);

			return result;

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Getting Profile", e);
			return null;
		}
	}

	@Override
	public HeroProfile getHero(Realm realm, String profile, int tag, int id) {
		try {
			String server = realm.getApiHost();
			profile = URLEncoder.encode(profile, "UTF-8");

			log.info("getHero(" + realm.getDisplayName() + "," + profile + "-"
					+ tag + "/" + id + ")");

			HeroProfile hero = IO.getInstance().readHeroProfile(server,
					profile, tag, id);

			if (hero == null) {
				hero = new HeroProfile();
				hero.code = "Timeout";
				hero.reason = "Timeout";
				
			}

			if (hero.code != null)
				log.warning(realm.getDisplayName() + "/" + profile + "-" + tag
						+ " Code: " + hero.code + ", Reason: " + hero.reason);

			if (hero.items != null) {

				Map<String, ItemInformation> items = new TreeMap<String, ItemInformation>();

				for (Map.Entry<String, ItemInformation> e : hero.items
						.entrySet()) {
					ItemInformation item = getItem(server,
							e.getValue().tooltipParams);
					items.put(e.getKey(), item);

					// if (item.gems != null) {
					// for (int i = 0; i < item.gems.length; i++) {
					// ItemInformation.Gem g = item.gems[i];
					// g.item = getItem(server, g.item.tooltipParams);
					// }
					// }
				}

				hero.items = items;
			}

			return hero;

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Getting Hero", e);
			return null;
		}
	}

	private ItemInformation getItem(String server, String tooltipParams)
			throws JsonParseException, IOException {
		return IO.getInstance().readItemInformation(server, tooltipParams);
	}

	public String serializeFormData(FormData data) {
		try {
			String key = String.valueOf(Math.random() + "." + Math.random()
					+ ".FormData");
			ObjectMapper mapper = new ObjectMapper();

			String result = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(data);

			ClientBuffer.getInstance().put(key, result.getBytes());

			return key;

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Serializing Form Data", e);
			return null;
		}
	}

	@Override
	public FormData getClientData(String client) {
		String key = client + ".FormData";

		Object value = ClientBuffer.getInstance().get(key);

		if (value == null) {

			try {
				Thread.sleep(1000);
			} catch (Exception ignore) {
			}

			return null;
		}

		try {
			return (FormData) value;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Deserializing Form Data", e);
			return null;
		}
	}

	@Override
	public String exportData(ExportData data) {
		ExportExcel excel = new ExportExcel(data);
		try {
			String key = String.valueOf(Math.random() + "." + Math.random()
					+ ".Excel");
			byte[] bytes = excel.export();
			ClientBuffer.getInstance().put(key, bytes);

			return key;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Exporting Excel data", e);
			return null;
		}
	}

	@Override
	public Version getVersion() {
		return Version.getVersion();
	}

	public String toJson(JsonObject object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			String str = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(object);

			return str;
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Serializing Object", e);
			return null;
		}
	}

	public JsonObject fromJson(String json, String type) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return (JsonObject) mapper.readValue(json, Class.forName(type));
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception Deserializing JSON", e);
			return null;
		}
	}

	@Override
	public void logData(final CharacterData data) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				data.setDefaults();

				log.info("Logging data for " + data.getProfile() + "-"
						+ data.getTag() + "(" + data.getRealm().name() + ")/"
						+ data.getHero());

				DpsTableEntry entry = calculateDps(data);

				CouchDBDHCalcDatabase.getInstance().logDps(entry);
			}
		}).start();

	}

	public DpsTableEntry calculateDps(CharacterData data) {

		Build build = new Build();
		build.setSkills(data.getSkills());

		DpsTableEntry entry = new DpsTableEntry();

		data.setDefaults();
		ProfileHelper.updateWeaponDamage(data);
		ProfileHelper.updateCdr(data);
		data.setDefaults();

		entry.setBattletag(data.getProfile() + "-" + data.getTag() + "/"
				+ data.getHero());
		entry.setRealm(data.getRealm());
		entry.setBuild(build);
		entry.setParagon_dex(data.getParagonDexterity());
		entry.setParagon_cc(data.getParagonCC());
		entry.setParagon_cdr(data.getParagonCDR());
		entry.setParagon_chd(data.getParagonCHD());
		entry.setParagon_ias(data.getParagonIAS());
		entry.setParagon_hatred(data.getParagonHatred());
		entry.setParagon_rcr(data.getParagonRCR());
		entry.setParagon(data.getParagon());
		entry.setProfile(data.getProfile());
		entry.setTag(data.getTag());
		entry.setHeroId(data.getHero());
		entry.setHeroName(data.getHeroName());
		entry.setBp(data.getBp());
		entry.setPlayerAps(data.getAps());
		entry.setSentryDps(data.getSentryDps());
		entry.setSeasonal(data.isSeasonal());
		entry.setHardcore(data.isHardcore());
		entry.setDead(data.isDead());
		entry.setSheetDps(data.getSheetDps());
		entry.setLevel(data.getLevel());

		data.setNumAdditional(0);
		calculateDamage(data, entry);
		data.setNumAdditional(10);
		calculateDamage(data, entry);

		entry.setWhen(System.currentTimeMillis());

		return entry;
	}

	private void calculateDamage(CharacterData data, DpsTableEntry entry) {

		DamageResult damage = FiringData.calculateDamages(data);

		double total = 0.0;
		double totalElite = 0.0;
		double e = 1.0 + data.getTotalEliteDamage();

		for (Damage d : damage.damages) {
			total += d.actualDamage;
			// System.out.println(d.log);
		}

		total = total / damage.duration;

		totalElite = total * e;

		if (data.getNumAdditional() == 0) {
			entry.setSingle(total);
			entry.setSingle_elite(totalElite);
		} else {
			entry.setMultiple(total);
			entry.setMultiple_elite(totalElite);
		}
	}

	@Override
	public DBStatistics getStats(Rune sentryRune, ActiveSkill[] skills,
			Rune[] runes) {
		return CouchDBDHCalcDatabase.getInstance().getStatistics(sentryRune, skills, runes);
	}
}
