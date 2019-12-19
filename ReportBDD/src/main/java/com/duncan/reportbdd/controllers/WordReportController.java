package com.duncan.reportbdd.controllers;

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

	private WordReportViewModel wordReportViewModel;

	@Override
	public void generateReport() {
		unmarshalJson();
		initializeViewModel();
		new WordReportView(wordReportViewModel, writePath);
	}

	private void initializeViewModel() {
		wordReportViewModel = new WordReportViewModel();

		for (CucumberJsonPojo feature : root) {
			FeatureViewModel featureVM = new FeatureViewModel();
			featureVM.setDescription(feature.getDescription().trim().replaceAll("\\R", " ").replaceAll("\\s{2,}", ""));
			featureVM.setName(feature.getName().trim().replaceAll("\\s{2,}", ""));

			Boolean featurePass = true;

			// Get the backgrounds
			for (Element element : feature.getElements()) {
				if (element.getType().equals("background")) {
					StepViewModel backgroundVM = new StepViewModel();

					// Get the background steps
					for (Step step : element.getSteps()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setName(step.getName().trim().replaceAll("\\s{2,}", ""));
						stepVM.setKeyword(step.getKeyword().trim().replaceAll("\\s{2,}", ""));
						stepVM.setStatus(step.getResult().getStatus());
						stepVM.setErrorMessage(step.getResult().getError_message());
						if (!stepVM.getStatus().equals("passed")) {
							featurePass = false; // if any background step fails, the feature fails
						}
						backgroundVM.getSteps().add(stepVM);
					}

					featureVM.getBackgrounds().add(backgroundVM);
				}
			}

			// Get the scenarios
			for (Element element : feature.getElements()) {
				if (element.getType().equals("scenario")) {
					ScenarioViewModel scenarioVM = new ScenarioViewModel();
					scenarioVM.setDescription(element.getDescription().trim().replaceAll("\\s{2,}", ""));
					scenarioVM.setName(element.getName().trim().replaceAll("\\s{2,}", ""));

					Boolean scenarioPass = true;

					// Get the before steps
					for (Before beforeStep : element.getBefore()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setStatus(beforeStep.getResult().getStatus());
						stepVM.setErrorMessage(beforeStep.getResult().getError_message());
						if (!stepVM.getStatus().equals("passed")) {
							scenarioPass = false; // if any before step fails, the scenario fails
							featurePass = false; // if any before step fails, the feature fails
						}
						scenarioVM.getBeforeSteps().add(stepVM);
					}

					// Get the steps
					for (Step step : element.getSteps()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setName(step.getName().trim().replaceAll("\\s{2,}", ""));
						stepVM.setKeyword(step.getKeyword().trim().replaceAll("\\s{2,}", ""));
						stepVM.setStatus(step.getResult().getStatus());
						stepVM.setErrorMessage(step.getResult().getError_message());
						if (!stepVM.getStatus().equals("passed")) {
							scenarioPass = false; // if any step fails, the scenario fails
							featurePass = false; // if any step fails, the feature fails
						}
						scenarioVM.getSteps().add(stepVM);
					}

					// Get the after steps
					for (After afterStep : element.getAfter()) {
						StepViewModel stepVM = new StepViewModel();
						stepVM.setStatus(afterStep.getResult().getStatus());
						stepVM.setErrorMessage(afterStep.getResult().getError_message());
						if (!stepVM.getStatus().equals("passed")) {
							scenarioPass = false; // if any after step fails, the scenario fails
							featurePass = false; // if any after step fails, the feature fails
						}
						scenarioVM.getAfterSteps().add(stepVM);
					}

					if (scenarioPass) {
						scenarioVM.setStatus("passed");
					} else {
						scenarioVM.setStatus("failed");
					}
					featureVM.getScenarios().add(scenarioVM);
				}
			}

			if (featurePass) {
				featureVM.setStatus("passed");
			} else {
				featureVM.setStatus("failed");
			}
			wordReportViewModel.getFeatures().add(featureVM);
		}
	}

}
