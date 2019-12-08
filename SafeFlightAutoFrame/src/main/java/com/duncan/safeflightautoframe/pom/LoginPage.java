package com.duncan.safeflightautoframe.pom;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

	static By TITLE = By.id("pageName");
	
	static By INPUT_USERNAME = By.id("Username");
	static By INPUT_PASSWORD = By.id("Password");
	static By BTN_LOGIN = By.id("btnLogin");

	static By VALIDATION_MESSAGE_USERNAME = By.id("Username-error");
	static By VALIDATION_MESSAGE_PASSWORD = By.id("Password-error");
	static By VALIDATION_SUMMARY = By.id("viewBagValidationSummary");

	public static void goTo() {
		Driver.instance.navigate().to(URL + "Login");
	}

	public static void login(String username, String password) {
		Driver.instance.findElement(INPUT_USERNAME).sendKeys(username);
		Driver.instance.findElement(INPUT_PASSWORD).sendKeys(password);
		Driver.instance.findElement(BTN_LOGIN).click();
	}

	public static boolean verifyIsAt() {
		if (Driver.instance.findElement(TITLE).getText().equals("Login")) {
			return true;
		}
		return false;
	}
	
	public static boolean verifyValidationUsernameIsRequired() {
		String message = Driver.instance.findElement(VALIDATION_MESSAGE_USERNAME).getText();
		if (message.equals("The Username field is required.")) {
			return true;
		}
		return false;
	}

	public static boolean verifyValidationPasswordIsRequired() {
		String message = Driver.instance.findElement(VALIDATION_MESSAGE_PASSWORD).getText();
		if (message.equals("The Password field is required.")) {
			return true;
		}
		return false;
	}

	public static boolean verifyValidationUsernamePasswordComboIsInvalid() {
		String message = Driver.instance.findElement(VALIDATION_SUMMARY).getText();
		if (message.equals("The username/password combination is invalid.")) {
			return true;
		}
		return false;
	}

}
