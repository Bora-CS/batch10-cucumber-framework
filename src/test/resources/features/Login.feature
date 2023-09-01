Feature: Login

  @ui @boratech
  Scenario: Login Postive Test
    Given user is on the BoraTech homepage
    When user navigates to the Login page
    And user enters email - "hui-pretender@outlook.com" and password - "Hui123456" then click the Login button
    Then user should be on the Dashboard page

  @api
  Scenario: Login Positive Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
