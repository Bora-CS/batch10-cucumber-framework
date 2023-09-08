@UI
Feature: Add experiences

  @UI
  Scenario: POM - Add Experience Happy Path
    Given user is logged in with email - "w.lydia.liu@gmail.com" and password - "Liu123456"
    When user is on the dashboard page, user click Add Experience button
    And user should be able to add new experience
      | title       | QA tester                 |
      | company     | BoraTech                  |
      | location    | Annandale                 |
      | from        | 2023-08-16                |
      | current     | true                      |
      | to          |                           |
      | description | This is a new experience. |
    Then user should be able to see Experience Added alert showing

  @UI
  Scenario Outline: Add Experience Unhappy Path
    Given user is logged in
      | username | w.lydia.liu@gmail.com |
      | password | Liu123456             |
    When user is on the dashboard page, user click Add Experience button
    When user tries to add new experiences
      | title       | <title>       |
      | company     | <company>     |
      | location    | <location>    |
      | from        | <from>        |
      | current     | <current>     |
      | to          | <to>          |
      | description | <description> |
    Then user should receive error messages
      | errors | <errors> |

    Examples: 
      | title                       | company  | location  | from       | to | current | description        | errors                                 |
      |                             | BoraTech | annandale | 2023-08-01 |    | true    | automation testing | Title is required                      |
      | automation testing engineer |          | annandale | 2023-08-01 |    | true    | automation testing | Company is required                    |
      |                             |          | annandale | 2023-08-01 |    | true    | automation testing | Company is required ,Title is required |
