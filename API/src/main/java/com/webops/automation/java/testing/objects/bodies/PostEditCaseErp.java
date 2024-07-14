package com.webops.automation.java.testing.objects.bodies;
import com.webops.automation.java.testing.objects.reponses.CaseNote;
import com.webops.automation.java.testing.objects.reponses.ProductSystem;
import com.webops.automation.java.testing.objects.reponses.ShippingInstruction;
import helpers.DBProcedures;
import helpers.DBProductSystems;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import objects.Procedures;

@Getter @Setter @ToString
public class PostEditCaseErp {
    private String messageId;
    private String timestamp;
    private String manufacturerId;
    private String branchErpCode;
    private String caseIdentifier;
    private String caseType;
    private String surgeryDate;
    private String salesRepErpCode;
    private int coverageRepId;
    private String physicianErpCode;
    private String hospitalErpCode;
    private int procedureId;
    private String patientGender;
    private String patientAge;
    private String purchaseOrderNumber;
    private String billingOrderNumber;
    private String replenishmentOrderNumber;
    private boolean revisionSurgery;
    private int revisionReasonId;
    private String primaryInvoiceType;
    private String caseApprovalType;
    private String insuranceCompanyId;
    private String cashOnDeliveryNumber;
    private String specialInstructions;
    private CaseNote[] caseNotes;
    private String surgeryEntryFormComments;
    private String salesOrderFormComments;
    private String userName;
    private String manufacturerCaseAttributeValue;
    private ShippingInstruction[] shippingInstructions;
    private ProductSystem[] productSystems;
    private boolean orderHold;
    private String orderHoldReason;
    private boolean cap;
    private boolean revision;

    public PostEditCaseErp(String arriveByDate ) {
        Procedures procedure = DBProcedures.getAllBranchesProcedure();
        int productSystemId = DBProductSystems.getProductSystemByProcedureId(String.valueOf(procedure.getId())).getId();
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = "1015";
        this.branchErpCode = "34";
        this.caseIdentifier = "CS-123";
        this.caseType = "Standard";
        this.surgeryDate = "";
        this.salesRepErpCode = "9238339";
        this.coverageRepId = 11723;
        this.physicianErpCode = "20767";
        this.hospitalErpCode = "228634";
        this.procedureId = procedure.getId();
        this.specialInstructions = "Please handle it carefully";
        this.caseNotes = CaseNote.createDummyNotes(1);
        this.patientGender = "Male";
        this.patientAge = "40";
        this.revisionSurgery = true;
        this.revisionReasonId = 1;
        this.userName = "Xuan";
        this.shippingInstructions = new ShippingInstruction[1];
        this.shippingInstructions[0] = new ShippingInstruction(20522, 1628570605, "Attention field", arriveByDate);
        this.productSystems = new ProductSystem[2];
        this.productSystems[0] = new ProductSystem(productSystemId, "Attention field");
        this.productSystems[1] = new ProductSystem(productSystemId, "Attention field");
    }

    public PostEditCaseErp setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
        return this;
    }

    public PostEditCaseErp setBranchErpCode(String branchErpCode) {
        this.branchErpCode = branchErpCode;
        return this;
    }

    public PostEditCaseErp setHospitalErpCode(String hospitalErpCode) {
        this.hospitalErpCode = hospitalErpCode;
        return this;
    }
}