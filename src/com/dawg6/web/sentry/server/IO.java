package com.dawg6.web.sentry.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dawg6.web.sentry.server.db.couchdb.CouchDBSentryParameters;
import com.dawg6.web.sentry.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.sentry.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.sentry.shared.calculator.d3api.UrlHelper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IO {

	private static final Logger log = Logger.getLogger(IO.class.getName());

	private static final Integer DEFAULT_MAX_REQUESTS_PER_SECOND = 8;
	
	private static IO instance;
	
	public static synchronized IO getInstance() {
		if (instance == null)
			instance = new IO();
		
		return instance;
	}
	
	
	private String API_KEY;
	private int maxRequests = DEFAULT_MAX_REQUESTS_PER_SECOND;
	public final Cache<String, ItemInformation> itemCache = new Cache<String, ItemInformation>(true);
	
	private IO() {
		String value = CouchDBSentryParameters.getInstance().getParameter(CouchDBSentryParameters.MAX_REQUESTS, String.valueOf(DEFAULT_MAX_REQUESTS_PER_SECOND), new CouchDBSentryParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setMaxRequests(Integer.parseInt(value));
			}
		});
		setMaxRequests(Integer.parseInt(value));
		
		value = CouchDBSentryParameters.getInstance().getParameter(CouchDBSentryParameters.CACHE_SIZE, String.valueOf(Cache.DEFAULT_MAX_SIZE), new CouchDBSentryParameters.Listener() {
			
			@Override
			public void parameterChanged(String parameter, String value) {
				setCacheSize(Integer.parseInt(value));
			}
		});
		setCacheSize(Integer.parseInt(value));
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

	public synchronized String getApiKey() {

		if (API_KEY == null) {
			try {
				InputStream stream = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("API_KEY.txt");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(stream));
				API_KEY = reader.readLine();
				log.info("API_KEY = " + API_KEY);
				stream.close();
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception", e);
				API_KEY = "unknown";
			}
		}

		return "&apikey=" + API_KEY;
	}

	private final List<Long> requests = new LinkedList<Long>();

	public void throttle() {

		synchronized (requests) {
			long last = 0;

			if (requests.size() > 0)
				last = requests.get(0);

			long now = System.currentTimeMillis();
			long delta = now - last;

			if (delta < 1050) {
				// log.info("Throttling: " + (1050 - delta));
				try {
					Thread.sleep(1050 - delta);
				} catch (Exception ie) {
				}
			}

			while (requests.size() >= maxRequests)
				requests.remove(0);

			requests.add(System.currentTimeMillis());
		}
	}

	public CareerProfile readCareerProfile(String string)
			throws JsonParseException, JsonMappingException, IOException {
		return readCareerProfile(string, true);
	}

	public HeroProfile readHeroProfile(String string)
			throws JsonParseException, JsonMappingException, IOException {
		return readHeroProfile(string, true);
	}

	public ItemInformation readItemInformation(String string)
			throws JsonParseException, JsonMappingException, IOException {
		return readItemInformation(string, true);
	}

	public CareerProfile readCareerProfile(String string,
			boolean ignoreUnknown) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (ignoreUnknown) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
		}
		CareerProfile out = mapper.readValue(new File(string),
				CareerProfile.class);
		return out;
	}

	public HeroProfile readHeroProfile(String string,
			boolean ignoreUnknown) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (ignoreUnknown) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
		}
		HeroProfile out = mapper.readValue(new File(string), HeroProfile.class);
		return out;
	}

	public ItemInformation readItemInformation(String string,
			boolean ignoreUnknown) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (ignoreUnknown) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
		}
		ItemInformation out = mapper.readValue(new File(string),
				ItemInformation.class);
		return out;
	}

	public CareerProfile readCareerProfile(String server, String name,
			int code) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		throttle();
		URL url = new URL(UrlHelper.careerProfileUrl(server, name, code)
				+ getApiKey());

		// log.info(url.toString());
		//
		// InputStream stream = url.openStream();
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(stream));
		// String line;
		//
		// while (( line = reader.readLine()) != null) {
		// System.out.println(line);
		// }
		//
		// stream.close();

		CareerProfile out = mapper.readValue(url, CareerProfile.class);
		return out;
	}

	public HeroProfile readHeroProfile(String server, String name,
			int code, int id) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		throttle();
		HeroProfile out = mapper.readValue(
				new URL(UrlHelper.heroProfileUrl(server, name, code, id)
						+ getApiKey()), HeroProfile.class);
		return out;
	}


	private long cacheHit = 0;
	private long cacheMiss = 0;

	public long getCacheHits() {
		synchronized (itemCache) {
			return cacheHit;
		}
	}

	public long getCacheMisses() {
		synchronized (itemCache) {
			return cacheMiss;
		}
	}

	public void clearItemCache() {
		synchronized (itemCache) {
			itemCache.clear();
			cacheHit = 0;
			cacheMiss= 0;
		}
	}
	public ItemInformation readItemInformation(String server,
			String tooltipParams) throws JsonParseException, IOException {

		URL url = new URL(UrlHelper.itemInformationUrl(server, tooltipParams)
				+ getApiKey());
		String urlString = url.toExternalForm();

		synchronized (itemCache) {
			ItemInformation item = itemCache.get(urlString);

			if (item != null) {
				// log.info("Item Cache hit: " + server + "/" + tooltipParams);
				cacheHit++;
				return item;
			} else {
				cacheMiss++;
				// log.info("Item Cache miss: " + server + "/" + tooltipParams);
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		throttle();
		ItemInformation out = mapper.readValue(url, ItemInformation.class);

		synchronized (itemCache) {
			itemCache.put(urlString, out);
		}

		return out;

	}

}
