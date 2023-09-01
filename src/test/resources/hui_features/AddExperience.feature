Feature: Add Experience

  @api
  Scenario: Positive Test
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
