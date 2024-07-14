package objects;
import lombok.Getter;

@Getter
public class SalesRepId {
    private int id;
    private String rep_number;
    private int user_id;
    private int warehouse_id;
    private int month_quota;
    private int old_id;

    public SalesRepId(int id, String rep_number, int user_id, int warehouse_id, int month_quota, int old_id) {
        this.id = id;
        this.rep_number = rep_number;
        this.user_id = user_id;
        this.warehouse_id = warehouse_id;
        this.month_quota = month_quota;
        this.old_id = old_id;
    }
}
