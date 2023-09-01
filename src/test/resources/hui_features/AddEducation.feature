Feature: Add Education

  @api
  Scenario: Positive Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then [API] user adds a new [Education]
      | school       | BoraTech School                              |
      | degree       | Certificate of Test Automation Engineering   |
      | fieldofstudy | Test Automation Engineering                  |
      | from         | 2023-05-07                                   |
      | to           |                                              |
      | current      | true                                         |
      | description  | Web application testing for quality control. |
