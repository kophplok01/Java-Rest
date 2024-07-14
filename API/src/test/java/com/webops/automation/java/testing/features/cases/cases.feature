@Cases
Feature: user is able to interact with cases endpoints

  Scenario: user can create a new case
    Given the user is authenticated as a adminTest4
    Then the user can create a new case

  Scenario Outline: create cases by type '<caseType>'
    Given the user is authenticated as a adminTest4
    Then the user can create a new case with type '<caseType>'

    Examples:
      | caseType |
      | Standard |
      | Add On   |

  Scenario: user cant create two new cases with the same external id
    Given the user is authenticated as a adminTest4
    Then the user cant create a new case with the same external case id

  Scenario: user can get the details of a created case
    Given the user is authenticated as a adminTest4
    Then the user can get the details of a created case

  Scenario: user can edit the details of a case
    Given the user is authenticated as a adminTest4
    Then the user can edit the details of a created case

  Scenario: user can post the usage of a case
    Given the user is authenticated as a adminTest4
    And the user can create a new case
    Then the user can post the usage of a case

  Scenario: user can get the order id of posted case
    Given the user is authenticated as a adminTest4
    And the user can create a new case
    And the user can post the usage of a case
    Then the user can get the order id of posted case

  Scenario: User cannot create a new case if the Sales Rep ID does not belong to the Branch
    Given the user is authenticated as a adminTest4
    Then the user should receive an error message indicating that the Sales Rep ID is invalid

  Scenario: User cannot create a new case if the Coverage Rep ID does not belong to the branch
    Given the user is authenticated as a adminTest4
    Then the user should receive an error message indicating that the coverage rep id does not belong to the branch

  Scenario: User cannot create a new case if the Product System does not belong to the Procedure used
    Given the user is authenticated as a adminTest4
    Then the user should receive an error message when using a product system that does not belong to the procedure used

  Scenario: User creates a new case only with required fields
    Given the user is authenticated as a adminTest4
    Then the user can create a new case only with the required fields

  Scenario: User cant create a new case if required fields are missing
    Given the user is authenticated as a adminTest4
    Then the user can't create a new case without all the required fields

  Scenario Outline: Get cases by state '<caseStatus>'
    Given the user is authenticated as a adminTest4
    Then the user can get cases by status '<caseStatus>'

    Examples:
      | caseStatus    |
      | New           |
      | Kits Assigned |
      | Assembled     |
      | Shipped       |
      | Returned      |
      | Checked-In    |
      | Reconciled    |
      | Closed        |
      | Cancelled     |

  Scenario Outline: Get cases by '<searchType>'
    Given the user is authenticated as a adminTest4
    Then the user can get cases by '<searchType>'

    Examples:
      | searchType             |
      | ManufacturerId         |
      | BranchId               |
      | BranchErpCode          |
      | purchaseOrderReceived  |
      | confirmed              |
      | approved               |
      | billed                 |
      | usageReceived          |
      | usageCompleted         |
      | orderHold              |


  Scenario Outline: Get cases by type '<caseType>'
   Given the user is authenticated as a adminTest4
   Then the user can get cases by type '<caseType>'

    Examples:
      | caseType |
      | Standard |
      | Add On   |

  Scenario: Get cases by branch ID
    Given the user is authenticated as a adminTest4
    Then the user can get all cases by branch ID

  Scenario: Attempt to get cases with a non-configured manufacturer ID
    Given the user is authenticated as a adminTest4
    Then the user can see an error message when using an non-configured Manufacturer

  Scenario: Attempt to get cases with a non-existent branch ID
    Given the user is authenticated as a adminTest4
    Then the user can see an error message when using an non-existent branch ID

  Scenario: Attempt to get cases with a non-existent case status
    Given the user is authenticated as a adminTest4
    Then the user can see an error message when using a wrong case status

  Scenario: Attempt to get cases with a non-existent case type
    Given the user is authenticated as a adminTest4
    Then the user can see an error message when using a wrong case type

  Scenario: The user can not edit the case when the Sales Rep ID does not belong to the Branch
    Given the user is authenticated as a adminTest4
    Then the user can not edit a case when the Sales Rep ID does not belong to the branch

  Scenario: The user cannot edit the case when the Coverage Rep ID is not linked to the Sales Rep ID
    Given the user is authenticated as a adminTest4
    Then the edit should fail when using a coverage rep id that is not linked to the sales rep

  Scenario: The user cannot edit the case when the Procedure does not belong to the Branch
    Given the user is authenticated as a adminTest4
    Then the user cannot edit the case when the Procedure does not belong to the Branch

  Scenario: The user cannot edit the case when the System Product does not belong to the Branch
    Given the user is authenticated as a adminTest4
    Then the user cannot edit the case when the System Product does not belong to the Branch

  Scenario: User cant edit case if required fields are missing
    Given the user is authenticated as a adminTest4
    Then the user can not edit a case without all the required fields

  Scenario: User cant edit case only with required fields are missing
    Given the user is authenticated as a adminTest4
    Then the user can not edit a case only with required fields

  Scenario: user can create a new erp case only with required fields
    Given the user is authenticated as a adminTest4
    Then the user can create a new erp case only with the required fields

  Scenario: user can not create a new erp case using a normal branch
    Given the user is authenticated as a adminTest4
    Then the user can not create a new erp case using normal Branch

  Scenario: user can not create a new erp case using an existing external case id
    Given the user is authenticated as a adminTest4
    Then the user can not create a new erp case using an existing external case id

  Scenario: user can not create a new erp case if the arriveByDate is before today date
    Given the user is authenticated as a adminTest4
    Then the user can not create a new erp case using an arriveByDate before today date

  Scenario: user can not create a new erp case if the arriveByDate is after the surgery date
    Given the user is authenticated as a adminTest4
    Then the user can not create a new erp case using an arriveByDate after surgery date

  Scenario: user can create a new erp case with all possible fields
    Given the user is authenticated as a adminTest4
    Then user can create a new erp case with all possible fields

  Scenario: user can not create a new erp case when the hospitalErpCode is invalid
    Given the user is authenticated as a adminTest4
    Then user can not create a new erp case when the hospitalErpCode is invalid

  Scenario: user can not create a new erp case when the coverageRepId does not belong to the used branch
    Given the user is authenticated as a adminTest4
    Then user can not create a new erp case when the coverageRepId does not belong to the used branch

  Scenario: user can not create a new erp case when the salesRepErpCode is invalid
    Given the user is authenticated as a adminTest4
    Then user can not create a new erp case when the salesRepErpCode is invalid

  Scenario: user can not create a new erp case when the physicianErpCode is invalid
    Given the user is authenticated as a adminTest4
    Then user can not create a new erp case when the physicianErpCode is invalid

  Scenario Outline: User can not create a new erp case without using the '<requiredField>' field
    Given the user is authenticated as a adminTest4
    Then the user can not create a new erp case without using the '<requiredField>' field

    Examples:
      | requiredField    |
      | messageId        |
      | timestamp        |
      | manufacturerId   |
      | branchErpCode    |
      | externalCaseId   |
      | caseType         |
      | surgeryDate      |
      | salesRepErpCode  |
      | physicianErpCode |
      | hospitalErpCode  |
      | procedureId      |

  Scenario: user can edit the details of an erp case
    Given the user is authenticated as a adminTest4
    Then the user can edit the details of a an erp case

  Scenario: user can edit the details of an erp case only using the required fields
    Given the user is authenticated as a adminTest4
    Then the user can edit the details of a an erp case only using the required fields

  Scenario Outline: User can not edit an erp case without using the '<requiredField>' field
    Given the user is authenticated as a adminTest4
    Then the user can not an erp case without using the '<requiredField>' field

    Examples:
      | requiredField    |
      | messageId        |
      | timestamp        |
      | manufacturerId   |
      | branchErpCode    |

  Scenario: user can not edit the details of an erp case when using an hospitalErpCode that does not belong to the used branch
    Given the user is authenticated as a adminTest4
    Then the user can not edit the details of an erp case when using an hospitalErpCode that does not belong to the used branch

  Scenario: user can not edit the details of an erp case when using an salesErpCode that does not belong to the used branch
    Given the user is authenticated as a adminTest4
    Then the user can not edit the details of an erp case when using an salesErpCode that does not belong to the used branch

  Scenario Outline: User can cancel a case with cancel type "'<cancelType>'"
    Given the user is authenticated as a adminTest4
    Then the user can cancel a case with a cancel type '<cancelType>'

    Examples:
      | cancelType                             |
      | Not Specified                          |
      | Product Recall                         |
      | Patient Conditions                     |
      | Insurance Not Approved                 |
      | Medical Office Cancel Surgery          |
      | Surgery Postponed Without Date         |
      | No Usage Registered                    |
      | Competitors Product Preferred          |
      | Used Hospital Inventory                |
      | Instruments Not Available              |
      | Implants Not Available                 |
      | Technical Representative Not Available |
      | Customer Credit Block                  |
      | Delivery Issues                        |
      | Duplicate Order                        |
      | Pricing not established                |
      | Duplicated Surgery                     |
      | System Issues                          |
      | Insufficient Funding                   |

  Scenario: user can not cancel an already canceled case
    Given the user is authenticated as a adminTest4
    Then user can not cancel an already canceled case

  Scenario: expected response when canceling an un-existent case
    Given the user is authenticated as a adminTest4
    Then expected response when canceling an un-existent case









