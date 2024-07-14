package com.webops.automation.java.testing.objects.bodies;
import helpers.DBProcedures;
import helpers.DBSalesRep;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.Procedures;
import objects.SalesRepId;

@Getter @Setter @ToString
public class PostNewCaseOnlyRequieredFileds {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private int branchId;
    private String externalCaseId;
    private String caseType;
    private String surgeryDate;
    private int salesRepId;
    private int physicianId;
    private int hospitalId;
    private int procedureId;

    public PostNewCaseOnlyRequieredFileds(boolean requiredFields) {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.branchId = 149;
        this.externalCaseId = DefaultValues.generateRandomString(20);
        this.caseType = "Standard";
        this.surgeryDate = "2023-10-11 18:00";
        SalesRepId salesRepId = DBSalesRep.getRandomSalesRepIdByBranchId(149);
        this.salesRepId = salesRepId.getId();
        this.physicianId = 65115;
        if(requiredFields){
            this.hospitalId = 12772;
            Procedures procedure = DBProcedures.getAllBranchesProcedure();
            this.procedureId = procedure.getId();
        }
    }
}