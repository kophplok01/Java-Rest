package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class GetCatalogs {

   private String messageId;
   private String timestamp;
   private int manufacturerId;
   private String catalogNumber;
   private String description;
   private double price;
   private double managementCost;
   private double standardCost;/*
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
   private String currencyCode;
   private String openField1;
   private String openField2;
   private int openField3;
   private String globalTradeItemNumber;*/
   private Integer companyId;/*
   private String updatedAtMin;
   private String updatedAtMax;
   private int limit;
   private int page;*/

   public GetCatalogs() {
      this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
      this.timestamp = "2018-11-05T13:15:30Z";
      this.manufacturerId = 1015;
      this.companyId = null;
   }

   public void setCompanyId(int companyId) {
      this.companyId = companyId;
   }
}