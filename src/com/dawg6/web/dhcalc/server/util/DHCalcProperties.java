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
	
	private static final String APP_NAME = "dhcalc";
	private static final String FILE_NAME = "dawg6/" + APP_NAME + ".properties";
	private static final String DEVMODE = "com.dawg6." + APP_NAME + ".devmode";
	private static final String API_KEY = "com.dawg6." + APP_NAME + ".api_key";
	private static final String API_SECRET = "com.dawg6." + APP_NAME + ".api_secret";
	private static final String ACCESS_TOKEN = "com.dawg6." + APP_NAME + ".access_token";
	private static final String DB = "com.dawg6." + APP_NAME + ".db";
	
	private static final DHCalcProperties instance = new DHCalcProperties();
	private static final long started = System.currentTimeMillis();
	
	private boolean devmode;
	private String apiKey;
	private String apiSecret;
	private String db;
	private String accessToken;
	
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
			apiSecret = props.getProperty(API_SECRET);
			accessToken = props.getProperty(ACCESS_TOKEN);
			db = props.getProperty(DB);
			
			log.info("devmode = " + devmode);
			log.info("API_KEY = #" + apiKey.hashCode());
			log.info("API_SECRET = #" + apiSecret.hashCode());
			log.info("DB = " + db);
			
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error setting properties", e);
		}
	}
	
	public long getUptime() {
		return System.currentTimeMillis() - started;
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
	
	public String getApiSecret() {
		return apiSecret;
	}
	
	public String getDb() {
		return db;
	}
}
