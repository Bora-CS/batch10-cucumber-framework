Feature: Add Experience

  @API
  Scenario: API - Add Experience Happy Path
    Given [API] user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    Then [API] users should be able to add a new experience
      | company     | BoraTech                 |
      | title       | Software Engineer        |
      | location    | Annandale                |
      | from        | 2023-08-16               |
      | to          |                          |
      | current     | true                     |
      | description | This is a new experience |

  @UI @Parallel
  Scenario: UI - Add Experience Happy Path
    Given user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When user tries to add a new experience
      | company     | BoraTech                 |
      | title       | Software Engineer        |
      | location    | Annandale                |
      | from        | 08-16-2023               |
      | to          |                          |
      | current     | true                     |
      | description | This is a new experience |
    Then user should see a success alert that says [Experience Added]

  @UI @DataDriven
  Scenario Outline: UI - Add Experience Unhappy Path
    Given user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When user tries to add a new experience
      | company     | <company>     |
      | title       | <title>       |
      | location    | <location>    |
      | from        | <from>        |
      | to          | <to>          |
      | current     | <current>     |
      | description | <description> |
    Then user should see some add experience related validation errors
      | errors | <errors> |

    Examples: 
      | company  | title    | location  | from       | to | current | description              | errors                                                        |
      | BoraTech |          | Annandale | 08-16-2023 |    | true    | This is a new experience | Title is required                                             |
      |          | Engineer | Annandale | 08-16-2023 |    | true    | This is a new experience | Company is required                                           |
      | BoraTech | Engineer | Annandale |            |    | true    | This is a new experience | From date is required                                         |
      |          |          |           |            |    | true    |                          | Title is required, Company is required, From date is required |
      | BoraTech |          | Annandale |            |    | true    | This is a new experience | Title is required, From date is required                      |
