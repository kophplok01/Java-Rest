package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostGetBranches {


    private String messageId;
    private String timestamp;
    private Integer manufacturerId;
    private String erpCode;
    private String ids;
    private String name;
    private Boolean active;
    private int limit;
    private String updatedAtMin;
    private String updatedAtMax;


    public PostGetBranches() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.limit = 10;
    }

    public PostGetBranches setIds(String ids) {
        this.ids = ids;
        return this;
    }

    public PostGetBranches setErpCode(String erpCode) {
        this.erpCode = erpCode;
        return this;
    }

    public PostGetBranches setName(String name) {
        this.name = name;
        return this;
    }

    public PostGetBranches setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
        return this;
    }

    public PostGetBranches setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public PostGetBranches setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public PostGetBranches setUpdatedAtMin(String updatedAtMin) {
        this.updatedAtMin = updatedAtMin;
        return this;
    }

    public PostGetBranches setUpdatedAtMax(String updatedAtMax) {
        this.updatedAtMax = updatedAtMax;
        return this;
    }

    public PostGetBranches setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PostGetBranches setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }
}