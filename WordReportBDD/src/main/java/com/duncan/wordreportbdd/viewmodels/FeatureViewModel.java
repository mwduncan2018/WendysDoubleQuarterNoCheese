package com.duncan.wordreportbdd.viewmodels;

import java.util.List;

public class FeatureViewModel {
	private String name;
	private String description;
	private Boolean pass = null; // pass or fail

	private List<ScenarioViewModel> scenarios;

}
