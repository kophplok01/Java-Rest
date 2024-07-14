package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class CaseInfoSalesRep {

    private int id;
    private String firstName;
    private String lastName;
    private SalesRepEmail email;
    private SalesRepPhoneNumber phoneNumber;
    private String erpCode;
    private HospitalAliases[] aliases;
}