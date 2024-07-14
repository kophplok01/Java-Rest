package com.webops.automation.java.testing.objects.bodies;
import helpers.DBProcedures;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.Procedures;

@Getter @Setter @ToString
public class PostNewCaseErpWithOnlyRequiredFields {

    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private String branchErpCode;
    private String externalCaseId;
    private String caseType;
    private String surgeryDate;
    private String salesRepErpCode;
    private String physicianErpCode;
    private String hospitalErpCode;
    private int procedureId;

    public PostNewCaseErpWithOnlyRequiredFields(String arriveByDate) {
        Procedures procedure = DBProcedures.getAllBranchesProcedure();
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.branchErpCode = "55-Wash";
        this.externalCaseId = DefaultValues.generateRandomString(20);
        this.caseType = "Standard";
        this.surgeryDate = DefaultValues.getCurrentDateTime(0);
        this.salesRepErpCode = "55-SaleRep";
        this.physicianErpCode = "10720";
        this.hospitalErpCode = "55-Hospital";
        this.procedureId = procedure.getId();
    }

    public PostNewCaseErpWithOnlyRequiredFields setExternalCaseId(String externalCaseId) {
        this.externalCaseId = externalCaseId;
        return this;
    }
}