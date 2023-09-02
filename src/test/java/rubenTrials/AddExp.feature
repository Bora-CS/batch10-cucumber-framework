
Feature: AddExp

  @API
  Scenario: Title of your scenario
    Given [API] user is logged in
      | username | rubenmendozabri@gmail.com |
      |          |                           |
    Then [API] user should be able to add a new experience
      | company     | PADORI'S                     |
      | title       | enginEer                     |
      | location    | manassas, virginia           |
      | from        | 2022-02-02                   |
      | to          |                              |
      | current     | true                         |
      | description | maybe the company \\ name.// |
