package objects;
import lombok.Getter;

@Getter
public class DbBranch {
    private int id;
    private String erp_code;
    private int manufacturer_id;
    private int active;

    public DbBranch(int id, String description, int manufacturer_id, int active) {
        this.id = id;
        this.erp_code = description;
        this.manufacturer_id = manufacturer_id;
        this.active = active;
    }
}
