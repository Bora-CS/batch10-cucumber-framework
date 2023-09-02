Feature: Add Education

  @API
  Scenario Outline: API - Add Education Unhappy Path
    Given [API] user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When [API] user tries to add a new education
      | school       | <school>       |
      | degree       | <degree>       |
      | fieldofstudy | <fieldofstudy> |
      | from         | <from>         |
      | to           | <to>           |
      | current      | <current>      |
      | description  | <description>  |
    Then [API] user should receive missing form errors
      | errors | <errors> |

    Examples: 
      | school   | degree                   | fieldofstudy    | from       | to | current | description                  | errors                                                             |
      |          |                          | Coffee Drinking | 2023-08-01 |    | true    | Drinking all sorts of coffee | Degree is required, School is required                             |
      | BoraTech |                          | Coffee Drinking | 2023-08-01 |    | true    | Drinking all sorts of coffee | Degree is required                                                 |
      |          | Certified Coffee Drinker | Coffee Drinking | 2023-08-01 |    | true    | Drinking all sorts of coffee | School is required                                                 |
      | BoraTech | Certified Coffee Drinker |                 | 2023-08-01 |    | true    | Drinking all sorts of coffee | Field of study is required is required                             |
      |          |                          |                 | 2023-08-01 |    | true    | Drinking all sorts of coffee | Degree is required, School is required, Field of study is required |
