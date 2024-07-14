package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialMasters {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private String enterpriseId;
    private String productCode;
    private String productName;
    private String baseOrCapital;
    private boolean biologicsTissueIndicator;
    private boolean capitalizationCategory;
    private boolean dangerousGoodsIndicator;
    private boolean sterileIndicator;
    private String countryOfOrigin;
    private String itemType;
    private String svmxcTracking;
    private String productHierarchyCode;
    private String productDivision;
    private String businessUnit;
    private String odpCategory;
    private String brand;
    private String ibpLevel7;
    private String deliveryUnit;
    private String deliveryUnitUom;
    private String salesUnitUom;
    private boolean isActive;
    private String conversionRate;
    private String alternateUnitOfMeasure;
    private String materialStatus;
    private String gtin;

    public MaterialMasters() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2018-11-05T13:15:30Z";
        this.manufacturerId = 1015;
        this.enterpriseId = "enterpriseIDExample";
        this.productCode = "productCodeExample";
        this.productName = "productNameExample";
        this.baseOrCapital = "b";
        this.biologicsTissueIndicator = true;
        this.capitalizationCategory = true;
        this.dangerousGoodsIndicator = true;
        this.sterileIndicator = true;
        this.countryOfOrigin = "US";
        this.itemType = "itemTypeExample";
        this.svmxcTracking = "svmxcTrackingExample";
        this.productHierarchyCode = "productHierarchyCodeExample";
        this.productDivision = "30 - Physiology";
        this.businessUnit = "20 - businessUnitExample";
        this.odpCategory = "30 - Physiology";
        this.brand = "30 - Physiology";
        this.ibpLevel7 = "30 - Physiology";
        this.deliveryUnit = "example";
        this.deliveryUnitUom = "example";
        this.salesUnitUom = "example";
        this.isActive = true;
        this.conversionRate = "2";
        this.alternateUnitOfMeasure = "9";
        this.materialStatus = "materialStatusExample";
        this.gtin = "example gtin";
    }
}

