Feature: API practice with cucumber
  
  I want to practice API using cucumber

  @api @login
  Scenario Outline: API login: validation
    Given call login API with "<email>" and "<password>"
    Then we should get response code <code>

    Examples: 
      | email                     | password    | code |
      | rubenmendozabri@gmail.com | 12345qwerT! |  200 |
      | bademail@gmail.com        | blahblah    |  400 |
      | goodemail@gmail.com       | haha        |  400 |
