Feature: ui_Experience

  @ui
  Scenario: [HappyPath] User adds a new experience successfully
    Given user is logged in
      | username | anth0ny@gmail.com |
      | password | PaSsWoRd123!      |
    When user navigates to the Experience page
    And user tries to add a new experience
      | company     | ToraBech             |
      | title       | Noob Automator       |
      | location    | Borannandale         |
      | from        | 01/01/2001           |
      | to          |                      |
      | current     | true                 |
      | description | git push from master |
    Then user should be able to see Experience Added alert showing

  @ui
  Scenario Outline: [Unhappy Path] User receives error messages when adding experience
    Given user is logged in
      | username | anth0ny@gmail.com |
      | password | PaSsWoRd123!      |
    When user navigates to the Experience page
    And user tries to add a new experience
      | company     | <company>     |
      | title       | <title>       |
      | location    | <location>    |
      | from        | <from>        |
      | to          | <to>          |
      | current     | <current>     |
      | description | <description> |
    Then user should receive error messages
      | errors | <errors> |

    Examples: 
      | company     | title       | location   | from       | to | current | description                     | errors                                                   |
      | ToraBech    |             | some where | 2023-08-01 |    | true    | sysout                          | Title is required                                        |
      |             | Noob tester | No where   | 2023-08-01 |    | true    | prettyformat                    | Company is required                                      |
      |             |             | home       | 2023-08-01 |    | true    | git merge your branch to master | Company is required, Title is required                   |
      |             |             | bathroom   |            |    | true    | description                     | Company is required, Title is required, From is required |
      |             | tester Noob | toilet     |            |    | true    | description                     | Company is required, From is required                    |
      | Capital two |             | car        |            |    | true    | description                     | Title is required, From is required                      |
