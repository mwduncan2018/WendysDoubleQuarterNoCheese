package com.duncan.wordreportbdd.cucumberjsonpojo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CucumberJsonPojoTests {
	private String EXAMPLE_JSON_FOLDER = "C:\\dev\\Java\\WendysDoubleQuarterNoCheese\\WordReportBDD\\src\\test\\resources\\ExampleJSONs\\";

	@Test
	void test05() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(EXAMPLE_JSON_FOLDER + "cucumber.json");

		CucumberJsonPojo[] root = objectMapper.readValue(file, CucumberJsonPojo[].class);

		System.out.println("=======================================================");
		System.out.println("Test Cucumber JSON");
		for (CucumberJsonPojo r : root) {
			System.out.println("  description = " + r.getDescription());
		}
	}

	@Test
	void test04() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(EXAMPLE_JSON_FOLDER + "example4.json");

		CucumberJsonPojo[] root = objectMapper.readValue(file, CucumberJsonPojo[].class);

		System.out.println("=======================================================");
		System.out.println("Test 04");
		for (CucumberJsonPojo r : root) {
			System.out.println("  description = " + r.getDescription());
		}
	}

	@Test
	void test03() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(EXAMPLE_JSON_FOLDER + "example3.json");

		CucumberJsonPojo[] root = objectMapper.readValue(file, CucumberJsonPojo[].class);

		System.out.println("=======================================================");
		System.out.println("Test 03");
		for (CucumberJsonPojo r : root) {
			System.out.println("  description = " + r.getElements()[0].getDescription());
		}
	}

	@Test
	void test02() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(EXAMPLE_JSON_FOLDER + "example2.json");

		CucumberJsonPojo root = objectMapper.readValue(file, CucumberJsonPojo.class);

		System.out.println("=======================================================");
		System.out.println("Test 02");
		System.out.println("  line = " + root.getLine());
		System.out.println("  elements -> description = " + root.getElements()[0].getDescription());
	}

	@Test
	void test01() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(EXAMPLE_JSON_FOLDER + "example1.json");

		CucumberJsonPojo root = objectMapper.readValue(file, CucumberJsonPojo.class);

		System.out.println("=======================================================");
		System.out.println("Test 01");
		System.out.println("  line = " + root.getLine());
	}

}
