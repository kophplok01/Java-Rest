package com.webops.automation.java.testing.objects.bodies;
import com.webops.automation.java.testing.objects.reponses.ProductSystem;
import com.webops.automation.java.testing.objects.reponses.ShippingInstruction;
import helpers.DBProductSystems;
import helpers.DBSalesRep;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostEditCase {
    private String messageId;
    private String timestamp;
    private int manufacturerId;
    private int branchId;
    private String caseIdentifier;
    private String caseType;
    private String surgeryDate;
    private int salesRepId;
    private int coverageRepId;
    private int physicianId;
    private int hospitalId;
    private int procedureId;
    private String patientGender;
    private int patientAge;
    private boolean revisionSurgery;
    private int revisionReasonId;
    private String primaryInvoiceType;
    private String caseApprovalType;
    private String cashOnDeliveryNumber;
    private String specialInstructions;
    private String surgeryEntryFormComments;
    private String salesOrderFormComments;
    private String userName;
    private ShippingInstruction[] shippingInstructions;
    private ProductSystem[] productSystems;

    public PostEditCase(String surgeryDate, String arriveByDate, int salesRedId , int procedureId) {
        this.messageId = "2d648c64-3eee-4fc7-9e19-df6f14c3fefd";
        this.timestamp = "2023-10-11T13:15:30Z";
        this.manufacturerId = 1015;
        this.branchId = 149;
        this.caseIdentifier = "CS-123";
        this.caseType = "Standard";
        this.surgeryDate = surgeryDate;
        this.salesRepId = salesRedId;
        this.coverageRepId = DBSalesRep.getSalesRepById(salesRedId).getUser_id();
        this.physicianId = 65115;
        this.hospitalId = 12772;
        this.procedureId = procedureId;
        this.patientGender = "Male";
        this.patientAge = 40;
        this.revisionSurgery = true;
        this.revisionReasonId = 1;
        this.primaryInvoiceType = "Hospital";
        this.caseApprovalType = "Approved";
        this.cashOnDeliveryNumber = " ";
        this.specialInstructions = "API request - Special Instructions";
        this.surgeryEntryFormComments = "API request - Surgery Entry Form Comments";
        this.salesOrderFormComments = "API request - Sales Order Form Comments";
        this.userName = "Xuan";
        this.shippingInstructions = new ShippingInstruction[1];
        this.shippingInstructions[0] = new ShippingInstruction(20505, 1628570598, "Attention field", arriveByDate);
        this.productSystems = new ProductSystem[1];
        this.productSystems[0] = new ProductSystem(DBProductSystems.getProductSystemByProcedureId(String.valueOf(procedureId)).getId(),
                "Attention field");
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }

    public PostEditCase setCoverageRepId(int coverageRepId) {
        this.coverageRepId = coverageRepId;
        return this;
    }

    public PostEditCase setProcedureId(int procedureId) {
        this.procedureId = procedureId;
        return this;
    }

    public PostEditCase setProductSystems(ProductSystem productSystems) {
        this.productSystems = new ProductSystem[]{productSystems};
        return this;
    }
}