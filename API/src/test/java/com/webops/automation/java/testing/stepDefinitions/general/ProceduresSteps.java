package com.webops.automation.java.testing.stepDefinitions.general;
import com.webops.automation.java.testing.helpers.ProceduresApi;
import com.webops.automation.java.testing.objects.bodies.*;
import com.webops.automation.java.testing.objects.reponses.*;
import helpers.DefaultValues;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class ProceduresSteps {


    @Then("the user can create a new procedure")
    public void theUserCanCreateANewProcedure() {
        Response response = ProceduresApi.createNewProcedure(new PostNewProcedure());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Procedure procedure = response.body().as(Procedure.class);
        Assert.assertNotNull(procedure.getProcedures());
    }

    @Then("the user can create a new procedure only with the required fields")
    public void theUserCanCreateANewProcedureOnlyWithTheRequiredFields() {
        Response response = ProceduresApi.createNewProcedure(new PostProcedureWithRequiredFileds(true));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Procedure procedure = response.body().as(Procedure.class);
        Assert.assertNotNull(procedure.getProcedures());
    }

    @Then("the user can't create a new procedure without all the required fields")
    public void theUserCanTCreateANewProcedureWithoutAllTheRequiredFields() {
        Response response = ProceduresApi.createNewProcedure(new PostProcedureWithRequiredFileds(false));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Required field name is missing or empty", response.body().jsonPath().getString("message"));
    }

    @Then("the user can update a procedure")
    public void theUserCanUpdateAProcedure() {
        Procedure procedure = ProceduresApi.createNewProcedure(new PostNewProcedure()).body().as(Procedure.class);
        String newName = DefaultValues.generateRandomString(5);
        String newDescription = DefaultValues.generateRandomString(10);
        Response response = ProceduresApi.updateProcedure(new PostEditProcedure(newName, newDescription), String.valueOf(procedure.getProcedures()[0].getId()));
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Procedure updatedProcedure = response.body().as(Procedure.class);
        Assert.assertEquals(updatedProcedure.getProcedures()[0].getName(), newName);
        Assert.assertEquals(updatedProcedure.getProcedures()[0].getDescription(), newDescription);
    }

    @Then("user can not update a procedure using a companyId that does not belong to the manufacturer")
    public void userCanNotUpdateAProcedureUsingACompanyIdThatDoesNotBelongToTheManufacturer() {
        Procedure procedure = ProceduresApi.createNewProcedure(new PostNewProcedure()).body().as(Procedure.class);
        Response response = ProceduresApi.updateProcedure(new PostEditProcedure("zzz", "zzzz").setCompanyIds(String.valueOf(7)), String.valueOf(procedure.getProcedures()[0].getId()));
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "companyId 7 does not belong to the provided manufacturer.", response.jsonPath().getString("message"));

    }

    @Then("the user can not update a procedure without using the {string} field")
    public void theUserCanNotUpdateAProcedureWithoutUsingTheRequiredFieldField(String field) {
        Procedure procedure = ProceduresApi.createNewProcedure(new PostNewProcedure()).body().as(Procedure.class);
        String id = String.valueOf(procedure.getProcedures()[0].getId());
        Response response;
        switch (field) {
            case "messageId":
                response = ProceduresApi.updateProcedure(new PostEditProcedure("zz", "zzzz").setMessageId(""), id);
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "messageId value is blank.", response.jsonPath().getString("message"));
                break;
            case "timestamp":
                response = ProceduresApi.updateProcedure(new PostEditProcedure("zz", "zzzz").setTimestamp(""), id);
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "An unexpected server error occurred while processing the request", response.jsonPath().getString("message"));
                break;
            case "manufacturerId":
                response = ProceduresApi.updateProcedure(new PostEditProcedure("zz", "zzzz").setManufacturerId(null), id);
                Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
                Assert.assertEquals(response.getBody().asString(), "Manufacturer ID is blank or not provided.", response.jsonPath().getString("message"));
                break;
            default:
                Assert.fail("Field not found");
        }
    }
}