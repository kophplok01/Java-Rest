package objects;
import lombok.Getter;

@Getter
public class Hospital {
    private int id;
    private String name;
    private int branchId;
    private String crossLinkHospitalId;
    private int active;
    private String billToNumber;

    public Hospital(int id, String name, int branchId, String crossLinkHospitalId, int active, String billToNumber) {
        this.id = id;
        this.name = name;
        this.branchId = branchId;
        this.crossLinkHospitalId = crossLinkHospitalId;
        this.active = active;
        this.billToNumber = billToNumber;
    }
}
