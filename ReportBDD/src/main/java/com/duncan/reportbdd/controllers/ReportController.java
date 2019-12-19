package com.duncan.reportbdd.controllers;

import java.io.File;
import java.io.IOException;

import com.duncan.reportbdd.models.cucumberjsonpojo.CucumberJsonPojo;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ReportController {

	protected String cucumberJsonPath;
	protected String writePath;
	protected CucumberJsonPojo[] root;

	public abstract void generateReport();

	public ReportController setCucumberJsonPath(String cucumberJsonPath) {
		this.cucumberJsonPath = cucumberJsonPath;
		return this;
	}

	public ReportController setWritePath(String writePath) {
		this.writePath = writePath;
		return this;
	}

	protected void unmarshalJson() {
		// Using Jackson to unmarshal the Cucumber JSON
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(cucumberJsonPath);
			root = objectMapper.readValue(file, CucumberJsonPojo[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
