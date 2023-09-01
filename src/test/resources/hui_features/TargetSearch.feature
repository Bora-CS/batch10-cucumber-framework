Feature: Target Search Functionality

  @target @retail @ui
  Scenario Outline: Search for products based on search term
    Given user is on Target homepage
    When user enters <search term> in search input field and clicks on submit button
    Then users sees all the products related to the <search term>

    Examples: 
      | search term    |
      | mens shoes     |
      | womens dresses |
      | star wars      |
