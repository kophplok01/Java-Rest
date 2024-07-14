package com.webops.automation.java.testing.stepDefinitions.general;

import com.webops.automation.java.testing.helpers.BranchesApi;
import com.webops.automation.java.testing.objects.bodies.PostGetBranches;
import com.webops.automation.java.testing.objects.reponses.GetBranches;
import com.webops.automation.java.testing.objects.reponses.Branch;

import helpers.DBBranches;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import objects.DbBranch;
import org.junit.Assert;

import java.time.Instant;

public class branchSteps {
    @Then("the user can get branches by {string}")
    public void theUserCanGetBranchesBySearchType(String searchType) {
        String stepName = "the user can get branches by " + searchType;
        String stepDescription = "";
        String stepExpectedResult = "";
        Response response;
        GetBranches branches;
        switch (searchType) {
            case "ManufacturerId":
                stepDescription = "Retrieve branches by a specific ManufacturerId";
                stepExpectedResult = "Status code should be 200. All returned branches should belong to the provided ManufacturerId";
                response = BranchesApi.getBranches(new PostGetBranches().setManufacturerId(1015));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                for (Branch branch : branches.getBranches()) {
                    DbBranch dbBranch = DBBranches.getBranchById(branch.getId());
                    Assert.assertEquals("Branch does not belong to the expected manufacturer", 1015, dbBranch.getManufacturer_id() );
                }
                break;
            case "ids":
                stepDescription = "Retrieve branches by a specific id";
                stepExpectedResult = "Status code should be 200. The returned branch should have the expected id.";
                response = BranchesApi.getBranches(new PostGetBranches().setIds("149"));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Assert.assertEquals("Branch id does not match", 149, branches.getBranches()[0].getId());
                Assert.assertEquals( 1, branches.getBranches().length);
                break;
            case "name":
                stepDescription = "Retrieve branches by a specific name)";
                stepExpectedResult = "Status code should be 200. The returned branch should have the expected name'.";
                response = BranchesApi.getBranches(new PostGetBranches().setName("Pacific Northwest Washington (F38)"));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Assert.assertEquals("Branch name does not match", "Pacific Northwest Washington (F38)", branches.getBranches()[0].getName());
                break;
            case "erpCode":
                stepDescription = "Retrieve branches by a specific ERP code";
                stepExpectedResult = "Status code should be 200. The returned branch should have the expected ERP code.";
                response = BranchesApi.getBranches(new PostGetBranches().setErpCode("55-Wash"));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Assert.assertEquals("Branch name does not match", "55-Wash", branches.getBranches()[0].getErpCode());
                break;
            case "limit":
                stepDescription = "Retrieve branches defining a limit of 20 branches to be returned";
                stepExpectedResult = "Status code should be 200. The number of returned branches should be 20.";
                response = BranchesApi.getBranches(new PostGetBranches().setLimit(20));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Assert.assertEquals( 20, branches.getBranches().length);
                break;
            case "active":
                stepDescription = "Retrieve branches by their active status (true/false)";
                stepExpectedResult = "Status code should be 200. The returned branches should match the specified active status.";
                boolean [] activeValue = {true, false};
                for (boolean active : activeValue) {
                    response = BranchesApi.getBranches(new PostGetBranches().setActive(active));
                    Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                    branches = response.body().as(GetBranches.class);
                    for (Branch branch : branches.getBranches()) {
                        if(active){
                            Assert.assertTrue("branch id "+branch.getId()+ " does not have the expected active value" , branch.isActive());
                        }else {
                            Assert.assertFalse("brancg id "+branch.getId()+ " does not have the expected active value" , branch.isActive());
                        }
                    }
                }
                break;
            case "updatedAtMin":
                stepDescription = "Retrieve branches updated after a specific date ";
                stepExpectedResult = "Status code should be 200. The ChangedDate field should be after the provided date for all returned branches.";
                String startDate = "2023-10-11T13:15:30Z";
                response = BranchesApi.getBranches(new PostGetBranches().setUpdatedAtMin(startDate));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Instant startInstant = Instant.parse(startDate);
                for (Branch branch : branches.getBranches()) {
                    Instant branchChangedDate = Instant.parse(branch.getChangedDate());
                    Assert.assertTrue("Branch updated at date is before the expected date", branchChangedDate.isAfter(startInstant));
                }
                break;
            case "updatedAtMax":
                stepDescription = "Retrieve branches within a specific date range";
                stepExpectedResult = "Status code should be 200. The ChangedDate field should be after the provided starDate and Before the endDate for all returned branches.";
                String startDate2 = "2023-10-11T13:15:30Z";
                String endDate = "2023-10-11T13:15:30Z";
                response = BranchesApi.getBranches(new PostGetBranches().setUpdatedAtMin(startDate2).setUpdatedAtMax(endDate));
                Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
                branches = response.body().as(GetBranches.class);
                Instant startInstant2 = Instant.parse(startDate2);
                Instant endInstant = Instant.parse(endDate);
                for (Branch branch : branches.getBranches()) {
                    Instant branchChangedDate = Instant.parse(branch.getChangedDate());
                    Assert.assertTrue("Branch updated at date is after the expected date", branchChangedDate.isAfter(startInstant2));
                    Assert.assertTrue("Branch updated at date is after the expected date", branchChangedDate.isBefore(endInstant));
                }
                break;
            default:
                Assert.fail("Field not found");
        }
        System.setProperty("stepName", stepName);
        System.setProperty("stepDescription", stepDescription);
        System.setProperty("stepExpectedResult", stepExpectedResult);
    }

    @Then("the user can not get branches without using the required field {string}")
    public void theUserCanNotGetBranchesWithoutUsingTheRequiredFieldField(String field) {
        String stepName = "the user can not get branches without using the required field " + field;
        String stepDescription = "";
        String stepExpectedResult = "";
        Response response;
        switch (field) {
            case "ManufacturerId":
                response = BranchesApi.getBranches(new PostGetBranches().setManufacturerId(null));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals("Manufacturer ID is blank or not provided.", response.body().jsonPath().getString("message"));
                stepDescription = "The user attempts to get branches without providing ManufacturerId.";
                stepExpectedResult = "Expected result: The request should fail with a status code 422. The response message should be 'Manufacturer ID is blank or not provided.'";
                break;
            case "timestamp":
                response = BranchesApi.getBranches(new PostGetBranches().setTimestamp(null));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals("An unexpected server error occurred while processing the request", response.body().jsonPath().getString("message"));
                stepDescription = "The user attempts to get branches without providing timestamp.";
                stepExpectedResult = "Expected result: The request should fail with a status code 422. The response message should be 'An unexpected server error occurred while processing the request'.";
                break;
            case "messageId":
                response = BranchesApi.getBranches(new PostGetBranches().setMessageId(null));
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals("messageId value is blank.", response.body().jsonPath().getString("message"));
                stepDescription = "The user attempts to get branches without providing messageId.";
                stepExpectedResult = "Expected result: The request should fail with a status code 422. The response message should be 'messageId value is blank.'";
                break;
            default:
                Assert.fail("Field not found");
        }

        System.setProperty("stepName", stepName);
        System.setProperty("stepDescription", stepDescription);
        System.setProperty("stepExpectedResult", stepExpectedResult);
    }
}
