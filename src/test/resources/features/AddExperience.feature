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
