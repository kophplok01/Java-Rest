package com.webops.automation.java.testing.objects.bodies;
import com.webops.automation.java.testing.objects.reponses.CaseNote;
import com.webops.automation.java.testing.objects.reponses.ProductSystem;
import com.webops.automation.java.testing.objects.reponses.ShippingInstruction;
import helpers.DBProcedures;
import helpers.DBProductSystems;
import helpers.DBSalesRep;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.Procedures;
import objects.SalesRepId;

@Getter @Setter @ToString
public class PostNewCase {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private int branchId;
    private String externalCaseId;
    private String caseIdentifier;
    private boolean isLoaner;
    private String caseType;
    private String surgeryDate;
    private int salesRepId;
    private int coverageRepId;
    private int physicianId;
    private int hospitalId;
    private int procedureId;
    private String specialInstructions;
    private CaseNote[] caseNotes;
    private String patientGender;
    private int patientAge;
    private boolean revisionSurgery;
    private int revisionReasonId;
    private Boolean usePhysicianPref;
    private String userName;
    private ShippingInstruction[] shippingInstructions;
    private ProductSystem[] productSystems;

    public PostNewCase(boolean isReconciled) {

        SalesRepId salesRepId = DBSalesRep.getRandomSalesRepIdByBranchId(149);
        Procedures procedure = DBProcedures.getAllBranchesProcedure();
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = DefaultValues.getCurrentDateInISOFormat();
        this.manufacturerId = 1015;
        this.branchId = 149;
        this.externalCaseId = DefaultValues.generateRandomString(20);
        this.caseIdentifier = "CS-123";
        this.caseType = "Standard";
        this.surgeryDate = "2023-10-11 18:00";
        this.salesRepId = salesRepId.getId();
        this.coverageRepId = salesRepId.getUser_id();
        this.physicianId = 65115;
        this.hospitalId = 12772;
        this.procedureId = procedure.getId();
        this.specialInstructions = "Please handle it carefully";
        this.caseNotes = CaseNote.createDummyNotes(1);
        this.patientGender = "Male";
        this.patientAge = 40;
        this.revisionSurgery = true;
        this.revisionReasonId = 1;
        this.userName = "Xuan";
        if (!isReconciled) {
            this.isLoaner = false;
            this.usePhysicianPref = false;
            this.shippingInstructions = new ShippingInstruction[1];
            this.shippingInstructions[0] = new ShippingInstruction(20505, 1628570598, "Attention field", "2023-10-11T13:15:30Z");
            this.productSystems = new ProductSystem[2];
            this.productSystems[0] = new ProductSystem(DBProductSystems.getProductSystemByProcedureId(String.valueOf(procedure.getId())).getId(), "Attention field");
            this.productSystems[1] = new ProductSystem(DBProductSystems.getProductSystemByProcedureId(String.valueOf(procedure.getId())).getId(), "Attention field");
        }else{
            this.usePhysicianPref = null;
        }
    }

    public PostNewCase setExternalCaseId(String externalCaseId) {
        this.externalCaseId = externalCaseId;
        return this;
    }

    public PostNewCase setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
        return this;
    }

    public PostNewCase setCoverageRepId(int coverageRepId) {
        this.coverageRepId = coverageRepId;
        return this;
    }

    public PostNewCase setProductSystems(ProductSystem productSystems) {
        this.productSystems = new ProductSystem[]{productSystems};
        return this;
    }

    public PostNewCase setCaseType(String caseType) {
        this.caseType = caseType;
        return this;
    }
}