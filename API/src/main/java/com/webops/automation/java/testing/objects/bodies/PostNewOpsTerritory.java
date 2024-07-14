package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostNewOpsTerritory {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private int division;
    private String bu;
    private String branch;
    private String customer;
    private String regionId;
    private String regionName;
    private String action;


    public PostNewOpsTerritory() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.division = 17101070;
        this.bu = "JR";
        this.branch = "IH1";
        this.customer = "Mercy Hospital";
        this.regionId = "JR-1000-RM01";
        this.regionName = "NorthEast";
        this.action = "A";
    }
}