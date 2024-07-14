package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewExecution {
    private String projectKey;
    private String testCaseKey;
    private String testCycleKey;
    private String statusName;

    public PostNewExecution(String projectKey, String testCaseKey, String testCycleKey, String statusName) {
        this.projectKey = projectKey;
        this.testCaseKey = testCaseKey;
        this.testCycleKey = testCycleKey;
        this.statusName = statusName;
    }

}
