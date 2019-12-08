package com.duncan.safeflightautoframe.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.duncan.safeflightautoframe.pom.DeleteWatchListEntryPage;
import com.duncan.safeflightautoframe.pom.Driver;

public class WatchListPage extends BasePage {

	static By TITLE = By.id("pageName");
	
	public static void goTo() {
		Driver.instance.navigate().to(URL + "WatchList");
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Create Watch List Entry")) {
			return true;
		}
		return false;
	}

	public static void deleteUserFromWatchList(String lastName, String firstName) throws Exception {
		goTo();
		List<WebElement> bountyList = Driver.instance.findElements(By.id("bounty"));
		List<WebElement> firstNameList = Driver.instance.findElements(By.id("firstName"));
		List<WebElement> lastNameList = Driver.instance.findElements(By.id("lastName"));
		List<WebElement> deleteList = Driver.instance.findElements(By.linkText("Delete"));

		int index = 0;
		for (WebElement firstNameElement : firstNameList) {
			if (firstNameElement.getText().equals(firstName)) {
				if (lastNameList.get(index).getText().equals(lastName)) {
					deleteList.get(index).click();
					DeleteWatchListEntryPage.deleteEntry();
				}
			}
			index++;
		}
	}

}
