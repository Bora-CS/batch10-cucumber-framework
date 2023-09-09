Feature: Post

  @UI @Parallel
  Scenario: Create Post
    Given user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When user navigates to the Posts page
    And user enters the post content
      | content                                          |
      | Cucumber automation is a lot more than you think |
    And user clicks on the submit button
    Then user should see a success alert that says [Post Created]

  @API
  Scenario: API - Create Post Happy Path
    Given [API] user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    Then [API] user should be able to create a new post
      | content                                          |
      | Cucumber automation is a lot more than you think |

  @E2E
  Scenario: API - Create Post Happy Path
    Given [API] user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    Then [API] user should be able to create a new post
      | content                                          |
      | Cucumber automation is a lot more than you think |
    When user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When user navigates to the Posts page
    Then user should see the post that was created previously
    When user deletes the post that was created previously
    Then [API] user can validate that the post they created previously is not there anymore
