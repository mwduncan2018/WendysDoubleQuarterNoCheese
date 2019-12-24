package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureViewModel {

	private String name;
	private String description;
	private Boolean status;
	
	private List<ScenarioViewModel> scenarios = new ArrayList<ScenarioViewModel>();
	private Long numberScenariosPassed;
	private Long numberScenariosFailed;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public Long getNumberScenariosPassed() {
		return numberScenariosPassed;
	}

	public void setNumberScenariosPassed(Long numberScenariosPassed) {
		this.numberScenariosPassed = numberScenariosPassed;
	}

	public Long getNumberScenariosFailed() {
		return numberScenariosFailed;
	}

	public void setNumberScenariosFailed(Long numberScenariosFailed) {
		this.numberScenariosFailed = numberScenariosFailed;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		this.status = b;
	}

	public List<ScenarioViewModel> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<ScenarioViewModel> scenarios) {
		this.scenarios = scenarios;
	}

}
