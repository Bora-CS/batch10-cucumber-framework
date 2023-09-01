#Author: your.email@your.domain.com
#Feature: Login
#Scenario: valid login
#Scenario: invalid login
Feature: Login

  @ui
  Scenario: Login Positive Test
    Given user is on demo login page
    When user enter "username" and "password" and then clicks Login button
      | username | standard_user |
      | password | secret_sauce  |
    Then user is on Products page

  @ui
  Scenario Outline: Login Negative Test
    Given user is on demo login page
    When user attempts to enter <username> and <password> and then clicks Login button
    Then login page displays <error message>

    Examples: 
      | username        | password     | error message                                               |
      |                 |              | Username is required                                        |
      |                 | pass         | Username is required                                        |
      | user            |              | Password is required                                        |
      | user            | pass         | Username and password do not match any user in this service |
      | standard_user   | pass         | Username and password do not match any user in this service |
      | user            | secret_sauce | Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Sorry, this user has been locked out.                       |
