Feature: Post

  @ui
  Scenario: Create Post
    Given user is logged in
      | username | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user navigates to the Posts page
    And user enters the post content
      | content                      |
      | I still want to fight a fish |
    And user clicks on the submit button
    Then user should see a success alert that says "Post Created"

  @api
  Scenario: API - Post Positive Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then [API] user posts a new [Post]
      | content                      |
      | I still want to fight a fish |
