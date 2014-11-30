package com.dawg6.web.sentry.server.db.aws;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.ConsistentReads;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.PaginationLoadingStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dawg6.server.common.aws.dao.AWSDatabase;
import com.dawg6.web.sentry.server.Parameters;
import com.dawg6.web.sentry.server.SentryServiceImpl;
import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.Build;
import com.dawg6.web.sentry.shared.calculator.CharacterData;
import com.dawg6.web.sentry.shared.calculator.ProfileHelper;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.dawg6.web.sentry.shared.calculator.stats.StatCategory;
import com.dawg6.web.sentry.shared.calculator.stats.Statistics;

public class Database extends AWSDatabase {

	private static final Logger log = Logger
			.getLogger(Database.class.getName());

	private static Database instance = null;

	public static final int DATABASE_VERSION = 1;
	
	private final Object dpsLock = new Object();
	private String dpsTableName = null;

	public static final String DPS_TABLE_NAME = "sentry-dps";

	public static synchronized Database getInstance() {
		if (instance == null)
			instance = new Database();

		return instance;
	}

	private Database() {
	}

	protected void setSchemaVersion(String value) {
		log.info("Schema Version = " + value);

	}

	public static void main(String[] args) throws Exception {
		// fixBuilds();

		// importData("sentry-dps", "sentry-dps.dev");

//		 fixProfile("sentry-dps");

		Database.getInstance().updateDpsData("sentry-dps", System.currentTimeMillis());
	}

	protected void updateDpsData(String table, long since) {
		AmazonDynamoDBClient client = this.getClient();

		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
		builder.setConsistentReads(ConsistentReads.EVENTUAL);
		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
		builder.setTableNameOverride(TableNameOverride
				.withTableNameReplacement(table));

		DynamoDBMapperConfig fromMapperConfig = builder.build();
		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		scanExpression.addFilterCondition(
				"when",
				new Condition().withComparisonOperator(ComparisonOperator.LT)
						.withAttributeValueList(
								new AttributeValue().withN(String
										.valueOf(since))));

		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
				scanExpression);

		SentryServiceImpl service = new SentryServiceImpl();

		int count = 0;

		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
			DpsTableEntry source = iter.next();

			System.out.println(count + ". Updating DPS for "
					+ source.getRealm().name() + "/" + source.getBattletag());
			Realm realm = source.getRealm();
			String battletag = source.getBattletag();
			String[] split1 = battletag.split("/");
			String[] split2 = split1[0].split("-");
			String profile = split2[0];
			Integer tag = Integer.parseInt(split2[1]);
			Integer heroId = Integer.parseInt(split1[1]);
			HeroProfile hero = service.getHero(realm, profile, tag, heroId);

			if (hero.code == null) {
				CharacterData data = ProfileHelper.importHero(hero);
				data.setRealm(realm);
				data.setProfile(profile);
				data.setTag(tag);
				data.setHero(heroId);
				data.setParagonCC(source.getParagon_cc());
				data.setParagonCHD(source.getParagon_chd());
				data.setParagonCDR(source.getParagon_cdr());
				data.setParagonIAS(source.getParagon_ias());
				
				DpsTableEntry entry = service.calculateDps(data);

				fromMapper.save(entry);
			} else {
				log.warning("Unable to find hero: " + battletag);
			}

