package com.webops.automation.java.testing.Zephyr.Objects.Responses;

import lombok.Getter;

@Getter
public class TestCaseValues {

    private int id;
    private String key;
    private String name;
    private Project project;
    private String createdOn;
    private String objective;
    private String precondition;
    private String estimatedTime;
    private Labels[] labels;
    private Priority priority;
    private Status status;
    private Folder folder;
    private Owner owner;
    private TestScript testScript;
    private Links links;
}