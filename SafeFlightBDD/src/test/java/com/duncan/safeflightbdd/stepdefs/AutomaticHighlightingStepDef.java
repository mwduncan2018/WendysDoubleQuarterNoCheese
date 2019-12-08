package com.duncan.safeflightbdd.stepdefs;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.duncan.safeflightautoframe.pom.CreateFlightPage;
import com.duncan.safeflightautoframe.pom.CreateWatchListEntryPage;
import com.duncan.safeflightautoframe.pom.Driver;
import com.duncan.safeflightautoframe.pom.FlightsPage;
import com.duncan.safeflightautoframe.pom.WatchListPage;
import com.duncan.safeflightautoframe.testmodels.Flight;
import com.duncan.safeflightautoframe.testmodels.WatchListEntry;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutomaticHighlightingStepDef {

	private Flight flight = null;
	private WatchListEntry watchListEntry = null;

	private void cleanupTestData() throws Exception {
		if (flight != null) {
			FlightsPage.deleteFlight(flight.getLastName(), flight.getFirstName());
		}
		if (watchListEntry != null) {
			WatchListPage.deleteUserFromWatchList(watchListEntry.getLastName(), watchListEntry.getFirstName());
		}
	}
	
	@Before(value="@AutomaticHighlightingFeature", order=1)
	public void beforeScenario() {
		flight = null;
	}

	@After(value="@AutomaticHighlightingFeature", order=99)
	public void afterScenario() throws Exception {
		cleanupTestData();
	}

	@Given("a flight is added with a name that is not on the watch list")
	public void a_flight_is_added_with_a_name_that_is_not_on_the_watch_list() throws Exception {
		flight = new Flight(Flight.FLIGHT_DEFAULT.STANDARD);
		flight.setFirstName("Ben");
		flight.setLastName("Linus");
		CreateFlightPage.createFlight(flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getFirstName(), flight.getLastName());
	}

	@Given("a flight is added with a name that is on the watch list")
	public void a_flight_is_added_with_a_name_that_is_on_the_watch_list() throws Exception {
		flight = new Flight(Flight.FLIGHT_DEFAULT.STANDARD);
		flight.setFirstName("Frederich");
		flight.setLastName("Nietzsche");
		CreateFlightPage.createFlight(flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getFirstName(), flight.getLastName());
	}

	@Given("a flight is added with first name {string} and last name {string}")
	public void a_flight_is_added_with_first_name_and_last_name(String firstName, String lastName) throws Exception {
		flight = new Flight(Flight.FLIGHT_DEFAULT.STANDARD);
		flight.setFirstName(firstName);
		flight.setLastName(lastName);
		CreateFlightPage.createFlight(flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getFirstName(), flight.getLastName());
	}

	@Given("a watch list entry is added with first name {string} and last name {string}")
	public void a_watch_list_entry_is_added_with_first_name_and_last_name(String firstName, String lastName) throws Exception {
		watchListEntry = new WatchListEntry(WatchListEntry.WATCH_LIST_ENTRY_DEFAULT.STANDARD);
		watchListEntry.setFirstName(firstName);
		watchListEntry.setLastName(lastName);
		CreateWatchListEntryPage.createWatchListEntry(watchListEntry.getFirstName(), watchListEntry.getLastName(), watchListEntry.getBounty());
	}

	@Then("the flight is displayed on the flight list page")
	public void the_flight_is_displayed_on_the_Flight_List_page() throws Exception {
		assertTrue(FlightsPage.verifyFlightIsDisplayed(flight));
	}

	@Then("the flight is not checked in the watch list column")
	public void the_flight_is_not_checked_in_the_watch_list_column() throws Exception {
		assertFalse(FlightsPage.verifyWatchListColumnIsCheckedFor(flight.getLastName(), flight.getFirstName()));
	}

	@Then("the flight is checked in the watch list column")
	public void the_flight_is_checked_in_the_watch_list_column() throws Exception {
		assertTrue(FlightsPage.verifyWatchListColumnIsCheckedFor(flight.getLastName(), flight.getFirstName()));
	}

	@Then("the flight with first name {string} and last name {string} is checked in the watch list column")
	public void the_flight_with_first_name_and_last_name_is_checked_in_the_watch_list_column(String firstName,
			String lastName) throws Exception {
		assertTrue(FlightsPage.verifyWatchListColumnIsCheckedFor(lastName, firstName));
	}

	@Then("the flight with first name {string} and last name {string} is not checked in the watch list column")
	public void the_flight_with_first_name_and_last_name_is_not_checked_in_the_watch_list_column(String firstName,
			String lastName) throws Exception {
		assertFalse(FlightsPage.verifyWatchListColumnIsCheckedFor(firstName, lastName));
	}
	
}