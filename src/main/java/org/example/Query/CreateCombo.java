package org.example.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;
import org.example.TreatmentRows;


public class CreateCombo implements TreatmentRows {
    public void createCombo(Connection connection, String filePath) throws SQLException, IOException {
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInput);
        Sheet sheet = workbook.getSheetAt(0);
        Get get = new Get(connection);
        List<String> idList = get.namesCombo();
        for (String id : idList) {
            for (Row row : sheet) {
                Cell nameCell = row.getCell(2);
                Cell type2Cell = row.getCell(6);
                Cell idCell = row.getCell(7);
                String name = dataFormatter.formatCellValue(nameCell);
                String type2 = dataFormatter.formatCellValue(type2Cell);
                String idString = dataFormatter.formatCellValue(idCell);
                String idProduct = idString.trim();
                if (idProduct == null || idProduct.equals("") || idProduct.contains("Combo") || get.isCombo(idProduct)) {
                    continue;
                }
                if (!type2.contains("3") && (!idProduct.isEmpty() || idProduct != null)) {
                    InsertQuery insertQuery = new InsertQuery();
                    String insertQueryCombo = insertQuery.insert("product_item",connection);
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQueryCombo);
                    preparedStatement.setInt(1, get.getLastInsertId("product_item") + 1);
                    preparedStatement.setString(2, id);
                    preparedStatement.setInt(3, 1);
                    preparedStatement.setString(4, idProduct);
                    preparedStatement.setInt(5, 0);
                    preparedStatement.setInt(6, 0);
                    preparedStatement.setInt(7, 0);
                    preparedStatement.setInt(8, 1000);
                    preparedStatement.setString(9, "u");
                    preparedStatement.setInt(10, 0);
                    preparedStatement.setString(11, " ");
                    preparedStatement.setInt(12, 0);
                    preparedStatement.execute();
                }
            }
        }
    }
}