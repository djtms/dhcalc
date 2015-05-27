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
package com.dawg6.web.dhcalc.server.db.couchdb;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.DesignDocument;
import org.lightcouch.NoDocumentException;
import org.lightcouch.Response;
import org.lightcouch.View;

import com.dawg6.web.dhcalc.server.DHCalcServiceImpl;
import com.dawg6.web.dhcalc.server.util.DHCalcProperties;
import com.dawg6.web.dhcalc.shared.calculator.ActiveSkill;
import com.dawg6.web.dhcalc.shared.calculator.Build;
import com.dawg6.web.dhcalc.shared.calculator.CharacterData;
import com.dawg6.web.dhcalc.shared.calculator.ProfileHelper;
import com.dawg6.web.dhcalc.shared.calculator.Rune;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Hero;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation.Gem;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
import com.dawg6.web.dhcalc.shared.calculator.stats.DBStatistics;
import com.dawg6.web.dhcalc.shared.calculator.stats.DocumentBase;
import com.dawg6.web.dhcalc.shared.calculator.stats.DpsTableEntry;
import com.dawg6.web.dhcalc.shared.calculator.stats.StatCategory;
import com.dawg6.web.dhcalc.shared.calculator.stats.Statistics;
import com.google.gson.JsonObject;

public class CouchDBDHCalcDatabase {

	protected boolean LOGGING = false;

	private CouchDbClient dbClient;

	public static final String DB_NAME = "dhcalc";

	private static final String PARAM_PREFIX = "config.";
	
	private static final Logger log = Logger
			.getLogger(CouchDBDHCalcDatabase.class.getName());

	private static CouchDBDHCalcDatabase instance = null;

	public static synchronized CouchDBDHCalcDatabase getInstance() {
		if (instance == null)
			instance = new CouchDBDHCalcDatabase();

		return instance;
	}
	
	private final Object dpsLock = new Object();

