package com.duncan.safeflightautoframe.testmodels;

public class WatchListEntry {

	public static enum WATCH_LIST_ENTRY_DEFAULT {
		STANDARD
	}
	
	private String firstName;
	private String lastName;
	private String bounty;

	public WatchListEntry() {
	}
	
	public WatchListEntry(WATCH_LIST_ENTRY_DEFAULT watchListEntryDefault) {
		if (watchListEntryDefault == WATCH_LIST_ENTRY_DEFAULT.STANDARD) {
			setBounty("99999");
			setFirstName("Alpha");
			setLastName("Zed");
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

	public String getBounty() {
		return bounty;
	}

	public void setBounty(String bounty) {
		this.bounty = bounty;
	}

}
