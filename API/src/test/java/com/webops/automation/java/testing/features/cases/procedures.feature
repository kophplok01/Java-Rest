@Procedures
Feature: user is able to interact with cases endpoints

  Scenario: user can create a new procedure
    Given the user is authenticated as a adminTest4
    Then the user can create a new procedure

  Scenario: User creates a new procedure only with required fields
    Given the user is authenticated as a adminTest4
    Then the user can create a new procedure only with the required fields

  Scenario: User cant create a new procedure if required fields are missing
    Given the user is authenticated as a adminTest4
    Then the user can't create a new procedure without all the required fields

  Scenario: user can update a procedure
    Given the user is authenticated as a adminTest4
    Then the user can update a procedure

  Scenario: user can not update a procedure using a companyId that does not belong to the manufacturer used
    Given the user is authenticated as a adminTest4
    Then  user can not update a procedure using a companyId that does not belong to the manufacturer

  Scenario Outline: User can not update a procedure without using the '<requiredField>' field
    Given the user is authenticated as a adminTest4
    Then the user can not update a procedure without using the '<requiredField>' field

    Examples:
      | requiredField |
      | messageId     |
      | timestamp     |
      | manufacturerId|


