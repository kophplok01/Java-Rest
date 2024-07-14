package com.webops.automation.java.testing.stepDefinitions.general;
import com.webops.automation.java.testing.helpers.CustomersApi;
import com.webops.automation.java.testing.objects.reponses.GetCustomers;
import com.webops.automation.java.testing.objects.bodies.GetOpsTerritories;
import com.webops.automation.java.testing.objects.bodies.PostMultipleCustomers;
import com.webops.automation.java.testing.objects.bodies.PostNewCustomer;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class CustomersSteps {
    @Then("the user can create a new customer")
    public void theUserCanCreateANewCustomer() {
        Response response = CustomersApi.createNewCustomer(new PostNewCustomer());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Successful", response.jsonPath().getString("message"));
    }

    @Then("the user can create multiple customers at the same time")
    public void theUserCanCreateMultipleCustomersAtTheSameTime() {
        Response response = CustomersApi.createMultipleCustomers(new PostMultipleCustomers());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        Assert.assertEquals(response.getBody().asString(), "Success", response.jsonPath().getString("message"));
    }

    @Then("the user can get all created customers")
    public void theUserCanGetAllCreatedCustomers() {
        Response response = CustomersApi.getCustomers(new GetOpsTerritories());
        Assert.assertEquals(response.getBody().asString(), 200, response.getStatusCode());
        GetCustomers customers = response.as(GetCustomers.class);
        Assert.assertTrue(customers.getCustomers().length > 0);
    }
}