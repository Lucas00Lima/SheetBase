package org.example.Query;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;

import java.net.ConnectException;
import java.sql.*;
public class CreateCategory {
    private int idCategory = 0;
    private DataFormatter dataFormatter = new DataFormatter();
    public int createCategory(Connection connection, Cell categoryCell) throws SQLException {
        String table = "category";
        Get get = new Get(connection);
        InsertQuery insert = new InsertQuery();
        String insertQueryCategory = insert.insert(table,connection);
        String nameValue = dataFormatter.formatCellValue(categoryCell);
        idCategory = get.getLastInsertId("category") + 1;
        PreparedStatement categoryStatement = connection.prepareStatement(insertQueryCategory);
        categoryStatement.setInt(1, idCategory); //Id
        categoryStatement.setString(2, nameValue); //Name
        categoryStatement.setString(3, ""); //Descrição
        categoryStatement.setInt(4, 1); //Type
        categoryStatement.setString(5, "1"); //Sub-Type
        categoryStatement.setInt(6, idCategory); //Panel_position
        categoryStatement.setInt(7, 1); //Panel
        categoryStatement.setInt(8, 0); //Web
        categoryStatement.setInt(9, 1); //Active
        categoryStatement.setInt(10, 1); //Apply_taxes
        categoryStatement.setString(11, ""); //Parameters
        categoryStatement.setInt(12, 0); //Panel_order_elements
        categoryStatement.setString(13, nameValue); //Panel_name
        categoryStatement.setInt(14, 0); //Father_Id
        categoryStatement.setInt(15, 0); //is_Father_category
        categoryStatement.setString(16, ""); //Kitchen_notes
        categoryStatement.execute();
        return idCategory;
    }
}
