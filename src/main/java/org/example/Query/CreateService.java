package org.example.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;
import org.example.factory.ServiceFactory;
import org.example.entidades.Service;

public class CreateService {
    public void createService(Connection connection, Sheet sheet, int rowIndex) {
        try {
            String table = "service";
            ServiceFactory serviceFactory = new ServiceFactory();
            Service service = serviceFactory.servic(sheet, rowIndex);
            InsertQuery insertQuery = new InsertQuery();
            String insertQueryService = insertQuery.insert(table,connection);
            Get get = new Get(connection);
            int id = get.getLastInsertId(table) + 1;
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryService);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, service.getName());
            preparedStatement.setString(3, ""); // Description
            preparedStatement.setString(4, service.getId());
            preparedStatement.setString(5, "");
            preparedStatement.setInt(6, 1);
            preparedStatement.setInt(7, 1); // type
            preparedStatement.setInt(8, 0); // brand_id
            preparedStatement.setInt(9, get.idGet()); // category
            preparedStatement.setString(10, "u"); // measure_unit
            preparedStatement.setInt(11, 0); // price
            preparedStatement.setInt(12, 0);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setInt(15, 0); // cost
            preparedStatement.setInt(16, 0);
            preparedStatement.setInt(17, 0);
            preparedStatement.setInt(18, 0); // current_stock
            preparedStatement.setInt(19, 0);
            preparedStatement.setInt(20, 0);
            preparedStatement.setInt(21, 1); // printer_id
            preparedStatement.setInt(22, 0);
            preparedStatement.setInt(23, 0);
            preparedStatement.setInt(24, 1); // selection_papel
            preparedStatement.setInt(25, 0);
            preparedStatement.setInt(26, 0); // pcp_column
            preparedStatement.setInt(27, 0);
            preparedStatement.setInt(28, 0);
            preparedStatement.setInt(29, 0); // tax3
            preparedStatement.setInt(30, 0);
            preparedStatement.setInt(31, 0);
            preparedStatement.setInt(32, 0);
            preparedStatement.setInt(33, 0); // tax1_code
            preparedStatement.setInt(34, 0);
            preparedStatement.setInt(35, 0);
            preparedStatement.setInt(36, 0); // tax4_code
            preparedStatement.setInt(37, 0);
            preparedStatement.setInt(38, 0);
            preparedStatement.setInt(39, 0); // ncm
            preparedStatement.setInt(40, 0);
            preparedStatement.setInt(41, 1);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
