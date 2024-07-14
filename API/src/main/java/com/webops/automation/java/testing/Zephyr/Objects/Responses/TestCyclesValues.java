package com.webops.automation.java.testing.Zephyr.Objects.Responses;

import lombok.Getter;

@Getter
public class TestCyclesValues {

    private int id;
    private String key;
    private String name;
    private Project project;
    private Status status;
    private Folder folder;
    private String description;
    private String plannedStartDate;
    private String plannedEndDate;
    private Owner owner;
}