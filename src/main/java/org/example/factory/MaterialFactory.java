package org.example.factory;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.example.entidades.Material;

public class MaterialFactory {
    DataFormatter dataFormatter = new DataFormatter();
    public Material material(Sheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell idCell = row.getCell(0);
        Cell nameCell = row.getCell(1);
        Cell medidaCell = row.getCell(2);
        double id = idCell.getNumericCellValue();
        String name = dataFormatter.formatCellValue(nameCell);
        String medida = dataFormatter.formatCellValue(medidaCell);
        return new Material(id,name,medida);
    }
}
