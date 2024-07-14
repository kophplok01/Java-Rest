package com.webops.automation.java.testing.objects.bodies;

import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostMultipleCatalogsItems {
    private String catalogNumber;
    private String description;
    private double price;
    private double managementCost;
    private double standardCost;
    private double listPrice;
    private String unitOfMeasure;
    private String productCategoryType;
    private String productCategoryDescription;
    private boolean active;
    private boolean obsolete;
    private String productSizeCode;
    private String marketDivisionCode;
    private String marketDivisionDescription;
    private String productLine;
    private String productLineDescription;
    private String productSize;
    private String productSizeDescription;
    private String productType2;
    private String productLineCode;
    private String inventoryTypeCode;
    private boolean ignoreLotCode;
    private boolean biologicsTissueIndicator;
    private String currencyCode;
    private String openField1;
    private String openField2;
    private int openField3;
    private GlobalTradeItemNumbers[] globalTradeItemNumbers;
    private int companyId;

    public PostMultipleCatalogsItems() {
        this.catalogNumber = DefaultValues.generateRandomString(10);
        this.description = "10-4567 description";
        this.price = 500.75;
        this.managementCost = 400.75;
        this.standardCost = 400.75;
        this.listPrice = 400.75;
        this.unitOfMeasure = "EA";
        this.productCategoryType = "10";
        this.productCategoryDescription = "IMPLANTS";
        this.active = true;
        this.obsolete = true;
        this.productSizeCode = "OR";
        this.marketDivisionCode = "40";
        this.marketDivisionDescription = "KNEES";
        this.productLine = "017";
        this.productLineDescription = "INSTRUMENTS";
        this.productSize = "150";
        this.productSizeDescription = "INSTRUMENTS SIZE";
        this.productType2 = "INSTRUMENTS-A";
        this.productLineCode = "ABC";
        this.inventoryTypeCode = "ABC";
        this.ignoreLotCode = false;
        this.biologicsTissueIndicator = false;
        this.currencyCode = "USD";
        this.openField1 = "string";
        this.openField2 = "string";
        this.openField3 = 1234;
        this.globalTradeItemNumbers = new GlobalTradeItemNumbers[1];
        this.globalTradeItemNumbers[0] = new GlobalTradeItemNumbers();
        this.companyId = 1;
    }

    public PostMultipleCatalogsItems setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
        return this;
    }
}