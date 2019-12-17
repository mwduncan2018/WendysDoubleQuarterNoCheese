package com.duncan.wordreportbdd.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class ScenarioViewModel {
	private String name;
	private String description;
	private Boolean pass = null; // pass or fail

	private List<StepViewModel> beforeSteps = new ArrayList<StepViewModel>();
	private List<StepViewModel> steps = new ArrayList<StepViewModel>();
	private List<StepViewModel> afterSteps = new ArrayList<StepViewModel>();

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

	public List<StepViewModel> getBeforeSteps() {
		return beforeSteps;
	}

	public void setBeforeSteps(List<StepViewModel> beforeSteps) {
		this.beforeSteps = beforeSteps;
	}

	public List<StepViewModel> getSteps() {
		return steps;
	}

	public void setSteps(List<StepViewModel> steps) {
		this.steps = steps;
	}

	public List<StepViewModel> getAfterSteps() {
		return afterSteps;
	}

	public void setAfterSteps(List<StepViewModel> afterSteps) {
		this.afterSteps = afterSteps;
	}

}
