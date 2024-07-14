@OpsTerritories
Feature: user is able to interact with OpsTerritories endpoints

  Scenario: user can load a single ops territory
    Given the user is authenticated as a adminTest4
    Then the user can load a new OpsTerritory

  Scenario: user can load multiple ops territories
    Given the user is authenticated as a adminTest4
    Then the user can load a multiples OpsTerritories at the same time

  Scenario: user can get all ops territories
    Given the user is authenticated as a adminTest4
    Then the user can get all OpsTerritories