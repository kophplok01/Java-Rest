package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;

@Getter
public class Cases {

    private int branchId;
    private String branchErpCode;
    private int id;
    private String encryptedWebOpsCaseId;
    private String externalCaseId;
    private String surgeryDate;
    private String caseType;
    private String caseIdentifier;
    private int salesRepId;
    private String salesRepErpCode;
    private int coverageRepId;
    private String coverageRepErpCode;
    private int physicianId;
    private String physicianErpCode;
    private int hospitalId;
    private String hospitalErpCode;
    private int procedureId;
    private int procedureCompanyId;
    private String caseStatus;
    private String purchaseOrderNumber;
    private boolean confirmed;
    private boolean assembled;
    private int freight;
    private boolean usageReceived;
    private boolean usageReordered;
    private boolean usageCompleted;
    private String patientGender;
    private int patientAge;
    private boolean caseBilled;
    private boolean revisionSurgery;
    private int revisionReasonId;
    private String primaryInvoiceType;
    private String caseApprovalType;
    private String specialInstructions;
    private CaseNote[] caseNotes;
    private String pickStatusType;
    private String createdDate;
    private String changedBy;
    private String changedDate;
    private String timezone;
    private String surgeryEntryFormComments;
    private String salesOrderFormComments;
    private ShippingInstruction[] shippingInstructions;
    private ProductSystem[] productSystems;
    private boolean orderHold;
    private boolean cap;
    private boolean revision;

}