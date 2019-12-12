package com.duncan.safeflightbdd.stepdefs;

import static org.junit.Assert.*;

import org.junit.Test;

import com.duncan.safeflightautoframe.pom.DestinationsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class DestinationsStepDef {

	@Given("I navigate to the destinations page")
	public void I_navigate_to_the_destinations_page() {
		DestinationsPage.goTo();
	}

	@When("New York is selected")
	public void New_York_is_selected() {
		DestinationsPage.goToNewYorkCarouselImage();
	}

	@When("London is selected")
	public void London_is_selected() {
		DestinationsPage.goToLondonCarouselImage();
	}

	@When("Denver is selected")
	public void Denver_is_selected() {
		DestinationsPage.goToDenverCarouselImage();
	}

	@Then("a destination image is displayed")
	public void a_destination_image_is_displayed() {
		assertTrue(DestinationsPage.verifyADestinationImageIsDisplayed());
	}
	@Then("an image advertising New York is displayed")
	public void an_image_advertising_New_York_is_displayed() {
		assertTrue(DestinationsPage.verifyNewYorkCarouselImage());
	}

	@Then("an image advertising Denver is displayed")
	public void an_image_advertising_Denver_is_displayed() {
		assertTrue(DestinationsPage.verifyDenverCarouselImage());
	}

	@Then("an image advertising London is displayed")
	public void an_image_advertising_London_is_displayed() {
		assertTrue(DestinationsPage.verifyLondonCarouselImage());
	}
}
