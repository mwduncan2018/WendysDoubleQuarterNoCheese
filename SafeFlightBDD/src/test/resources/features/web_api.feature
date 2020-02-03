@WebApiFeature
Feature: Web API
  Duncan Safe Flight Web API

  @smoke
  Scenario Outline: Flight with watch list match, data driven
    Given the Web API is used to add a watch list entry with first name "<firstName>" and last name "<lastName>"
	Then verify the watch list entry with first name "<firstName>" and last name "<lastName>" has been added with the Web API

    Examples: 
      | firstName  | lastName  |
      | Carl       | Sagan     |
      | Lance      | Bittner   |
      | Rob        | Limpert   |
