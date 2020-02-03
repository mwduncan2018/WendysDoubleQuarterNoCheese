package com.duncan.safeflightbdd.stepdefs;

import com.duncan.safeflightautoframe.api.WatchListApi;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebApiStepDef {

	@Before(value="@WebApiFeature", order=1)
	public void beforeScenario() {
		
	}
	
	@After(value="@WebApiFeature", order=99)
	public void afterScenario() {
		WatchListApi.deleteEntry("Carl", "Sagan");
		WatchListApi.deleteEntry("Lance", "Bittner");
		WatchListApi.deleteEntry("Rob", "Limpert");
	}
	
	@Given("the Web API is used to add a watch list entry with first name {string} and last name {string}")
	public void add_watch_list_entry_with_web_api(String firstName, String lastName) {
		WatchListApi.postEntry(firstName, lastName, 0);
	}

	@Then("verify the watch list entry with first name {string} and last name {string} has been added with the Web API")
	public void verify_watch_list_entry_added_with_web_api(String firstName, String lastName) {
		WatchListApi.verifyEntryExists(firstName, lastName);
	}

	
}
