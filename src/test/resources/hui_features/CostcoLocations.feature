Feature: Update Customer Preference

  @costco @retail @ui
  Scenario: Change default warehouse and delivery location
    Given user is on Costco homepage
    When user clicks on My Warehouse
    And user enters zip coode "20112" and clicks on Find
    And user chooses a store and sets it as My Warehouse
      | Manassas |
    And users clicks on Delivery Location and enters "20112"
    Then users see updated information
      | Manassas |
      |    20112 |
