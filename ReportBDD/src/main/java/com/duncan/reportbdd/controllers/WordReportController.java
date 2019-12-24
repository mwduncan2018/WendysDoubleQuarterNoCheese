package com.duncan.reportbdd.controllers;

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
import com.duncan.reportbdd.viewmodels.wordreport.ScenarioViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.StepViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.WordReportViewModel;
import com.duncan.reportbdd.views.WordReportView;

public class WordReportController extends ReportController {

	private WordReportViewModel wordReportViewModel = new WordReportViewModel();

	@Override
	public void generateReport() {
		this.unmarshalJson();
		this.populateViewModel();

		this.calculateStatusOfFeaturesAndScenarios();
		this.calculateFeaturesPassed();
		this.calculateFeaturesFailed();
		this.calculateScenariosFailedForEachFeature();
		this.calculateScenariosPassedForEachFeature();
		this.calculateScenariosPassedForEntireTestRun();
		this.calculateScenariosFailedForEntireTestRun();
		
		new WordReportView(wordReportViewModel, writePath);
	}

	private void populateViewModel() {
		// Convert all the data to the View Models in this for loop
		for (CucumberJsonPojo feature : root) {
			FeatureViewModel featureVM = new FeatureViewModel();
			featureVM.setDescription(feature.getDescription().trim().replaceAll("\\R", " ").replaceAll("\\s{2,}", ""));
			featureVM.setName(feature.getName().trim().replaceAll("\\s{2,}", ""));

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

					List<StepViewModel> beforeSteps = new ArrayList<StepViewModel>();
					for (Before before : elements.get(i).getBefore()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setDuration(before.getResult().getDuration());
						stepVM.setErrorMessage(before.getResult().getError_message());
						stepVM.setKeyword("");
						stepVM.setName("");
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
						stepVM.setStatus(after.getResult().getStatus().equals("passed"));
						afterSteps.add(stepVM);
					}
					scenarioVM.setAfterSteps(afterSteps);

				}
				featureVM.getScenarios().add(scenarioVM);
			}
			wordReportViewModel.getFeatures().add(featureVM);
		}
	}

	private void calculateStatusOfFeaturesAndScenarios() {
		// Set the status for each feature and scenario in this loop
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			for (ScenarioViewModel s : f.getScenarios()) {
				s.setStatus(s.getBackgrounds().stream().allMatch(x -> x.getStatus() == true)
						&& s.getBeforeSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getAfterSteps().stream().allMatch(x -> x.getStatus() == true));
				System.out.println("=============================");
				System.out.println("Scenario: " + s.getName());
				System.out.println("Status: " + s.getStatus());
				System.out.println("=============================");
			}
			f.setStatus(f.getScenarios().stream().allMatch(x -> x.getStatus() == true));
			System.out.println("=============================");
			System.out.println("Feature: " + f.getName());
			System.out.println("Status: " + f.getStatus());
			System.out.println("=============================");
		}
	}

	private void calculateScenariosPassedForEachFeature() {
		// Calculate the total number of tests passed in this loop for each feature
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			f.setNumberScenariosPassed(f.getScenarios().stream().filter(x -> x.getStatus() == true).count());
			System.out.println("=============================");
			System.out.println("Feature: " + f.getName());
			System.out.println("Total Scenarios Passed: " + f.getNumberScenariosPassed());
			System.out.println("=============================");
		}
	}

	private void calculateScenariosFailedForEachFeature() {
		// Calculate the total number of tests failed in this loop for each feature
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			f.setNumberScenariosFailed(f.getScenarios().stream().filter(x -> x.getStatus() == false).count());
			System.out.println("=============================");
			System.out.println("Feature: " + f.getName());
			System.out.println("Total Scenarios Failed: " + f.getNumberScenariosFailed());
			System.out.println("=============================");
		}
	}

	private void calculateFeaturesPassed() {
		wordReportViewModel.setNumberFeaturesPassed(
				wordReportViewModel.getFeatures().stream().filter(x -> x.getStatus() == true).count());
		System.out.println("features passed = " + wordReportViewModel.getNumberFeaturesPassed());

	}

	private void calculateFeaturesFailed() {
		wordReportViewModel.setNumberFeaturesFailed(
				wordReportViewModel.getFeatures().stream().filter(x -> x.getStatus() == false).count());
		System.out.println("features failed  = " + wordReportViewModel.getNumberFeaturesFailed());
	}

	private void calculateScenariosPassedForEntireTestRun() {
		// Calculate the total number of tests passed for the entire test run
		wordReportViewModel.setNumberScenariosPassed(wordReportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == true).count();
		}).collect(Collectors.summingLong(Long::longValue)));
		System.out.println("scenarios passed = " + wordReportViewModel.getNumberScenariosPassed());
	}

	private void calculateScenariosFailedForEntireTestRun() {
		// Calculate the total number of tests failed for the entire test run
		wordReportViewModel.setNumberScenariosFailed(wordReportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == false).count();
		}).collect(Collectors.summingLong(Long::longValue)));
		System.out.println("scenarios failed = " + wordReportViewModel.getNumberScenariosFailed());
	}

	// Calculate the total time... ... in the future

}
