package com.aarav.wbx.core.serialization.deserialization.pageproperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagAttribute {

	@SerializedName("tagPath")
	@Expose
	private String tagPath;
	
	@SerializedName("tagName")
	@Expose
	private String tagName;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public TagAttribute() {
	}

	/**
	 * 
	 * @param tagPath
	 * @param tagName
	 */
	public TagAttribute(String tagPath, String tagName) {
		super();
		this.tagPath = tagPath;
		this.tagName = tagName;
	}

	public String getTagPath() {
		return tagPath;
	}

	public void setTagPath(String tagPath) {
		this.tagPath = tagPath;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tagPath", tagPath).append("tagName", tagName).toString();
	}

}
