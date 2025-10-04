Feature: Login functionality

  @login
  Scenario: Successful login with valid credentials
    Given the app is launched
    When user enters username "standard_user" and password "secret_sauce"
    And user clicks login button
    Then user should be logged in successfully

    @login
    Scenario: Unsuccessful login with invalid credentials
      Given the app is launched
      When user enters username "invalid_user" and password "wrong_password"
      And user clicks login button
      Then user should see an expected error message
