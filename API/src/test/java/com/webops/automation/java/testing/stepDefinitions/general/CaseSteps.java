package com.webops.automation.java.testing.stepDefinitions.general;

import com.webops.automation.java.testing.helpers.CaseApi;
import com.webops.automation.java.testing.helpers.OrdersApi;
import com.webops.automation.java.testing.objects.reponses.*;
import com.webops.automation.java.testing.objects.bodies.*;
import helpers.DBBranches;
import helpers.DBHospitals;
import helpers.DefaultValues;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CaseSteps {

    Case newCase;
    CaseUsage caseUsage;

    @Then("the user can create a new case")
    public void theUserCanCreateANewCase() {
        Response response = CaseApi.createNewCase(new PostNewCase(false));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Assert.assertNotNull(newCase.getCases());
    }

    @Then("the user cant create a new case with the same external case id")
    public void theUserCantCreateANewCaseWithTheSameExternalId() {
        ErrorNewCase errorNewCase = CaseApi.createNewCase(new PostNewCase(false).setExternalCaseId("W007")).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "externalCaseId W007 already exists. Value must be unique.", errorNewCase.getMessage());
    }

    @Then("the user can get the details of a created case")
    public void theUserCanGetTheDetailsOfACreatedCase() {
        Case newCase = CaseApi.createNewCase(new PostNewCase(false)).body().as(Case.class);
        String id = String.valueOf(newCase.getCases()[0].getId());
        GetCases getCases = new GetCases();
        getCases.setIds(id);
        Response response = CaseApi.getCases(getCases);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Case caseDetails = response.body().as(Case.class);
        Assert.assertEquals(id, String.valueOf(caseDetails.getCases()[0].getId()));
        Assert.assertEquals(caseDetails.getCases().length, 1);
    }

    @Then("the user can edit the details of a created case")
    public void theUserCanEditTheDetailsOfACreatedCase() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Response response = CaseApi.editCase(new PostEditCase(currentDateTime.format(formatter), pastDateTime.format(formatter2)
                        , createdCase.getSalesRepId(), createdCase.getProcedureId()),
                String.valueOf(createdCase.getId()));
        Case editedCase = response.body().as(Case.class);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(currentDateTime.format(formatter), editedCase.getCases()[0].getSurgeryDate());
        Assert.assertEquals(pastDateTime.format(formatter2), editedCase.getCases()[0].getShippingInstructions()[0].getArriveByDate());
    }

    @Then("the user can post the usage of a case")
    public void theUserCanPostTheUsageOfACase() {
        String id = String.valueOf(newCase.getCases()[0].getId());
        Response response = CaseApi.postCaseUsage(new PostCaseUsage(id), id);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
        caseUsage = response.body().as(CaseUsage.class);
        Assert.assertEquals(caseUsage.getDetails()[0].getMessage(), "Success");
    }

    @Then("the user can get the order id of posted case")
    public void theUserCanGetTheOrderIdOfPostedCase() {
        Response response = OrdersApi.getOrderByCaseId(new GetOrder().setIds(caseUsage.getDetails()[0].getExternalCaseUsageId()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Order order = response.body().as(Order.class);
        Assert.assertEquals(String.valueOf(order.getOrders()[0].getCaseInfo().getId()), caseUsage.getDetails()[0].getExternalCaseUsageId());
    }

    @Then("the user should receive an error message indicating that the Sales Rep ID is invalid")
    public void theUserShouldReceiveAnErrorMessageIndicatingThatTheSalesRepIDIsInvalid() {
        ErrorNewCase errorNewCase = CaseApi.createNewCase(new PostNewCase(false).setSalesRepId(1234)).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "salesRepId 1234 does not belong to branch 149.", errorNewCase.getMessage());
    }

    @Then("the user should receive an error message indicating that the coverage rep id does not belong to the branch")
    public void theUserShouldReceiveAnErrorMessageIndicatingThatTheCoverageRepIdDoesNotBelongToTheBranch() {
        ErrorNewCase errorNewCase = CaseApi.createNewCase(new PostNewCase(false).setCoverageRepId(3657)).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "coverageRepId 3657 does not belong to branch 149.", errorNewCase.getMessage());
    }

    @Then("the user should receive an error message when using a product system that does not belong to the procedure used")
    public void theUserShouldReceiveAnErrorMessageWhenUsingAProductSystemThatDoesNotBelongToTheProcedureUsed() {
        ErrorNewCase errorNewCase = CaseApi.createNewCase(new PostNewCase(false)
                .setProductSystems(new ProductSystem(3971, "Attention field"))).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "productSystems - productSystemId 3971 does not belong to branch 149.", errorNewCase.getMessage());
    }

    @Then("the user can create a new case only with the required fields")
    public void theUserCanCreateANewCaseOnlyWithTheRequiredFields() {
        Response response = CaseApi.createNewCase(new PostNewCaseOnlyRequieredFileds(true));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Assert.assertNotNull(newCase.getCases());
    }

    @Then("the user can't create a new case without all the required fields")
    public void theUserCanTCreateANewCaseWithoutAllTheRequiredFields() {
        Response response = CaseApi.createNewCase(new PostNewCaseOnlyRequieredFileds(false));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
    }

    @Then("the user can get cases by type {string}")
    public void theUserCanGetCasesByTypeCaseType(String caseType) {
        Response response = CaseApi.getCases(new GetCases().setCaseType(caseType));
        Case cases = response.body().as(Case.class);
        for (Cases caseItem : cases.getCases()) {
            Assert.assertEquals(caseType, caseItem.getCaseType());
        }
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());

    }

    @Then("the user can get all cases by branch ID")
    public void theUserCanGetAllCasesByBranchID() {
        Response response = CaseApi.getCases(new GetCases());
        Case cases = response.body().as(Case.class);
        for (Cases caseItem : cases.getCases()) {
            Assert.assertEquals(149, caseItem.getBranchId());
        }
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
    }

    @Then("the user can see an error message when using an non-configured Manufacturer")
    public void theUserCanSeeAnErrorMessageWhenUsingAnNonConfiguredManufacturer() {
        ErrorNewCase errorNewCase = CaseApi.getCases(new GetCases().setManufacturerId(1234)).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "Provided Manufacturer ID is not properly configured.", errorNewCase.getMessage());
    }

    @Then("the user can see an error message when using an non-existent branch ID")
    public void theUserCanSeeAnErrorMessageWhenUsingAnNonExistentBranchID() {
        ErrorNewCase errorNewCase = CaseApi.getCases(new GetCases().setBranchId(1234555)).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "branchId 1234555 is unknown or invalid", errorNewCase.getMessage());
    }

    @Then("the user can see an error message when using a wrong case status")
    public void theUserCanSeeAnErrorMessageWhenUsingAWrongCaseStatus() {
        ErrorNewCase errorNewCase = CaseApi.getCases(new GetCases().setCaseStatus("Wrong")).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "caseStatus Wrong is unknown or invalid", errorNewCase.getMessage());
    }

    @Then("the user can see an error message when using a wrong case type")
    public void theUserCanSeeAnErrorMessageWhenUsingAWrongCaseType() {
        ErrorNewCase errorNewCase = CaseApi.getCases(new GetCases().setCaseType("Wrong")).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "Submitted value for caseType is not one of the allowable values. Allowable Values: Standard, Add On", errorNewCase.getMessage());
    }

    @Then("the user can get cases by status {string}")
    public void theUserCanGetCasesByStatusCaseStatus(String caseStatus) {
        Response response = CaseApi.getCases(new GetCases().setCaseStatus(caseStatus));
        Case cases = response.body().as(Case.class);
        for (Cases caseItem : cases.getCases()) {
            Assert.assertEquals(caseStatus, caseItem.getCaseStatus());
        }
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());

    }

    @Then("the user can not edit a case when the Sales Rep ID does not belong to the branch")
    public void theUserCanNotEditACaseWhenTheSalesRepIDDoesNotBelongToTheBranch() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ErrorNewCase errorNewCase = CaseApi.editCase(new PostEditCase(currentDateTime.format(formatter), pastDateTime.format(formatter2)
                , 1234, createdCase.getProcedureId()), String.valueOf(createdCase.getId())).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "salesRepId 1234 does not belong to branch 149.", errorNewCase.getMessage());
    }

    @Then("the edit should fail when using a coverage rep id that is not linked to the sales rep")
    public void theEditShouldFailWhenUsingACoverageRepIdThatIsNotLinkedToTheSalesRep() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        PostEditCase postEditCase = new PostEditCase(currentDateTime.format(formatter), pastDateTime.format(formatter2)
                , createdCase.getSalesRepId(), createdCase.getProcedureId());
        postEditCase = postEditCase.setCoverageRepId(123);
        ErrorNewCase errorNewCase = CaseApi.editCase(postEditCase, String.valueOf(createdCase.getId())).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "coverageRepId 123 is unknown or invalid", errorNewCase.getMessage());
    }

    @Then("the user cannot edit the case when the Procedure does not belong to the Branch")
    public void theUserCannotEditTheCaseWhenTheProcedureDoesNotBelongToTheBranch() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        PostEditCase postEditCase = new PostEditCase(currentDateTime.format(formatter), pastDateTime.format(formatter2)
                , createdCase.getSalesRepId(), createdCase.getProcedureId());
        postEditCase = postEditCase.setProcedureId(123);
        ErrorNewCase errorNewCase = CaseApi.editCase(postEditCase, String.valueOf(createdCase.getId())).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "procedureId 123 is unknown or invalid", errorNewCase.getMessage());
    }

    @Then("the user cannot edit the case when the System Product does not belong to the Branch")
    public void theUserCannotEditTheCaseWhenTheSystemProductDoesNotBelongToTheBranch() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        PostEditCase postEditCase = new PostEditCase(currentDateTime.format(formatter), pastDateTime.format(formatter2)
                , createdCase.getSalesRepId(), createdCase.getProcedureId());
        postEditCase = postEditCase.setProductSystems(new ProductSystem(3971, "Attention field"));
        ErrorNewCase errorNewCase = CaseApi.editCase(postEditCase, String.valueOf(createdCase.getId())).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "productSystems - productSystemId 3971 does not belong to branch 149.", errorNewCase.getMessage());
    }

    @Then("the user can not edit a case without all the required fields")
    public void theUserCanNotEditACaseWithoutAllTheRequiredFields() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        ErrorNewCase errorNewCase = CaseApi.editCase(new EditCaseWithOnlyRequieredFields(false), String.valueOf(createdCase.getId())).body().as(ErrorNewCase.class);
        Assert.assertEquals("Unexpected", "Manufacturer ID is blank or not provided.", errorNewCase.getMessage());
    }

    @Then("the user can not edit a case only with required fields")
    public void theUserCanNotEditACaseOnlyWithRequiredFields() {
        Cases createdCase = CaseApi.getCases(new GetCases()).body().as(Case.class).getCaseByStatus("New");
        Response response = CaseApi.editCase(new EditCaseWithOnlyRequieredFields(true), String.valueOf(createdCase.getId()));
        Case editedCase = response.body().as(Case.class);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(149, editedCase.getCases()[0].getBranchId());
    }

    @Then("the user can not create a new erp case using normal Branch")
    public void theUserCanNotCreateANewErpCaseUsingNormalBranch() {
        Response response = CaseApi.createNewCaseErp(new PostNewCase(false));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Unrecognized parameter(s): branchId.", response.jsonPath().getString("message"));
    }

    @Then("the user can not create a new erp case using an existing external case id")
    public void theUserCanNotCreateANewErpCaseUsingAnExistingExternalCaseId() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErpWithOnlyRequiredFields(DefaultValues.getCurrentDateInISOFormat()).setExternalCaseId("90564"));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "externalCaseId 90564 already exists. Value must be unique.", response.jsonPath().getString("message"));
    }

    @Then("the user can not create a new erp case using an arriveByDate before today date")
    public void theUserCanNotCreateANewErpCaseUsingAnArriveByDateBeforeTodayDate() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getRandomDateBeforeTodayInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "shippingInstructions - arriveByDate is invalid. Cannot be before todays date.", response.jsonPath().getString("message"));
    }

    @Then("the user can not create a new erp case using an arriveByDate after surgery date")
    public void theUserCanNotCreateANewErpCaseUsingAnArriveByDateAfterSurgeryDate() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getRandomDateAfterTodayInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "shippingInstructions - arriveByDate is invalid. Cannot be after surgeryDate", response.jsonPath().getString("message"));
    }

    @Then("the user can create a new erp case only with the required fields")
    public void theUserCanCreateANewErpCaseOnlyWithTheRequiredFields() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErpWithOnlyRequiredFields(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Assert.assertNotNull(newCase.getCases());
    }

    @Then("user can create a new erp case with all possible fields")
    public void userCanCreateANewErpCaseWithAllPossibleFields() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Assert.assertNotNull(newCase.getCases());
    }

    @Then("user can not create a new erp case when the coverageRepId does not belong to the used branch")
    public void userCanNotCreateANewErpCaseWhenTheCoverageRepIdDoesNotBelongToTheUsedBranch() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setBranchErpCode("55-Wash")
                .setHospitalErpCode("55-Hospital").setSalesRepErpCode("55-SaleRep")
                .setPhysicianErpCode("10720"));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "coverageRepId 11723 does not belong to branch 149.", response.jsonPath().getString("message"));
    }

    @Then("user can not create a new erp case when the hospitalErpCode is invalid")
    public void userCanNotCreateANewErpCaseWhenTheHospitalErpCodeIsInvalid() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setBranchErpCode("55-Wash"));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "hospitalErpCode 228634 is unknown or invalid", response.jsonPath().getString("message"));
    }

    @Then("user can not create a new erp case when the salesRepErpCode is invalid")
    public void userCanNotCreateANewErpCaseWhenTheSalesRepErpCodeIsInvalid() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setBranchErpCode("55-Wash")
                .setHospitalErpCode("55-Hospital"));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "salesRepErpCode 9238339 is unknown or invalid", response.jsonPath().getString("message"));
    }

    @Then("user can not create a new erp case when the physicianErpCode is invalid")
    public void userCanNotCreateANewErpCaseWhenThePhysicianErpCodeIsInvalid() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setBranchErpCode("55-Wash")
                .setHospitalErpCode("55-Hospital").setSalesRepErpCode("55-SaleRep"));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "physicianErpCode 20767 is unknown or invalid", response.jsonPath().getString("message"));
    }

    @Then("the user can not create a new erp case without using the {string} field")
    public void theUserCanNotCreateANewErpCaseWithoutUsingTheRequiredFieldField(String field) {
        Response response;
        switch (field) {
            case "messageId":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setMessageId(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "messageId value is blank.", response.jsonPath().getString("message"));
                break;
            case "timestamp":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setTimestamp(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "manufacturerId":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setManufacturerId(null));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Manufacturer ID is blank or not provided.", response.jsonPath().getString("message"));
                break;
            case "branchErpCode":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setBranchErpCode(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Required field branchErpCode is missing or empty", response.jsonPath().getString("message"));
                break;
            case "externalCaseId":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setExternalCaseId(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "caseType":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setCaseType(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "surgeryDate":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setSurgeryDate(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "salesRepErpCode":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setSalesRepErpCode(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Required field salesRepErpCode is missing or empty", response.jsonPath().getString("message"));
                break;
            case "physicianErpCode":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setPhysicianErpCode(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Required field physicianErpCode is missing or empty", response.jsonPath().getString("message"));
                break;
            case "hospitalErpCode":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setHospitalErpCode(""));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Required field hospitalErpCode is missing or empty", response.jsonPath().getString("message"));
                break;
            case "procedureId":
                response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()).setProcedureId(null));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            default:
                Assert.fail("Field not found");
        }
    }

    @Then("the user can edit the details of a an erp case")
    public void theUserCanEditTheDetailsOfAAnErpCase() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Response response2 = CaseApi.editCaseErp(new PostEditCaseErp(pastDateTime.format(formatter2))
                        .setSurgeryDate(currentDateTime.format(formatter)),
                String.valueOf(newCase.getCases()[0].getId()));
        Case editedCase = response2.body().as(Case.class);
        Assert.assertEquals(response2.getBody().asString(), 200, response2.getStatusCode());
        Assert.assertEquals(currentDateTime.format(formatter), editedCase.getCases()[0].getSurgeryDate());
        Assert.assertEquals(pastDateTime.format(formatter2), editedCase.getCases()[0].getShippingInstructions()[0].getArriveByDate());

    }

    @Then("the user can edit the details of a an erp case only using the required fields")
    public void theUserCanEditTheDetailsOfAAnErpCaseOnlyUsingTheRequiredFields() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Response response2 = CaseApi.editCaseErp(new PostEditCaseErpWithOnlyRequiredFields(),
                String.valueOf(newCase.getCases()[0].getId()));
        Case editedCase = response2.body().as(Case.class);
        Assert.assertEquals(response2.getBody().asString(), 200, response2.getStatusCode());
        Assert.assertEquals("zzzzz", editedCase.getCases()[0].getSpecialInstructions());
    }

    @Then("the user can not an erp case without using the {string} field")
    public void theUserCanNotAnErpCaseWithoutUsingTheRequiredFieldField(String field) {
        Response response1 = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response1.getBody().asString(), 200, response1.getStatusCode());
        newCase = response1.body().as(Case.class);
        Response response;
        switch (field) {
            case "messageId":
                response = CaseApi.editCaseErp(new PostEditCaseErpWithOnlyRequiredFields().setMessageId(""), String.valueOf(newCase.getCases()[0].getId()));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "messageId value is blank.", response.jsonPath().getString("message"));
                break;
            case "timestamp":
                response = CaseApi.editCaseErp(new PostEditCaseErpWithOnlyRequiredFields().setTimestamp(""), String.valueOf(newCase.getCases()[0].getId()));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "manufacturerId":
                response = CaseApi.editCaseErp(new PostEditCaseErpWithOnlyRequiredFields().setManufacturerId(""), String.valueOf(newCase.getCases()[0].getId()));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Manufacturer ID is blank or not provided.", response.jsonPath().getString("message"));
                break;
            case "branchErpCode":
                response = CaseApi.editCaseErp(new PostEditCaseErpWithOnlyRequiredFields().setBranchErpCode(""), String.valueOf(newCase.getCases()[0].getId()));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Required field branchErpCode is missing or empty", response.jsonPath().getString("message"));
                break;
            default:
                Assert.fail("Field not found");
        }
    }

    @Then("the user can not edit the details of an erp case when using an hospitalErpCode that does not belong to the used branch")
    public void theUserCanNotEditTheDetailsOfAnErpCaseWhenUsingAnHospitalErpCodeThatDoesNotBelongToTheUsedBranch() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Response response2 = CaseApi.editCaseErp(new PostEditCaseErp(pastDateTime.format(formatter2))
                        .setSurgeryDate(currentDateTime.format(formatter)).setBranchErpCode(DBBranches.getAllBranchesByManufacturerId(1015)[0].getErp_code()),
                String.valueOf(newCase.getCases()[0].getId()));
        Assert.assertEquals(response2.getBody().asString(), 422, response2.getStatusCode());
        Assert.assertEquals(response2.getBody().asString(), "hospitalErpCode 228634 is unknown or invalid", response2.jsonPath().getString("message"));
    }

    @Then("the user can not edit the details of an erp case when using an salesErpCode that does not belong to the used branch")
    public void theUserCanNotEditTheDetailsOfAnErpCaseWhenUsingAnSalesErpCodeThatDoesNotBelongToTheUsedBranch() {
        Response response = CaseApi.createNewCaseErp(new PostNewCaseErp(DefaultValues.getCurrentDateInISOFormat()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        LocalDateTime currentDateTime = LocalDateTime.now().plusMonths(2);
        LocalDateTime pastDateTime = currentDateTime.minusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String erpCode = DBBranches.getAllBranchesByManufacturerId(1015)[0].getErp_code();
        Response response2 = CaseApi.editCaseErp(new PostEditCaseErp(pastDateTime.format(formatter2))
                        .setSurgeryDate(currentDateTime.format(formatter)).setBranchErpCode(erpCode).setHospitalErpCode(DBHospitals.getAllHospitalsByErpBranchCode(erpCode)[0].getBillToNumber()),
                String.valueOf(newCase.getCases()[0].getId()));
        Assert.assertEquals(response2.getBody().asString(), 422, response2.getStatusCode());
        Assert.assertEquals(response2.getBody().asString(), "salesRepErpCode 9238339 is unknown or invalid", response2.jsonPath().getString("message"));
    }

    @Then("the user can create a new case with type {string}")
    public void theUserCanCreateANewCaseWithTypeCaseType(String type) {
        Response response;
        response = CaseApi.createNewCase(new PostNewCase(false).setCaseType(type));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        newCase = response.body().as(Case.class);
        Assert.assertNotNull(newCase.getCases());
        Assert.assertEquals(newCase.getCases()[0].getCaseType(), type);
    }

    @Then("the user can cancel a case with a cancel type {string}")
    public void theUserCanCancelACaseWithACancelTypeCancelType(String cancelType) {
        newCase = CaseApi.createNewCase(new PostNewCase(false)).body().as(Case.class);
        Response response = CaseApi.cancelCase(new PostCancelCase().setCaseCancellationType(cancelType), String.valueOf(newCase.getCases()[0].getId()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Case cancelledCase = response.body().as(Case.class);
        Assert.assertEquals(cancelledCase.getCases()[0].getCaseStatus(), "Cancelled");

    }

    @Then("user can not cancel an already canceled case")
    public void userCanNotCancelAnAlreadyCanceledCase() {
        newCase = CaseApi.createNewCase(new PostNewCase(false)).body().as(Case.class);
        Response response = CaseApi.cancelCase(new PostCancelCase().setCaseCancellationType("Not Specified"), String.valueOf(newCase.getCases()[0].getId()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Response response2 = CaseApi.cancelCase(new PostCancelCase().setCaseCancellationType("Not Specified"), String.valueOf(newCase.getCases()[0].getId()));
        Assert.assertEquals(response2.getBody().asString(), 422, response2.getStatusCode());
        Assert.assertEquals(response2.getBody().asString(), "Case " + newCase.getCases()[0].getId() + " has already been cancelled", response2.jsonPath().getString("message"));
    }

    @Then("expected response when canceling an un-existent case")
    public void expectedResponseWhenCancelingAnUnExistentCase() {
        Response response = CaseApi.cancelCase(new PostCancelCase().setCaseCancellationType("Not Specified"), String.valueOf(123123123));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "WebOpsCaseId 123123123 is unknown or invalid", response.jsonPath().getString("message"));
    }

    @Then("the user can get cases by {string}")
    public void theUserCanGetCasesBySearchType(String searchType) {
        Response response;
        Case cases;
        switch (searchType) {
            case "ManufacturerId":
                response = CaseApi.getCases(new GetCases().setManufacturerId(1015).setBranchId(null));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                cases = response.body().as(Case.class);
                for (Cases caseItem : cases.getCases()) {
                    GetCases getCases = new GetCases();
                    getCases.setBranchId(null).setIds(String.valueOf(caseItem.getId()));
                    Response response2 = CaseApi.getCases(getCases);
                    Assert.assertEquals(response2.getBody().asString(), 200, response2.getStatusCode());
                    Case caseDetails = response2.body().as(Case.class);
                    Assert.assertEquals(String.valueOf(caseItem.getId()), String.valueOf(caseDetails.getCases()[0].getId()));
                }
                break;
            case "BranchId":
                response = CaseApi.getCases(new GetCases().setBranchId(149));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                cases = response.body().as(Case.class);
                for (Cases caseItem : cases.getCases()) {
                    Assert.assertEquals(149, caseItem.getBranchId());
                }
                break;
            case "BranchErpCode":
                response = CaseApi.getCases(new GetCases().setBranchId(null).setBranchErpCode("55-Wash"));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                cases = response.body().as(Case.class);
                for (Cases caseItem : cases.getCases()) {
                    Assert.assertEquals("55-Wash", caseItem.getBranchErpCode());
                }
                break;
            case "purchaseOrderReceived":
                boolean [] purchaseOrderReceived = {true, false};
                for (boolean purchaseOrder : purchaseOrderReceived) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setPurchaseOrderReceived(purchaseOrder));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(purchaseOrder){
                            Assert.assertNotNull("case id "+caseItem.getId()+ " does not have a purchase order number" , caseItem.getPurchaseOrderNumber());
                        }else {
                            Assert.assertNull("case id "+caseItem.getId()+ " has a purchase order number" , caseItem.getPurchaseOrderNumber());
                        }
                    }
                }
                break;
            case "confirmed":
                boolean [] confirmedValue = {true, false};
                for (boolean confirmed : confirmedValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setConfirmed(confirmed));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(confirmed){
                            Assert.assertTrue("case id "+caseItem.getId()+ " does not have the expected confirm value" , caseItem.isConfirmed());
                        }else {
                            Assert.assertFalse("case id "+caseItem.getId()+ " does not have the expected confirm value" , caseItem.isConfirmed());
                        }
                    }
                }
                break;
            case "billed":
                boolean [] billedValue = {true, false};
                for (boolean billed : billedValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setBilled(billed));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(billed){
                            Assert.assertTrue("case id "+caseItem.getId()+ " does not have the expected billed value" , caseItem.isCaseBilled());
                        }else {
                            Assert.assertFalse("case id "+caseItem.getId()+ " does not have the expected billed value" , caseItem.isCaseBilled());
                        }
                    }
                }
                break;
            case "approved":
                boolean [] approvedValue = {true, false};
                for (boolean approved : approvedValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setApproved(approved));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(approved){
                            Assert.assertEquals("case id "+caseItem.getId()+ " does not have the expected assembled value","Approved" , caseItem.getCaseApprovalType());
                        }else {
                            Assert.assertEquals("case id "+caseItem.getId()+ " does not have the expected assembled value","Pending" , caseItem.getCaseApprovalType());
                        }
                    }
                }
                break;
            case "usageReceived":
                boolean [] usageReceivedValue = {true, false};
                for (boolean usageReceived : usageReceivedValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setUsageReceived(usageReceived));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(usageReceived){
                            Assert.assertTrue("case id "+caseItem.getId()+ " does not have the expected usageReceived value" , caseItem.isUsageReceived());
                        }else {
                            Assert.assertFalse("case id "+caseItem.getId()+ " does not have the expected usageReceived value" , caseItem.isUsageReceived());
                        }
                    }
                }
                break;
            case "usageCompleted":
                boolean [] usageCompletedValue = {true, false};
                for (boolean usageCompleted : usageCompletedValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setUsageCompleted(usageCompleted));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(usageCompleted){
                            Assert.assertTrue("case id "+caseItem.getId()+ " does not have the expected usageCompleted value" , caseItem.isUsageCompleted());
                        }else {
                            Assert.assertFalse("case id "+caseItem.getId()+ " does not have the expected usageCompleted value" , caseItem.isUsageCompleted());
                        }
                    }
                }
                break;
            case "orderHold":
                boolean [] orderHoldValue = {true, false};
                for (boolean orderHold : orderHoldValue) {
                    response = CaseApi.getCases(new GetCases().setBranchId(null).setOrderHold(orderHold));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    cases = response.body().as(Case.class);
                    for (Cases caseItem : cases.getCases()) {
                        if(orderHold){
                            Assert.assertTrue("case id "+caseItem.getId()+ " does not have the expected orderHold value" , caseItem.isOrderHold());
                        }else {
                            Assert.assertFalse("case id "+caseItem.getId()+ " does not have the expected orderHold value" , caseItem.isOrderHold());
                        }
                    }
                }
                break;
            default:
                Assert.fail("Field not found");
        }
    }
}