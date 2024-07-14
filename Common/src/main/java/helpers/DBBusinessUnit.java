package helpers;

import objects.BusinessUnit;
import objects.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBBusinessUnit {

    public static BusinessUnit[] getAllBusinessUnitByManufacturerId(int manufacturerId) {
        String query = "SELECT TOP 100 * FROM TBL_BUSINESS_UNIT WHERE MANUFACTURER_ID = ? AND ACTIVE = 1";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, manufacturerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<BusinessUnit> businessUnitList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("NAME");
                    String description = resultSet.getString("DESCRIPTION");
                    int manufacturerIdFromDB = resultSet.getInt("MANUFACTURER_ID");
                    int active = resultSet.getInt("ACTIVE");
                    BusinessUnit businessUnit = new BusinessUnit(id, name, description, manufacturerIdFromDB, active);
                    businessUnitList.add(businessUnit);
                }

                return businessUnitList.toArray(new BusinessUnit[0]);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving business units from database", e);
        }
    }
}


