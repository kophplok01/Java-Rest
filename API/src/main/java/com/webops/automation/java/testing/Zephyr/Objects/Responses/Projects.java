package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Projects {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private ProjectsValues[] values;

    public ProjectsValues getProjectByName(String key) {
        return Arrays.stream(values)
                .filter(value -> value.getKey().equals(key))
                .findFirst()
                .orElse(null);
    }


}