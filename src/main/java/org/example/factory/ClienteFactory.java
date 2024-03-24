package org.example.factory;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.ClientComponents;
import org.example.Functions.Get;
import org.example.entidades.Client;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClienteFactory {
    LocalDate currentDate = LocalDate.now();
    DataFormatter dataFormatter = new DataFormatter();

    public void cliente(Sheet sheet, int rowIndex, Connection connection, Client client) throws SQLException {
        Get get = new Get(connection);
        Row row = sheet.getRow(rowIndex);
        ClientComponents cl = new ClientComponents(connection);

        client.setId(cl.idClient(row.getCell(0)));
        client.setName(dataFormatter.formatCellValue(row.getCell(1)));
        //Definir oque é CNPJ ou CPF
        client.setType1(cl.cpfOrCnpj(row.getCell(2)));
        if (cl.cpfOrCnpj(row.getCell(2)) == 2) {
            client.setNumDoc(cl.document(row.getCell(3)));
            client.setCompanyName(dataFormatter.formatCellValue(row.getCell(4)));
        } else {
            client.setNumDoc2(cl.document(row.getCell(3)));
        }
        cl.verifyCell(client, row.getCell(5));
        client.setGender(cl.gender(row.getCell(6)));
        client.setEmail(dataFormatter.formatCellValue(row.getCell(7)));
        client.setBirthday(cl.dateBrithday(row.getCell(8)));
        client.setState(get.stateId(row.getCell(9)));
        client.setCity(get.cityId(row.getCell(10)));
        client.setStreet(dataFormatter.formatCellValue(row.getCell(11)));
        client.setRegister(java.sql.Date.valueOf(currentDate));
    }
}
