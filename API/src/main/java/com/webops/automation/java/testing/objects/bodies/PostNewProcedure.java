package com.webops.automation.java.testing.objects.bodies;

import helpers.DBBusinessUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.BusinessUnit;

@Getter @Setter @ToString
public class PostNewProcedure {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private String name;
    private String description;
    private String companyIds;
    private boolean left;
    private boolean right;
    private boolean bilateral;
    private boolean sterile;
    private boolean manufacturerGlobal;
    private String branchErpCodes;

    public PostNewProcedure() {
        BusinessUnit[] businessUnit = DBBusinessUnit.getAllBusinessUnitByManufacturerId(1015);
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2018-11-05T13:15:30Z";
        this.manufacturerId = 1015;
        this.name = "proc";
        this.description = "desc";
        this.companyIds = String.valueOf(businessUnit[0].getId());
        this.left = true;
        this.right = true;
        this.bilateral = true;
        this.sterile = true;
        this.manufacturerGlobal = true;
        this.branchErpCodes = "55-Wash";
    }
}