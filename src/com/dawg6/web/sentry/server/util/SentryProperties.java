package com.dawg6.web.sentry.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SentryProperties {

	private static final Logger log = Logger.getLogger(SentryProperties.class.getName());
	
	private static final String FILE_NAME = "dawg6/sentry.properties";
	private static final String DEVMODE = "com.dawg6.sentry.devmode";
	private static final String API_KEY = "com.dawg6.sentry.api_key";
	private static final String DB = "com.dawg6.sentry.db";
	
	private static final SentryProperties instance = new SentryProperties();

	private boolean devmode;
	private String apiKey;
	private String db;
	
	private SentryProperties() {
		try {
			String userDir = System.getProperty("user.home");
			String sep = System.getProperty("file.separator");
			InputStream stream = new FileInputStream(userDir + sep + FILE_NAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			Properties props = new Properties();
			props.load(reader);
			
			devmode = Boolean.valueOf(props.getProperty(DEVMODE, "false"));
			apiKey = props.getProperty(API_KEY);
			db = props.getProperty(DB);
			
			log.info("devmode = " + devmode);
			log.info("API_KEY = " + apiKey);
			log.info("DB = " + db);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error setting properties", e);
		}
	}
	
	public static SentryProperties getInstance() {
		return instance;
	}

	public boolean isDevmode() {
		return devmode;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getDb() {
		return db;
	}
}
