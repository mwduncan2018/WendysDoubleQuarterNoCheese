package com.duncan.wordreportbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.duncan.wordreportbdd.cucumberjsonpojo.CucumberJsonPojo;
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

	private void initializeWordReportViewModel() {
		wordReportViewModel = new WordReportViewModel();
		
		for (CucumberJsonPojo feature : root) {
			
		}
		
		// if the background of a feature fails, the entire feature fails
		
		
		
		// if any step of a scenario fails, the entire scenario fails

	}

	public void generateReport() {
		unmarshalCucumberJson();
		initializeWordReportViewModel();
		try {
			// Using Apache POI to create the word report
			XWPFDocument doc = new XWPFDocument();
			FileOutputStream out = new FileOutputStream(new File(writePath));
			XWPFParagraph par = doc.createParagraph();
			XWPFRun run = par.createRun();
			run.setText("Testing");

			doc.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
