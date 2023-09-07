Feature: Post

  @ui @hero
  Scenario: Create Post
    Given user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user navigates to the Posts page
    And user enters the post content
      | content                       |
      | I still want to fight a fish. |
    And user clicks on the Submit button
    Then user should see a success alert

  @api
  Scenario: API - Post Positive Test
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then [API] user posts a new [Post]
      | content                       |
      | I still want to fight a fish. |
