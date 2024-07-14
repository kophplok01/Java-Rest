package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class TestSteps {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private Values[] values;

}