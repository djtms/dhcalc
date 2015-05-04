package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;

import com.dawg6.web.dhcalc.shared.calculator.JsonObject;

public class D3Message extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String code;
	public String reason;
	
}
