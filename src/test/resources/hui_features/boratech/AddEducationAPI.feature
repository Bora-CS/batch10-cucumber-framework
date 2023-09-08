Feature: Add Education

  @api
  Scenario: API - Positive Test
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

  @api
  Scenario Outline: API - Negative Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When [API] user adds a wrong [Education] with <school>, <degree>, <fieldofstudy> and <from>
    Then [API] user sees a list of error messages of [Education]
      | error | <error> |

    Examples: 
      | school      | degree      | fieldofstudy | from       | error                                                                                  |
      |             |             |              |            | School is required,Degree is required,Field of study is required,From date is required |
      |             | Test Degree | Test Study   | 2000-02-02 | School is required                                                                     |
      | Test School |             | Test Study   | 2000-02-02 | Degree is required                                                                     |
      | Test School | Test Degree |              | 2000-02-02 | Field of study is required                                                             |
      | Test School | Test Degree | Test Study   |            | From date is required                                                                  |
      |             |             | Test Study   | 2000-02-02 | School is required,Degree is required                                                  |
      | Test School | Test Degree |              |            | Field of study is required,From date is required                                       |
      | Test School |             |              | 2000-02-02 | Degree is required,Field of study is required                                          |
      |             | Test Degree | Test Study   |            | School is required,From date is required                                               |
      |             |             |              | 2002-02-02 | School is required,Degree is required,Field of study is required                       |
      |             |             | Test Study   |            | School is required,Degree is required,From date is required                            |
      |             | Test Degree |              |            | School is required,Field of study is required,From date is required                    |
      | Test School |             |              |            | Degree is required,Field of study is required,From date is required                    |
