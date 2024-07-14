package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShippingInstruction {
    private int shipToAddressId;
    private int shippingMethodId;
    private String attention;
    private String arriveByDate;

    public ShippingInstruction() {
        this.shipToAddressId = 0;
        this.shippingMethodId = 0;
        this.attention = "";
        this.arriveByDate = "";
    }

    public ShippingInstruction(int shipToAddressId, int shippingMethodId, String attention, String arriveByDate) {
        this.shipToAddressId = shipToAddressId;
        this.shippingMethodId = shippingMethodId;
        this.attention = attention;
        this.arriveByDate = arriveByDate;
    }
}