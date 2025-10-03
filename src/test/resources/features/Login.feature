Feature: Login functionality

  @login
  Scenario: Successful login with valid credentials
    Given the app is launched
    When user enters username "standard_user" and password "secret_sauce"
    And user clicks login button
    Then user should be logged in successfully