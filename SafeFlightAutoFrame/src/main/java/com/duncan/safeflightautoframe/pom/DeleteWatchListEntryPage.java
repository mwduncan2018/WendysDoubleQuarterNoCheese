package com.duncan.safeflightautoframe.pom;

import org.openqa.selenium.By;

import com.duncan.safeflightautoframe.pom.Driver;

public class DeleteWatchListEntryPage {

	public static boolean verifyIsAt() throws Exception {
		String actualPageName = Driver.instance.findElement(By.id("pageName")).getText();
		if (actualPageName.equals("Delete Watch List Entry Page")) {
			return true;
		}
		return false;		
	}

	public static void deleteEntry() throws Exception {
		Driver.instance.findElement(By.id("btnDelete")).click();
	}

}
