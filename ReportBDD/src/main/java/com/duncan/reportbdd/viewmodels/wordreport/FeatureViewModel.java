package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;

public class FeatureViewModel {

	private String name;
	private String description;
	private String status; // pass or fail
	private List<ScenarioViewModel> scenarios = new ArrayList<ScenarioViewModel>();

	public Integer getNumTestsPassed() {
		//return scenarios.stream().map(x -> x.getStatus)
		
	}
	
	public Integer getNumTestsFailed() {
		
		return 0;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ScenarioViewModel> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<ScenarioViewModel> scenarios) {
		this.scenarios = scenarios;
	}

}
