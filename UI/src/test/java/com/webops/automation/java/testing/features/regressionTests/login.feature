@login
Feature: User is able to interact with login page properly

  Scenario: Successful login
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be redirected to the home page