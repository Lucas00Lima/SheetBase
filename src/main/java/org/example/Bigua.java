package org.example;

import org.apache.poi.ss.usermodel.*;
import org.example.Functions.Update;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Bigua {
    private static final DataFormatter dataFormatter = new DataFormatter();
    private static final List<String> defaultValues = new ArrayList<>(0);
    private static final List<String> listaDefault = new ArrayList<>();
    StringBuilder insertQuery = new StringBuilder();
    int totalColumnsInDatabase;

    public void biguaMethod(Connection connection, String filePath) {
        Update update = new Update();
        String table = "product";
        String defaultValue = "";
        try (connection) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, table, null);
            String[] excludedColumn = getColumnProduct();
            insertQuery.append("INSERT INTO ").append(table).append(" (");
            for (int i = 0; i < excludedColumn.length; i++) {
                insertQuery.append(excludedColumn[i]);
                if (i < excludedColumn.length - 1) {
                    insertQuery.append(",");
                }
            }
            totalColumnsInDatabase = excludedColumn.length;
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                if (!columnName.equals("id") && !columnName.equals("validity") && !columnName.equals("deleted_at") && !isExcludedProduct(columnName)) {
                    if (totalColumnsInDatabase > 0) {
                        insertQuery.append(",");
                    }
                    insertQuery.append(columnName);
                    getDefaultValues().add(defaultValue);
                    totalColumnsInDatabase++;
                }
                if (columnName.equals("deleted_at")) {
                    break;
                }
            }
            insertQuery.append(")").append(" VALUES (");
            for (int i = 0; i < totalColumnsInDatabase; i++) {
                insertQuery.append("?");
                if (i < totalColumnsInDatabase - 1) {
                    insertQuery.append(",");
                }
            }
            insertQuery.append(")");
            FileInputStream fileInput = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInput);
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex;
            for (rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Cell codeCell = row.getCell(0); //Internal_code
                Cell barcodeCell = row.getCell(1); //Codigo de barra
                Cell nameCell = row.getCell(2); //Nome
                Cell priceCell = row.getCell(3);
                Cell ncmCell = row.getCell(5);
                Cell cfopCell = row.getCell(6);
                Cell cestCell = row.getCell(7);
                Cell cstCell = row.getCell(8);

                String codeValue = dataFormatter.formatCellValue(codeCell);
                String barcodeValue = dataFormatter.formatCellValue(barcodeCell);
                String nameValue = dataFormatter.formatCellValue(nameCell);
                String priceValueString = dataFormatter.formatCellValue(priceCell);
                int priceValue = 0;
                if (priceValueString.contains(",")) {
                    int index = priceValueString.indexOf(",");
                    priceValueString = priceValueString.replace(",", "");
                    if (priceValueString.length() == 1) {
                        priceValue = Integer.parseInt(priceValueString) * 100;

                    } else if (priceValueString.length() == 2) {
                        priceValue = Integer.parseInt(priceValueString) * 10;

                    } else if (index == 2 && priceValueString.length() == 3) {
                        priceValueString += "0";
                        priceValue = Integer.parseInt(priceValueString);

                    } else if (index != 2 && priceValueString.length() == 3) {
                        priceValue = Integer.parseInt(priceValueString);
                    } else {
                        priceValue = Integer.parseInt(priceValueString);
                    }
                } else if (priceValueString.contains(".")) {
                    priceValueString = priceValueString.replace(".", "");
                    int index = priceValueString.indexOf("0");
                    String textoSemUltimoZero = priceValueString.substring(0, index) + priceValueString.substring(index + 1);
                    priceValue = Integer.parseInt(textoSemUltimoZero);
                } else if (priceValueString == "") {
                    priceValue = 0;
                } else {
                    if (priceValueString.length() == 1) {
                        priceValue = Integer.parseInt(priceValueString) * 100;
                    } else if (priceValueString.length() == 2) {
                        priceValue = Integer.parseInt(priceValueString) * 100;
                    } else if (priceValueString.length() == 3) {
                        priceValue = Integer.parseInt(priceValueString) * 100;
                    } else {
                        priceValue = Integer.parseInt(priceValueString);
                    }
                }
                String ncmValue = dataFormatter.formatCellValue(ncmCell);
                String cfopValue = dataFormatter.formatCellValue(cfopCell);
                String cestValue = dataFormatter.formatCellValue(cestCell);
                String cstValue = dataFormatter.formatCellValue(cstCell);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString());
                preparedStatement.setInt(1, 1); // category_id
                preparedStatement.setString(2, barcodeValue); //barcode
                preparedStatement.setString(3, nameValue); //name
                preparedStatement.setString(4, ""); //description
                preparedStatement.setInt(5, 2); //type
                preparedStatement.setInt(6, 1); //type2
                preparedStatement.setInt(7, 0); //cost
                preparedStatement.setInt(8, priceValue); //price
                preparedStatement.setString(9, ncmValue); //ncm
                preparedStatement.setString(10, cfopValue); //cfop
                preparedStatement.setString(12, cstValue); //cst
                preparedStatement.setString(11, cestValue); //cest
                preparedStatement.setInt(13, 0); //icms
                preparedStatement.setString(14, "0"); //pisCod
                preparedStatement.setInt(15, 0); //pis
                preparedStatement.setString(16, "0"); //cofinsCod
                preparedStatement.setInt(17, 0); //cofins
                preparedStatement.setInt(18, 0); //current_stock
                preparedStatement.setString(19, codeValue); // Internal_Code
                for (int j = 0; j < defaultValues.size(); j++) {
                    String value = defaultValues.get(j);
                    if (value.isEmpty()) {
                        preparedStatement.setInt(j + 20, 0);
                    } else {
                        preparedStatement.setString(j + 20, value);
                    }
                }
                preparedStatement.execute();
            }
            int id = 1;
            String tableCategory1 = "category";
            String insert = "INSERT INTO ";
            String values = " (id,name,description,type,sub_type,panel_position,panel,web,active,apply_taxes,parameters,panel_order_elements,panel_name,father_id,is_father_category,kitchen_notes) ";
            int idMateria = id + 1;
            String materia_prima = "VALUES (" + idMateria + ",'MATÉRIA-PRIMA','',3,0," + idMateria + ",1,0,1,1,'',0,'MATÉRIA-PRIMA',0,0,'')";
            int idPreparo = idMateria + 1;
            String preparo = "VALUES (" + idPreparo + ",'PREPARO','',3,0," + idPreparo + ",1,0,1,1,'',0,'PREPARO',0,0,'')";
            PreparedStatement categoryPreparedStatement = connection.prepareStatement(insert + tableCategory1 + values + materia_prima);
            categoryPreparedStatement.addBatch(insert + tableCategory1 + values + preparo);
            categoryPreparedStatement.execute();
            categoryPreparedStatement.executeBatch();
//            update.update(connection);
//            update.waiterDevices(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isExcludedProduct(String columnName) {
        String[] excludedColumns = getColumnProduct();
        for (String excludedColumn : excludedColumns) {
            if (columnName.equals(excludedColumn)) {
                return true;
            }
        }
        return false;
    }

    public static String[] getColumnProduct() {
        return new String[]{"category_id", "barcode", "name", "description", "type", "type2", "cost", "price", "ncm", "cfop", "tax4_code", "tax1_code", "tax1", "tax2_code", "tax2", "tax3_code", "tax3", "current_stock", "internal_code"};
    }

    public static List<String> getDefaultValues() {
        return defaultValues;
    }

    public static List<String> getDefaultList() {
        return listaDefault;
    }
}
