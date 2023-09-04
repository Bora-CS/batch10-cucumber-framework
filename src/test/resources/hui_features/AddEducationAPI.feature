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
      | school       | School is required         |
      | degree       | Degree is required         |
      | fieldofstudy | Field of study is required |
      | from         | From date is required      |

    Examples: 
      | school      | degree      | fieldofstudy | from       |
      |             |             |              |            |
      |             | Test Degree | Test Study   | 2000-02-02 |
      | Test School |             | Test Study   | 2000-02-02 |
      | Test School | Test Degree |              | 2000-02-02 |
      | Test School | Test Degree | Test Study   |            |
      |             |             | Test Study   | 2000-02-02 |
      | Test School | Test Degree |              |            |
      | Test School |             |              | 2000-02-02 |
      |             | Test Degree | Test Study   |            |
      |             |             |              | 2002-02-02 |
      |             |             | Test Study   |            |
      |             | Test Degree |              |            |
      | Test School |             |              |            |
