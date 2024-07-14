package com.webops.automation.java.testing.objects.reponses;
import lombok.Getter;

@Getter
public class CatalogItem {

    private int inventoryItemId;
    private String catalogNumber;
    private String description;
    private double price;
    private double managementCost;
    private double standardCost;
    private double listPrice;
    private String unitOfMeasure;
    private boolean active;
    private boolean obsolete;
    private boolean ignoreLotCode;
    private String currencyCode;
    private String changedDate;
    private int companyId;
    private String companyName;

    public CatalogItem(int inventoryId,String catalogNumber) {
        this.inventoryItemId = inventoryId;
        this.catalogNumber = catalogNumber ;
        this.description = "10-4567 description";
        this.price = 500.75;
        this.managementCost = 400.75;
        this.standardCost = 400.75;
        this.listPrice = 400.75;
        this.unitOfMeasure = "EA";
        this.active = true;
        this.obsolete = true;
        this.ignoreLotCode = false;
        this.currencyCode = "USD";
        this.companyId = 1;
    }

}