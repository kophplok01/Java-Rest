package com.webops.automation.java.testing.objects.reponses;

import lombok.Getter;
import com.webops.automation.java.testing.objects.bodies.OpsTerritoriesMasters;

@Getter
public class OpsTerritories {

    private String timestamp;
    private OpsTerritoriesMasters[] opsTerritory;

}