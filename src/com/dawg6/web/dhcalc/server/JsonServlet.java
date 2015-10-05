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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dawg6.web.dhcalc.server.util.UrlHelper;
import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;

public class JsonServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(JsonServlet.class
			.getName());

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final String BASE_URL = "http://us.battle.net/d3/en/tooltip/item";

	@SuppressWarnings("unused")
	private static final int MAX_BUFFER = 102400;

	@SuppressWarnings("unused")
	private static final String USER_AGENT = "Mozilla/5.0";

	public static final String REALM = "realm";
	public static final String PROFILE = "profile";
	public static final String TAG = "tag";
	public static final String ID = "id";
	public static final String ITEM = "item";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String realmStr = getParameter(req, REALM);
			String profile = getParameter(req, PROFILE);
			String tag = getParameter(req, TAG);
			String id = getParameter(req, ID);
			String item = getParameter(req, ITEM);
			Realm realm = null;

			if (realmStr != null) {
				try {
					realm = Realm.valueOf(realmStr);
				} catch (Exception i1) {
				}
			}

			if (realm != null) {
				URL url = null;

				try {
					if (item != null) {
						url = new URL(UrlHelper.itemInformationUrl(item)
								+ IO.getInstance().getApiKey());
					} else if (profile != null) {

						profile = URLDecoder.decode(profile, "UTF-8");
						String encodedProfile = URLEncoder.encode(profile,
								"UTF-8");

						if (tag != null) {
							if (id != null) {
								url = new URL(UrlHelper.heroProfileUrl(
										realm.getApiHost(), encodedProfile,
										Integer.valueOf(tag),
										Integer.valueOf(id))
										+ IO.getInstance().getApiKey());
							} else {
								url = new URL(UrlHelper.careerProfileUrl(
										realm.getApiHost(), encodedProfile,
										Integer.valueOf(tag)) + IO.getInstance().getApiKey());
							}
						}
					}
				} catch (Exception e2) {
				}

				if (url != null) {
					try {

						IO.getInstance().throttle();

						BufferedReader in = new BufferedReader(
								new InputStreamReader(url.openStream(), "UTF-8"));
						String inputLine;

						PrintWriter writer = resp.getWriter();

						while ((inputLine = in.readLine()) != null) {
							writer.println(inputLine);
						}

						writer.flush();
						in.close();
					} catch (Exception e) {
						log.log(Level.SEVERE,
								"URL: " + url + "; " + e.getMessage());
						log.log(Level.WARNING, "Exception", e);
					}
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception", e);
			// resp.setContentType("text/plain");
			// e.printStackTrace(resp.getWriter());
		}
	}

	private String getParameter(HttpServletRequest req, String name) {
		String value = req.getParameter(name);

		if ((value != null) && (value.trim().length() == 0))
			return null;
		else
			return value;
	}

}
