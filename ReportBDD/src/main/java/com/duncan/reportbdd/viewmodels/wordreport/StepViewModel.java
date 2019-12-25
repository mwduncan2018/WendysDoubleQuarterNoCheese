package com.duncan.reportbdd.viewmodels.wordreport;

public class StepViewModel {

	private String keyword; // Given, When, or Then
	private String name; // description text
	private Boolean status; // pass or fail
	private String duration; // nanoseconds duration
	private String errorMessage;
	private String methodMatch;

	public String getMethodMatch() {
		return methodMatch;
	}

	public void setMethodMatch(String methodMatch) {
		this.methodMatch = methodMatch;
	}

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean pass) {
		this.status = pass;
	}

}
