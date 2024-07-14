package com.webops.automation.java.testing.Zephyr.Helpers;

import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewTestCase;
import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewTestStep;
import io.restassured.response.Response;

import java.util.HashMap;


public class TestCasesApi extends AbstractRequest {

    public static String basepath = "/testcases";

    public static Response getTestCasesByFolder(HashMap<String, String> params) {
        params.putIfAbsent("folderId", "0");
        params.putIfAbsent("maxResults", "1000");
        return getZephyrAuthenticatedRequestSpecs()
                .queryParams(params)
                .basePath(basepath)
                .get();
    }

    public static Response createNewCase(PostNewTestCase postNewTestCase) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .body(postNewTestCase)
                .post();
    }

    public static Response createTestStep(PostNewTestStep postNewTestStep, String testCaseId) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath+"/"+testCaseId+"/teststeps")
                .body(postNewTestStep)
                .post();
    }

    public static Response getTestSteps(String testCaseId) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath+"/"+testCaseId+"/teststeps")
                .get();
    }

}