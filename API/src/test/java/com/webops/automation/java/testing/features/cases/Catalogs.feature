@Catalogs
Feature: user is able to interact with Catalogs endpoints

  Scenario: User can create multiple catalogs at the same time
    Given the user is authenticated as a adminTest4
    Then the user can create multiple catalogs at the same time

  Scenario: User can not create multiple catalogs with the same gtin
    Given the user is authenticated as a adminTest4
    Then the user can not create multiple catalogs with the same gtin

  Scenario: User can not create multiple catalogs with a wrong gtin
    Given the user is authenticated as a adminTest4
    Then the user can not create multiple catalogs with a wrong gtin

  Scenario: User can get all created catalogs
    Given the user is authenticated as a adminTest4
    Then the user can get all created catalogs

  Scenario: User can not get all created catalogs for an un-existent company
    Given the user is authenticated as a adminTest4
    Then the user can see an error message when trying to get all created for an un-existent company

  Scenario: User can update a catalog
    Given the user is authenticated as a adminTest4
    Then the user can see successfully edit a created catalog

  Scenario: User can not create multiple catalogs with the same catalog number
    Given the user is authenticated as a adminTest4
    Then the user can not create multiple catalogs with the same catalog number