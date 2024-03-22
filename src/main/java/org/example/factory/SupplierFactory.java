package org.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.Get;
import org.example.entidades.Fornecedor;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierFactory {
    DataFormatter dataFormatter = new DataFormatter();
    public Fornecedor supplier(Sheet sheet, int rowIndex, Connection connection) throws SQLException {
        Get get = new Get(connection);
        Row row = sheet.getRow(rowIndex);
        Cell idCell = row.getCell(0);
        Cell nameCell = row.getCell(1);
        Cell aux_nameCell = row.getCell(2);
        Cell emailCell = row.getCell(3);
        Cell numDocCell = row.getCell(4);
        Cell numDocCell2 = row.getCell(5);
        Cell telCell = row.getCell(6);
        Cell phoneCell = row.getCell(7);
        Cell streetCell = row.getCell(8);
        Cell numberCell = row.getCell(9);
        Cell cepCell = row.getCell(10);
        Cell stateCell = row.getCell(11);
        Cell cityCell = row.getCell(12);
        String id = dataFormatter.formatCellValue(idCell);
        String name = dataFormatter.formatCellValue(nameCell);
        String companyName = dataFormatter.formatCellValue(aux_nameCell);
        String email = dataFormatter.formatCellValue(emailCell);
        String numDoc = dataFormatter.formatCellValue(numDocCell);
        String numDoc2 = dataFormatter.formatCellValue(numDocCell2);
        String tel = dataFormatter.formatCellValue(telCell);
        String phone = dataFormatter.formatCellValue(phoneCell);
        String street = dataFormatter.formatCellValue(streetCell);
        String number = dataFormatter.formatCellValue(numberCell);
        String cep = dataFormatter.formatCellValue(cepCell);
        int state = get.stateId(stateCell);
        int city = get.cityId(cityCell);
        return new Fornecedor(id,name,companyName,email,numDoc,numDoc2,tel,phone,street,number,cep,state,city);
    }

}
