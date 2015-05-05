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
package com.dawg6.web.dhcalc.shared.calculator.stats;

import com.dawg6.common.data.objects.UniqueObject;
import com.google.gson.annotations.SerializedName;

public abstract class DocumentBase extends UniqueObject {

	private static final long serialVersionUID = 8734204762823369830L;

	@SerializedName("_id")
	protected String id;
	@SerializedName("_rev")
	private String revision;
	@SerializedName("type")
	private String documentType;
	
	protected DocumentBase(String documentType) {
		this.documentType = documentType;
	}
	
	@Override
	public String getId() {
		return id;
	}

	public String getRevision() {
		return revision;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
}
