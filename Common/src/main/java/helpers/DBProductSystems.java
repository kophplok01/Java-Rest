package helpers;

import objects.DatabaseConnector;
import objects.ProductsSystems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBProductSystems {

    public static ProductsSystems getProductSystemByProcedureId(String procedureId) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT TOP 1 ps.* FROM TBL_PRODUCT_SYSTEM ps " +
                             "INNER JOIN TBL_PROCEDURE_CATEGORY_PRODUCT_SYSTEM pcps ON ps.ID = pcps.PRODUCT_SYSTEM_ID " +
                             "INNER JOIN TBL_PROCEDURE_CATEGORY pc ON pcps.PROCEDURE_CATEGORY_ID = pc.ID " +
                             "WHERE pc.PROCEDURE_ID = ? and ALL_BRANCHES = 1"
             )) {
            preparedStatement.setString(1, procedureId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                String changedBy = resultSet.getString("CHANGED_BY");
                String changedDate = resultSet.getString("CHANGED_DATE");
                int branchId = resultSet.getInt("BRANCH_ID");
                int oldId = resultSet.getInt("OLD_ID");
                int manufacturerId = resultSet.getInt("MANUFACTURER_ID");
                int allBranches = resultSet.getInt("ALL_BRANCHES");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int businessUnitId = resultSet.getInt("BUSINESS_UNIT_ID");

                return new ProductsSystems(id, name, description, changedBy, changedDate,
                        branchId, oldId, manufacturerId, allBranches, isActive, businessUnitId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving active product systems from database", e);
        }
        return null;
    }

}


