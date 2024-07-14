package objects;
import lombok.Getter;

@Getter
public class Manufacturers {
    private final int id;
    private final String name;

    public Manufacturers(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
