package com.webops.automation.java.testing.objects.bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostCaseUsage {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private String userName;

    private PostUsageItems[] usageItems;

    public PostCaseUsage(String externalCaseUsageId) {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.userName = "apiv1";
        this.usageItems = new PostUsageItems[1];
        this.usageItems[0] = new PostUsageItems( externalCaseUsageId);

    }
}