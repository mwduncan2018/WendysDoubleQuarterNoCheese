package com.duncan.safeflightautoframe.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.duncan.safeflightautoframe.pom.DeleteFlightPage;
import com.duncan.safeflightautoframe.pom.Driver;
import com.duncan.safeflightautoframe.testmodels.Flight;

public class FlightsPage extends BasePage {

	static By TITLE = By.id("pageName");

	private static List<WebElement> watchListColumn;
	private static List<WebElement> firstNameColumn;
	private static List<WebElement> lastNameColumn;
	private static List<WebElement> departureAirportColumn;
	private static List<WebElement> departureTimeColumn;
	private static List<WebElement> arrivalAirportColumn;
	private static List<WebElement> arrivalTimeColumn;
	private static List<WebElement> editColumn;
	private static List<WebElement> detailsColumn;
	private static List<WebElement> deleteColumn;

	private static void refreshTable() {
		watchListColumn = Driver.instance.findElements(By.cssSelector("#watchList > input"));
		firstNameColumn = Driver.instance.findElements(By.id("firstName"));
		lastNameColumn = Driver.instance.findElements(By.id("lastName"));
		departureAirportColumn = Driver.instance.findElements(By.id("departureAirport"));
		departureTimeColumn = Driver.instance.findElements(By.id("departureTime"));
		arrivalAirportColumn = Driver.instance.findElements(By.id("arrivalAirport"));
		arrivalTimeColumn = Driver.instance.findElements(By.id("arrivalTime"));
		editColumn = Driver.instance.findElements(By.linkText("Edit"));
		detailsColumn = Driver.instance.findElements(By.linkText("Details"));
		deleteColumn = Driver.instance.findElements(By.linkText("Delete"));
	}

	private static Integer indexOf(String lastName, String firstName) {
		int index = 0;
		for (WebElement firstNameElement : firstNameColumn) {
			String temp = firstNameElement.getText();
			if (firstNameElement.getText().equals(firstName)) {
				String other = lastNameColumn.get(index).getText();
				if (lastNameColumn.get(index).getText().equals(lastName)) {
					return index;
				}
			}
			index++;
		}
		return -1;
	}

	public static void goTo() {
		Driver.instance.navigate().to(URL + "Flights");
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Flight List")) {
			return true;
		}
		return false;
	}

	public static boolean verifyFlightIsDisplayed(Flight flight) {
		goTo();
		refreshTable();
		int index = indexOf(flight.getLastName(), flight.getFirstName());
		if (index >= 0) {
			if (firstNameColumn.get(index).getText().equals(flight.getFirstName())
					&& lastNameColumn.get(index).getText().equals(flight.getLastName())
					&& departureAirportColumn.get(index).getText().equals(flight.getDepartureAirport())
					&& departureTimeColumn.get(index).getText().equals(flight.getDepartureTime())
					&& arrivalAirportColumn.get(index).getText().equals(flight.getArrivalAirport())
					&& arrivalTimeColumn.get(index).getText().equals(flight.getArrivalTime())) {
				return true;
			}
		}
		return false;
	}
	
	public static void deleteFlight(String lastName, String firstName) throws Exception {
		goTo();
		refreshTable();
		int index = indexOf(lastName, firstName);
		if (index >= 0) {
			deleteColumn.get(index).click();
			DeleteFlightPage.deleteFlight();
		}
	}

	public static boolean verifyWatchListColumnIsCheckedFor(String lastName, String firstName) {
		goTo();
		refreshTable();
		int index = indexOf(lastName, firstName);
		if (index >= 0) {
			if (watchListColumn.get(index).isSelected()) {
				return true;
			}
		}
		return false;
	}

}