			count++;
		}
	}

	protected void fixProfile(String table) {
		AmazonDynamoDBClient client = this.getClient();

		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
		builder.setConsistentReads(ConsistentReads.EVENTUAL);
		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
		builder.setTableNameOverride(TableNameOverride
				.withTableNameReplacement(table));

		DynamoDBMapperConfig fromMapperConfig = builder.build();
		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
				scanExpression);

		int count = 0;

		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
			DpsTableEntry source = iter.next();

			System.out.println(count + ". Fixing Profile for "
					+ source.getRealm().name() + "/" + source.getBattletag());
			String battletag = source.getBattletag();
			String[] split1 = battletag.split("/");
			String[] split2 = split1[0].split("-");
			String profile = split2[0];
			Integer tag = Integer.parseInt(split2[1]);
			Integer heroId = Integer.parseInt(split1[1]);

			source.setProfile(profile);
			source.setTag(tag);
			source.setHeroId(heroId);

			fromMapper.save(source);
			count++;
		}
	}

	protected void importData(String from, String to) {
		AmazonDynamoDBClient client = this.getClient();

		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
		builder.setConsistentReads(ConsistentReads.EVENTUAL);
		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
		builder.setTableNameOverride(TableNameOverride
				.withTableNameReplacement(from));

		DynamoDBMapperConfig fromMapperConfig = builder.build();
		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);

		builder.setTableNameOverride(TableNameOverride
				.withTableNameReplacement(to));
		DynamoDBMapperConfig toMapperConfig = builder.build();
		DynamoDBMapper toMapper = new DynamoDBMapper(client, toMapperConfig);

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
				scanExpression);

		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
			DpsTableEntry source = iter.next();

			System.out.println("Copying " + source.getRealm().name() + "/"
					+ source.getBattletag());

			toMapper.save(source);
		}
	}

	public DpsTableEntry getDps(String battletag, Realm realm) {
		try {

			synchronized (dpsLock) {
				AmazonDynamoDBClient client = getClient();

				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
				builder.setConsistentReads(ConsistentReads.EVENTUAL);
				builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
				builder.setTableNameOverride(TableNameOverride
						.withTableNameReplacement(getDpsTableName()));
				DynamoDBMapperConfig mapperConfig = builder.build();

				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

				DpsTableEntry hashKObject = new DpsTableEntry();
				hashKObject.setBattletag(battletag);
				hashKObject.setRealm(realm);
				DynamoDBQueryExpression<DpsTableEntry> queryExpression = new DynamoDBQueryExpression<DpsTableEntry>()
						.withHashKeyValues(hashKObject);

				List<DpsTableEntry> list = mapper.query(DpsTableEntry.class,
						queryExpression);
				Iterator<DpsTableEntry> iter = list.iterator();

				if ((iter != null) && iter.hasNext())
					return iter.next();
				else
					return null;
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);

			return null;
		}

	}

	private synchronized String getDpsTableName() {

		if (dpsTableName == null) {

			setSchemaVersion(Parameters.getInstance().getParameter(
					Parameters.SCHEMA_VERSION, "0", new Parameters.Listener() {

						@Override
						public void parameterChanged(String parameter,
								String value) {
							setSchemaVersion(value);
						}
					}));

			setDpsTableName(Parameters.getInstance().getParameter(
					Parameters.SCHEMA_NAME, "undefined",
					new Parameters.Listener() {

						@Override
						public void parameterChanged(String parameter,
								String value) {
							setDpsTableName(value);
						}
					}));
		}

		return dpsTableName;
	}

	protected synchronized void setDpsTableName(String value) {

		log.info("Dps Table Name = " + value);

		dpsTableName = value;
	}

	public void logDps(DpsTableEntry entry) {
		try {
			synchronized (dpsLock) {
				AmazonDynamoDBClient client = getClient();
				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
				builder.setTableNameOverride(TableNameOverride
						.withTableNameReplacement(getDpsTableName()));
				DynamoDBMapperConfig mapperConfig = builder.build();
				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);

				mapper.save(entry);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);
		}

	}

	public interface IteratorTask {

		void complete();

		void addItem(DpsTableEntry next);

	}

	public void iterateDatabase(IteratorTask task) {
		try {
			synchronized (dpsLock) {
				Iterator<DpsTableEntry> iter = getDatabaseIterator();

				while ((iter != null) && iter.hasNext()) {
					task.addItem(iter.next());
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);
		} finally {
			task.complete();
		}

	}

	private Iterator<DpsTableEntry> getDatabaseIterator() {
		try {
			synchronized (dpsLock) {
				AmazonDynamoDBClient client = getClient();
				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();

				builder.setConsistentReads(ConsistentReads.EVENTUAL);
				builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
				builder.setTableNameOverride(TableNameOverride
						.withTableNameReplacement(getDpsTableName()));
				DynamoDBMapperConfig mapperConfig = builder.build();

				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);
				DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

				List<DpsTableEntry> list = mapper.scan(DpsTableEntry.class,
						scanExpression);

				return list.iterator();
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);

			return null;
		}
	}

	public DBStatistics getStatistics(Rune sentryRune, ActiveSkill[] skills,
			Rune[] runes) {
		DBStatistics stats = new DBStatistics();

		Map<ActiveSkill, Rune> skillMap = new HashMap<ActiveSkill, Rune>();

		int skillCount = 0;

		for (int i = 0; i < 3; i++) {
			if (skills[i] != null)
				skillCount++;
		}

		synchronized (dpsLock) {
			Iterator<DpsTableEntry> iter = getDatabaseIterator();

			while ((iter != null) && (iter.hasNext())) {
				DpsTableEntry e = iter.next();

				addStatistics(stats.stats, e);

				Build build = e.getBuild();

				if ((sentryRune == Rune.All_Runes)
						|| (sentryRune == build.getSentryRune())) {

					int match = 0;

					for (int i = 0; i < 3; i++) {
						ActiveSkill s1 = skills[i];
						Rune r2 = build.getRune(s1);

						if ((s1 == ActiveSkill.Any) || (r2 != null)) {
							Rune r1 = runes[i];

							if ((r1 == Rune.All_Runes) || (r1 == r2)) {
								match++;
							}
						}
					}

					if ((match == skillCount)
							&& (build.getSkills().size() <= skillCount)) {

						Statistics s = stats.builds.get(build);

						if (s == null) {
							s = new Statistics();
							stats.builds.put(build, s);
						}

						addStatistics(s, e);
					}
				}
			}
		}

		return stats;
	}

	private void addStatistics(Statistics stats, DpsTableEntry e) {

		for (StatCategory c : StatCategory.values()) {
			DpsTableEntry old = stats.max.get(c);
			double value = c.getValue(e);

			if ((old == null) || (value > c.getValue(old))) {
				stats.max.put(c, e);
			}

			Double avg = stats.average.get(c);

			if (avg == null) {
				stats.average.put(c, value);
			} else {
				Double newAverage = ((avg * stats.total) + value)
						/ (stats.total + 1);
				stats.average.put(c, newAverage);
			}
		}

		stats.total++;
	}
}
