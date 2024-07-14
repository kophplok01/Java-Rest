package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class OrderItems {
    private int orderItemId;
    private int usageItemId;
    private int lineNumber;
    private String catalogNumber;
    private String lotCode;
    private int quantity;
    private String shipPriority;
    private ShipMethod shipMethod;
    private int price;
    private boolean requiresReplenishment;
    private boolean requiresShipment;
    private boolean requiresQuote;
    private Object consignedInventoryLocation;
    private Object replenishmentInventoryLocation;
}