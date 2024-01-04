package com.example.factory;

import com.example.entidades.Service;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.example.TreatmentRows;

public class ServiceFactory implements TreatmentRows {
    public Service servic(Sheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell idCell = row.getCell(0);
        Cell nameCell = row.getCell(1);
        String id = dataFormatter.formatCellValue(idCell);
        String name = dataFormatter.formatCellValue(nameCell);
        return new Service(id,name);
    }

}
