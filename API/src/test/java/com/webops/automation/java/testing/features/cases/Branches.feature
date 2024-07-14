@Branches
Feature: user is able to interact with branches endpoints

  Scenario Outline: Get branches by '<searchType>'
    Given the user is authenticated as a adminTest4
    Then the user can get branches by '<searchType>'

    Examples:
      | searchType     |
      | ManufacturerId |
      | ids            |
      | name           |
      | erpCode        |
      | active         |
      | limit          |
      | updatedAtMin   |
      | updatedAtMax   |

    Scenario Outline: User can not get branches without using the required field '<field>'
      Given the user is authenticated as a adminTest4
      Then the user can not get branches without using the required field '<field>'

      Examples:
        | field           |
        | timestamp       |
        | messageId       |
        | ManufacturerId  |