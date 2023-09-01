Feature: Add Experience

  #@api
  #Scenario: Positive Test
    #Given [API] user is logged in
      #| email    | hui-pretender@outlook.com |
      #| password | Hui123456                 |
    #Then [API] user adds a new [Experience]
      #| company     | Walmart             |
      #| title       | Cashier             |
      #| location    | Manassas, Virginia  |
      #| from        | 2006-06-06          |
      #| to          | 2008-11-11          |
      #| current     | false               |
      #| description | Payment collection. |

  @api
  Scenario Outline: Negative Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When [API] user adds a wrong [Experience] with <company>, <title> and <from>
    Then [API] user sees a list of error messages of [Experience]
      | title   | Title is required     |
      | company | Company is required   |
      | from    | From date is required |

    Examples: 
      | company      | title      | from       |
      |              |            |            |
      |              | Test Title | 2002-02-02 |
      | Test Company |            | 2002-02-02 |
      | Test Company | Test Title |            |
      |              |            | 2002-02-02 |
      |              | Test Title |            |
      | Test Company |            |            |
