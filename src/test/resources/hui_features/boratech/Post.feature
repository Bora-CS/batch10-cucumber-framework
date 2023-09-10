Feature: Post

  @ui
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

  @e2e
  Scenario: [API - UI][UI - API] Create a post
    Given [API] user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    Then [API] user posts a new [Post]
      | content                       |
      | I still want to fight a fish. |
    When user is logged in
      | email    | hui-pretender@outlook.com |
      | password | Hui123456                 |
    When user navigates to the Posts page
    Then user sees the newly created post
    When user deletes the newly created post
    Then [API] users gets a list of posts that does not contain the previously created post
