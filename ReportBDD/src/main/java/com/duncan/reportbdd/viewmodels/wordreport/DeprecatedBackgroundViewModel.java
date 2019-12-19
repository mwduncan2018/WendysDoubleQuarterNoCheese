package com.duncan.reportbdd.viewmodels.wordreport;

import java.util.ArrayList;
import java.util.List;

public class DeprecatedBackgroundViewModel {

	private List<StepViewModel> steps = new ArrayList<StepViewModel>();

	public List<StepViewModel> getSteps() {
		return steps;
	}

	public void setSteps(List<StepViewModel> steps) {
		this.steps = steps;
	}

}
