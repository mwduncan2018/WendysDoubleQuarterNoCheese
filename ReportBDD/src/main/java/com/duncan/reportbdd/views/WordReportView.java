package com.duncan.reportbdd.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.duncan.reportbdd.utilities.ColorToRGB;
import com.duncan.reportbdd.viewmodels.wordreport.FeatureViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ScenarioViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.StepViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.WordReportViewModel;

public class WordReportView {
	private String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	private String reportDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

	public WordReportView(WordReportViewModel vm, String writePath) {

		// Using Apache POI to create the word report
		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(new File(writePath));) {
			XWPFRun run;

			// Heading
			XWPFParagraph heading = doc.createParagraph();
			heading.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setFontSize(20);
			run.setBold(true);
			run.setText("Official Test Report");
			run.addCarriageReturn();

			// Date of Report
			XWPFParagraph dateTime = doc.createParagraph();
			dateTime.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setItalic(true);
			run.setText(reportDate);
			run.addCarriageReturn();

			// Summary Heading
			XWPFParagraph summaryHeading = doc.createParagraph();
			summaryHeading.setAlignment(ParagraphAlignment.LEFT);
			run = summaryHeading.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText("Summary");
			run.addCarriageReturn();

			// Summary Paragraph
			XWPFParagraph summaryParagraph = doc.createParagraph();
			summaryParagraph.setAlignment(ParagraphAlignment.LEFT);
			run = summaryParagraph.createRun();
			run.setText("Tests Passed: " + vm.getNumTestsPassed());
			run.addCarriageReturn();

			// Test Results Heading
			XWPFParagraph testResultsHeading = doc.createParagraph();
			testResultsHeading.setAlignment(ParagraphAlignment.LEFT);
			run = testResultsHeading.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText("Test Results");
			run.addCarriageReturn();

			for (FeatureViewModel featureVM : vm.getFeatures()) {

				// Display Feature Info
				XWPFParagraph featureInfoParagraph = doc.createParagraph();
				featureInfoParagraph.setAlignment(ParagraphAlignment.LEFT);
				run = featureInfoParagraph.createRun();
				run.setBold(true);
				run.setText("Feature: " + featureVM.getName());
				run.addCarriageReturn();
				run = featureInfoParagraph.createRun();
				run.setText("Description: " + featureVM.getDescription());
				run.addCarriageReturn();

				for (ScenarioViewModel scenarioVM : featureVM.getScenarios()) {

					// Display Scenario
					XWPFParagraph scenarioParagraph = doc.createParagraph();
					scenarioParagraph.setAlignment(ParagraphAlignment.LEFT);
					run = scenarioParagraph.createRun();
					run.setText("Scenario (aka Test): " + scenarioVM.getName());
					run.addCarriageReturn();
					if (scenarioVM.getStatus().equals("failed")) {
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setBold(true);
						run.setText("Status: " + scenarioVM.getStatus());
						run.addCarriageReturn();
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setBold(false);
						run.setFontSize(8);
						for (StepViewModel stepVM : scenarioVM.getBeforeSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText("Error Message: " + stepVM.getErrorMessage());
								run.addCarriageReturn();
							}
						}
						for (StepViewModel stepVM : scenarioVM.getSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText("Error Message: " + stepVM.getErrorMessage());
								run.addCarriageReturn();
							}
						}
						for (StepViewModel stepVM : scenarioVM.getAfterSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText("Error Message: " + stepVM.getErrorMessage());
								run.addCarriageReturn();
							}
						}

					} else {
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("DARKGREEN"));
						run.setBold(true);
						run.setText("Status: " + scenarioVM.getStatus());
					}

					run.addCarriageReturn();
				}

			}

			doc.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
