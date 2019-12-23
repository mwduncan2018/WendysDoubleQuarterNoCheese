package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureViewModel {

	private String name;
	private String description;
	private String status;
	
	private List<ScenarioViewModel> scenarios = new ArrayList<ScenarioViewModel>();
	private Integer numberScenariosPassed;
	private Integer numberScenariosFailed;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getNumberScenariosPassed() {
		return numberScenariosPassed;
	}

	public void setNumberScenariosPassed(Integer numberScenariosPassed) {
		this.numberScenariosPassed = numberScenariosPassed;
	}

	public Integer getNumberScenariosFailed() {
		return numberScenariosFailed;
	}

	public void setNumberScenariosFailed(Integer numberScenariosFailed) {
		this.numberScenariosFailed = numberScenariosFailed;
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
