@DestinationsFeature
Feature: Destinations Feature
  To increase sales, advertise popular destinations with images.

  Background:
    Given I navigate to the destinations page

  @smoke
  Scenario: Smoke test for the image canvas
    Then a destination image is displayed
  
  Scenario: New York advertisement
    When New York is selected
    Then an image advertising New York is displayed

  Scenario: London advertisement
    When London is selected
    Then an image advertising London is displayed

  Scenario: Denver advertisement
    When Denver is selected
    Then an image advertising Denver is displayed
	
  Scenario: Forced Failure
    When New York is selected
	Then an image advertising Denver is displayed