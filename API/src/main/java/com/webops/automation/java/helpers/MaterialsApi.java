package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;

public class MaterialsApi extends AbstractRequest {
    private static final String BASEPATH = "/1.0/";

    public static Response createNewMaterial(PostNewMaterial newMaterial) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadMaterial")
                .body(newMaterial)
                .post();
    }

    public static Response createMultipleMaterials(PostMultipleMaterials postMultipleMaterials) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"loadMaterials")
                .body(postMultipleMaterials)
                .post();
    }

    public static Response getMaterials(GetOpsTerritories getOpsTerritories) {
        return getAuthenticatedRequestSpecs()
                .basePath(BASEPATH+"materials")
                .body(getOpsTerritories)
                .post();
    }

}