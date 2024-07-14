package objects;
import lombok.Getter;

@Getter
public class ProductsSystems {
    private int id;
    private String name;
    private String description;
    private String changed_by;
    private String changed_date;
    private int branch_id;
    private int old_id;
    private int manufacturer_id;
    private int all_branches;
    private int is_active;
    private int business_unit_id;

    public ProductsSystems(int id, String name, String description, String changed_by,
                           String changed_date, int branch_id, int old_id, int
                                   manufacturer_id, int all_branches, int is_active, int business_unit_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.changed_by = changed_by;
        this.changed_date = changed_date;
        this.branch_id = branch_id;
        this.old_id = old_id;
        this.manufacturer_id = manufacturer_id;
        this.all_branches = all_branches;
        this.is_active = is_active;
        this.business_unit_id = business_unit_id;
    }

    // Getters and setters
}

