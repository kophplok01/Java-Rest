package objects;
import lombok.Getter;

@Getter
public class Procedures {
    private int id;
    private String name;
    private String description;
    private int is_left;
    private int is_right;
    private int is_bilateral;
    private String changed_by;
    private String changed_date;
    private int procedure_status_id;
    private int is_sterile;
    private int branch_id;
    private int old_id;
    private int manufacturer_id;
    private int all_branches;
    private int is_active;
    private int business_unit_id;
    private int IS_MOBILE_AVAILABILITY_CHECK_ALLOWED;

    public Procedures(int id, String name, String description, int is_left, int is_right, int is_bilateral, String changed_by,
                      String changed_date, int procedure_status_id, int is_sterile, int branch_id, int old_id, int
                              manufacturer_id, int all_branches, int is_active, int business_unit_id, int IS_MOBILE_AVAILABILITY_CHECK_ALLOWED) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.is_left = is_left;
        this.is_right = is_right;
        this.is_bilateral = is_bilateral;
        this.changed_by = changed_by;
        this.changed_date = changed_date;
        this.procedure_status_id = procedure_status_id;
        this.is_sterile = is_sterile;
        this.branch_id = branch_id;
        this.old_id = old_id;
        this.manufacturer_id = manufacturer_id;
        this.all_branches = all_branches;
        this.is_active = is_active;
        this.business_unit_id = business_unit_id;
        this.IS_MOBILE_AVAILABILITY_CHECK_ALLOWED = IS_MOBILE_AVAILABILITY_CHECK_ALLOWED;
    }
}
