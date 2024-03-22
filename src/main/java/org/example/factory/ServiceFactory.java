package org.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.entidades.Service;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactory {
    DataFormatter dataFormatter = new DataFormatter();
    public Service servic(Sheet sheet, int rowIndex, Connection connection) throws SQLException {
        Row row = sheet.getRow(rowIndex);
        Cell idCell = row.getCell(0);
        Cell nameCell = row.getCell(1);
        String id = dataFormatter.formatCellValue(idCell);
        String name = dataFormatter.formatCellValue(nameCell);
        Service service = new Service(id,name);
        return service;
    }

}
