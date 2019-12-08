package com.duncan.safeflightautoframe.pom;

import org.openqa.selenium.By;

import com.duncan.safeflightautoframe.pom.Driver;

public class DeleteFlightPage extends BasePage {

	public static boolean verifyIsAt() throws Exception {
		String actualPageName = Driver.instance.findElement(By.id("pageName")).getText();
		if (actualPageName.equals("Delete Flight")) {
			return true;
		}
		return false;		
	}
	
	public static void deleteFlight() throws Exception {
		Driver.instance.findElement(By.id("btnDelete")).click();
	}

}
