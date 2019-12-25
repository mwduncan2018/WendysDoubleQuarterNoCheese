@DestinationsFeature
Feature: Destinations Feature
  To increase sales, advertise popular destinations with images.

  Background:
    Given I navigate to the destinations page

  @smoke
  Scenario: Smoke test for the image canvas
    Then a destination image is displayed
  
  Scenario: New York advertisement 
    (Deliberate Failure) An advertisement for New York should be displayed. The failure occurs when the automation checks for an image of Denver also.
    When New York is selected
    Then an image advertising New York is displayed
	And an image advertising Denver is displayed

  Scenario: London advertisement
    An advertisement for London should be displayed.
    When London is selected
    Then an image advertising London is displayed

  Scenario: Denver advertisement
    An advertisement for Denver should be displayed.
    When Denver is selected
    Then an image advertising Denver is displayed
	