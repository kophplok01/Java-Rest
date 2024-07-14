@Materials
Feature: user is able to interact with materials endpoints

  Scenario: User can create a new Material
    Given the user is authenticated as a adminTest4
    Then the user can create a new material

  Scenario: User can create multiple materials at the same time
    Given the user is authenticated as a adminTest4
    Then the user can create multiple materials at the same time

  Scenario: User can get all created materials
    Given the user is authenticated as a adminTest4
    Then the user can get all created materials