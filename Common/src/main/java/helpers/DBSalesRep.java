package helpers;
import objects.DatabaseConnector;
import objects.SalesRepId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSalesRep {

    public static SalesRepId[] getAllSalesRepId() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOP 100 ID, REP_NUMBER, USER_ID, WAREHOUSE_ID, MONTH_QUOTA, OLD_ID FROM TBL_SALES_REP ORDER BY ID DESC");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<SalesRepId> salesRepIdsList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String repNumber = resultSet.getString("REP_NUMBER");
                int userId = resultSet.getInt("USER_ID");
                int warehouseId = resultSet.getInt("WAREHOUSE_ID");
                int monthQuota = resultSet.getInt("MONTH_QUOTA");
                int oldId = resultSet.getInt("OLD_ID");
                SalesRepId salesRepId = new SalesRepId(id, repNumber, userId, warehouseId, monthQuota, oldId);
                salesRepIdsList.add(salesRepId);
            }

            return salesRepIdsList.toArray(new SalesRepId[0]);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving sales reps from database", e);
        }
    }

    public static SalesRepId getRandomSalesRepIdByBranchId(int branchId) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT TOP 1 sr.* " +
                             "FROM TBL_SALES_REP AS sr " +
                             "JOIN TBL_USER AS u ON sr.USER_ID = u.ID " +
                             "WHERE u.BRANCH_ID = ?"
             )
        ) {
            preparedStatement.setInt(1, branchId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String repNumber = resultSet.getString("REP_NUMBER");
                    int userId = resultSet.getInt("USER_ID");
                    int warehouseId = resultSet.getInt("WAREHOUSE_ID");
                    int monthQuota = resultSet.getInt("MONTH_QUOTA");
                    int oldId = resultSet.getInt("OLD_ID");
                    return new SalesRepId(id, repNumber, userId, warehouseId, monthQuota, oldId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving sales reps from database", e);
        }

        return null;
    }

    public static SalesRepId getSalesRepById(int salesRepId) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM TBL_SALES_REP WHERE ID = ?"
             )
        ) {
            preparedStatement.setInt(1, salesRepId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String repNumber = resultSet.getString("REP_NUMBER");
                    int userId = resultSet.getInt("USER_ID");
                    int warehouseId = resultSet.getInt("WAREHOUSE_ID");
                    int monthQuota = resultSet.getInt("MONTH_QUOTA");
                    int oldId = resultSet.getInt("OLD_ID");
                    return new SalesRepId(id, repNumber, userId, warehouseId, monthQuota, oldId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving sales reps from database", e);
        }
        return null;
    }

}


