package com.webops.automation.java.testing.Zephyr.Helpers;

import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewExecution;
import com.webops.automation.java.testing.Zephyr.Objects.Bodies.UpdateExecution;
import io.restassured.response.Response;

import java.util.HashMap;


public class TestExecutionApi extends AbstractRequest {

    public static String basepath = "/testexecutions";

    public static Response createNewTestExecutions(PostNewExecution postNewExecution) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .body(postNewExecution)
                .post();
    }

    public static Response updateTestExecution(UpdateExecution updateExecution, String testExecutionId) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath+"/"+testExecutionId)
                .body(updateExecution)
                .put();
    }

    public static Response getTestExecutions(HashMap<String, String> params) {
        params.putIfAbsent("maxResults", "1000");
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .queryParams(params)
                .get();
    }

    public static Response getTestExecutions() {
        return getTestExecutions(new HashMap<>());
    }
}