Feature: Login functionality

  @login
  Scenario: Successful login with valid credentials
    Given the app is launched
    When user enters valid username and password from "Login" sheet
    And user clicks login button
    Then user should be logged in successfully

    @login
    Scenario: Unsuccessful login with invalid credentials
      Given the app is launched
      When user enters invalid username and password from "Login" sheet
      And user clicks login button
      Then user should see an expected error message
