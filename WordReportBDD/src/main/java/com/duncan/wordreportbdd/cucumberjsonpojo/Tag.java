package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Tag {

	private String name;
	private String type;
	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String tag) {
		this.type = tag;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + "]";
	}
}
