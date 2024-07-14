package com.webops.automation.java.testing.objects.bodies;
import com.webops.automation.java.testing.objects.reponses.CaseNote;
import com.webops.automation.java.testing.objects.reponses.ProductSystem;
import com.webops.automation.java.testing.objects.reponses.ShippingInstruction;
import helpers.DBProcedures;
import helpers.DBProductSystems;
import helpers.DefaultValues;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.Procedures;

@Getter @Setter @ToString
public class PostNewCaseErp {

    private String messageId;
    private String timestamp;
    private Integer manufacturerId;
    private String branchErpCode;
    private String externalCaseId;
    private String caseIdentifier;
    private boolean isLoaner;
    private String caseType;
    private String surgeryDate;
    private String salesRepErpCode;
    private int coverageRepId;
    private String physicianErpCode;
    private String hospitalErpCode;
    private Integer procedureId;
    private String specialInstructions;
    private CaseNote[] caseNotes;
    private String patientGender;
    private int patientAge;
    private boolean revisionSurgery;
    private int revisionReasonId;
    private boolean usePhysicianPref;
    private String userName;
    private ShippingInstruction[] shippingInstructions;
    private ProductSystem[] productSystems;

    public PostNewCaseErp(String arriveByDate) {
        Procedures procedure = DBProcedures.getAllBranchesProcedure();
        int productSystemId = DBProductSystems.getProductSystemByProcedureId(String.valueOf(procedure.getId())).getId();
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.branchErpCode = "34";
        this.externalCaseId = DefaultValues.generateRandomString(20);
        this.caseIdentifier = "CS-123";
        this.isLoaner = true;
        this.caseType = "Standard";
        this.surgeryDate = DefaultValues.getCurrentDateTime(3);
        this.salesRepErpCode = "9238339";
        this.coverageRepId = 11723;
        this.physicianErpCode = "20767";
        this.hospitalErpCode = "228634";
        this.procedureId = procedure.getId();
        this.specialInstructions = "Please handle it carefully";
        this.caseNotes = CaseNote.createDummyNotes(1);
        this.patientGender = "Male";
        this.patientAge = 40;
        this.revisionSurgery = true;
        this.revisionReasonId = 1;
        this.usePhysicianPref = false;
        this.userName = "Xuan";
        this.shippingInstructions = new ShippingInstruction[1];
        this.shippingInstructions[0] = new ShippingInstruction(20522, 1628570605, "Attention field",arriveByDate);
        this.productSystems = new ProductSystem[2];
        this.productSystems[0] = new ProductSystem(productSystemId, "Attention field");
        this.productSystems[1] = new ProductSystem(productSystemId, "Attention field");
    }

    public PostNewCaseErp setBranchErpCode(String branchErpCode) {
        this.branchErpCode = branchErpCode;
        return this;
    }

    public PostNewCaseErp setHospitalErpCode(String hospitalErpCode) {
        this.hospitalErpCode = hospitalErpCode;
        return this;
    }
    public PostNewCaseErp setSalesRepErpCode(String salesRepErpCode) {
        this.salesRepErpCode = salesRepErpCode;
        return this;
    }

    public PostNewCaseErp setPhysicianErpCode(String physicianErpCode) {
        this.physicianErpCode = physicianErpCode;
        return this;
    }

    public PostNewCaseErp setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public PostNewCaseErp setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PostNewCaseErp setManufacturerId(Integer manufacturerId ) {
        this.manufacturerId = manufacturerId;
        return this;
    }

    public PostNewCaseErp setExternalCaseId(String externalCaseId) {
        this.externalCaseId = externalCaseId;
        return this;
    }

    public PostNewCaseErp setCaseType (String caseType) {
        this.caseType = caseType;
        return this;
    }

    public PostNewCaseErp setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
        return this;
    }

    public PostNewCaseErp setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
        return this;
    }
}