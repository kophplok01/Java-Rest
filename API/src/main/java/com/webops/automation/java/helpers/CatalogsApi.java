package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class CatalogsApi extends AbstractRequest {
    private static final String BASEPATH = "/1.0/";
    public static String retrievePath = "";

    public static Response createMultipleCatalogs(PostMultipleCatalogs postMultipleCatalogs) {
        RequestSpecification requestSpec= (RequestSpecification) getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"catalogItems/bulk")
                .body(postMultipleCatalogs);
        System.out.println("PostMultipleCatalogs: " + postMultipleCatalogs.toString());
        retrievePath = SpecificationQuerier.query(requestSpec).getURI();
        return requestSpec.post();
    }

    public static Response editMultipleCatalogs(EditMultipleCatalogsItems editMultipleCatalogsItems) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"catalogItems")
                .body(editMultipleCatalogsItems)
                .put();
    }

    public static Response getCatalogs(GetCatalogs getCatalogs) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"catalogItems")
                .body(getCatalogs)
                .post();
    }

}