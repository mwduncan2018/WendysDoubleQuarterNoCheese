package com.duncan.wordreportbdd.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class FeatureViewModel {

	private String name;
	private String description;
	private Boolean pass = null; // pass or fail
	private List<BackgroundViewModel> backgrounds = new ArrayList<BackgroundViewModel>();
	private List<ScenarioViewModel> scenarios = new ArrayList<ScenarioViewModel>();

	public List<BackgroundViewModel> getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(List<BackgroundViewModel> backgrounds) {
		this.backgrounds = backgrounds;
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

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public List<ScenarioViewModel> getScenarios() {
		return scenarios;
	}

	public void setScenarios(List<ScenarioViewModel> scenarios) {
		this.scenarios = scenarios;
	}

}
