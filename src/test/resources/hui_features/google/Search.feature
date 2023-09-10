Feature: Search functionality

  @ui
  Scenario Outline: Search Term
    Given user is on Google home page
    When user enters "<search term>" in search box
    And user clicks on search button
    Then user sees "<search term>" related titles

    Examples: 
      | search term |
      | men's shoes |
