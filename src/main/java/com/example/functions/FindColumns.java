package com.example.functions;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class FindColumns {
    private final String filePath;
    public FindColumns (String filePath) {
        this.filePath = filePath;
    }
    public int localizarColunas(String coluna) throws IOException {
        int index = 0;
        try (FileInputStream arquivo = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(arquivo);
            Sheet sheet = workbook.getSheetAt(0);
            Row primeiraLinha = sheet.getRow(2);
            int numeroDeColunas = primeiraLinha.getLastCellNum();
            for (int i = 0; i < numeroDeColunas; i++) {
                Cell cell = primeiraLinha.getCell(i);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String valorCelula = cell.getStringCellValue();
                    if (valorCelula.contains(coluna)) {
                        index = i;
                        break;
                    }
                }
            }
        }
        return index;
    }
}
