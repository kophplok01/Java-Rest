package com.webops.automation.java.testing.stepDefinitions.general;

import com.webops.automation.java.testing.objects.reponses.OpsTerritories;
import com.webops.automation.java.testing.objects.bodies.GetOpsTerritories;
import com.webops.automation.java.testing.objects.bodies.PostMultipleOpsTerritories;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import com.webops.automation.java.testing.helpers.OpsTerritoryApi;
import com.webops.automation.java.testing.objects.bodies.PostNewOpsTerritory;

public class OpsTerritorySteps {

    @Then("the user can load a new OpsTerritory")
    public void theUserCanLoadANewOpsTerritory() {
        Response response = OpsTerritoryApi.createNewOpsTerritory(new PostNewOpsTerritory());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Successful", response.jsonPath().getString("message"));
    }

    @Then("the user can load a multiples OpsTerritories at the same time")
    public void theUserCanLoadAMultiplesOpsTerritoriesAtTheSameTime() {
        Response response = OpsTerritoryApi.createMultipleOpsTerritories(new PostMultipleOpsTerritories());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
    }

    @Then("the user can get all OpsTerritories")
    public void theUserCanGetAllOpsTerritories() {
        Response response = OpsTerritoryApi.getOpsTerritories(new GetOpsTerritories());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        OpsTerritories opsTerritories = response.as(OpsTerritories.class);
        Assert.assertTrue(opsTerritories.getOpsTerritory().length > 0);
    }
}