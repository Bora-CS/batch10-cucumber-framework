Feature: Add Experience

  @ui
  Scenario: Positive Test
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Experience] button
    And user enters a new [Experience] data
      | company         | Walmart             |
      | job title       | Cashier             |
      | location        | Manassas, Virginia  |
      | from            | 06/06/2006          |
      | to              | 11/11/2008          |
      | current         | false               |
      | job description | Payment collection. |
    Then user sees a newly added [Experience] on Dashboard page
