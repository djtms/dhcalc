package com.dawg6.web.dhcalc.server.db.couchdb;

import com.dawg6.web.dhcalc.shared.calculator.stats.DocumentBase;


public class ParameterDocument extends DocumentBase {

	public static final String DOCUMENT_TYPE = "Parameter";

	private String value;
	
	public static final String PARAMETERS = "parameters";
	
	private static final long serialVersionUID = -1429905321879323388L;

	protected ParameterDocument() {
		super(DOCUMENT_TYPE);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
