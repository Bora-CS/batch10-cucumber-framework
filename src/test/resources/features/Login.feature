Feature: Login

  @UI
  Scenario: Happy Path
    Given user is on the boratech homepage
    When user navigates to the Login page
    And user enters the username - "muradil.erkin@boratechschool.com" and password - "Boratech" and submit
    Then user should be on the Dashboard page
