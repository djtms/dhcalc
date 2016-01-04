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
package com.dawg6.web.dhcalc.server;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.dawg6.d3api.server.Cache;
import com.dawg6.d3api.server.D3IO;
import com.dawg6.d3api.server.oauth.Token;
import com.dawg6.d3api.shared.Const;
import com.dawg6.d3api.shared.ItemInformation;
import com.dawg6.d3api.shared.ItemInformationSet.Rank;
import com.dawg6.d3api.shared.Realm;
import com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcDatabase;
import com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcParameters;
import com.dawg6.web.dhcalc.server.db.couchdb.ItemDocument;
import com.dawg6.web.dhcalc.server.db.couchdb.SetDocument;
import com.dawg6.web.dhcalc.server.util.DHCalcProperties;

public class IO extends D3IO {

	private static IO instance;
	
	public static synchronized IO getInstance() {
		if (instance == null)
			instance = new IO();
		
		return instance;
	}
	
	
	private IO() {
		String value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.MAX_REQUESTS, String.valueOf(DEFAULT_MAX_REQUESTS_PER_SECOND), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setMaxRequests(Integer.parseInt(value));
			}
		});
		setMaxRequests(Integer.parseInt(value));
		
		value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.CACHE_SIZE, String.valueOf(Cache.DEFAULT_MAX_SIZE), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setCacheSize(Integer.parseInt(value));
			}
		});
		setCacheSize(Integer.parseInt(value));
		
		value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.CONNECT_TIMEOUT, String.valueOf(DEFAULT_CONNECT_TIMEOUT), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setConnectTimeout(Integer.parseInt(value));
			}
		});
		setConnectTimeout(Integer.parseInt(value));

		value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.READ_TIMEOUT, String.valueOf(DEFAULT_READ_TIMEOUT), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setReadTimeout(Integer.parseInt(value));
			}
		});
		setReadTimeout(Integer.parseInt(value));
		
		value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.TIMEOUT_RETRIES, String.valueOf(DEFAULT_TIMEOUT_RETRIES), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setTimeoutRetries(Integer.parseInt(value));
			}
		});
		setTimeoutRetries(Integer.parseInt(value));

		value = CouchDBDHCalcParameters.getInstance().getParameter(CouchDBDHCalcParameters.ITEM_REALM, DEFAULT_ITEM_REALM.name(), new CouchDBDHCalcParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setItemRealm(Realm.valueOf(value));
			}
		});
		setItemRealm(Realm.valueOf(value));
	}
	
	protected void setTimeoutRetries(int value) {
		this.timeoutRetries = value;
	}

	protected void setItemRealm(Realm realm) {
		this.itemRealm = realm;
	}

	protected void setReadTimeout(int value) {
		this.readTimeout = value;
	}

	protected void setConnectTimeout(int value) {
		this.connectTimeout = value;
	}

	protected void setCacheSize(int value) {
		synchronized (itemCache) {
			log.info("Setting ItemCache Size to " + value);
			
			itemCache.setMaxSize(value);
		}
	}

	private void setMaxRequests(int value) {
		synchronized (requests) {
			log.info("Setting Max # of Requests Per Second to " + value);
			
			maxRequests = value;
		}
	}

	@Override
	public synchronized String getApiKey() {
		
		if (apiKey == null) {
			apiKey = DHCalcProperties.getInstance().getApiKey();
		}
		
		return super.getApiKey();
	}

	@Override
	protected synchronized Token requestToken() throws Exception {
		
		if (apiKey == null) {
			apiKey = DHCalcProperties.getInstance().getApiKey();
		}
		
		if (apiSecret == null) {
			apiSecret = DHCalcProperties.getInstance().getApiSecret();
		}
		
		return super.requestToken();
	}

	public long getNumRequests() {
		synchronized (requests) {
			return numRequests;
		}
	}
	


	public Realm getItemRealm() {
		return itemRealm;
	}

	public Cache<String, ItemInformation> getItemCache() {
		return this.itemCache;
	}
	
	@Override
	protected void newItem(ItemInformation item) {
//		log.info("Potential new Item " + item.name);

		String name = item.name;
		
		if (name.equals(Const.HELLFIRE_AMULET)) {
			if ((item.attributes != null) && (item.attributes.passive != null) && (item.attributes.passive.length == 1)) {
				int i = item.attributes.passive[0].text.indexOf(Const.PASSIVE);
				String sub = item.attributes.passive[0].text.substring(Const.HELLFIRE_PASSIVE.length(), i).replaceAll(" ", "_");
				name = name + "." + sub;
			}
		}
		
		ItemDocument doc = CouchDBDHCalcDatabase.getInstance().get(ItemDocument.class, name);
		boolean changed = false;
		
		if (doc == null) {
//			log.info("New Item " + item.name);
			
			doc = new ItemDocument();
			doc.setId(name);
			
			doc.setIcon(item.icon);
			doc.setItemId(item.id);
			doc.setTooltipParams(item.tooltipParams);
			changed = true;
		}

		if (doc.getRawAttributes() == null)
			doc.setRawAttributes(new TreeSet<String>());
		
		if ((doc.getTooltipParams() == null) && (item.tooltipParams != null)) {
			doc.setTooltipParams(item.tooltipParams);
			changed = true;
		}
		
		if (item.attributesRaw != null) {
			Set<String> set = doc.getRawAttributes();
			
			for (String a : item.attributesRaw.keySet()) {
				if (!set.contains(a)) {
					set.add(a);
					changed = true;
				}
			}
		}
		
		if (changed) {
			if (doc.getRevision() == null)
				log.info("Creating Item " + doc.getId());
			else
				log.info("Updating Item " + doc.getId());
			
			CouchDBDHCalcDatabase.getInstance().persist(doc);
		}
		
		if (item.set != null) {
			newSetItem(item);
		}

	}

	private void newSetItem(ItemInformation item) {
		SetDocument doc = CouchDBDHCalcDatabase.getInstance().get(SetDocument.class, item.set.name);
		boolean changed = false;
		
		if (doc == null) {
			doc = new SetDocument();
			doc.setId(item.set.name);
			doc.setSlug(item.set.slug);
			changed = true;
		}
		
		if (doc.getRawAttributes() == null)
			doc.setRawAttributes(new TreeMap<String, Integer>());
		
		if (doc.getTooltipParams() == null) {
			doc.setTooltipParams(item.tooltipParams);
			changed = true;
		}
		
		if (item.set.ranks != null) {
			Map<String, Integer> map = doc.getRawAttributes();
			
			for (Rank r : item.set.ranks) {
				Integer n = r.required;
				
				for (String a : r.attributesRaw.keySet()) {
					
					Integer o = map.get(a);
					
					if ((o == null) || (o != n)) {
						map.put(a, n);
						changed = true;
					}
				}
			}
		}
		
		
		if (changed) {
			if (doc.getRevision() == null)
				log.info("Creating Set Type " + doc.getId());
			else
				log.info("Updating Set Type " + doc.getId());
			
			CouchDBDHCalcDatabase.getInstance().persist(doc);
		}
	}
}