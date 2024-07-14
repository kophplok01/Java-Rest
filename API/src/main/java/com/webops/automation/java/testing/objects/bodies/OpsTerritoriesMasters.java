package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class OpsTerritoriesMasters {

    private int division;
    private String bu;
    private String branch;
    private String customer;
    private String regionId;
    private String regionName;
    private String action;

    public OpsTerritoriesMasters() {
        this.division = 17101070;
        this.bu = "JR";
        this.branch = "IH1";
        this.customer = "Mercy Hospital";
        this.regionId = "JR-1000-RM01";
        this.regionName = "NorthEast";
        this.action = "A";
    }

}