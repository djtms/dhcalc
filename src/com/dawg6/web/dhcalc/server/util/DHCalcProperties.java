package com.dawg6.web.dhcalc.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DHCalcProperties {

	private static final Logger log = Logger.getLogger(DHCalcProperties.class.getName());
	
	private static final String FILE_NAME = "dawg6/dhcalc.properties";
	private static final String DEVMODE = "com.dawg6.dhcalc.devmode";
	private static final String API_KEY = "com.dawg6.dhcalc.api_key";
	private static final String DB = "com.dawg6.dhcalc.db";
	
	private static final DHCalcProperties instance = new DHCalcProperties();

	private boolean devmode;
	private String apiKey;
	private String db;
	
	private DHCalcProperties() {
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
	
	public static DHCalcProperties getInstance() {
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
