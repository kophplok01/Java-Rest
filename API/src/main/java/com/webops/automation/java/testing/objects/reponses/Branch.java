package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class Branch {

    private int id;
    private String name;
    private String erpCode;
    private String changedDate;
    private boolean active;
    private Address address;
    private Contact contact;
    private int inventoryLocationId;
    private int advancedExpirationCutoff;
    private int advancedExpirationWarning;
    private int dueBackDayDefault;
    private boolean loanerBank;
    private String languageCode;
    private String countryCode;
    private String baseCurrencyCode;
    private boolean mobileLoanerBankRequests;
    private String timezone;
    private int daysBeforeSurgery;
    private int daysAfterSurgery;

}