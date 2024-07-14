package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.PostGetBranches;
import io.restassured.response.Response;

public class BranchesApi extends AbstractRequest {
    private static final String BASEPATH = "/2.0/";

    public static Response getBranches(PostGetBranches getBranches) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"branches")
                .body(getBranches)
                .post();
    }
}