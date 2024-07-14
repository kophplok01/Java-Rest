package objects;
import lombok.Getter;

@Getter
public class BusinessUnit {
    private int id;
    private String name;
    private String description;
    private int manufacturer_id;
    private int active;

    public BusinessUnit(int id, String name, String description, int manufacturer_id, int active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufacturer_id = manufacturer_id;
        this.active = active;
    }
}
