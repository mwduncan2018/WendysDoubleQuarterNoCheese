package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Element {

	private String start_timestamp;
	private Before[] before;
	private String line;
	private String name;
	private String description;
	private String id;
	private After[] after;
	private String type;
	private String keyword;
	private Step[] steps;
	private Tag[] tags;

	public String getStart_timestamp() {
		return start_timestamp;
	}

	public void setStart_timestamp(String start_timestamp) {
		this.start_timestamp = start_timestamp;
	}

	public Before[] getBefore() {
		return before;
	}

	public void setBefore(Before[] before) {
		this.before = before;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
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

	public After[] getAfter() {
		return after;
	}

	public void setAfter(After[] after) {
		this.after = after;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Step[] getSteps() {
		return steps;
	}

	public void setSteps(Step[] steps) {
		this.steps = steps;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ClassPojo [start_timestamp = " + start_timestamp + ", before = " + before + ", line = " + line
				+ ", name = " + name + ", description = " + description + ", id = " + id + ", after = " + after
				+ ", type = " + type + ", keyword = " + keyword + ", steps = " + steps + ", tags = " + tags + "]";
	}
}
