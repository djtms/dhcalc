package com.dawg6.web.dhcalc.server.util;

import java.net.URLEncoder;
import java.util.logging.Logger;

import com.dawg6.web.dhcalc.shared.calculator.stats.DpsTableEntry;

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

	public static boolean isProductionMode() {

		return !DHCalcProperties.getInstance().isDevmode();
	}
}
