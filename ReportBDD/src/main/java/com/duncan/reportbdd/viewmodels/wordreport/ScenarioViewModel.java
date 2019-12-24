package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;

public class ScenarioViewModel {
	
	private String name;
	private String description;
	private Boolean status;

	private List<StepViewModel> backgrounds = new ArrayList<StepViewModel>();
	private List<StepViewModel> beforeHooks = new ArrayList<StepViewModel>();
	private List<StepViewModel> steps = new ArrayList<StepViewModel>();
	private List<StepViewModel> afterHooks = new ArrayList<StepViewModel>();

	public List<StepViewModel> getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(List<StepViewModel> backgrounds) {
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		this.status = b;
	}

	public List<StepViewModel> getBeforeSteps() {
		return beforeHooks;
	}

	public void setBeforeSteps(List<StepViewModel> beforeSteps) {
		this.beforeHooks = beforeSteps;
	}

	public List<StepViewModel> getSteps() {
		return steps;
	}

	public void setSteps(List<StepViewModel> steps) {
		this.steps = steps;
	}

	public List<StepViewModel> getAfterSteps() {
		return afterHooks;
	}

	public void setAfterSteps(List<StepViewModel> afterSteps) {
		this.afterHooks = afterSteps;
	}

}
