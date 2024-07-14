package objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String server = PropertiesFile.Data.readProperties("server." + System.getProperty("environment"))[0];
            String db = null;
            if (User.getActiveUser().getActor().contains("Test8")) {
                db = "test8";
            } else if (User.getActiveUser().getActor().contains("Test4")) {
                db = "test4";
            }
            String url = "jdbc:sqlserver://" + server + ";databaseName=" + db + "";
            if(System.getenv("DB_USER") == null){
                String[] propertyValues = PropertiesFile.Users.readProperties("dbadmin." + System.getProperty("environment"));
                connection = DriverManager.getConnection(url, propertyValues[0], User.getPassword(propertyValues[1]));
            }else{
                connection = DriverManager.getConnection(url, System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Can't connect to the db " + e.getMessage());
        }
    }
}

