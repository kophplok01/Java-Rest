package helpers;
import objects.DatabaseConnector;
import objects.Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHospitals {
    public static Hospital[] getAllHospitalsByErpBranchCode(String branchErpCode) {
        String query = "SELECT TOP 100 *\n" +
                "FROM tbl_hospital\n" +
                "WHERE branch_id IN (\n" +
                "    SELECT ID\n" +
                "    FROM TBL_BRANCH\n" +
                "    WHERE ERP_CODE = ?\n" +
                "      AND ACTIVE = 1\n" +
                ")";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, branchErpCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Hospital> hospitalList = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("NAME");
                    int branchId = resultSet.getInt("BRANCH_ID");
                    String crossLinkHospitalId = resultSet.getString("CROSS_LINK_HOSPITAL_ID");
                    String billToNumber = resultSet.getString("BILL_TO_NUMBER");
                    int active = resultSet.getInt("IS_ACTIVE");
                    Hospital hospital = new Hospital(id, name, branchId, crossLinkHospitalId, active, billToNumber);
                    hospitalList.add(hospital);
                }

                return hospitalList.toArray(new Hospital[0]);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving hospitals from database", e);
        }
    }
}


