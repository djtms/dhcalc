package com.dawg6.web.dhcalc.shared.calculator.d3api;

import java.io.Serializable;
import java.util.Map;

public class SocketEffect implements Serializable {

	private static final long serialVersionUID = 1L;

	public ItemInformation.Attributes attributes;

	public String itemTypeName;
	public String itemTypeId;
	
	public Map<String, Value<Float>> attributesRaw;
}
