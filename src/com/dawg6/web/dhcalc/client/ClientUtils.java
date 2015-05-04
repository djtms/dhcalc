package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.stats.DpsTableEntry;
import com.google.gwt.http.client.URL;

public class ClientUtils {

	public static String getProfileUrl(DpsTableEntry entry) {

		StringBuffer buf = new StringBuffer();
	
		buf.append(entry.getRealm().getHost());
		buf.append("/d3/profile/");
		
		String[] split = entry.getBattletag().split("/");
		
		try {
			buf.append(URL.encode(split[0]));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
		buf.append("/hero/" + split[1]);
		
		return buf.toString();
	}

}
