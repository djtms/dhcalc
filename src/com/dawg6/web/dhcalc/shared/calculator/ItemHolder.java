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
