package com.dawg6.web.dhcalc.shared.calculator;

import java.io.Serializable;

public class ItemHolder implements Serializable {

	private static final long serialVersionUID = -5634555767124529221L;

	private SpecialItemType type;
	private AttributeData attributes;

	public ItemHolder() { }
	
	public ItemHolder(ItemHolder other) { 
		this.type = other.type;
		this.attributes = new AttributeData(other.attributes);
	}

	public SpecialItemType getType() {
		return type;
	}

	public void setType(SpecialItemType type) {
		this.type = type;
	}

	public AttributeData getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributeData attributes) {
		this.attributes = attributes;
	}
}
