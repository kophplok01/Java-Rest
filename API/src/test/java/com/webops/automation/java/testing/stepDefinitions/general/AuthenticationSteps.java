package com.webops.automation.java.testing.stepDefinitions.general;
import com.webops.automation.java.testing.helpers.Authentication;
import io.cucumber.java.en.Given;

public class AuthenticationSteps {

    @Given("^the user is authenticated as a (.*)$")
    public void webOpsAuthentication(String actor) {
        System.setProperty("stepName", "the user is authenticated as a " + actor);
        System.setProperty("stepDescription", "the user is authenticated as a " + actor+ " using the WebOps API -- Test4 is the environment that contains" +
                " the latest version of the API");
        System.setProperty("stepExpectedResult", "The user is authenticated and able to send any request he wants");
        Authentication.authenticate(actor);
    }

}