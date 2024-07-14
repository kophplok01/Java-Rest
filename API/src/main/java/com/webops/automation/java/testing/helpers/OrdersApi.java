package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;

public class OrdersApi extends AbstractRequest {

    public static Response getOrderByCaseId(GetOrder getOrder) {
        return getAuthenticatedRequestSpecs()
                .basePath("/1.0/orders")
                .body(getOrder)
                .post();
    }
}