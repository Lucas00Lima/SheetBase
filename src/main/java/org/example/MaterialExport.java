package org.example;

import org.apache.poi.ss.usermodel.*;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class MaterialExport {
    private static final DataFormatter dataFormatter = new DataFormatter();
    public void materialExport(Connection connection, String filePath){
        try (connection) {
            FileInputStream fileInput = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInput);
            Sheet sheet = workbook.getSheetAt(0);
            InsertQuery insert = new InsertQuery();
            Get get = new Get(connection);
            int rowIndex;
            int panel_position = 1;
            for (rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Cell codeCell = row.getCell(0);//Codigo
                Cell categoryCell = row.getCell(1);//categoryName
                Cell nameCell = row.getCell(2);//name
                Cell medidaCell = row.getCell(3);//medida
                if ((codeCell == null || codeCell.getCellType() == CellType.BLANK) && (nameCell == null || nameCell.getCellType() == CellType.BLANK)) {
                    continue;
                }
                if ((codeCell != null && codeCell.getCellType() != CellType.BLANK) && (categoryCell != null && categoryCell.getCellType() != CellType.BLANK)) {
                    String table = "category";
                    int ultimoCategory = get.getLastInsertId(table) + 1 ;
                    String insertQueryCategory = insert.insert(table,connection);
                    String categoryName = dataFormatter.formatCellValue(categoryCell);
                    PreparedStatement categoryStatement = connection.prepareStatement(insertQueryCategory);
                    categoryStatement.setInt(1, ultimoCategory); //Id
                    categoryStatement.setString(2, categoryName); //Name
                    categoryStatement.setString(3, ""); //Descrição
                    categoryStatement.setInt(4, 3); //Type
                    categoryStatement.setString(5, "1"); //Sub-Type
                    categoryStatement.setInt(6, ultimoCategory); //Panel_position
                    categoryStatement.setInt(7, 1); //Panel
                    categoryStatement.setInt(8, 0); //Web
                    categoryStatement.setInt(9, 1); //Active
                    categoryStatement.setInt(10, 1); //Apply_taxes
                    categoryStatement.setString(11, ""); //Parameters
                    categoryStatement.setInt(12, 0); //Panel_order_elements
                    categoryStatement.setString(13, categoryName); //Panel_name
                    categoryStatement.setInt(14, 0); //Father_Id
                    categoryStatement.setString(15, "0"); //is_Father_category
                    categoryStatement.setString(16, ""); //Kitchen_notes
                    categoryStatement.execute();
                } else if (nameCell != null && nameCell.getCellType() != CellType.BLANK) {
                    String table = "material";
                    String nameValue = dataFormatter.formatCellValue(nameCell);
                    String medidaValue = dataFormatter.formatCellValue(medidaCell);
                    String insertQueryMaterial = insert.insert(table,connection);
                    PreparedStatement ps = connection.prepareStatement(insertQueryMaterial);
                    ps.setInt(1, panel_position);
                    ps.setString(2, nameValue);
                    ps.setString(3, "");
                    ps.setLong(4, panel_position);
                    ps.setString(5, "");
                    ps.setInt(6, 0);
                    ps.setLong(7, get.idGet()); //category_id
                    ps.setInt(8, 0); //brand_id
                    ps.setInt(9, 0); //purchase_amount
                    ps.setInt(10, 1);
                    ps.setString(11, medidaValue); //measure_unit
                    ps.setInt(12, 0); //
                    ps.setInt(13, 0); //
                    ps.setInt(14, 0); //
                    ps.setInt(15, 0); //
                    ps.setInt(16, 0); //
                    ps.setInt(17, 0); //
                    ps.setInt(18, 0); //
                    ps.setInt(19, 0); // current_stock
                    ps.setInt(20, 0); //
                    ps.setInt(21, 0); //
                    ps.setInt(22, 0); //
                    ps.setInt(23, 0); //
                    ps.setInt(24, 0); //
                    ps.setInt(25, 0); //
                    ps.setInt(26, 1); // active
                    ps.setInt(27, 0); // loss
                    ps.setInt(28, 3); //
                    ps.setInt(29, 0);
                    ps.setInt(30, 1000);
                    ps.execute();
                    panel_position++;
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
