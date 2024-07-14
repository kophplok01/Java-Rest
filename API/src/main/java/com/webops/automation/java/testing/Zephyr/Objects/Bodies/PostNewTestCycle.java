package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewTestCycle {
    private String projectKey;
    private String name;
    private int folderId;


    public PostNewTestCycle(String projectKey, String name, int folderId) {
        this.projectKey = projectKey;
        this.name = name;
        this.folderId = folderId;
    }
}
