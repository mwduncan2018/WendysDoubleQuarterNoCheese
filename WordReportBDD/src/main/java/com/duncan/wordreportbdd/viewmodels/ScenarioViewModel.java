package com.duncan.wordreportbdd.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class ScenarioViewModel {
	private String name;
	private String description;
	private Boolean pass = null; // pass or fail

	private List<BeforeViewModel> beforeSteps = new ArrayList<BeforeViewModel>();
	private List<StepViewModel> steps = new ArrayList<StepViewModel>();
	private List<AfterViewModel> afterSteps = new ArrayList<AfterViewModel>();

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

	public List<BeforeViewModel> getBeforeSteps() {
		return beforeSteps;
	}

	public void setBeforeSteps(List<BeforeViewModel> beforeSteps) {
		this.beforeSteps = beforeSteps;
	}

	public List<StepViewModel> getSteps() {
		return steps;
	}

	public void setSteps(List<StepViewModel> steps) {
		this.steps = steps;
	}

	public List<AfterViewModel> getAfterSteps() {
		return afterSteps;
	}

	public void setAfterSteps(List<AfterViewModel> afterSteps) {
		this.afterSteps = afterSteps;
	}

}
