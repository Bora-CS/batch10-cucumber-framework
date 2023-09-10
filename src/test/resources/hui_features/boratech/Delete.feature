Feature: Delete

  Scenario: Delete Experience
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then user deletes all experiences

  Scenario: Delete Education
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then user deletes all educations
