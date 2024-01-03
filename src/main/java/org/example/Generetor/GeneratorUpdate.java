package org.example.Generetor;

import org.apache.poi.ss.usermodel.*;
import org.example.DataAcess;
import org.example.Query.CreateProduct;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
//TODO: foi criado o update, porém esta inutilizavel
public class GeneratorUpdate {
    private final DataAcess dataAcess;
    private final String filePath;
    public GeneratorUpdate(DataAcess dataAcess, String filePath) {
        this.dataAcess = dataAcess;
        this.filePath = filePath;
    }

    public void UpdateProduct() throws SQLException, IOException {
        CreateProduct createProduct = new CreateProduct();
        Connection connection = dataAcess.connectionDB();
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInput);
        Sheet sheet = workbook.getSheetAt(0);
        int rowIndex;
        int emptyRowCount = 0;
        for (rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            if (isRowEmpty(row)) {
                emptyRowCount++;
                if (emptyRowCount >= 3) {
                    break;
                }
            } else {
                emptyRowCount = 0;
            }
            Cell codeCell = row.getCell(0);
            Cell nameCell = row.getCell(2);
            Cell priceCell = row.getCell(4);
            if ((codeCell == null || codeCell.getCellType() == CellType.BLANK)
                    && (nameCell == null || nameCell.getCellType() == CellType.BLANK)) {
                continue;
            } else if ((nameCell != null && nameCell.getCellType() != CellType.BLANK) || (priceCell != null || priceCell.getCellType() != CellType.BLANK)) {
                createProduct.createProduct(connection, sheet, rowIndex);
            }
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
