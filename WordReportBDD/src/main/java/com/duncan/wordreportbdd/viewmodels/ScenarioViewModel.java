package com.duncan.wordreportbdd.viewmodels;

import java.util.List;

public class ScenarioViewModel {
	private String name;
	private String description;
	private Boolean pass = null; // pass or fail

	private List<BeforeViewModel> beforeSteps;
	private List<StepViewModel> steps;
	private List<AfterViewModel> afterSteps;
	
}
