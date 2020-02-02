package com.duncan.reportbdd.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.duncan.reportbdd.views.WordReportView;

public class WordReportController extends ReportController {

	@Override
	public WordReportController generateReport() {
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

		new WordReportView(reportViewModel, writePath);

		return this;
	}

}
