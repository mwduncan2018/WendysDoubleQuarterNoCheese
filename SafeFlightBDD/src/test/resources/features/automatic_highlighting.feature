@AutomaticHighlightingFeature
Feature: Automatic Highlighting
  For the security of airline transportation, any person on the watch list
  that books a flight should be highlighted for easy identification.

  @smoke
  Scenario: Flight with no watch list match
    Given a flight is added with a name that is not on the watch list
    Then the flight is displayed on the flight list page
    And the flight is not checked in the watch list column

  @smoke
  Scenario: Flight with watch list match
    Given a flight is added with a name that is on the watch list
    Then the flight is displayed on the flight list page
    And the flight is checked in the watch list column

  @regression
  Scenario Outline: Flight with watch list match, data driven
    Given a flight is added with first name "<firstName>" and last name "<lastName>"
    And a watch list entry is added with first name "<firstName>" and last name "<lastName>"
    Then the flight is displayed on the flight list page
    And the flight with first name "<firstName>" and last name "<lastName>" is checked in the watch list column
		
    Examples: 
      | firstName  | lastName |
      | Reinhold   | Bogner   |
      | Randall    | Smith    |
      | Jerry      | Seinfeld |
	  
  @regression
  Scenario Outline: Flight with no watch list match, data driven
    Given a flight is added with first name "<firstName>" and last name "<lastName>"
    Then the flight is displayed on the flight list page
    And the flight with first name "<firstName>" and last name "<lastName>" is not checked in the watch list column

    Examples: 
      | firstName  | lastName |
      | James      | Hetfield |
      | Kirk       | Hammett  |
      | Dave       | Mustaine |
