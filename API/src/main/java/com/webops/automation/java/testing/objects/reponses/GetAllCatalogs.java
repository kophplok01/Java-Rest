package com.webops.automation.java.testing.objects.reponses;
import lombok.Getter;

@Getter
public class GetAllCatalogs {

    private String timestamp;
    private String message;
    private CatalogItem[] catalogItem;

}