	private CouchDBDHCalcDatabase() {
		try {
			CouchDbProperties props = new CouchDbProperties()
				.setDbName(DB_NAME)
				.setCreateDbIfNotExist(true)
				.setProtocol("http")
				.setHost(DHCalcProperties.getInstance().getDb())
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

	public <T> T reduce(Class<T> clazz, String viewName, Object... key) {
		View view = dbClient.view(DB_NAME + "/" + viewName);
		
		if (key.length > 0) {
			view.key(key);
		}

		List<T> list = view.query(clazz);
		
		return list.isEmpty() ? null : list.get(0);
	}

	public <T extends DocumentBase> List<T> view(Class<T> clazz, String viewName, List<String> keys) {
		View view = dbClient.view(DB_NAME + "/" + viewName)
				.includeDocs(true);
		
		if (keys.size() > 0)
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
	
	public <T extends DocumentBase> List<T> viewRange(Class<T> clazz, String viewName, Object start, Object end) {
		
		View view = dbClient.view(DB_NAME + "/" + viewName)
				.includeDocs(true);
		
		view.startKey(start);
		view.endKey(end);
		
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


	protected void updateDpsData(Long since) {

		DHCalcServiceImpl service = new DHCalcServiceImpl();
		Long start = (long)0;
		List<DpsTableEntry> list = this.viewRange(DpsTableEntry.class, DpsTableEntry.BY_TIME, start, since);
		int count = 1;
		int num = list.size();
		
		for (DpsTableEntry source : list) {

			System.out.println(count + "/" + num + ": Updating DPS for "
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
				CharacterData data = ProfileHelper.importHero(hero, null);
				data.setRealm(realm);
				data.setProfile(profile);
				data.setTag(tag);
				data.setHero(heroId);
				data.setParagonCC(source.getParagon_cc());
				data.setParagonCHD(source.getParagon_chd());
				data.setParagonCDR(source.getParagon_cdr());
				data.setParagonIAS(source.getParagon_ias());
				data.setParagonHatred(getValue(source.getParagon_hatred(), 0));
				data.setParagonRCR(getValue(source.getParagon_rcr(), 0));
				
				DpsTableEntry entry = service.calculateDps(data);
				entry.setId(source.getId());
				entry.setRevision(source.getRevision());
				
				persist(entry);
			} else {
				log.warning("Unable to find hero: " + battletag);
			}

			count++;
		}
	}


	public int getValue(Integer value, int defaultValue) {
		return (value == null) ? defaultValue : value;
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
		try {

			synchronized (dpsLock) {
				JsonObject q = new JsonObject();
				q.addProperty(DpsTableEntry.REALM, realm.name());
				q.addProperty(DpsTableEntry.BATTLETAG, battletag);
				
				return this.viewOne(DpsTableEntry.class, DpsTableEntry.PROFILES, q);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);

			return null;
		}

	}

	public void logDps(DpsTableEntry entry) {
		try {
			synchronized (dpsLock) {
				DpsTableEntry existing = this.getDps(entry.getBattletag(), entry.getRealm());
				
				if (existing != null) {
					entry.setId(existing.getId());
					entry.setRevision(existing.getRevision());
				}

				this.persist(entry);
			}

		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);
		}

	}

	public static class DBStat {
		public double single_elite;
		public double single;
		public double multiple_elite;
		public double multiple;
	}

	public static class DBStatHolder {
		public int count;
		public DBStat total;
		public DBStat min;
		public DBStat max;
		public DBStat average;
	}
	
	public static class DBStats {
		public String key;
		public DBStatHolder value;
	}
	
	public DBStats getStatistics(Build build) {
		
		if (build != null) {
			
			StringBuilder sb = new StringBuilder();
			
//			if (build.isSentry()) {
//				sb.append(build.getSentryRune().name());
//				sb.append("/");
//			}
//			
//			for (SkillAndRune skr : build.getSkills()) {
//				sb.append(skr.getSkill().name());
//				sb.append(".");
//				sb.append(skr.getRune().name());
//				sb.append("/");
//			}
			
			sb.append(build.toString());
			
			String key = sb.toString();
			
			
			log.info("Build = " + key);
			
			return this.reduce(DBStats.class, DpsTableEntry.DPS_SUMMARY, key);
		} else {
			return this.reduce(DBStats.class, DpsTableEntry.DPS_SUMMARY);
		}
	}
	
	public DBStatistics getStatistics(Rune sentryRune, ActiveSkill[] skills,
			Rune[] runes) {
		DBStatistics stats = new DBStatistics();

		Map<ActiveSkill, Rune> skillMap = new HashMap<ActiveSkill, Rune>();

		int skillCount = 0;

		for (int i = 0; i < skills.length; i++) {
			if (skills[i] != null)
				skillCount++;
		}

		synchronized (dpsLock) {

			for (DpsTableEntry e : this.findAll(DpsTableEntry.class)) {
				addStatistics(stats.stats, e);

				Build build = e.getBuild();

//				if ((sentryRune == Rune.All_Runes)
//						|| (sentryRune == build.getSentryRune())) {
//
//					int match = 0;
//
//					for (int i = 0; i < skills.length; i++) {
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
	
	public static void main(String[] args) {
		try {
			getAttributes();
			CouchDBDHCalcDatabase db = CouchDBDHCalcDatabase.getInstance();
			
			List<DpsTableEntry> list = db.findAll(DpsTableEntry.class);
			
//			long since = System.currentTimeMillis();
//			System.out.println("Start Time = " + since);
//			
//			Build build = new Build();
//			build.setSentry(true);
//			build.setSentryRune(Rune.Polar_Station);
//			Set<SkillAndRune> skills = new TreeSet<SkillAndRune>();
//			skills.add(new SkillAndRune(ActiveSkill.CA, Rune.Maelstrom));
//			skills.add(new SkillAndRune(ActiveSkill.EF, Rune.Focus));
//			build.setSkills(skills);
//			
//			DBStats stats = db.getStatistics(build);
//
//			Gson gson = new Gson();
//			System.out.println("Stats = " + gson.toJson(stats));
	//		Long start = (long)0;
	//		List<DpsTableEntry> list = db.viewRange(DpsTableEntry.class, DpsTableEntry.BY_TIME, start, since);
	//		
	//		System.out.println("Count = " + list.size());
//			db.updateDpsData(since);
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			System.out.println("End Time = " + System.currentTimeMillis());
		}
	}
	
	private static class Profile {
		public Realm realm;
		public String profile;
		public Integer tag;
		
		public Profile(DpsTableEntry e) {
			this.realm = e.getRealm();
			this.profile = e.getProfile().toLowerCase();
			this.tag = e.getTag();
		}
		
		@Override
		public String toString() {
			return realm.name() + "/" + profile + "-" + tag;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((profile == null) ? 0 : profile.hashCode());
			result = prime * result + ((realm == null) ? 0 : realm.hashCode());
			result = prime * result + ((tag == null) ? 0 : tag.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Profile other = (Profile) obj;
			if (profile == null) {
				if (other.profile != null)
					return false;
			} else if (!profile.equals(other.profile))
				return false;
			if (realm != other.realm)
				return false;
			if (tag == null) {
				if (other.tag != null)
					return false;
			} else if (!tag.equals(other.tag))
				return false;
			return true;
		}
		
	}
	
	private static void getAttributes() {
		try {
			CouchDBDHCalcDatabase db = CouchDBDHCalcDatabase.getInstance();
			Set<String> attributes = new TreeSet<String>();
			DHCalcServiceImpl service = new DHCalcServiceImpl();
			
			Collection<Profile> profiles = db.getAllProfiles();
			
			int n = 0;
			
			for (Profile p : profiles) {
				n++;

				try {
					System.out.println("Profile " + n + "/" + profiles.size());
	
					CareerProfile career = service.getProfile(p.realm, p.profile, p.tag);
					
					if (career.heroes != null) {
						System.out.println(career.heroes.length + " Heroes");
						
						for (Hero h : career.heroes) {
							HeroProfile hp = service.getHero(p.realm, p.profile, p.tag, h.id);
							
							if (hp.items != null) {
								for (ItemInformation i : hp.items.values()) {
									attributes.addAll(i.attributesRaw.keySet());
									
									if (i.gems != null) {
										for (Gem g : i.gems) {
											attributes.addAll(g.attributesRaw.keySet());
										}
									}
								}
							}
						}
					}
				} catch (Exception e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				}
			}
			
			FileOutputStream stream = new FileOutputStream("attributes.txt");
			PrintWriter writer = new PrintWriter(stream);
			
			for (String s : attributes) {
				System.out.println(s);
				writer.println(s);
			}
			
			writer.flush();
			stream.close();
				
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public Collection<Profile> getAllProfiles() {
		Set<Profile> profiles = new HashSet<Profile>();

		List<DpsTableEntry> list = this.findAll(DpsTableEntry.class);
		
		for (DpsTableEntry e : list) {
			Profile p = new Profile(e);
			profiles.add(p);
		}
		
		return profiles;
	}
}
