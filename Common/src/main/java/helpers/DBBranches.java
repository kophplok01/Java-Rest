package helpers;

import objects.DbBranch;
import objects.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBBranches {

    public static DbBranch[] getAllBranchesByManufacturerId(int manufacturerId) {
        String query = "SELECT TOP 100 * FROM TBL_BRANCH WHERE MANUFACTURER_ID = ? AND ACTIVE = 1";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, manufacturerId); // Establecer el parámetro manufacturerId en la consulta

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<DbBranch> branchList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String erpCode = resultSet.getString("ERP_CODE");
                    int manufacturerIdFromDB = resultSet.getInt("MANUFACTURER_ID");
                    int active = resultSet.getInt("ACTIVE");
                    DbBranch branch = new DbBranch(id, erpCode, manufacturerIdFromDB, active);
                    branchList.add(branch);
                }

                return branchList.toArray(new DbBranch[0]);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving branches from database", e);
        }
    }


    public static DbBranch getBranchById(int branchId) {
        String query = "SELECT * FROM TBL_BRANCH WHERE ID = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, branchId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String erpCode = resultSet.getString("ERP_CODE");
                    int manufacturerIdFromDB = resultSet.getInt("MANUFACTURER_ID");
                    int active = resultSet.getInt("ACTIVE");
                    return new DbBranch(id, erpCode, manufacturerIdFromDB, active);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving branch from database", e);
        }
    }

}


