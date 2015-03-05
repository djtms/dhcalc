package com.dawg6.web.sentry.server.db.couchdb;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.DesignDocument;
import org.lightcouch.NoDocumentException;
import org.lightcouch.Response;
import org.lightcouch.View;

import com.dawg6.web.sentry.shared.calculator.ActiveSkill;
import com.dawg6.web.sentry.shared.calculator.Rune;
import com.dawg6.web.sentry.shared.calculator.d3api.Realm;
import com.dawg6.web.sentry.shared.calculator.stats.DBStatistics;
import com.dawg6.web.sentry.shared.calculator.stats.DocumentBase;
import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;
import com.dawg6.web.sentry.shared.calculator.stats.Statistics;

public class CouchDBSentryDatabase {

	protected boolean LOGGING = false;

	private CouchDbClient dbClient;

	public static final String DB_NAME = "sentry";

	private static final String PARAM_PREFIX = "config.";
	
	private static final Logger log = Logger
			.getLogger(CouchDBSentryDatabase.class.getName());

	private static CouchDBSentryDatabase instance = null;

	public static synchronized CouchDBSentryDatabase getInstance() {
		if (instance == null)
			instance = new CouchDBSentryDatabase();

		return instance;
	}

	private CouchDBSentryDatabase() {
		try {
			CouchDbProperties props = new CouchDbProperties()
				.setDbName(DB_NAME)
				.setCreateDbIfNotExist(true)
				.setProtocol("http")
//				.setHost("192.168.1.52")
				.setHost("127.0.0.1")
				.setPort(5984)
				.setMaxConnections(100)
				.setConnectionTimeout(0);
			
			dbClient = new CouchDbClient(props);
			
			DesignDocument designDoc = dbClient.design().getFromDesk(DB_NAME);
			
			dbClient.design().synchronizeWithDb(designDoc); 
		} 
		
		catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void setLogging(boolean logging) {
		this.LOGGING = logging;
	}
	
	public <T extends DocumentBase> void persist(List<T> objects) {
		List<Response> resp = dbClient.bulk(objects, true);

		for (int i = 0; i < objects.size(); i++) {
			Response r = resp.get(i);
			T obj = objects.get(i);
			obj.setId(r.getId());
			obj.setRevision(r.getRev());
		}
	}
	
	public <T extends DocumentBase> T persist(T object) {
		
		Response resp = null;
		
		if (object.getRevision() == null)
			resp = dbClient.save(object);
		else
			resp = dbClient.update(object);
		
		object.setId(resp.getId());
		object.setRevision(resp.getRev());

		return object;
	}
	
	public <T extends DocumentBase> T viewOne(Class<T> clazz, String viewName, Object... key) {
		List<T> list = view(clazz, viewName, key);
		
		return list.isEmpty() ? null : list.get(0);
	}

	public <T extends DocumentBase> List<T> view(Class<T> clazz, String viewName, List<String> keys) {
		View view = dbClient.view(DB_NAME + "/" + viewName)
				.includeDocs(true);
		
		view.keys(keys);

		return view.query(clazz);
	}
	
	public <T extends DocumentBase> List<T> view(Class<T> clazz, String viewName, Object... key) {
		
		View view = dbClient.view(DB_NAME + "/" + viewName)
				.includeDocs(true);
		
		if (key.length > 0) {
			view.key(key);
		}

		return view.query(clazz);
	}
	
	public <T extends DocumentBase> void truncate(Class<T> clazz) {
		
		try {
			List<T> list = findAll(clazz);

			for (T doc : list) {
				dbClient.remove(doc);
			}
		} catch (Exception e) { 
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public <T extends DocumentBase> List<T> findAll(Class<T> clazz) {
		
		try {
			String type = clazz.newInstance().getDocumentType();
		
			return view(clazz, "allByType", type);
		} catch (Exception e) { 
			log.log(Level.SEVERE, e.getMessage(), e);
			
			return new Vector<T>();
		}
	}
	
	public <T extends DocumentBase> void delete(List<T> list) {
		for (T obj : list)
			dbClient.remove(obj);
	}

	public <T extends DocumentBase> void delete(T obj) {
		dbClient.remove(obj);
	}

	public <T extends DocumentBase> void delete(Class<T> clazz, Collection<String> idList) {
		
		for (String id : idList)
			delete(clazz, id);
	}

	public <T extends DocumentBase> void delete(Class<T> clazz, String id) {
		T obj = get(clazz, id);
		
		if (obj != null)
			dbClient.remove(obj.getId(), obj.getRevision());
		else
			log.warning("Unable to find doucment with id " + id);
	}
	
	public <T extends DocumentBase> T get(Class<T> clazz, String id) {
		
		try {
			return dbClient.find(clazz, id);
		} catch (NoDocumentException e) {
			return null;
		}
	}

	public String getParameter(String parameter) {

		String name = PARAM_PREFIX + parameter;
		ParameterDocument doc = this.get(ParameterDocument.class, name);
		
		return (doc != null) ? doc.getValue() : null;
	}

	public void putParameter(String parameter, String value) {

		String name = PARAM_PREFIX + parameter;
		ParameterDocument doc = this.get(ParameterDocument.class, name);

		if (doc == null) {
			doc = new ParameterDocument();
			doc.setId(name);
		}
		
		doc.setValue(value);

		this.persist(doc);
	}

	public <T extends DocumentBase> List<String> createIdListFromObjects(List<T> objects) {
		Set<String> list = new HashSet<String>();
		
		for (T t : objects)
			list.add(t.getId());
		
		return new Vector<String>(list);
	}


	protected void updateDpsData(String table, long since) {
//		AmazonDynamoDBClient client = this.getClient();
//
//		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//		builder.setConsistentReads(ConsistentReads.EVENTUAL);
//		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
//		builder.setTableNameOverride(TableNameOverride
//				.withTableNameReplacement(table));
//
//		DynamoDBMapperConfig fromMapperConfig = builder.build();
//		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);
//
//		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//
//		scanExpression.addFilterCondition(
//				"when",
//				new Condition().withComparisonOperator(ComparisonOperator.LT)
//						.withAttributeValueList(
//								new AttributeValue().withN(String
//										.valueOf(since))));
//
//		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
//				scanExpression);
//
//		SentryServiceImpl service = new SentryServiceImpl();
//
//		int count = 0;
//
//		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
//			DpsTableEntry source = iter.next();
//
//			System.out.println(count + ". Updating DPS for "
//					+ source.getRealm().name() + "/" + source.getBattletag());
//			Realm realm = source.getRealm();
//			String battletag = source.getBattletag();
//			String[] split1 = battletag.split("/");
//			String[] split2 = split1[0].split("-");
//			String profile = split2[0];
//			Integer tag = Integer.parseInt(split2[1]);
//			Integer heroId = Integer.parseInt(split1[1]);
//			HeroProfile hero = service.getHero(realm, profile, tag, heroId);
//
//			if (hero.code == null) {
//				CharacterData data = ProfileHelper.importHero(hero);
//				data.setRealm(realm);
//				data.setProfile(profile);
//				data.setTag(tag);
//				data.setHero(heroId);
//				data.setParagonCC(source.getParagon_cc());
//				data.setParagonCHD(source.getParagon_chd());
//				data.setParagonCDR(source.getParagon_cdr());
//				data.setParagonIAS(source.getParagon_ias());
//				
//				DpsTableEntry entry = service.calculateDps(data);
//
//				fromMapper.save(entry);
//			} else {
//				log.warning("Unable to find hero: " + battletag);
//			}
//
//			count++;
//		}
	}

	protected void fixProfile(String table) {
//		AmazonDynamoDBClient client = this.getClient();
//
//		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//		builder.setConsistentReads(ConsistentReads.EVENTUAL);
//		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
//		builder.setTableNameOverride(TableNameOverride
//				.withTableNameReplacement(table));
//
//		DynamoDBMapperConfig fromMapperConfig = builder.build();
//		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);
//
//		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//
//		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
//				scanExpression);
//
//		int count = 0;
//
//		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
//			DpsTableEntry source = iter.next();
//
//			System.out.println(count + ". Fixing Profile for "
//					+ source.getRealm().name() + "/" + source.getBattletag());
//			String battletag = source.getBattletag();
//			String[] split1 = battletag.split("/");
//			String[] split2 = split1[0].split("-");
//			String profile = split2[0];
//			Integer tag = Integer.parseInt(split2[1]);
//			Integer heroId = Integer.parseInt(split1[1]);
//
//			source.setProfile(profile);
//			source.setTag(tag);
//			source.setHeroId(heroId);
//
//			fromMapper.save(source);
//			count++;
//		}
	}

	protected void importData(String from, String to) {
//		AmazonDynamoDBClient client = this.getClient();
//
//		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//		builder.setConsistentReads(ConsistentReads.EVENTUAL);
//		builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
//		builder.setTableNameOverride(TableNameOverride
//				.withTableNameReplacement(from));
//
//		DynamoDBMapperConfig fromMapperConfig = builder.build();
//		DynamoDBMapper fromMapper = new DynamoDBMapper(client, fromMapperConfig);
//
//		builder.setTableNameOverride(TableNameOverride
//				.withTableNameReplacement(to));
//		DynamoDBMapperConfig toMapperConfig = builder.build();
//		DynamoDBMapper toMapper = new DynamoDBMapper(client, toMapperConfig);
//
//		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//
//		List<DpsTableEntry> fromList = fromMapper.scan(DpsTableEntry.class,
//				scanExpression);
//
//		for (Iterator<DpsTableEntry> iter = fromList.iterator(); iter.hasNext();) {
//			DpsTableEntry source = iter.next();
//
//			System.out.println("Copying " + source.getRealm().name() + "/"
//					+ source.getBattletag());
//
//			toMapper.save(source);
//		}
	}

	public DpsTableEntry getDps(String battletag, Realm realm) {
//		try {
//
//			synchronized (dpsLock) {
//				AmazonDynamoDBClient client = getClient();
//
//				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//				builder.setConsistentReads(ConsistentReads.EVENTUAL);
//				builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
//				builder.setTableNameOverride(TableNameOverride
//						.withTableNameReplacement(getDpsTableName()));
//				DynamoDBMapperConfig mapperConfig = builder.build();
//
//				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);
//
//				DpsTableEntry hashKObject = new DpsTableEntry();
//				hashKObject.setBattletag(battletag);
//				hashKObject.setRealm(realm);
//				DynamoDBQueryExpression<DpsTableEntry> queryExpression = new DynamoDBQueryExpression<DpsTableEntry>()
//						.withHashKeyValues(hashKObject);
//
//				List<DpsTableEntry> list = mapper.query(DpsTableEntry.class,
//						queryExpression);
//				Iterator<DpsTableEntry> iter = list.iterator();
//
//				if ((iter != null) && iter.hasNext())
//					return iter.next();
//				else
//					return null;
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "Exception", e);

			return null;
//		}

	}

	public void logDps(DpsTableEntry entry) {
//		try {
//			synchronized (dpsLock) {
//				AmazonDynamoDBClient client = getClient();
//				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//				builder.setTableNameOverride(TableNameOverride
//						.withTableNameReplacement(getDpsTableName()));
//				DynamoDBMapperConfig mapperConfig = builder.build();
//				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);
//
//				mapper.save(entry);
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "Exception", e);
//		}

	}

	public interface IteratorTask {

		void complete();

		void addItem(DpsTableEntry next);

	}

	public void iterateDatabase(IteratorTask task) {
//		try {
//			synchronized (dpsLock) {
//				Iterator<DpsTableEntry> iter = getDatabaseIterator();
//
//				while ((iter != null) && iter.hasNext()) {
//					task.addItem(iter.next());
//				}
//			}
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "Exception", e);
//		} finally {
//			task.complete();
//		}

	}

