package com.webops.automation.java.testing.Zephyr.Helpers;

import com.webops.automation.java.testing.Zephyr.Objects.Bodies.PostNewFolder;
import io.restassured.response.Response;

import java.util.HashMap;


public class FoldersApi extends AbstractRequest {

    public static String basepath = "/folders";

    public static Response getFoldersByProjectId(HashMap<String, String> params) {
        params.putIfAbsent("maxResults", "1000");
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .params(params)
                .get();
    }

    public static Response createNewFolder(PostNewFolder postNewFolder) {
        return getZephyrAuthenticatedRequestSpecs()
                .basePath(basepath)
                .body(postNewFolder)
                .post();
    }

}