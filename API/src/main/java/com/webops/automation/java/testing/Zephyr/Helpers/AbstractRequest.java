package com.webops.automation.java.testing.Zephyr.Helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import objects.PropertiesFile;
import objects.User;

class AbstractRequest {
     static RequestSpecification getZephyrAuthenticatedRequestSpecs() {
         String[] bearerToken = PropertiesFile.Data.readProperties("zephyr");
         return RestAssured.given()
                .baseUri("https://" + PropertiesFile.Urls.read("zephyr"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + User.getPassword(bearerToken[0]));
    }
}