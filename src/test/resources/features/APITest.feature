Feature: API pratice with Cucumber
  I want to practice API by using cucumber

  @login
  Scenario Outline: API login validation
    Given call login api with "<email>" and "<password>"
    Then we should get reponse code <code>

    Examples: 
      | email                            | password    | code |
      | muradil.erkin@boratechschool.com | Boratech123 |  400 |
      | invalid@gmail.com                |         123 |  300 |
      | muradil.erkin@boratechschool.com | Boratech    |  200 |
      | muradil.erkin@boratechschool.com | invalid     |  400 |

  @api
  Scenario: API login validate with scenraio
    Given login with user and validate status
      | email                            | password | code |
      | muradil.erkin@boratechschool.com | invalid  |  400 |
      | muradil.erkin@boratechschool.com | Boratech |  200 |
