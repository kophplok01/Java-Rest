package com.webops.automation.java.testing.stepDefinitions.general;
import com.webops.automation.java.testing.helpers.MaterialsApi;
import com.webops.automation.java.testing.objects.reponses.Materials;
import com.webops.automation.java.testing.objects.bodies.*;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class MaterialsSteps {
    @Then("the user can create a new material")
    public void theUserCanCreateANewMaterial() {
        Response response = MaterialsApi.createNewMaterial(new PostNewMaterial());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Successful", response.jsonPath().getString("message"));
    }

    @Then("the user can create multiple materials at the same time")
    public void theUserCanCreateMultipleMaterialsAtTheSameTime() {
        Response response = MaterialsApi.createMultipleMaterials(new PostMultipleMaterials());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
    }

    @Then("the user can get all created materials")
    public void theUserCanGetAllCreatedMaterials() {
        Response response = MaterialsApi.getMaterials(new GetOpsTerritories());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Materials materials = response.as(Materials.class);
        Assert.assertTrue(materials.getMaterials().length > 0);
    }
}