package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSystem {
    private int productSystemId;
    private String additionalSpecifications;

    public ProductSystem() {
        this.productSystemId = 0;
        this.additionalSpecifications = "";
    }

    public ProductSystem(int productSystemId, String additionalSpecifications) {
        this.productSystemId = productSystemId;
        this.additionalSpecifications = additionalSpecifications;
    }
}