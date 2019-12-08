package com.duncan.safeflightautoframe.pom;

import org.openqa.selenium.By;

public class CreateWatchListEntryPage extends BasePage {

	static By TITLE = By.id("pageName");
	
	static By INPUT_BOUNTY = By.id("Bounty");
	static By INPUT_FIRST_NAME = By.id("FirstName");
	static By INPUT_LAST_NAME = By.id("LastName");
	static By BTN_CREATE = By.id("btnCreate");
	
	public static void goTo() {
		Driver.instance.navigate().to(URL + "WatchList/Create");
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Create Watch List Entry")) {
			return true;
		}
		return false;
	}

	public static void createWatchListEntry(String firstName, String lastName, String bounty) {
		goTo();
		Driver.instance.findElement(INPUT_BOUNTY).sendKeys(bounty);
		Driver.instance.findElement(INPUT_FIRST_NAME).sendKeys(firstName);
		Driver.instance.findElement(INPUT_LAST_NAME).sendKeys(lastName);
		Driver.instance.findElement(BTN_CREATE).click();
	}

}
