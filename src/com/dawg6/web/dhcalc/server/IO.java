package com.dawg6.web.dhcalc.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.dawg6.web.dhcalc.server.db.couchdb.CouchDBDHCalcParameters;
import com.dawg6.web.dhcalc.server.util.DHCalcProperties;
import com.dawg6.web.dhcalc.shared.calculator.d3api.CareerProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.HeroProfile;
import com.dawg6.web.dhcalc.shared.calculator.d3api.ItemInformation;
import com.dawg6.web.dhcalc.shared.calculator.d3api.UrlHelper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IO {

	private static final Logger log = Logger.getLogger(IO.class.getName());

	private static final Integer DEFAULT_MAX_REQUESTS_PER_SECOND = 85;
	private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
	private static final int DEFAULT_READ_TIMEOUT = 30000;
	
	private static IO instance;
	
	public static synchronized IO getInstance() {
		if (instance == null)
			instance = new IO();
		
		return instance;
	}
	
	
	private int maxRequests = DEFAULT_MAX_REQUESTS_PER_SECOND;
	public final Cache<String, ItemInformation> itemCache = new Cache<String, ItemInformation>(true);

	private int connectTimeout;

	private int readTimeout;
	
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

	public synchronized String getApiKey() {
		return "&apikey=" + DHCalcProperties.getInstance().getApiKey();
	}

	private final List<Long> requests = new LinkedList<Long>();

	private long numRequests = 0;
	private long blockTime;
	
	
	public long getNumRequests() {
		synchronized (requests) {
			return numRequests;
		}
	}
	
	public double getAverageBlockTime() {
		synchronized (requests) {
			return (numRequests > 0) ?
					((double)blockTime / (double)numRequests)
					: 0.0;
		}
	}

	public void throttle() {

		long start = System.currentTimeMillis();
		
		synchronized (requests) {
			
			if (requests.size() >= maxRequests) {
				long last = 0;
	
				if (requests.size() > 0)
					last = requests.get(0);
	
				long now = System.currentTimeMillis();
				long delta = now - last;
	
				if (delta < 1000) {
					// log.info("Throttling: " + (1000 - delta));
					try {
						Thread.sleep(1000 - delta);
					} catch (Exception ie) {
					}
				}
	
				while (requests.size() >= maxRequests)
					requests.remove(0);
			}

			long finish = System.currentTimeMillis();

			requests.add(finish);
			numRequests++;
			blockTime += (finish - start);
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

		CareerProfile out = readValue(mapper, url, CareerProfile.class);
		return out;
	}

	private <T> T readValue(ObjectMapper mapper, URL url, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		
		HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.setRequestProperty("Content-length", "0");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.setConnectTimeout(connectTimeout);
        c.setReadTimeout(readTimeout);
        c.connect();
        int status = c.getResponseCode();
        
        switch (status) {
        case 200:
        case 201:
            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            return mapper.readValue(sb.toString(), clazz);
        }
        
        return null;
	}
	
	public HeroProfile readHeroProfile(String server, String name,
			int code, int id) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		throttle();
		HeroProfile out = readValue(mapper, 
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
		ItemInformation out = readValue(mapper, url, ItemInformation.class);

		synchronized (itemCache) {
			itemCache.put(urlString, out);
		}

		return out;

	}

}
