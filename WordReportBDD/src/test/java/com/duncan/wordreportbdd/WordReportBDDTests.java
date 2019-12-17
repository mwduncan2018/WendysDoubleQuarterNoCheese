package com.duncan.wordreportbdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordReportBDDTests {
	String CUCUMBER_JSON_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\SafeFlightBDD\\target\\cucumber.json";
	String WORD_DOCX_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\WordReportBDD\\target\\cucumber.docx";
	
	@Test
	void test01() {
		// Create Word report with Apache POI
		WordReportBDD word = new WordReportBDD();
		word.setCucumberJsonPath(CUCUMBER_JSON_PATH);
		word.setWritePath(WORD_DOCX_PATH);
		word.generateReport();
	}

}