	private Iterator<DpsTableEntry> getDatabaseIterator() {
//		try {
//			synchronized (dpsLock) {
//				AmazonDynamoDBClient client = getClient();
//				DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
//
//				builder.setConsistentReads(ConsistentReads.EVENTUAL);
//				builder.setPaginationLoadingStrategy(PaginationLoadingStrategy.ITERATION_ONLY);
//				builder.setTableNameOverride(TableNameOverride
//						.withTableNameReplacement(getDpsTableName()));
//				DynamoDBMapperConfig mapperConfig = builder.build();
//
//				DynamoDBMapper mapper = new DynamoDBMapper(client, mapperConfig);
//				DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//
//				List<DpsTableEntry> list = mapper.scan(DpsTableEntry.class,
//						scanExpression);
//
//				return list.iterator();
//			}
//
//		} catch (Exception e) {
//			log.log(Level.SEVERE, "Exception", e);
//
			return null;
//		}
	}

	public DBStatistics getStatistics(Rune sentryRune, ActiveSkill[] skills,
			Rune[] runes) {
		DBStatistics stats = new DBStatistics();

//		Map<ActiveSkill, Rune> skillMap = new HashMap<ActiveSkill, Rune>();
//
//		int skillCount = 0;
//
//		for (int i = 0; i < 3; i++) {
//			if (skills[i] != null)
//				skillCount++;
//		}
//
//		synchronized (dpsLock) {
//			Iterator<DpsTableEntry> iter = getDatabaseIterator();
//
//			while ((iter != null) && (iter.hasNext())) {
//				DpsTableEntry e = iter.next();
//
//				addStatistics(stats.stats, e);
//
//				Build build = e.getBuild();
//
//				if ((sentryRune == Rune.All_Runes)
//						|| (sentryRune == build.getSentryRune())) {
//
//					int match = 0;
//
//					for (int i = 0; i < 3; i++) {
//						ActiveSkill s1 = skills[i];
//						Rune r2 = build.getRune(s1);
//
//						if ((s1 == ActiveSkill.Any) || (r2 != null)) {
//							Rune r1 = runes[i];
//
//							if ((r1 == Rune.All_Runes) || (r1 == r2)) {
//								match++;
//							}
//						}
//					}
//
//					if ((match == skillCount)
//							&& (build.getSkills().size() <= skillCount)) {
//
//						Statistics s = stats.builds.get(build);
//
//						if (s == null) {
//							s = new Statistics();
//							stats.builds.put(build, s);
//						}
//
//						addStatistics(s, e);
//					}
//				}
//			}
//		}

		return stats;
	}

	private void addStatistics(Statistics stats, DpsTableEntry e) {

//		for (StatCategory c : StatCategory.values()) {
//			DpsTableEntry old = stats.max.get(c);
//			double value = c.getValue(e);
//
//			if ((old == null) || (value > c.getValue(old))) {
//				stats.max.put(c, e);
//			}
//
//			Double avg = stats.average.get(c);
//
//			if (avg == null) {
//				stats.average.put(c, value);
//			} else {
//				Double newAverage = ((avg * stats.total) + value)
//						/ (stats.total + 1);
//				stats.average.put(c, newAverage);
//			}
//		}
//
//		stats.total++;
	}
}
