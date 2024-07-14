package com.webops.automation.java.testing.objects.bodies;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostMultipleOpsTerritories {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private OpsTerritoriesMasters[] opsTerritoryMasters;


    public PostMultipleOpsTerritories() {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.opsTerritoryMasters = new OpsTerritoriesMasters[2];
        this.opsTerritoryMasters[0] = new OpsTerritoriesMasters();
        this.opsTerritoryMasters[1] = new OpsTerritoriesMasters();
    }
}