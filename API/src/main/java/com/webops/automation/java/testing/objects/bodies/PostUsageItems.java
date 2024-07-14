package com.webops.automation.java.testing.objects.bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostUsageItems {
    private String externalCaseUsageId;
    private String catalogNumber;
    private String lotCode;


    public PostUsageItems(String externalCaseUsageId) {
        this.externalCaseUsageId = externalCaseUsageId;
        this.catalogNumber = "5517-F-201";
        this.lotCode = "RPX2P";

    }
}