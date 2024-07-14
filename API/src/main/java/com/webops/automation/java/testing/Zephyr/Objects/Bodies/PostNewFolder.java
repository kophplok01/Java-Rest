package com.webops.automation.java.testing.Zephyr.Objects.Bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewFolder {
    private Integer parentId;
    private String name;
    private String projectKey;
    private String folderType;

    public PostNewFolder(String parentId, String name, String projectKey, String folderType) {
        this.parentId = (parentId != null && !parentId.isEmpty()) ? Integer.parseInt(parentId) : null;
        this.name = name;
        this.projectKey = projectKey;
        this.folderType = folderType;
    }
}
