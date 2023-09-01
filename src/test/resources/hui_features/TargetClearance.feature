Feature: Target Clearance

  @target @retail @ui
  Scenario: Clearance Filter and Sort
    Given user is on Target homepage
    And user clicks on Deals drop down menu
    And user clicks on Clearance option
    When user is on Clearance page
    And user clicks on Price button and then user enters "minimum" whole dollar and "maximum" whole dollar amounts
      | minimum | 10 |
      | maximum | 99 |
    And user clicks on Apply button
    And user clicks on Sort button and then clicks on Price: low to high
    And user clicks on Apply button
    Then the outcomes are validated against filter and sort conditions

  @target @retail @ui
  Scenario: Clearance Sort
    Given user is on Target homepage
    And user clicks on Categories drop down menu
    And user clicks on Clearance option and choose all
    When user is on Clearance page
    And user clicks on Sort button and then clicks on Price: high to low
    And user clicks on Apply button
    Then the outcomes are displayed
