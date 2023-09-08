@UI @Ardal
Feature: Add Experience UI Flow

  @UI @Ardal
  Scenario: Add Experience Happy Path
    Given user Ardal is logged in
      | email    | ardal002713@gamil.com |
      | password | ardal123              |
    When user clicks on [Add Experience] button
    Then user should be on the add experience page
    And user enters [Experience] content
      | company     | SalesForce |
      | title       | HR         |
      | location    | Vancouver  |
      | from        | 01/02/2020 |
      | to          | 01/02/2021 |
      | current     | false      |
      | description | I like it  |
    Then user should be on the DashBoard page

  @UI @Ardal
  Scenario Outline: Add Experience Unhappy Path
    Given user Ardal is logged in
      | email    | ardal002713@gamil.com |
      | password | Ardal123              |
    When user clicks on [Add Experience] button
    Then user should be on the add experience page
    And user enters [Experience] content 
      | company     | <company>     |
      | title       | <title>       |
      | location    | <location>    |
      | from        | <from>        |
      | to          | <to>          |
      | current     | <current>     |
      | description | <description> |
    Then user should see errors pop up on screen
      | error | <error> |

    Examples: 
      | company    | title | location  | from       | current | to         | description | error                                                       |
      |            |       | Vancouver |            | false   | 03/03/2022 | I like it.  | Company is required,Title is required,From date is required |
      |            |       | Vancouver | 01/02/2020 | false   | 03/03/2022 | I like it.  | Company is required,Title is required                       |
      |            | HR    | Vancouver | 01/02/2020 | false   | 03/03/2022 | I like it.  | Company is required                                         |
      | SalesForce |       | Vancouver | 01/02/2020 | false   | 03/03/2022 | I like it.  | Title is required                                           |
