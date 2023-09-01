Feature: CDC (Centers for Disease Control and Prevention) Data Search

  @filter @sort @ui
  Scenario: Data page filters and sorts
    Given user is on CDC Data homepage
    And user clicks on Data Catalog on top menu bar
    And user is on Data search page
    When user expands [Chronic Diseases] and clicks on [Youth Risk Behaviors] under [Categories]
      | Categories     | Chronic Diseases     |
      | Sub-Categories | Youth Risk Behaviors |
    And user clicks on [Charts] under [View Types]
      | View Types | Charts |
    And user expands [Tags] and on the pop up menu, user clicks on [youth online]
      | Tags | youth online |
    And user selects "Most Accessed" from Sort drop down menu
    Then users sees all filters on top of the result page
    And the result page is sorted by view counts
