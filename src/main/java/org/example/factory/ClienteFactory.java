package org.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.Get;
import org.example.TreatmentRows;
import org.example.entidades.Client;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClienteFactory implements TreatmentRows {
    LocalDate currentDate = LocalDate.now();

    public void cliente(Sheet sheet, int rowIndex, Connection connection, Client client) throws SQLException {
        Get get = new Get(connection);
        Row row = sheet.getRow(rowIndex);
        Cell idCell = row.getCell(0);
        Cell nameCell = row.getCell(1);
        Cell numDocCell = row.getCell(2);
        Cell numDoc2Cell = row.getCell(3);
        Cell companyNameCell = row.getCell(4);
        Cell telCell = row.getCell(5);
        Cell genderCell = row.getCell(6);
        Cell emailCell = row.getCell(7);
        Cell birthdayCell = row.getCell(8);
        Cell stateCell = row.getCell(9);
        Cell cityCell = row.getCell(10);
        Cell streetCell = row.getCell(11);
        Cell nameContact1Cell = row.getCell(12);
        Cell celContact1Cell = row.getCell(13);
        Cell nameContact2Cell = row.getCell(14);
        Cell celContact2Cell = row.getCell(15);
        Cell obsCell = row.getCell(16);

        client.setId(dataFormatter.formatCellValue(idCell));
        client.setName(dataFormatter.formatCellValue(nameCell));
        client.setType1(treatmentVariable.cpfOrCnpj(numDocCell));
        client.setNumDoc(treatmentVariable.document(numDocCell));
        client.setNumDoc2(treatmentVariable.document(numDoc2Cell));
        client.setCompanyName(dataFormatter.formatCellValue(companyNameCell));
        treatmentVariable.verifyCell(client,telCell);
        client.setGender(treatmentVariable.gender(genderCell));
        client.setEmail(dataFormatter.formatCellValue(emailCell));
        client.setBirthday(treatmentVariable.dateBrithday(birthdayCell));
        client.setState(get.stateId(stateCell));
        client.setCity(get.cityId(cityCell));
        client.setStreet(dataFormatter.formatCellValue(streetCell));
        client.setNameContact1(dataFormatter.formatCellValue(nameContact1Cell));
        client.setCelContact1(dataFormatter.formatCellValue(celContact1Cell));
        client.setNameContact2(dataFormatter.formatCellValue(nameContact2Cell));
        client.setCelContact2(dataFormatter.formatCellValue(celContact2Cell));
        client.setObs(dataFormatter.formatCellValue(obsCell));
        client.setRegister(java.sql.Date.valueOf(currentDate));
    }
}
