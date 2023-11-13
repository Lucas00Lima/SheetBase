package org.example.Query;

import org.apache.poi.ss.usermodel.*;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;
import org.example.TreatmentRows;
import org.example.entidades.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CreatePizza implements TreatmentRows {
    private String[] idList;
    private int idValue;
    public static void main(String[] args) throws SQLException, IOException {
        DataAcess dataAcess = new DataAcess();
        Connection connection = dataAcess.connectionDB();
        String filePath = dataAcess.accessSheet();
        CreatePizza createPizza = new CreatePizza();
        createPizza.createFlavors(connection, filePath);
    }
    public void createFlavors(Connection connection, String filePath) throws SQLException, IOException {
        FileInputStream fileInput = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInput);
        Sheet sheet = workbook.getSheetAt(0);
        Get get = new Get(connection);
        for (Row row : sheet) {
            Cell type2Cell = row.getCell(6);
            String type2 = dataFormatter.formatCellValue(type2Cell);
            Cell idFlavor = row.getCell(8);
            String id = dataFormatter.formatCellValue(idFlavor);
            Cell nameCell = row.getCell(2);
            String name = dataFormatter.formatCellValue(nameCell);
            if (type2.equals("5")) {
                idList = id.split(",");
                for (int i = 0; i < idList.length; i++) {
                        idValue = Integer.parseInt(idList[i]);
//                        Agora preciso procurar o id na planilha pegar o nome e me retorna o id do produto na tabela product
                System.out.println(idValue + name);
//                    }
                }
            }
        }
    }
}
