package com.duncan.safeflightautoframe.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	public static WebDriver instance = null;
	public static String GECKODRIVER_PATH = "D:\\Development Tools\\Selenium Drivers\\geckodriver-v0.24.0-win64\\geckodriver.exe";  
	
	public static void initialize() {
		System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);
		
		if (instance == null) {
			instance = new FirefoxDriver();
		}
		instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}
	
	public static void close() {
		instance.quit();
		instance = null;
	}
}
