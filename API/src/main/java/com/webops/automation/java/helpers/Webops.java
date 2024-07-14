package com.webops.automation.java.testing.helpers;
import io.restassured.response.Response;
import org.junit.Assert;


public class Webops extends AbstractRequest {
    public static String accessToken;

    public static void loginWebops() {
        Response response;
        response = initiateWebopsAuthentication();
        Assert.assertEquals("Status code is not 200", 200, response.getStatusCode());
        Assert.assertNotNull("Access token cant be null", response.getHeader("Access_token"));
        accessToken = response.getHeader("Access_token");
    }

    private static Response initiateWebopsAuthentication() {
        return getBasicWebOpsRequestSpecs()
                .basePath("/1.0/token")
                .post();
    }
}