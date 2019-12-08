package com.duncan.safeflightautoframe.pom;

import org.openqa.selenium.By;

public class CreateFlightPage extends BasePage {

	static By TITLE = By.id("pageName");
	
	static By INPUT_DEPARTURE_AIRPORT = By.id("DepartureAirport");
	static By INPUT_ARRIVAL_AIRPORT = By.id("ArrivalAirport");
	static By INPUT_DEPARTURE_TIME = By.id("DepartureTime");
	static By INPUT_ARRIVAL_TIME = By.id("ArrivalTime");
	static By INPUT_FIRST_NAME = By.id("FirstName");
	static By INPUT_LAST_NAME = By.id("LastName");
	static By BTN_CREATE = By.id("btnCreate");
	
	public static void goTo() {
		Driver.instance.navigate().to(URL + "Flights/Create");
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Create Flight")) {
			return true;
		}
		return false;
	}

	public static void createFlight(String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, String firstName, String lastName) {
		goTo();
		Driver.instance.findElement(INPUT_DEPARTURE_AIRPORT).sendKeys(departureAirport);
		Driver.instance.findElement(INPUT_ARRIVAL_AIRPORT).sendKeys(arrivalAirport);
		Driver.instance.findElement(INPUT_DEPARTURE_TIME).sendKeys(departureTime);
		Driver.instance.findElement(INPUT_ARRIVAL_TIME).sendKeys(arrivalTime);
		Driver.instance.findElement(INPUT_FIRST_NAME).sendKeys(firstName);
		Driver.instance.findElement(INPUT_LAST_NAME).sendKeys(lastName);
		Driver.instance.findElement(BTN_CREATE).click();
	}
	
}
