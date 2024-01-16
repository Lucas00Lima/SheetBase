package com.example.generetor;

import com.example.DataAcess;
import com.example.query.*;
import org.apache.poi.ss.usermodel.*;
import com.example.alert.Error;
import com.example.LogTex;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Generator {
    private final DataAcess dataAcess;
    private final String table;
    private final String filePath;
    public Generator(DataAcess dataAcess, String table, String filePath) {
        this.dataAcess = dataAcess;
        this.table = table;
        this.filePath = filePath;
    }
    public void generetor() {
        Error alert = new Error();
        try {
            CreateProduct createProduct = new CreateProduct();
            if (filePath == null ) {
                alert.Alerta("Escolha uma planilha");
            } else {
                FileInputStream fileInput = new FileInputStream(filePath);
                Workbook workbook = WorkbookFactory.create(fileInput);
                Sheet sheet = workbook.getSheetAt(0);
                Connection connection = dataAcess.connectionDB();
                int rowIndex;
                int emptyRowCount = 0;
                if (table == null ){
                    alert.Alerta("Escolha um checkBox");
                } else {
                    switch (table) {
                        case "product":
                            LogTex.textInfo("Criando produtos e categoria");
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
                                if ((codeCell == null || codeCell.getCellType() == CellType.BLANK) && (nameCell == null || nameCell.getCellType() == CellType.BLANK)) {
                                    continue;
                                }
                                if ((nameCell != null && nameCell.getCellType() != CellType.BLANK) || (priceCell != null && priceCell.getCellType() != CellType.BLANK)) {
                                    createProduct.createProduct(connection, sheet, rowIndex);
                                }
                            }
                            LogTex.textInfo("Tudo Concluido");
                            break;
                        case "client":
                            for (rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                                Row row = sheet.getRow(rowIndex);
                                if (isRowEmpty(row)) {
                                    emptyRowCount++;
                                    if (emptyRowCount >= 3) {
                                        break;
                                    }
                                } else {
                                    emptyRowCount = 0;
                                }
                                CreateClient createClient = new CreateClient();
                                createClient.createClient(connection, sheet, rowIndex);
                            }
                            LogTex.textInfo("Tudo Concluido no clientes");
                            break;
                        case "service":
                            for (rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                                Row row = sheet.getRow(rowIndex);
                                if (isRowEmpty(row)) {
                                    emptyRowCount++;
                                    if (emptyRowCount >= 3) {
                                        break;
                                    }
                                } else {
                                    emptyRowCount = 0;
                                }
                                Cell codeCell = row.getCell(0);
                                if (codeCell == null || codeCell.getCellType() == CellType.BLANK) {
                                    CreateService createService = new CreateService();
                                    createService.createService(connection, sheet, rowIndex);
                                }
                            }
                            LogTex.textInfo("Service Concluido");
                            break;
                        case "supplier":
                            for (rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                                Row row = sheet.getRow(rowIndex);
                                if (isRowEmpty(row)) {
                                    emptyRowCount++;
                                    if (emptyRowCount >= 3) {
                                        break;
                                    }
                                } else {
                                    emptyRowCount = 0;
                                }
                                CreateSupplier createSupplier = new CreateSupplier();
                                createSupplier.createSupplier(connection, sheet, rowIndex);
                            }
                            LogTex.textInfo("Tudo Concluido");
                            break;
                        case "material":
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
                                Cell codeCell = row.getCell(0);//Codigo
                                Cell nameCell = row.getCell(1);//name
                                if ((codeCell == null || codeCell.getCellType() == CellType.BLANK) && (nameCell != null && nameCell.getCellType() != CellType.BLANK)) {
                                    CreateMaterial createMaterial = new CreateMaterial();
                                    createMaterial.createMaterial(connection, sheet, rowIndex);
                                }
                                LogTex.textInfo("Tudo Concluido");
                            }
                            break;
                        default:
                    }
                    CreateExtra createExtra = new CreateExtra();
                    createExtra.createExtra(connection);
                }
            }
        } catch (SQLException | IOException e) {
            LogTex.textError("Erro no generator");
        }
    }

    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
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
