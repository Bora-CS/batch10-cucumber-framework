Feature: Add Education

  @ui
  Scenario: Positive Test
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Education] button
    And user enters a new [Education] data
      | school              | BoraTech School                              |
      | degree              | Certificate of Test Automation Engineering   |
      | fieldofstudy        | Test Automation Engineering                  |
      | from                | 05/07/2023                                   |
      | to                  |                                              |
      | current             | true                                         |
      | program description | Web application testing for quality control. |
      | error               |                                              |
    Then user sees a newly added [Education] on Dashboard page

  @ui
  Scenario Outline: Negative Test
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user clicks on [Add Education] button
    And user enters a new [Education] data
      | school              | <school>                                     |
      | degree              | <degree>                                     |
      | fieldofstudy        | <fieldofstudy>                               |
      | from                | <from>                                       |
      | to                  |                                              |
      | current             | true                                         |
      | program description | Web application testing for quality control. |
      | error               | <error>                                      |
    Then user sees a list of error messages of [Education]

    Examples: 
      | school      | degree      | fieldofstudy | from       | error                                                                                  |
      |             |             |              |            | School is required,Degree is required,Field of study is required,From date is required |
      |             | Test Degree | Test Study   | 02/02/2002 | School is required                                                                     |
      | Test School |             | Test Study   | 02/02/2002 | Degree is required                                                                     |
      | Test School | Test Degree |              | 02/02/2002 | Field of study is required                                                             |
      | Test School | Test Degree | Test Study   |            | From date is required                                                                  |
      |             |             | Test Study   | 02/02/2002 | School is required,Degree is required                                                  |
      | Test School | Test Degree |              |            | Field of study is required,From date is required                                       |
      | Test School |             |              | 02/02/2002 | Degree is required,Field of study is required                                          |
      |             | Test Degree | Test Study   |            | School is required,From date is required                                               |
      |             |             |              | 02/02/2002 | School is required,Degree is required,Field of study is required                       |
      |             |             | Test Study   |            | School is required,Degree is required,From date is required                            |
      |             | Test Degree |              |            | School is required,Field of study is required,From date is required                    |
      | Test School |             |              |            | Degree is required,Field of study is required,From date is required                    |
