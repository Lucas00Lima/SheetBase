package com.example.generetor;

import com.example.DataAcess;
import com.example.functions.FindColumns;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.TreatmentRows.dataFormatter;

//TODO: Esta classe precisa ser revisada e otimizada
public class GeneratorUpdate {

    private final DataAcess dataAcess;
    private final String filePath;

    public GeneratorUpdate(DataAcess dataAcess, String filePath) {
        this.dataAcess = dataAcess;
        this.filePath = filePath;
    }

    public void UpdateProduct() throws SQLException, IOException {
        Connection connection = dataAcess.connectionDB();
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInput);
        Sheet sheet = workbook.getSheetAt(0);
        FindColumns findColumns = new FindColumns(filePath);
        int rowIndex;
        int emptyRowCount = 0;
        for (rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {continue;}
            if (isRowEmpty(row)) {
                emptyRowCount++;
                if (emptyRowCount >= 3) {break;}
            } else {emptyRowCount = 0;}
            String id = dataFormatter.formatCellValue(row.getCell(0));
            String ncm = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("NCM")));
            String cfop = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("CFOP")));
            String cest = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("CEST")));
            String cst = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("CST")));
            if (cst.length() == 1) {cst = cst + "0";}
            String icmsString = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("ICMS Aliq")));
            icmsString = icmsString.replace(",", "");
            icmsString = icmsString + "0" + "0" + "0";
            int icms = Integer.parseInt(icmsString);

            String pisCod = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("PIS Cod")));
            String pisAString = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("PIS Aliq")));
            pisAString = pisAString.replace(",", "");
            pisAString = pisAString + "0";
            int pisA = Integer.parseInt(pisAString);

            String cofinsCod = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("COFINS Cod")));
            cofinsCod = "0" + cofinsCod;
            String cofinsAString = dataFormatter.formatCellValue(row.getCell(findColumns.localizarColunas("COFINS Aliq")));
            cofinsAString = cofinsAString.replace(",", "");
            cofinsAString = cofinsAString + "0" + "0" + "0";
            int cofinsA = Integer.parseInt(cofinsAString);

            String sql = "UPDATE product " +
                    "SET ncm = ?, " +
                    "cfop = ?, " +
                    "tax4_code = ?, " +
                    "tax1_code = ?, " +
                    "tax1 = ?, " +
                    "tax2_code = ?, " +
                    "tax2 = ?, " +
                    "tax3_code = ?, " +
                    "tax3 = ? " +
                    "WHERE internal_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ncm);
            preparedStatement.setString(2, cfop);
            preparedStatement.setString(3, cest);
            preparedStatement.setString(4, cst);
            preparedStatement.setInt(5, icms);
            preparedStatement.setString(6, pisCod);
            preparedStatement.setInt(7, pisA);
            preparedStatement.setString(8, cofinsCod);
            preparedStatement.setInt(9, cofinsA);
            preparedStatement.setString(10, id);
            preparedStatement.executeUpdate();
        }
    }
    private static boolean isRowEmpty(Row row) {
        if (row == null) {return true;}
        // Faz a leitura para o código não percorrer a planilha toda e sim só até 3
        // linhas vazias, acima disso ele para de ler
        for (int cellIndex = row.getFirstCellNum() + 2; cellIndex <= row.getLastCellNum(); cellIndex++) {
            Cell cell = row.getCell(cellIndex);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
