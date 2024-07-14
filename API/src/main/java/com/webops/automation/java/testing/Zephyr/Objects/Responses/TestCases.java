package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class TestCases {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private TestCaseValues[] values;

    public TestCaseValues getTestCaseByName(String name) {
        return Arrays.stream(values)
                .filter(testCase -> testCase.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}