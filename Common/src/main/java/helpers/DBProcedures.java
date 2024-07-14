package helpers;

import objects.DatabaseConnector;
import objects.Procedures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBProcedures {

    public static Procedures getAllBranchesProcedure() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT TOP 1 * FROM TBL_PROCEDURE WHERE ALL_BRANCHES = 1 AND IS_ACTIVE = 1"
             );
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                int isLeft = resultSet.getInt("IS_LEFT");
                int isRight = resultSet.getInt("IS_RIGHT");
                int isBilateral = resultSet.getInt("IS_BILATERAL");
                String changedBy = resultSet.getString("CHANGED_BY");
                String changedDate = resultSet.getString("CHANGED_DATE");
                int procedureStatusId = resultSet.getInt("PROCEDURE_STATUS_ID");
                int isSterile = resultSet.getInt("IS_STERILE");
                int branchId = resultSet.getInt("BRANCH_ID");
                int oldId = resultSet.getInt("OLD_ID");
                int manufacturerId = resultSet.getInt("MANUFACTURER_ID");
                int allBranches = resultSet.getInt("ALL_BRANCHES");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int businessUnitId = resultSet.getInt("BUSINESS_UNIT_ID");
                int isMobileAvailabilityCheckAllowed = resultSet.getInt("IS_MOBILE_AVAILABILITY_CHECK_ALLOWED");

                return new Procedures(id, name, description, isLeft, isRight, isBilateral, changedBy, changedDate,
                        procedureStatusId, isSterile, branchId, oldId, manufacturerId, allBranches, isActive,
                        businessUnitId, isMobileAvailabilityCheckAllowed);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving active procedures from database", e);
        }

        return null;
    }

}


