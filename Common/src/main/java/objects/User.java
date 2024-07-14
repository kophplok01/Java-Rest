package objects;
import lombok.Getter;
import lombok.Setter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class User {
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private int id;
    @Getter
    private String password;
    @Getter
    private String accountname;
    @Getter
    @Setter
    private String actor;
    @Getter
    private String provider;
    @Getter
    @Setter
    private static User activeUser;

    public User(String actor) {
        User user = new User(actor, false);
        this.password = user.password;
        this.accountname = user.accountname;
        this.actor = user.actor;
        this.provider = user.provider;
    }

    public User(String actor, boolean isActive) {
        String[] propertyValues = PropertiesFile.Users.readProperties(System.getProperty("tenant") + "." + actor + "." + System.getProperty("environment"));
        this.accountname = propertyValues[0];
        this.password = propertyValues[1];

        if (actor.contains("test4")) {
            this.provider = "test5";
            this.actor = actor;
        } else if (actor.contains("test3")) {
            this.provider = "qaswa";
            this.actor = actor.replace("zz", "");
        } else if (actor.contains("zz")) {
            this.provider = "zz";
            this.actor = actor.replace("xx", "");
        } else {
            this.provider = "test8";
            this.actor = actor;
        }


    }

    public String getPassword() {
        return new String(Base64.getDecoder().decode(password));
    }

    public static String getPassword(String password) {
        return new String(Base64.getDecoder().decode(password));
    }


    public static Manufacturers[] getManufacturersForAdminSuperUser() {
        int rowCount = 0;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement countStatement = connection.prepareStatement("SELECT COUNT(*) FROM TBL_MANUFACTURER");
             ResultSet countResultSet = countStatement.executeQuery()) {
            if (countResultSet.next()) {
                rowCount = countResultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting row count from database", e);
        }

        Manufacturers[] manufacturersArray = new Manufacturers[rowCount];
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID, NAME FROM TBL_MANUFACTURER");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            int index = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Manufacturers manufacturer = new Manufacturers(id, name);
                manufacturersArray[index] = manufacturer;
                index++;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting list of manufacturers from database", e);
        }
        return manufacturersArray;
    }





}
