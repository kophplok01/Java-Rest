package com.webops.automation.java.testing.Zephyr.Helpers;

import com.webops.automation.java.testing.objects.bodies.PostNewCase;
import io.restassured.response.Response;


public class ProjectsApi extends AbstractRequest {

    public static String basepath = "/projects";

    public static Response getAllProjects() {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .get();
    }

}