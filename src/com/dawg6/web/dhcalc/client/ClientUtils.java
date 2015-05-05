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
