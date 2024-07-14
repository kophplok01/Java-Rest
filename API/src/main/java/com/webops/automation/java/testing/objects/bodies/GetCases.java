package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetCases {


    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private Integer branchId;
    private String branchErpCode;
    private String ids;
    private String caseType;
    private  Boolean purchaseOrderReceived;
    private int limit;
    private String caseStatuses;
    private Boolean confirmed;
    private Boolean approved;
    private  Boolean billed;
    private Boolean usageReceived;
    private Boolean usageCompleted;
    private Boolean orderHold;


    public GetCases() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.branchId = 149;
        this.limit = 10;
    }

    public GetCases setIds(String ids) {
        this.ids = ids;
        return this;
    }

    public GetCases setBranchErpCode(String branchErpCode) {
        this.branchErpCode = branchErpCode;
        return this;
    }

    public GetCases setCaseType(String caseType) {
        this.caseType = caseType;
        return this;
    }

    public GetCases setCaseStatus(String caseStatuses) {
        this.caseStatuses = caseStatuses;
        return this;
    }

    public GetCases setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
        return this;
    }

    public GetCases setBranchId(Integer branchId) {
        this.branchId = branchId;
        return this;
    }

    public GetCases setPurchaseOrderReceived(Boolean purchaseOrderReceived) {
        this.purchaseOrderReceived = purchaseOrderReceived;
        return this;
    }

    public GetCases setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public GetCases setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public GetCases setBilled(Boolean billed) {
        this.billed = billed;
        return this;
    }

    public GetCases setUsageReceived(Boolean usageReceived) {
        this.usageReceived = usageReceived;
        return this;
    }

    public GetCases setUsageCompleted(Boolean usageCompleted) {
        this.usageCompleted = usageCompleted;
        return this;
    }

    public GetCases setOrderHold(Boolean orderHold) {
        this.orderHold = orderHold;
        return this;
    }
}