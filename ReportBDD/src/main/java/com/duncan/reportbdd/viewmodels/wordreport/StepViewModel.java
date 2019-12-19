package com.duncan.reportbdd.viewmodels.wordreport;

public class StepViewModel {

	private String keyword; // Given, When, or Then
	private String name; // description text
	private String status; // pass or fail
	private String duration; // nanoseconds duration
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String pass) {
		this.status = pass;
	}

}
