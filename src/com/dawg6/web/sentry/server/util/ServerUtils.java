package com.dawg6.web.sentry.server.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dawg6.web.sentry.shared.calculator.stats.DpsTableEntry;

public class ServerUtils {

	private static final Logger log = Logger.getLogger(ServerUtils.class.getName());

	public static String getProfileUrl(DpsTableEntry entry) {

		StringBuffer buf = new StringBuffer();
	
		buf.append(entry.getRealm().getHost());
		buf.append("/d3/profile/");
		
		String[] split = entry.getBattletag().split("/");
		
		try {
			buf.append(URLEncoder.encode(split[0], "UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		buf.append("/hero/" + split[1]);
		
		return buf.toString();
	}

	private static Boolean productionMode = null;

	public static synchronized boolean isProductionMode() {

		if (productionMode == null) {
			try {
				InputStream stream = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("devmode.txt");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(stream));
				String value = reader.readLine();
				log.info("DEVMODE = " + value);
				stream.close();
				productionMode = !Boolean.valueOf(value);
			} catch (Exception e) {
				log.log(Level.SEVERE, "Exception", e);
				productionMode = false;
			}
		}

		return productionMode;
	}
}
