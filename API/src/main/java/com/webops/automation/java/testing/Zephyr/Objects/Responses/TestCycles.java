package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class TestCycles {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private TestCyclesValues[] values;

    public TestCyclesValues getTestCycleByNameAndFolder(String name, Integer expectedFolderId) {
        return Arrays.stream(values)
                .filter(testCycle -> testCycle.getName().equals(name) &&
                        (testCycle.getFolder() == null ? expectedFolderId == null : testCycle.getFolder().getId() == expectedFolderId))
                .findFirst()
                .orElse(null);
    }

}