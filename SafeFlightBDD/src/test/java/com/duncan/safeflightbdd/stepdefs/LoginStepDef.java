package com.duncan.safeflightbdd.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import com.duncan.safeflightautoframe.pom.Driver;
import com.duncan.safeflightautoframe.pom.FlightsPage;
import com.duncan.safeflightautoframe.pom.LoginPage;

public class LoginStepDef {

	@Given("I navigate to the login page")
	public void I_navigate_to_the_login_page() {
		LoginPage.goTo();
	}

	@When("I login with a blank username field and a blank password field")
	public void I_login_with_a_blank_username_field_and_a_blank_password_field() throws Exception {
		LoginPage.login("", "");
	}

	@When("I login with a valid username field and a valid password field")
	public void I_login_with_a_valid_username_field_and_a_valid_password_field() throws Exception {
		LoginPage.login("mduncan", "cucumber");	
	}

	@Then("a validation message stating that the username field is required is displayed")
	public void a_validation_message_stating_that_the_username_field_is_required_is_displayed() throws Exception {
		LoginPage.verifyValidationUsernameIsRequired();
	}

	@Then("a validation message stating that the password field is required is displayed")
	public void a_validation_message_stating_that_the_password_field_is_required_is_displayed() throws Exception {
		LoginPage.verifyValidationPasswordIsRequired();
	}

	@Then("a validation message stating that the username and password combination is invalid is displayed")
	public void a_validation_message_stating_that_the_username_and_password_combination_is_invalid_is_displayed()
			throws Exception {
		LoginPage.verifyValidationUsernamePasswordComboIsInvalid();
	}

	@Then("I am redirected to the flights page")
	public void I_am_redirected_to_the_flights_page() throws Exception {
		FlightsPage.verifyIsAt();
	}

	@Given("I login with username {string} and password {string}")
	public void I_login_with_username_and_password(String username, String password) throws Exception {
		LoginPage.login(username, password);
	}

}
