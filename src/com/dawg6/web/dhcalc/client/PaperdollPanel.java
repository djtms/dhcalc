package com.dawg6.web.dhcalc.client;

import com.dawg6.web.dhcalc.shared.calculator.d3api.Realm;
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
