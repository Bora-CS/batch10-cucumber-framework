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
      | error           | false               |
    Then user sees a newly added [Experience] on Dashboard page

  @ui @hui
  Scenario Outline: Negative Test
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Experience] button
    And user enters a new [Experience] data
      | company         | <company>           |
      | job title       | <title>             |
      | location        | Manassas, Virginia  |
      | from            | <from>              |
      | to              | 11/11/2008          |
      | current         | false               |
      | job description | Payment collection. |
      | error           | true                |
    Then user sees a list of error messages of [Experience]
      | job title | Title is required     |
      | company   | Company is required   |
      | from      | From date is required |

    Examples: 
      | company      | title      | from       |
      |              |            |            |
      |              | Test Title | 2002-02-02 |
      | Test Company |            | 2002-02-02 |
      | Test Company | Test Title |            |
      |              |            | 2002-02-02 |
      |              | Test Title |            |
      | Test Company |            |            |
