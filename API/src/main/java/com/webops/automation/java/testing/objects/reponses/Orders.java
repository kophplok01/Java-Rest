package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class Orders {
    private int id;
    private String status;
    private int freightPrice;
    private boolean active;
    private String currencyCode;
    private Boolean usagePricingComplete;
    private QuoteInfo quoteInfo;
    private CaseInfo caseInfo;
    private OrderAttributes[] orderAttributes;
    private StatusTimelines[] statusTimelines;
    private OrderItems[] orderItems;
    private boolean orderHold;
    private String changedDate;



}