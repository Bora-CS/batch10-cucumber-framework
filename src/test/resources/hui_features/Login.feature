Feature: Login

  #@ui @boratech
  #Scenario: Login Postive Test
    #Given user is on the BoraTech homepage
    #When user navigates to the Login page
    #And user enters email - "hui-pretender@outlook.com" and password - "Hui123456" then click the Login button
    #Then user should be on the Dashboard page
#
  #@api
  #Scenario: Login Positive Test
    #Given [API] user is logged in
      #| email    | hui-pretender@outlook.com |
      #| password | Hui123456                 |

  @api
  Scenario Outline: API - Negative Test
    Given [API] user enters wrong credentials
      | email    | <email>    |
      | password | <password> |
    Then [API] user should receive login errors
      | error | <error> |

    Examples: 
      | email                         | password | error                |
      | someone.some@email.domain.com |          | Password is required |
      |                               |          | Email is required    |
      |                               |   123456 | Email is required    |
