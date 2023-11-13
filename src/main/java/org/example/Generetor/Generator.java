package org.example.Generetor;

import org.apache.poi.ss.usermodel.*;
import org.example.Query.*;
import org.example.DataAcess;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void generetor() throws SQLException, IOException {
        CreateProduct createProduct = new CreateProduct();
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInput);
        Sheet sheet = workbook.getSheetAt(0);
        Connection connection = dataAcess.connectionDB();
        int rowIndex;
        int emptyRowCount = 0;
        switch (table) {
            case "product":
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
                    } if ((nameCell != null && nameCell.getCellType() != CellType.BLANK) || (priceCell != null || priceCell.getCellType() != CellType.BLANK)) {
                        createProduct.createProduct(connection, sheet, rowIndex);
                    }
                }
                System.out.println("Import de produtos e categoria");
                System.out.println("Tudo Concluido");
                break;
            case "client":
                PreparedStatement preparedStatementVarchar = connection.prepareStatement("alter table client MODIFY column name varchar(300)");
                preparedStatementVarchar.execute();
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
                System.out.println("Tudo Concluido no clientes");
            case "service":
                preparedStatementVarchar = connection.prepareStatement("alter table service MODIFY column name varchar(300)");
                preparedStatementVarchar.execute();
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
                    Cell nameCell = row.getCell(2);
                    if ((nameCell != null && nameCell.getCellType() != CellType.BLANK)) {
                        //criar categoria
                    } else if (codeCell == null || codeCell.getCellType() == CellType.BLANK) {
                        CreateService createService = new CreateService();
                        createService.createService(connection, sheet, rowIndex);
                    }
                }
                System.out.println("Service Concluido");
                break;
            case "supplier":
                preparedStatementVarchar = connection.prepareStatement("alter table supplier MODIFY column name varchar(300)");
                preparedStatementVarchar.execute();
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
                    createSupplier.createService(connection, sheet, rowIndex);
                }
                System.out.println("Tudo Concluido");
                break;
            case "material":
                for (rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) { continue; }
                    if (isRowEmpty(row)) {
                        emptyRowCount++;
                        if (emptyRowCount >= 3) { break; }
                    } else { emptyRowCount = 0; }
                    Cell codeCell = row.getCell(0);//Codigo
                    Cell nameCell = row.getCell(1);//name
                    Cell medidaCell = row.getCell(2);//medida
                    if ((codeCell != null || codeCell.getCellType() != CellType.BLANK) &&
                            (nameCell != null || nameCell.getCellType() != CellType.BLANK) &&
                            (medidaCell == null || medidaCell.getCellType() == CellType.BLANK)) {
                    } else if ((codeCell == null || codeCell.getCellType() == CellType.BLANK) &&
                            (nameCell != null || nameCell.getCellType() != CellType.BLANK)) {
                        CreateMaterial createMaterial = new CreateMaterial();
                        createMaterial.createMaterial(connection, sheet, rowIndex);
                    }
                    System.out.println("Tudo Concluido");
                }
                    break;
            default:
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
