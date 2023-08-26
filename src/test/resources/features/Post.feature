Feature: Post

  Scenario: Create Post
    Given user is logged in
      | username | muradil.erkin@boratechschool.com |
      | password | Boratech                         |
    When user navigates to the Posts page
    And user enters the post content
      | content                                          |
      | Cucumber automation is a lot more than you think |
    And user clicks on the submit button
    Then user should see a success alert that says "Post Created"
