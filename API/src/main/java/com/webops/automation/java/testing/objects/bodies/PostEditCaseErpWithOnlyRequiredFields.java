package com.webops.automation.java.testing.objects.bodies;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostEditCaseErpWithOnlyRequiredFields {
    private String messageId;
    private String timestamp;
    private String manufacturerId;
    private String branchErpCode;
    private String specialInstructions;

    public PostEditCaseErpWithOnlyRequiredFields() {
        this.messageId = DefaultValues.generateRandomString(20);
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = "1015";
        this.branchErpCode = "34";
        this.specialInstructions = "zzzzz";
    }

    public PostEditCaseErpWithOnlyRequiredFields setManufacturerId(String manufacturer ) {
        this.manufacturerId = manufacturer;
        return this;
    }

    public PostEditCaseErpWithOnlyRequiredFields setBranchErpCode(String branchErpCode) {
        this.branchErpCode = branchErpCode;
        return this;
    }

    public PostEditCaseErpWithOnlyRequiredFields setMessageId (String messageId) {
        this.messageId = messageId;
        return this;
    }

    public PostEditCaseErpWithOnlyRequiredFields setTimestamp (String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}