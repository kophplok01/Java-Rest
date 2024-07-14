package com.webops.automation.java.testing.stepDefinitions;

import helpers.DBUser;
import io.cucumber.java.en.When;
import com.webops.automation.java.testing.helpers.BrowserHelper;
import objects.Url;
import objects.User;
import io.cucumber.java.en.Given;
import lombok.SneakyThrows;

public class AuthenticationSteps extends AbstractPages {


    @SneakyThrows
    @Given("^the user is logged into '(:?test8|test4|test3)' as a '(.*)'$")
    public void logonProvider(String provider, String actor) {
        actor = actor.replace(" ", "");
        switch (provider) {
            case "test4":
                User.setActiveUser(new User("test4" + actor));
                break;
            case "test3":
                User.setActiveUser(new User("test3" + actor));
                break;
            default:
                User.setActiveUser(new User(actor));
        }
        logonWebops();
        auth0Page().saveCookies();
       //  needs to be implemented to Webops.setProfile(AuthApi.getProfile().body().as(GetProfile.class));

    }
    private void logonWebops() {
        Sleep(3);
        auth0Page().isDisplayed();
        User user = User.getActiveUser();
        auth0Page().login(user);
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        BrowserHelper.getPage().navigate(Url.getBaseUrl());
        auth0Page().isDisplayed();
    }

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        User.setActiveUser(new User("adminTest8"));
        DBUser.setUserDetails();
        auth0Page().login(User.getActiveUser());
    }

}