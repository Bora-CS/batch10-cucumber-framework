Feature: Nike

  @UI @Nike_Search
  Scenario: Product Search
    Given user is on the nike homepage
    When user searches for "Air Jordan"
    Then user should see search results for "Air Jordan"
