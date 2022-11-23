Feature: User login into tvnz application
  @def
  Scenario: login to tvnz application
    Given I navigate to login page
    When I enter user name "k10@grr.la"
    And I enter password "11111111"
    And I click submit button
    Then User should be login to tvnz app
    And username should display on home page