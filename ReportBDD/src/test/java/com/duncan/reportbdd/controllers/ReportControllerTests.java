package com.duncan.reportbdd.controllers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.duncan.reportbdd.controllers.WordReportController;

class ReportControllerTests {
	String CUCUMBER_JSON_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\SafeFlightBDD\\target\\cucumber.json";
	String WORD_DOCX_PATH = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\ReportBDD\\target\\cucumber.docx";

	@Test
	void test01() {
		new WordReportController()
			.setCucumberJsonPath(CUCUMBER_JSON_PATH)
			.setWritePath(WORD_DOCX_PATH)
			.generateReport();
		
	}
	
	@Test
	void test02() {
		new ConsoleReportController()
			.setCucumberJsonPath(CUCUMBER_JSON_PATH)
			.setWritePath(WORD_DOCX_PATH)
			.generateReport();
		
	}
	
	

}
