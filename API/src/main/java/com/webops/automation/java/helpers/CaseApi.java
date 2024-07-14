package com.webops.automation.java.testing.helpers;

import com.webops.automation.java.testing.objects.bodies.*;
import io.restassured.response.Response;

public class CaseApi extends AbstractRequest {

    public static Response createNewCase(PostNewCase newCase) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case")
                .body(newCase)
                .post();
    }

    public static Response createNewCaseErp(PostNewCaseErpWithOnlyRequiredFields newCaseErp) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/erp")
                .body(newCaseErp)
                .post();
    }

    public static Response createNewCaseErp(PostNewCaseErp newCaseErp) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/erp")
                .body(newCaseErp)
                .post();
    }

    public static Response createNewCaseErp(PostNewCase newCase) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/erp")
                .body(newCase)
                .post();
    }

    public static Response createNewCase(PostNewCaseOnlyRequieredFileds newCase) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case")
                .body(newCase)
                .post();
    }

    public static Response getCases(GetCases getCases) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/cases")
                .body(getCases)
                .post();
    }

    public static Response editCase(PostEditCase postEditCase, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/"+id)
                .body(postEditCase)
                .put();
    }

    public static Response editCaseErp(PostEditCaseErp postEditCaseErp, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/erp/"+id)
                .body(postEditCaseErp)
                .put();
    }

    public static Response editCaseErp(PostEditCaseErpWithOnlyRequiredFields postEditCaseErpWithOnlyRequiredFields, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/erp/"+id)
                .body(postEditCaseErpWithOnlyRequiredFields)
                .put();
    }

    public static Response editCase(EditCaseWithOnlyRequieredFields editCaseWithOnlyRequieredFields, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/"+id)
                .body(editCaseWithOnlyRequieredFields)
                .put();
    }

    public static Response postCaseUsage(PostCaseUsage postCaseUsage, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/1.0/case/"+id+"/usage")
                .body(postCaseUsage)
                .post();
    }

    public static Response cancelCase(PostCancelCase postCancelCase, String id) {
        return getAuthenticatedRequestSpecs()
                .basePath("/2.0/case/"+id+"/cancel")
                .body(postCancelCase)
                .put();
    }


}