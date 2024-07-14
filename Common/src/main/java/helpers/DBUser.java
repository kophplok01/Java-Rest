package helpers;

import objects.DatabaseConnector;
import objects.PropertiesFile;
import objects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static void setUserDetails() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TBL_USER WHERE USERNAME = ?")) {
            String userName;
            if(System.getProperty("tenant").equals("test") && User.getActiveUser().getActor().contains("Test8")) {
                userName = PropertiesFile.Users.readProperties("test8.adminTest8." + System.getProperty("environment"))[0];
            }
            if(System.getProperty("tenant").equals("test") && User.getActiveUser().getActor().contains("Test4")) {
               userName = PropertiesFile.Users.readProperties("test8.adminTest8." + System.getProperty("environment"))[0];
            }else{
                userName = User.getActiveUser().getAccountname();
            }
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User.getActiveUser().setId(resultSet.getInt("ID"));
                    User.getActiveUser().setFirstName(resultSet.getString("FIRST_NAME"));
                    User.getActiveUser().setLastName(resultSet.getString("LAST_NAME"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error loading user from database", e);
        }
    }


}


