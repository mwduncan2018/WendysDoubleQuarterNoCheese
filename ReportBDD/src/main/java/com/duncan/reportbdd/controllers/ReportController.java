package com.duncan.reportbdd.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.duncan.reportbdd.models.cucumberjsonpojo.After;
import com.duncan.reportbdd.models.cucumberjsonpojo.Before;
import com.duncan.reportbdd.models.cucumberjsonpojo.CucumberJsonPojo;
import com.duncan.reportbdd.models.cucumberjsonpojo.Element;
import com.duncan.reportbdd.models.cucumberjsonpojo.Step;
import com.duncan.reportbdd.viewmodels.wordreport.FeatureViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ReportViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ScenarioViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.StepViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ReportController {

	protected ReportViewModel reportViewModel = new ReportViewModel();

	protected String cucumberJsonPath;
	protected String writePath;
	protected CucumberJsonPojo[] root;

	public abstract ReportController generateReport();

	public ReportController setCucumberJsonPath(String cucumberJsonPath) {
		this.cucumberJsonPath = cucumberJsonPath;
		return this;
	}

	public ReportController setWritePath(String writePath) {
		this.writePath = writePath;
		return this;
	}

	protected void unmarshalJson() {
		// Using Jackson to unmarshal the Cucumber JSON
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(cucumberJsonPath);
			root = objectMapper.readValue(file, CucumberJsonPojo[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void extractData() {
		// Extract data from the JSON and populate the WordReportViewModel

		this.unmarshalJson();
		for (CucumberJsonPojo feature : root) {
			FeatureViewModel featureVM = new FeatureViewModel();
			featureVM.setName(feature.getName().trim().replaceAll("\\s{2,}", ""));
			featureVM.setDescription(feature.getDescription().trim().replaceAll("\\R", " ").replaceAll("\\s{2,}", ""));

			List<Element> elements = Arrays.asList(feature.getElements());
			for (int i = 0; i < elements.size(); i++) {
				ScenarioViewModel scenarioVM = new ScenarioViewModel();

				// Background Steps
				List<StepViewModel> backgrounds = new ArrayList<StepViewModel>();
				while (elements.get(i).getType().equals("background")) {
					for (Step step : elements.get(i).getSteps()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setDuration(step.getResult().getDuration());
						stepVM.setErrorMessage(step.getResult().getError_message());
						stepVM.setKeyword(step.getKeyword());
						stepVM.setName(step.getName());
						stepVM.setStatus(step.getResult().getStatus().equals("passed"));
						backgrounds.add(stepVM);
						i++;
					}
				}
				scenarioVM.setBackgrounds(backgrounds);

				if (elements.get(i).getType().equals("scenario")) {
					scenarioVM.setName(elements.get(i).getName().trim().replaceAll("\\s{2,}", ""));
					scenarioVM.setDescription(
							elements.get(i).getDescription().trim().replaceAll("\\R", " ").replaceAll("\\s{2,}", ""));

					List<StepViewModel> beforeSteps = new ArrayList<StepViewModel>();
					for (Before before : elements.get(i).getBefore()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setDuration(before.getResult().getDuration());
						stepVM.setErrorMessage(before.getResult().getError_message());
						stepVM.setKeyword("");
						stepVM.setName("");
						stepVM.setMethodMatch(before.getMatch().getLocation());
						stepVM.setStatus(before.getResult().getStatus().equals("passed"));
						beforeSteps.add(stepVM);
					}
					scenarioVM.setBeforeSteps(beforeSteps);

					List<StepViewModel> steps = new ArrayList<StepViewModel>();
					for (Step step : elements.get(i).getSteps()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setDuration(step.getResult().getDuration());
						stepVM.setErrorMessage(step.getResult().getError_message());
						stepVM.setKeyword(step.getKeyword());
						stepVM.setName(step.getName());
						stepVM.setMethodMatch(step.getMatch().getLocation());
						stepVM.setStatus(step.getResult().getStatus().equals("passed"));
						steps.add(stepVM);
					}
					scenarioVM.setSteps(steps);

					List<StepViewModel> afterSteps = new ArrayList<StepViewModel>();
					for (After after : elements.get(i).getAfter()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setDuration(after.getResult().getDuration());
						stepVM.setErrorMessage(after.getResult().getError_message());
						stepVM.setKeyword("");
						stepVM.setName("");
						stepVM.setMethodMatch(after.getMatch().getLocation());
						stepVM.setStatus(after.getResult().getStatus().equals("passed"));
						afterSteps.add(stepVM);
					}
					scenarioVM.setAfterSteps(afterSteps);

				}
				featureVM.getScenarios().add(scenarioVM);
			}
			reportViewModel.getFeatures().add(featureVM);
		}

	}

	protected void calculateStatusOfScenarios() {
		// Set the status for each scenario
		for (FeatureViewModel f : reportViewModel.getFeatures()) {
			for (ScenarioViewModel s : f.getScenarios()) {
				s.setStatus(s.getBackgrounds().stream().allMatch(x -> x.getStatus() == true)
						&& s.getBeforeSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getAfterSteps().stream().allMatch(x -> x.getStatus() == true));
			}
		}
	}

	protected void calculateStatusOfFeatures() {
		// Set the status for each feature
		for (FeatureViewModel f : reportViewModel.getFeatures()) {
			f.setStatus(f.getScenarios().stream().allMatch(x -> x.getStatus() == true));
		}
	}

	protected void calculateScenariosPassedForEachFeature() {
		// Calculate the total number of scenarios passed for each feature
		for (FeatureViewModel f : reportViewModel.getFeatures()) {
			f.setNumberScenariosPassed(f.getScenarios().stream().filter(x -> x.getStatus() == true).count());
		}
	}

	protected void calculateScenariosFailedForEachFeature() {
		// Calculate the total number of scenarios failed for each feature
		for (FeatureViewModel f : reportViewModel.getFeatures()) {
			f.setNumberScenariosFailed(f.getScenarios().stream().filter(x -> x.getStatus() == false).count());
		}
	}

	protected void calculateFeaturesPassed() {
		reportViewModel.setNumberFeaturesPassed(
				reportViewModel.getFeatures().stream().filter(x -> x.getStatus() == true).count());
	}

	protected void calculateFeaturesFailed() {
		reportViewModel.setNumberFeaturesFailed(
				reportViewModel.getFeatures().stream().filter(x -> x.getStatus() == false).count());
	}

	protected void calculateScenariosPassedForEntireTestRun() {
		// Calculate the total number of scenarios passed for the entire test run
		reportViewModel.setNumberScenariosPassed(reportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == true).count();
		}).collect(Collectors.summingLong(Long::longValue)));
	}

	protected void calculateScenariosFailedForEntireTestRun() {
		// Calculate the total number of scenarios failed for the entire test run
		reportViewModel.setNumberScenariosFailed(reportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == false).count();
		}).collect(Collectors.summingLong(Long::longValue)));
	}

	// Calculate the total time... ... in the future

	
}
