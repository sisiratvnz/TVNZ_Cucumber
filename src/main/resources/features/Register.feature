Feature: Register new users into TVNZ+

  Scenario: Register new user
    Given TVNZ application loaded
    When I click on 'Login' link
    And I click on 'Sign up now'
    And I fill registration details
    And I select House Rules check and Email Me about check boxes
    And I click 'SIGN ME UP' button
    Then I can see new user get registered into TVNZ

