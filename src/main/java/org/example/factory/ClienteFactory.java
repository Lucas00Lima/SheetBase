package org.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.TreatmentRows;
import org.example.entidades.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClienteFactory implements TreatmentRows {
    LocalDate currentDate = LocalDate.now();
    public Client cliente(Sheet sheet, int rowIndex, Connection connection) throws SQLException {
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
        String id = dataFormatter.formatCellValue(idCell);
        String name = dataFormatter.formatCellValue(nameCell);
        int type1 = treatmentVariable.cpfOrCnpj(numDocCell);
        String numDoc = dataFormatter.formatCellValue(numDocCell);
        String numDoc2 = dataFormatter.formatCellValue(numDoc2Cell);
        String companyName = dataFormatter.formatCellValue(companyNameCell);
        String tel = dataFormatter.formatCellValue(telCell);
        int gender = treatmentVariable.gender(genderCell);
        String email = dataFormatter.formatCellValue(emailCell);
        Date birthday = treatmentVariable.dateBrithday(birthdayCell);
        int state = get.stateId(stateCell);
        int city = get.cityId(cityCell);
        String street = dataFormatter.formatCellValue(streetCell);
        String nameContact1 = dataFormatter.formatCellValue(nameContact1Cell);
        String celContact1 = dataFormatter.formatCellValue(celContact1Cell);
        String nameContact2 = dataFormatter.formatCellValue(nameContact2Cell);
        String celContact2 = dataFormatter.formatCellValue(celContact2Cell);
        String obs = dataFormatter.formatCellValue(obsCell);
        Date register = java.sql.Date.valueOf(currentDate);
        Client client = new Client(id,name,numDoc,type1,numDoc2,companyName,tel,gender,email,birthday,state,city,street,nameContact1,celContact1,nameContact2,celContact2,obs,register);
        return client;
    }
}
