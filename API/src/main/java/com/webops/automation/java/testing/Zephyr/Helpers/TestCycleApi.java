package com.webops.automation.java.testing.Zephyr.Helpers;

import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewTestCase;
import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewTestCycle;
import io.restassured.response.Response;

import java.util.HashMap;


public class TestCycleApi extends AbstractRequest {

    public static String basepath = "/testcycles";

    public static Response createNewTestCycle(PostNewTestCycle postNewTestCycle) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .body(postNewTestCycle)
                .post();
    }

    public static Response getTestCycles(HashMap<String, String> params) {
        params.putIfAbsent("maxResults", "1000");
        return getZephyrAuthenticatedRequestSpecs()
                .queryParams(params)
                .basePath(basepath)
                .get();
    }

    public static Response getTestCycles() {
        return getTestCycles(new HashMap<>());
    }

}