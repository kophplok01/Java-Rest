package com.webops.automation.java.testing.objects.bodies;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class EditCaseWithOnlyRequieredFields {
    private String messageId;
    private String timestamp;
    private Integer manufacturerId;
    private Integer branchId;

    public EditCaseWithOnlyRequieredFields(Boolean requiredFields){
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        if(requiredFields){
            this.manufacturerId = 1015;
            this.branchId = 149;
        }else {
            this.manufacturerId = null;
            this.branchId = null;
        }
    }
}