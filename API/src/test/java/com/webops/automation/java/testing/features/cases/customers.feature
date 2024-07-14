@Customers
Feature: user is able to interact with customer endpoints

  Scenario: user can create a new customer
    Given the user is authenticated as a adminTest4
    Then the user can create a new customer

  Scenario: user can create multiple customers at the same time
    Given the user is authenticated as a adminTest4
    Then the user can create multiple customers at the same time

  Scenario: user can get all customers
    Given the user is authenticated as a adminTest4
    Then the user can get all created customers