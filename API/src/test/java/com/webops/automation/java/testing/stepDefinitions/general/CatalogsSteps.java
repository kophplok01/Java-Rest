package com.webops.automation.java.testing.stepDefinitions.general;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webops.automation.java.testing.helpers.CatalogsApi;
import com.webops.automation.java.testing.objects.bodies.*;
import com.webops.automation.java.testing.objects.reponses.CreateMultipleCatalgs;
import com.webops.automation.java.testing.objects.reponses.GetAllCatalogs;
import helpers.DefaultValues;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static com.webops.automation.java.testing.helpers.CatalogsApi.retrievePath;

public class CatalogsSteps {

    @Then("the user can get all created catalogs")
    public void theUserCanGetAllCreatedCatalogs() {
        Response response = CatalogsApi.getCatalogs(new GetCatalogs());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        GetAllCatalogs catalogs = response.as(GetAllCatalogs.class);
        Assert.assertTrue(catalogs.getCatalogItem().length > 0);
    }

    @Then("the user can see an error message when trying to get all created for an un-existent company")
    public void theUserCanSeeAnErrorMessageWhenTryingToGetAllCreatedForAnUnExistentCompany() {
        GetCatalogs getCatalogs = new GetCatalogs();
        getCatalogs.setCompanyId(1234);
        Response response = CatalogsApi.getCatalogs(getCatalogs);
        Assert.assertEquals(response.getBody().asString(), 422, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "companyId 1234 is unknown or invalid", response.jsonPath().getString("message"));
    }

    @Then("the user can create multiple catalogs at the same time")
    public void theUserCanCreateMultipleCatalogsAtTheSameTime() {
        System.setProperty("stepName", "the user can create multiple catalogs at the same time");
        System.setProperty("stepExpectedResult", "The API call should return a status code of 200, and the response message should indicate success.");
        PostMultipleCatalogs postMultipleCatalogs = new PostMultipleCatalogs();
        Response response = CatalogsApi.createMultipleCatalogs(postMultipleCatalogs);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String postMultipleCatalogsJson = gson.toJson(postMultipleCatalogs);
        String lineSeparator = System.lineSeparator();
        String stepDescription = String.format(
                "<ol>%s" +
                        "    <li>Verify that the user has the ability to create multiple catalogs simultaneously using the API endpoint.</li>%s" +
                        "    <li><strong>URL:</strong> %s</li>%s" +
                        "    <li><strong>Body:</strong></li>%s" +
                        "    <li><pre>%s</pre></li>%s" +
                        "</ol>",
                lineSeparator, lineSeparator, retrievePath, lineSeparator, lineSeparator, postMultipleCatalogsJson, lineSeparator
        );
        System.setProperty("stepDescription", stepDescription);

    }

    @Then("the user can not create multiple catalogs with the same gtin")
    public void theUserCanNotCreateMultipleCatalogsWithTheSameGtin() {
        PostMultipleCatalogs postMultipleCatalogs = new PostMultipleCatalogs();
        GlobalTradeItemNumbers globalTradeItemNumbers = new GlobalTradeItemNumbers();
        globalTradeItemNumbers.setGtin("12345678901234");
        postMultipleCatalogs.getItems()[0].setGlobalTradeItemNumbers(new GlobalTradeItemNumbers[]{globalTradeItemNumbers});
        postMultipleCatalogs.getItems()[1].setGlobalTradeItemNumbers(new GlobalTradeItemNumbers[]{globalTradeItemNumbers});
        Response response = CatalogsApi.createMultipleCatalogs(postMultipleCatalogs);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "gtin 12345678901234 already exists. Value must be unique.", response.jsonPath().getString("details[-1].message"));
    }

    @Then("the user can not create multiple catalogs with a wrong gtin")
    public void theUserCanNotCreateMultipleCatalogsWithAWrongGtin() {
        PostMultipleCatalogs postMultipleCatalogs = new PostMultipleCatalogs();
        GlobalTradeItemNumbers globalTradeItemNumbers = new GlobalTradeItemNumbers();
        String sdad = "12345678901";
        globalTradeItemNumbers.setGtin("12345678901");
        System.out.println(sdad);
        postMultipleCatalogs.getItems()[0].setGlobalTradeItemNumbers(new GlobalTradeItemNumbers[]{globalTradeItemNumbers});
        postMultipleCatalogs.getItems()[1].setGlobalTradeItemNumbers(new GlobalTradeItemNumbers[]{globalTradeItemNumbers});
        Response response = CatalogsApi.createMultipleCatalogs(postMultipleCatalogs);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Global Trade Identifier Number must be 14 characters", response.jsonPath().getString("details[-1].message"));
    }

    @Then("the user can see successfully edit a created catalog")
    public void theUserCanSeeSuccessfullyEditACreatedCatalog() {
        PostMultipleCatalogs postMultipleCatalogs = new PostMultipleCatalogs();
        Response response = CatalogsApi.createMultipleCatalogs(postMultipleCatalogs);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
        CreateMultipleCatalgs createMultipleCatalgs = response.as(CreateMultipleCatalgs.class);
        String toEdit1 = DefaultValues.generateRandomString(10);
        String toEdit2 = DefaultValues.generateRandomString(10);
        Response response1 = CatalogsApi.editMultipleCatalogs(new EditMultipleCatalogsItems(
                createMultipleCatalgs.getDetails()[0].getInventoryItemId(),
                createMultipleCatalgs.getDetails()[1].getInventoryItemId(),
                toEdit1, toEdit2));
        Assert.assertEquals(response1.getBody().asString(), 200, response1.getStatusCode());
        Assert.assertEquals(response1.getBody().asString(), "Success", response1.jsonPath().getString("message"));
        CreateMultipleCatalgs editMultipleCatalogs = response1.as(CreateMultipleCatalgs.class);
        Assert.assertEquals(editMultipleCatalogs.getDetails()[0].getCatalogNumber(), toEdit1);
        Assert.assertEquals(editMultipleCatalogs.getDetails()[1].getCatalogNumber(), toEdit2);
    }

    @Then("the user can not create multiple catalogs with the same catalog number")
    public void theUserCanNotCreateMultipleCatalogsWithTheSameCatalogNumber() {
        PostMultipleCatalogsItems postMultipleCatalogsItems = new PostMultipleCatalogsItems().setCatalogNumber("1234567890");
        PostMultipleCatalogsItems postMultipleCatalogsItems1 = new PostMultipleCatalogsItems().setCatalogNumber("1234567890");
        PostMultipleCatalogs postMultipleCatalogs = new PostMultipleCatalogs(new PostMultipleCatalogsItems[]{postMultipleCatalogsItems, postMultipleCatalogsItems1});
        Response response = CatalogsApi.createMultipleCatalogs(postMultipleCatalogs);
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Catalog Number already exists", response.jsonPath().getString("details[-1].message"));
    }
}