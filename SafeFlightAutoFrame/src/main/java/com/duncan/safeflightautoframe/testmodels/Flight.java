package com.duncan.safeflightautoframe.testmodels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Flight {

	public static enum FLIGHT_DEFAULT {
		STANDARD
	}
	
	private String firstName;
	private String lastName;
	private String departureAirport;
	private String arrivalAirport;
	private String departureTime;
	private String arrivalTime;

	public Flight() {
	}

	public Flight(FLIGHT_DEFAULT flightDefault) {
		if (flightDefault == FLIGHT_DEFAULT.STANDARD) {
			setDepartureAirport("BWI");
			setDepartureTime("1/1/1999 6:00:00 PM");
			setArrivalAirport("LAX");
			setArrivalTime("1/1/1999 11:00:00 PM");
			setFirstName("Foo");
			setLastName("Bar");
		}
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
