Feature: Show Pages of TVNZ+
  Background:
    Given I navigate to login page
    When I enter user name "k10@grr.la"
    And I enter password "11111111"
    And I click submit button
    Then User should be login to tvnz app
    And username should display on home page
  @def
  Scenario: Load a show page and play an episode
#   Given I click on a show "FBOY Island NZ"
    Given I click on a show tile
    Then I can see show page loading
    When I click on play episode Smart Watch button
    Then I can see episode playing
