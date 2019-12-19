package com.duncan.reportbdd.models.cucumberjsonpojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CucumberJsonPojo {

	private String line;
	private Element[] elements;
	private String name;
	private String description;
	private String id;
	private String keyword;
	private String uri;
	private Tag[] tags;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Element[] getElements() {
		return elements;
	}

	public void setElements(Element[] elements) {
		this.elements = elements;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ClassPojo [line = " + line + ", elements = " + elements + ", name = " + name + ", description = "
				+ description + ", id = " + id + ", keyword = " + keyword + ", uri = " + uri + ", tags = " + tags + "]";
	}
}
