Feature: User login into tvnz application
  @login
  Scenario: login to tvnz application
    Given I navigate to login page
    When I enter user name "k10@grr.la"
    And I enter password "11111111"
    And I click submit button
    Then User should be login to tvnz app
    And username should display on home page
    |abc|def|ghi|
    |111|222|333|

  @test
  Scenario Outline: login to tvnz application with multiple users
    Given I navigate to login page
    When I enter user name "<email>"
    And I enter password "<password>"
    And I click submit button
    Then User should be login to tvnz app
    And username should display on home page
      |<email>|<password>|ghi|
      |111|222|333|
  Examples:
    |email|password|
    |k77@grr.la|11111111|
    |k10@grr.la|11111111|