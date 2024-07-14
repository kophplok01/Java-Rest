package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewTestCase {
    private String projectKey;
    private String name;
    private int folderId;
    private String statusName;


    public PostNewTestCase(String projectKey, String name, int folderId, String statusName) {
        this.projectKey = projectKey;
        this.name = name;
        this.folderId = folderId;
        this.statusName = statusName;
    }
}
