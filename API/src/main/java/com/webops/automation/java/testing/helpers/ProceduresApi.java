package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;

public class ProceduresApi extends AbstractRequest {

    public static Response createNewProcedure(PostNewProcedure postNewProcedure) {
        return getAuthenticatedRequestSpecs()
                .basePath("/1.0/procedure")
                .body(postNewProcedure)
                .post();
    }

    public static Response createNewProcedure(PostProcedureWithRequiredFileds postProcedureWithRequiredFileds) {
        return getAuthenticatedRequestSpecs()
                .basePath("/1.0/procedure")
                .body(postProcedureWithRequiredFileds)
                .post();
    }

    public static Response updateProcedure(PostEditProcedure postEditProcedure, String procedureId) {
        return getAuthenticatedRequestSpecs()
                .basePath("/1.0/procedure/"+procedureId)
                .body(postEditProcedure)
                .put();
    }
}