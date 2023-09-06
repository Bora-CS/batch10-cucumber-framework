Feature: Add Experience

  @api
  Scenario: API - Positive Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then [API] user adds a new [Experience]
      | company     | Walmart             |
      | title       | Cashier             |
      | location    | Manassas, Virginia  |
      | from        | 2006-06-06          |
      | to          | 2008-11-11          |
      | current     | false               |
      | description | Payment collection. |

  @api
  Scenario Outline: API - Negative Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When [API] user adds a wrong [Experience] with <company>, <title> and <from>
    Then [API] user sees a list of error messages of [Experience]
      | error | <error> |

    Examples: 
      | company      | title      | from       | error                                                       |
      |              |            |            | Company is required,Title is required,From date is required |
      |              | Test Title | 2002-02-02 | Company is required                                         |
      | Test Company |            | 2002-02-02 | Title is required                                           |
      | Test Company | Test Title |            | From date is required                                       |
      |              |            | 2002-02-02 | Company is required,Title is required                       |
      |              | Test Title |            | Company is required,From date is required                   |
      | Test Company |            |            | Title is required,From date is required                     |
