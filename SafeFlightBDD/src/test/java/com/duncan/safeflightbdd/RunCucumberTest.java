package com.duncan.safeflightbdd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.duncan.safeflightautoframe.pom.Driver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/Destination", "json:target/cucumber.json"},
		// "html:target/Destination" puts the HTML report in 'target/Destination'
		// "json:target/cucumber.json" puts the JSON report in 'target/cucumber.json'
		monochrome = true,
		snippets = SnippetType.UNDERSCORE,
		// features = "src/test/resources"
		features = "classpath:features")
public class RunCucumberTest {


	@BeforeClass
	public static void globalSetup() {
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("Welcome to the test automation of the 'Safe Duncan Flight' C# MVC 5 web application");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
	}
	
	@AfterClass
	public static void globalTearDown() throws InterruptedException {
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("Closing browser");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		System.out.println("************************************************************************************");
		
		for (int i = 0; i < 60; i++) {
			Thread.sleep(1000);
			System.out.println("        waiting... " + ((Integer)i).toString());
		}
		Driver.close();
	}
	
}
