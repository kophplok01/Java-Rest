package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class CaseInfoPhysician {

    private int id;
    private String firstName;
    private String lastName;
    private String erpCode;
    private String nationalProviderIdentifier;
    private HospitalAliases[] aliases;
}