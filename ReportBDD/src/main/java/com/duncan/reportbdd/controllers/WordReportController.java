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

	private WordReportViewModel wordReportViewModel;

	@Override
	public void generateReport() {
		unmarshalJson();
		initializeViewModel();
		new WordReportView(wordReportViewModel, writePath);
	}

	private void initializeViewModel() {
		
		//return scenarios.stream().map(x -> x.getStatus()).collect(Collectors.summingInt(Integer::intValue));
		//return features.stream().map(x -> x.getNumTestsPassed()).collect(Collectors.summingInt(Integer::intValue));
		//return features.stream().map(x -> x.getNumTestsFailed()).collect(Collectors.summingInt(Integer::intValue));
		
		wordReportViewModel = new WordReportViewModel();

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
						stepVM.setStatus(step.getResult().getStatus());
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
						stepVM.setStatus(before.getResult().getStatus());
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
						stepVM.setStatus(step.getResult().getStatus());						
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
						stepVM.setStatus(after.getResult().getStatus());
						afterSteps.add(stepVM);						
					}
					scenarioVM.setAfterSteps(afterSteps);

				}
				featureVM.getScenarios().add(scenarioVM);
			}
			wordReportViewModel.getFeatures().add(featureVM);
		}
	}

}
