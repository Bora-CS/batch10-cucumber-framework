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
      | from date       | 06/06/2006          |
      | to date         | 11/11/2008          |
      | current         | false               |
      | job description | Payment collection. |
      | error           |                     |
    Then user sees a newly added [Experience] on Dashboard page

  @ui @hui
  Scenario: Add an experience and then delete the experience
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Experience] button
    And user enters a new [Experience] data
      | company         | Walmart             |
      | job title       | Cashier             |
      | location        | Manassas, Virginia  |
      | from date       | 06/06/2006          |
      | to date         | 11/11/2008          |
      | current         | false               |
      | job description | Payment collection. |
      | error           |                     |
    Then user sees a newly added [Experience] on Dashboard page
    And user deletes the newly added [Experience] on Dashboard page

  @ui @hui
  Scenario Outline: Negative Test
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Experience] button
    And user enters a new [Experience] data
      | company         | <company>           |
      | job title       | <job title>         |
      | location        | Manassas, Virginia  |
      | from date       | <from date>         |
      | to date         | 11/11/2008          |
      | current         | false               |
      | job description | Payment collection. |
      | error           | <error>             |
    Then user sees a list of error messages of [Experience]

    Examples: 
      | company      | job title  | from date  | error                                                       |
      |              |            |            | Company is required,Title is required,From date is required |
      |              | Test Title | 02/02/2002 | Company is required                                         |
      | Test Company |            | 02/02/2002 | Title is required                                           |
      | Test Company | Test Title |            | From date is required                                       |
      |              |            | 02/02/2002 | Company is required,Title is required                       |
      |              | Test Title |            | Company is required,From date is required                   |
      | Test Company |            |            | Title is required,From date is required                     |
