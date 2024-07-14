package com.webops.automation.java.testing.objects.bodies;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostCancelCase {
    private String messageId;
    private String timestamp;
    private String manufacturerId;
    private String branchId;
    private String branchErpCode;
    private String caseCancellationType;
    private String userName;

    public PostCancelCase() {
        this.messageId = DefaultValues.generateRandomString(36);
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = "1015";
        this.branchId = "149";
        this.branchErpCode = "ERP123";
        this.caseCancellationType = "Not Specified";
        this.userName = "John Doe";
    }

    public PostCancelCase setCaseCancellationType(String caseCancellationType) {
        this.caseCancellationType = caseCancellationType;
        return this;
    }
}