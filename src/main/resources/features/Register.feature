Feature: Register new users into TVNZ+

  @abc
  Scenario: Register new user
    Given I navigate to login page
    When I click on Sign up now
    And I fill registration details
    And I select House Rules check and Email Me about check boxes
    And I click SIGN ME UP button
    Then I can see new user get registered into TVNZ+

