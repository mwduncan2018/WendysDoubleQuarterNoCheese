package com.duncan.reportbdd.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
	public WordReportController generateReport() {
		this.wordReportViewModel.setTitle("Official Test Report");
		this.wordReportViewModel.setDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

		this.extractData();

		this.calculateStatusOfScenarios();
		this.calculateStatusOfFeatures();

		this.calculateScenariosPassedForEachFeature();
		this.calculateScenariosFailedForEachFeature();

		this.calculateScenariosPassedForEntireTestRun();
		this.calculateScenariosFailedForEntireTestRun();

		this.calculateFeaturesPassed();
		this.calculateFeaturesFailed();

		new WordReportView(wordReportViewModel, writePath);

		return this;
	}

	@Override
	public WordReportController writeResultsToConsole() {
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("CUCUMBER CONSOLE REPORT");
		System.out.println("TITLE: " + wordReportViewModel.getTitle());
		System.out.println("DATE: " + wordReportViewModel.getDate());
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("==================================================\n");

		System.out.println(wordReportViewModel.getNumberFeaturesPassed() + " Features Passed");
		System.out.println(wordReportViewModel.getNumberFeaturesFailed() + " Features Failed");
		System.out.println(wordReportViewModel.getNumberScenariosPassed() + " Scenarios Passed");
		System.out.println(wordReportViewModel.getNumberScenariosFailed() + " Scenarios Failed" + "\n");

		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			System.out.println(
					"======================================================================================================================================================");
			System.out.println(
					"======================================================================================================================================================");
			System.out.println("Feature: " + f.getName());
			if (f.getDescription().length() > 0) { System.out.println("Description: " + f.getDescription()); }
			System.out.println(f.getNumberScenariosPassed() + " Scenarios Passed");
			System.out.println(f.getNumberScenariosFailed() + " Scenarios Failed" + "\n");

			for (ScenarioViewModel s : f.getScenarios()) {
				System.out.println("   Scenario: " + s.getName());
				if (s.getDescription().length() > 0) { System.out.println("   Description: " + s.getDescription()); }
				System.out.println("   Pass? " + s.getStatus());

				if (s.getStatus() == false) {
					s.getBackgrounds().stream().filter(x -> x.getErrorMessage() != null)
							.forEach(y -> System.out.println("      " + y.getErrorMessage()));
					s.getBeforeSteps().stream().filter(x -> x.getErrorMessage() != null)
							.forEach(y -> System.out.println("      " + y.getErrorMessage()));
					s.getSteps().stream().filter(x -> x.getErrorMessage() != null)
							.forEach(y -> System.out.println("      " + y.getErrorMessage()));
					s.getAfterSteps().stream().filter(x -> x.getErrorMessage() != null)
							.forEach(y -> System.out.println("      " + y.getErrorMessage()));
				}

				System.out.println("");
			}

		}

		return this;
	}

	private void extractData() {
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
			wordReportViewModel.getFeatures().add(featureVM);
		}

	}

	private void calculateStatusOfScenarios() {
		// Set the status for each scenario
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			for (ScenarioViewModel s : f.getScenarios()) {
				s.setStatus(s.getBackgrounds().stream().allMatch(x -> x.getStatus() == true)
						&& s.getBeforeSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getSteps().stream().allMatch(x -> x.getStatus() == true)
						&& s.getAfterSteps().stream().allMatch(x -> x.getStatus() == true));
			}
		}
	}

	private void calculateStatusOfFeatures() {
		// Set the status for each feature
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			f.setStatus(f.getScenarios().stream().allMatch(x -> x.getStatus() == true));
		}
	}

	private void calculateScenariosPassedForEachFeature() {
		// Calculate the total number of scenarios passed for each feature
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			f.setNumberScenariosPassed(f.getScenarios().stream().filter(x -> x.getStatus() == true).count());
		}
	}

	private void calculateScenariosFailedForEachFeature() {
		// Calculate the total number of scenarios failed for each feature
		for (FeatureViewModel f : wordReportViewModel.getFeatures()) {
			f.setNumberScenariosFailed(f.getScenarios().stream().filter(x -> x.getStatus() == false).count());
		}
	}

	private void calculateFeaturesPassed() {
		wordReportViewModel.setNumberFeaturesPassed(
				wordReportViewModel.getFeatures().stream().filter(x -> x.getStatus() == true).count());
	}

	private void calculateFeaturesFailed() {
		wordReportViewModel.setNumberFeaturesFailed(
				wordReportViewModel.getFeatures().stream().filter(x -> x.getStatus() == false).count());
	}

	private void calculateScenariosPassedForEntireTestRun() {
		// Calculate the total number of scenarios passed for the entire test run
		wordReportViewModel.setNumberScenariosPassed(wordReportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == true).count();
		}).collect(Collectors.summingLong(Long::longValue)));
	}

	private void calculateScenariosFailedForEntireTestRun() {
		// Calculate the total number of scenarios failed for the entire test run
		wordReportViewModel.setNumberScenariosFailed(wordReportViewModel.getFeatures().stream().map(x -> {
			return x.getScenarios().stream().filter(y -> y.getStatus() == false).count();
		}).collect(Collectors.summingLong(Long::longValue)));
	}

	// Calculate the total time... ... in the future

}
