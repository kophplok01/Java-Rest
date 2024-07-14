package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class CreateMultipleCatalgs {

    private String timestamp;
    private String message;
    private String messageId;
    private int code;
    private DetailsCreatedCatalog[] details;


}