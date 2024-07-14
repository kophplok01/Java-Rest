package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;

public class CustomersApi extends AbstractRequest {
    private static final String BASEPATH = "/1.0/";

    public static Response createNewCustomer(PostNewCustomer newCustomer) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadCustomer")
                .body(newCustomer)
                .post();
    }

    public static Response createMultipleCustomers(PostMultipleCustomers postMultipleCustomers) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadCustomers")
                .body(postMultipleCustomers)
                .post();
    }

    public static Response getCustomers(GetOpsTerritories getOpsTerritories) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"customers")
                .body(getOpsTerritories)
                .post();
    }

}