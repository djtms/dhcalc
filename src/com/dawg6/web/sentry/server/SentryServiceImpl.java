package com.dawg6.web.sentry.server;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dawg6.web.sentry.client.SentryService;
import com.dawg6.web.sentry.server.db.couchdb.CouchDBSentryDatabase;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.BreakPoint;
import com.dawg6.web.sentry.shared.calculator.Build;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.Damage;
import com.dawg6.web.sentry.shared.calculator.ExportData;
import com.dawg6.web.sentry.shared.calculator.FiringData;
import com.dawg6.web.sentry.shared.calculator.FormData;
import com.dawg6.web.sentry.shared.calculator.JsonObject;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.SkillAndRune;
import com.dawg6.web.sentry.shared.calculator.SkillSet;
import com.dawg6.web.sentry.shared.calculator.Util;
import com.dawg6.web.sentry.shared.calculator.Version;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SentryServiceImpl extends RemoteServiceServlet implements
		SentryService {

	private static final Logger log = Logger.getLogger(SentryServiceImpl.class
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
	public HeroProfile getHero(Realm realm, String profile, int tag, int id) {
		try {
			String server = realm.getApiHost();
			profile = URLEncoder.encode(profile, "UTF-8");

			log.info("getHero(" + realm.getDisplayName() + "," + profile + "-"
					+ tag + "/" + id + ")");

			HeroProfile hero = IO.getInstance().readHeroProfile(server,
					profile, tag, id);

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

	@Override
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

	@Override
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

	@Override
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

				CouchDBSentryDatabase.getInstance().logDps(entry);
			}
		}).start();

	}

	public DpsTableEntry calculateDps(CharacterData data) {
		Map<ActiveSkill, Rune> skillMap = new TreeMap<ActiveSkill, Rune>();

		Build build = new Build();
		build.setSentryRune(data.getSentryRune());
		build.setSkills(new HashSet<SkillAndRune>(data.getSkills()));

		int n = 0;
		for (SkillAndRune sk : build.getSkills()) {

			if (n < 3)
				skillMap.put(sk.getSkill(), sk.getRune());

			n++;
		}

		SkillSet skillSet = new SkillSet(skillMap.keySet());

		skillMap.put(ActiveSkill.SENTRY, data.getSentryRune());
		skillMap.put(ActiveSkill.BOLT, data.getSentryRune());

		BreakPoint bp = BreakPoint.ALL[data.getBp()-1];

		DpsTableEntry entry = new DpsTableEntry();

		data.setDefaults();
		ProfileHelper.updateWeaponDamage(data);
		ProfileHelper.updateCdr(data);
		data.setDefaults();

		entry.setBattletag(data.getProfile() + "-" + data.getTag() + "/"
				+ data.getHero());
		entry.setRealm(data.getRealm());
		entry.setBuild(build);
		entry.setParagon_cc(data.getParagonCC());
		entry.setParagon_cdr(data.getParagonCDR());
		entry.setParagon_chd(data.getParagonCHD());
		entry.setParagon_ias(data.getParagonIAS());
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
		calculateDamage(skillMap, data, entry);
		data.setNumAdditional(10);
		calculateDamage(skillMap, data, entry);

		entry.setWhen(System.currentTimeMillis());

		return entry;
	}

	private void calculateDamage(Map<ActiveSkill, Rune> skillMap,
			CharacterData data, DpsTableEntry entry) {

		Damage[] damage = FiringData.calculateDamages(skillMap, data);

		double total = 0.0;
		double totalElite = 0.0;
		double e = 1.0 + data.getTotalEliteDamage();

		for (Damage d : damage) {
			total += d.totalDamage;
			// System.out.println(d.log);
		}

		total = total / FiringData.DURATION;

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
		return CouchDBSentryDatabase.getInstance().getStatistics(sentryRune, skills, runes);
	}
}
