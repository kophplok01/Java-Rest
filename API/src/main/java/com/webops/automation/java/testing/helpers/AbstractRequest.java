package com.webops.automation.java.testing.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import objects.Url;
import objects.User;

class AbstractRequest {

    static RequestSpecification getBasicWebOpsRequestSpecs() {
        String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString(
                (User.getActiveUser().getAccountname() + ":" + User.getActiveUser().getPassword()).getBytes());
        return new RestAssured().given()
                .baseUri(Url.getBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", basicAuthHeader);
    }

    static RequestSpecification getAuthenticatedRequestSpecs() {
        String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString(
                (User.getActiveUser().getAccountname() + ":" + User.getActiveUser().getPassword()).getBytes());
        return new RestAssured().given()
                .baseUri(Url.getBaseUrl())
                .contentType(ContentType.JSON)
                .header("Authorization", basicAuthHeader)
                .header("Access_token", Webops.accessToken);
    }
}