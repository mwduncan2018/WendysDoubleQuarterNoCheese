package com.duncan.reportbdd.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import com.duncan.reportbdd.viewmodels.wordreport.FeatureViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ScenarioViewModel;

public class ConsoleReportController extends ReportController {
	
	@Override
	public ReportController generateReport() {
		this.reportViewModel.setTitle("Official Test Report");
		this.reportViewModel.setDate(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

		this.extractData();

		this.calculateStatusOfScenarios();
		this.calculateStatusOfFeatures();

		this.calculateScenariosPassedForEachFeature();
		this.calculateScenariosFailedForEachFeature();

		this.calculateScenariosPassedForEntireTestRun();
		this.calculateScenariosFailedForEntireTestRun();

		this.calculateFeaturesPassed();
		this.calculateFeaturesFailed();
		
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("CUCUMBER CONSOLE REPORT");
		System.out.println("TITLE: " + reportViewModel.getTitle());
		System.out.println("DATE: " + reportViewModel.getDate());
		System.out.println("==================================================");
		System.out.println("==================================================");
		System.out.println("==================================================\n");

		System.out.println(reportViewModel.getNumberFeaturesPassed() + " Features Passed");
		System.out.println(reportViewModel.getNumberFeaturesFailed() + " Features Failed");
		System.out.println(reportViewModel.getNumberScenariosPassed() + " Scenarios Passed");
		System.out.println(reportViewModel.getNumberScenariosFailed() + " Scenarios Failed" + "\n");

		for (FeatureViewModel f : reportViewModel.getFeatures()) {
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

}
