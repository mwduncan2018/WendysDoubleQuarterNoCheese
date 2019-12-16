package com.duncan.wordreportbdd.viewmodels;

public class StepViewModel {

	private String keyword; // Given, When, or Then
	private String name; // description text
	private Boolean pass = null; // pass or fail

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

}
