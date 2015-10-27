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
package com.dawg6.web.dhcalc.client;

import com.dawg6.d3api.shared.Realm;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;

public class PaperdollPanel extends Composite {
	private final Frame frame;

	public PaperdollPanel() {

		frame = new Frame("http://www.google.com");
		initWidget(frame);
		frame.setSize("640px", "640px");

	}

	public void load(Realm realm, String profile, int tag, int id) {
		
		StringBuilder params = new StringBuilder();
		params.append("realm=" + realm.name());
		params.append("&profile=" + URL.encodeQueryString(profile));
		params.append("&tag=" + tag);
		params.append("&id=" + id);
		
		frame.setUrl("paperdoll.jsp?" + params.toString());
	}
}
