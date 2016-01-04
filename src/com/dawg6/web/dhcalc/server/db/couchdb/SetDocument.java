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
package com.dawg6.web.dhcalc.server.db.couchdb;

import java.util.Map;

import com.dawg6.web.dhcalc.shared.calculator.stats.DocumentBase;


public class SetDocument extends DocumentBase {

	private static final long serialVersionUID = -6227570607442906634L;

	public static final String DOCUMENT_TYPE = "SetItem";

	private Map<String, Integer>  rawAttributes;
	private String slug;
	private String tooltipParams;
	
	public SetDocument() {
		super(DOCUMENT_TYPE);
	}

	public Map<String, Integer> getRawAttributes() {
		return rawAttributes;
	}

	public void setRawAttributes(Map<String, Integer> rawAttributes) {
		this.rawAttributes = rawAttributes;
	}

	public String getTooltipParams() {
		return tooltipParams;
	}

	public void setTooltipParams(String tooltipParams) {
		this.tooltipParams = tooltipParams;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
}
