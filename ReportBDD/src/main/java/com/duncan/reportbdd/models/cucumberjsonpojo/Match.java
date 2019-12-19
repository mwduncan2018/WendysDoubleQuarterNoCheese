package com.duncan.reportbdd.models.cucumberjsonpojo;

public class Match {

	private String location;
	private Argument[] arguments;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Argument[] getArguments() {
		return arguments;
	}

	public void setArguments(Argument[] arguments) {
		this.arguments = arguments;
	}

	@Override
	public String toString() {
		return "ClassPojo [location = " + location + "]";
	}
}