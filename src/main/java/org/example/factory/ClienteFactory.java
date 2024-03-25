package org.example.factory;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.TreatmentRows;
import org.example.entidades.Client;
import java.time.LocalDate;

public class ClienteFactory implements TreatmentRows {
    LocalDate currentDate = LocalDate.now();

    public void cliente(Sheet sheet, int rowIndex, Client client) {
        Row row = sheet.getRow(rowIndex);
        client.setId(dataFormatter.formatCellValue(row.getCell(0)));
        client.setName(dataFormatter.formatCellValue(row.getCell(1)));
        client.setType1(treatmentVariable.cpfOrCnpj(row.getCell(2)));
        client.setNumDoc(treatmentVariable.document(row.getCell(2)));//CPF E CNPJ
        client.setNumDoc2(treatmentVariable.document(row.getCell(3)));//IE
        client.setCompanyName(dataFormatter.formatCellValue(row.getCell(4)));
        treatmentVariable.verifyCell(client,row.getCell(5));
        client.setGender(treatmentVariable.gender(row.getCell(6)));
        client.setEmail(dataFormatter.formatCellValue(row.getCell(8)));
        client.setBirthday(treatmentVariable.dateBrithday(row.getCell(9)));
        client.setStreet(dataFormatter.formatCellValue(row.getCell(10)));
        client.setRegister(java.sql.Date.valueOf(currentDate));
    }
}
