package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

@Getter
public class TestExecutions {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private TestExecutionsValues[] values;

}