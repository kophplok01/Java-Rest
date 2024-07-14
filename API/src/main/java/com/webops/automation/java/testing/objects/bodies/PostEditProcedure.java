package com.webops.automation.java.testing.objects.bodies;

import helpers.DBBusinessUnit;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.BusinessUnit;

@Getter @Setter @ToString
public class PostEditProcedure {
    private String messageId;
    private String timestamp;
    private Integer manufacturerId;
    private String name;
    private String description;
    private String companyIds;
    private boolean left;
    private boolean right;
    private boolean bilateral;
    private boolean sterile;
    private String branchErpCodes;

    public PostEditProcedure(String name, String description) {
        BusinessUnit [] businessUnits = DBBusinessUnit.getAllBusinessUnitByManufacturerId(1015);
        this.messageId = DefaultValues.generateRandomString(15);
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = 1015;
        this.name = name;
        this.description = description;
        this.companyIds = String.valueOf(businessUnits[0].getId());
        this.left = true;
        this.right = true;
        this.bilateral = true;
        this.sterile = true;
    }

    public PostEditProcedure setCompanyIds(String companyIds){this.companyIds = companyIds;
        return this;
    }

    public PostEditProcedure setManufacturerId(Integer manufacturerId){
        this.manufacturerId = manufacturerId;
        return this;
    }

    public PostEditProcedure setMessageId(String messageId){
        this.messageId = messageId;
        return this;
    }

    public PostEditProcedure setTimestamp(String timestamp){
        this.timestamp = timestamp;
        return this;
    }


}