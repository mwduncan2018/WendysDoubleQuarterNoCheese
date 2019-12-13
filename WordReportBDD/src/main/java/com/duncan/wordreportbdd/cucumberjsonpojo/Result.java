package com.duncan.wordreportbdd.cucumberjsonpojo;

public class Result {

	private String duration;
	private String status;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ClassPojo [duration = " + duration + ", status = " + status + "]";
	}
}
