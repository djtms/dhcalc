package com.dawg6.web.sentry.shared.calculator.stats;

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
