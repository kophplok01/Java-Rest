package com.webops.automation.java.testing.objects.bodies;

import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GlobalTradeItemNumbers {
    private String gtin;

    public GlobalTradeItemNumbers() {
        this.gtin = DefaultValues.generateRandomGtin();
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }
}

