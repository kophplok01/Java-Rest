package com.webops.automation.java.testing.objects.bodies;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostMultipleCatalogs {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private PostMultipleCatalogsItems[] items;

    public PostMultipleCatalogs() {
        this.messageId = DefaultValues.generateRandomString(30);
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = 1015;
        this.items = new PostMultipleCatalogsItems[2];
        this.items[0] = new PostMultipleCatalogsItems();
        this.items[1] = new PostMultipleCatalogsItems();
    }

    public PostMultipleCatalogs(PostMultipleCatalogsItems[] items) {
        this.messageId = DefaultValues.generateRandomString(30);
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = 1015;
        this.items = items;
    }
}