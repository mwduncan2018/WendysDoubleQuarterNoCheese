package com.duncan.reportbdd.viewmodels.wordreport;

public class AfterViewModel {

	private String matchLocation; // match -> location
	private Boolean pass = null; // pass or fail

	public String getMatchLocation() {
		return matchLocation;
	}

	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

}
