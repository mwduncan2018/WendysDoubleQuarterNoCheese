package com.duncan.reportbdd.views;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import com.duncan.reportbdd.utilities.ColorToRGB;
import com.duncan.reportbdd.viewmodels.wordreport.FeatureViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ScenarioViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.StepViewModel;
import com.duncan.reportbdd.viewmodels.wordreport.ReportViewModel;

public class WordReportView {
	private String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	public WordReportView(ReportViewModel vm, String writePath) {

		// Using Apache POI to create the word report
		try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream(new File(writePath));) {
			XWPFRun run;

			// Heading
			XWPFParagraph heading = doc.createParagraph();
			heading.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setFontSize(20);
			run.setBold(true);
			run.setText(vm.getTitle());
			run.addCarriageReturn();

			// Date of Report
			XWPFParagraph dateTime = doc.createParagraph();
			dateTime.setAlignment(ParagraphAlignment.CENTER);
			run = heading.createRun();
			run.setItalic(true);
			run.setText(vm.getDate());
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
			run.setText(vm.getNumberFeaturesPassed().toString() + " Features Passed");
			run.addCarriageReturn();
			run.setText(vm.getNumberFeaturesFailed().toString() + " Features Failed");
			run.addCarriageReturn();
			run.setText(vm.getNumberScenariosPassed().toString() + " Scenarios Passed");
			run.addCarriageReturn();
			run.setText(vm.getNumberScenariosFailed().toString() + " Scenarios Failed");
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
				if (featureVM.getDescription().length() > 0) {
					run = featureInfoParagraph.createRun();
					run.setText("Description: " + featureVM.getDescription());
					run.addCarriageReturn();	
				}
				
				for (ScenarioViewModel scenarioVM : featureVM.getScenarios()) {

					// Display Scenario
					XWPFParagraph scenarioParagraph = doc.createParagraph();
					scenarioParagraph.setAlignment(ParagraphAlignment.LEFT);
					
					run = scenarioParagraph.createRun();
					run.setText("Scenario: " + scenarioVM.getName());
					run.addCarriageReturn();
					if (scenarioVM.getDescription().length() > 0 ) {
						run = scenarioParagraph.createRun();
						run.setText("Description: " + scenarioVM.getDescription());
						run.addCarriageReturn();							
					}
					
					// if scenario passed
					if (scenarioVM.getStatus() == true) {

						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("DARKGREEN"));
						run.setBold(true);
						run.setText("Status: " + "passed");
					}
					
					// if scenario failed
					if (scenarioVM.getStatus() == false) {
						
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setBold(true);
						run.setText("Status: " + "failed");
						run.addCarriageReturn();
						run.addCarriageReturn();
						
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setBold(true);
						run.setText("Comments: ");
						run.addCarriageReturn();
						//run.setText("____________________________________________________________________");
						run.addCarriageReturn();
						//run.setText("____________________________________________________________________");
						run.addCarriageReturn();
						
						scenarioParagraph = doc.createParagraph();
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setBold(true);
						run.setText("Stack Trace:");
						run.addCarriageReturn();
						run = scenarioParagraph.createRun();
						run.setColor(ColorToRGB.get("RED"));
						run.setFontSize(8);

						for (StepViewModel stepVM : scenarioVM.getBeforeSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText(stepVM.getErrorMessage().replace("com.duncan.safeflightbdd.stepdefs.", ""));
								run.addCarriageReturn();
							}
						}
						for (StepViewModel stepVM : scenarioVM.getSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText(stepVM.getErrorMessage().replace("com.duncan.safeflightbdd.stepdefs.", ""));
								run.addCarriageReturn();
							}
						}
						for (StepViewModel stepVM : scenarioVM.getAfterSteps()) {
							if (stepVM.getErrorMessage() != null) {
								run.setText(stepVM.getErrorMessage().replace("com.duncan.safeflightbdd.stepdefs.", ""));
								run.addCarriageReturn();
							}
						}
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
