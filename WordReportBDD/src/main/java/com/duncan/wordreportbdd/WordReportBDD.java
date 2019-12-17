package com.duncan.wordreportbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.duncan.wordreportbdd.cucumberjsonpojo.After;
import com.duncan.wordreportbdd.cucumberjsonpojo.Before;
import com.duncan.wordreportbdd.cucumberjsonpojo.CucumberJsonPojo;
import com.duncan.wordreportbdd.cucumberjsonpojo.Element;
import com.duncan.wordreportbdd.cucumberjsonpojo.Step;
import com.duncan.wordreportbdd.utilities.ColorToRGB;
import com.duncan.wordreportbdd.viewmodels.BackgroundViewModel;
import com.duncan.wordreportbdd.viewmodels.BeforeViewModel;
import com.duncan.wordreportbdd.viewmodels.FeatureViewModel;
import com.duncan.wordreportbdd.viewmodels.ScenarioViewModel;
import com.duncan.wordreportbdd.viewmodels.StepViewModel;
import com.duncan.wordreportbdd.viewmodels.WordReportViewModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WordReportBDD {

	private String cucumberJsonPath;
	private String writePath;
	private CucumberJsonPojo[] root;
	private WordReportViewModel wordReportViewModel;

	public String getCucumberJsonPath() {
		return cucumberJsonPath;
	}

	public void setCucumberJsonPath(String cucumberJsonPath) {
		this.cucumberJsonPath = cucumberJsonPath;
	}

	public String getWritePath() {
		return writePath;
	}

	public void setWritePath(String writePath) {
		this.writePath = writePath;
	}

	private void unmarshalCucumberJson() {
		// Using Jackson to unmarshal the Cucumber JSON
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			File file = new File(cucumberJsonPath);
			root = objectMapper.readValue(file, CucumberJsonPojo[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This is the Controller
	 */
	private void initializeWordReportViewModel() {

		/*
		 * This is the View Model
		 */
		wordReportViewModel = new WordReportViewModel();

		for (CucumberJsonPojo feature : root) {
			FeatureViewModel featureVM = new FeatureViewModel();
			featureVM.setDescription(feature.getDescription().trim().replaceAll("\\R", " ").replaceAll("\\s{2,}", ""));
			featureVM.setName(feature.getName().trim().replaceAll("\\s{2,}", ""));

			Boolean featurePass = true;

			// Get the backgrounds
			for (Element element : feature.getElements()) {
				if (element.getType().equals("background")) {
					BackgroundViewModel backgroundVM = new BackgroundViewModel();

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

	public void generateReport() {
		String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		String reportDate = LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

		unmarshalCucumberJson();
		initializeWordReportViewModel();
		try {
			// Using Apache POI to create the word report
			XWPFDocument doc = new XWPFDocument();
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
			run.setText(LOREM_IPSUM);
			run.addCarriageReturn();

			// Test Results Heading
			XWPFParagraph testResultsHeading = doc.createParagraph();
			testResultsHeading.setAlignment(ParagraphAlignment.LEFT);
			run = testResultsHeading.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText("Test Results");
			run.addCarriageReturn();

			for (FeatureViewModel featureVM : wordReportViewModel.getFeatures()) {

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

			FileOutputStream out = new FileOutputStream(new File(writePath));
			doc.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
