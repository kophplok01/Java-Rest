package com.webops.automation.java.testing.stepDefinitions;
import helpers.DBSalesRep;
import io.cucumber.java.en.Then;
import com.webops.automation.java.testing.components.Components;
import objects.Manufacturers;
import objects.SalesRepId;
import objects.User;

public class NavigationSteps extends AbstractPages {

    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage() {
        Components.header().isDisplayed();
        Components.footer().isDisplayed();
        Manufacturers[] manufacturers = User.getManufacturersForAdminSuperUser();
        SalesRepId[] salesRepIds = DBSalesRep.getAllSalesRepId();
        SalesRepId salesRepId = DBSalesRep.getRandomSalesRepIdByBranchId(149);
        String dd = User.getActiveUser().getFirstName();

    }
}