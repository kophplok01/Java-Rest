package com.webops.automation.java.testing.Zephyr.Objects.Responses;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class Folders {

    private String next;
    private int startAt;
    private int maxResults;
    private int total;
    private boolean isLast;
    private FoldersValues[] values;

    public FoldersValues getFolderByNameAndType(String name, String expectedFolderType) {
        return Arrays.stream(values)
                .filter(value -> value.getName().equals(name) && value.getFolderType().equals(expectedFolderType))
                .findFirst()
                .orElse(null);
    }

    public FoldersValues[] getFoldersByParentId(int parentId) {
        return Arrays.stream(values)
                .filter(value -> value.getParentId() == parentId)
                .toArray(FoldersValues[]::new);
    }

    public FoldersValues findNameInParentIdFolders(int parentId, String name) {
        return Arrays.stream(getFoldersByParentId(parentId))
                .filter(folder -> folder.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}