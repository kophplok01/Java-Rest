package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.GetOpsTerritories;
import com.webops.automation.java.testing.objects.bodies.PostMultipleOpsTerritories;
import com.webops.automation.java.testing.objects.bodies.PostNewOpsTerritory;
import io.restassured.response.Response;

public class OpsTerritoryApi extends AbstractRequest {
    private static final String BASEPATH = "/1.0/";

    public static Response createNewOpsTerritory(PostNewOpsTerritory newOpsTerritory) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadSingleOpsTerritory")
                .body(newOpsTerritory)
                .post();
    }

    public static Response createMultipleOpsTerritories(PostMultipleOpsTerritories newPostMultipleOpsTerritories) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadOpsTerritory")
                .body(newPostMultipleOpsTerritories)
                .post();
    }

    public static Response getOpsTerritories(GetOpsTerritories getOpsTerritories) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"opsTerritory")
                .body(getOpsTerritories)
                .post();
    }

}