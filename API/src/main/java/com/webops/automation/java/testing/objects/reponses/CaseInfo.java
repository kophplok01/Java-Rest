package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class CaseInfo {

    private int id;
    private String externalCaseId;
    private String surgeryDate;
    private String caseType;
    private String hospitalErpCode;
    private String hospitalSystemErpCode;
    private String caseStatus;
    private String primaryInvoiceType;
    private HospitalAliases[] hospitalAliases;
    private CaseInfoBranch branch;
    private CaseInfoPhysician physician;
    private CaseInfoSalesRep salesRep;
}