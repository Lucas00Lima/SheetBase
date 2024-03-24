package org.example.Query;

import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.InsertQuery;
import org.example.entidades.Client;
import org.example.factory.ClienteFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateClient {
    public void createClient(Connection connection, Sheet sheet, int rowIndex) throws SQLException {
        String table = "client";
        Client client = new Client();
        ClienteFactory clientes = new ClienteFactory();
        clientes.cliente(sheet, rowIndex, connection, client);
        InsertQuery insertQuery = new InsertQuery();
        String insertQueryClient = insertQuery.insert(table, connection);
        PreparedStatement preparedStatement = connection.prepareStatement(insertQueryClient);
        preparedStatement.setInt(1, client.getId());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getCompanyName());
        preparedStatement.setString(4, "");
        preparedStatement.setInt(5, client.getType1()); // CPF ou CNPJ
        preparedStatement.setInt(6, 1); // type2 ??
        preparedStatement.setInt(7, client.getGender()); // M ou F
        preparedStatement.setDate(8, client.getBirthday());
        preparedStatement.setString(9, "");
        preparedStatement.setString(10, client.getTel());
        preparedStatement.setString(11, client.getCellPhone());
        preparedStatement.setString(12, "");
        preparedStatement.setString(13, ""); // phone2
        preparedStatement.setString(14, "");
        preparedStatement.setString(15, "");
        preparedStatement.setString(16, client.getEmail());
        preparedStatement.setInt(17, 0);
        preparedStatement.setInt(18, 0);
        preparedStatement.setInt(19, 0);
        preparedStatement.setString(20, client.getStreet());
        preparedStatement.setString(21, "");
        preparedStatement.setString(22, "");
        preparedStatement.setInt(23, 0); // neighborhood
        preparedStatement.setString(24, "");
        preparedStatement.setString(25, "");
        preparedStatement.setString(26, client.getNumDoc()); // doc_number2 = CPF
        preparedStatement.setString(27, client.getNumDoc()); // doc_number3 = CNPJ
        preparedStatement.setString(28, client.getNumDoc2()); // doc_number4 = IE
        preparedStatement.setString(29, "");
        preparedStatement.setInt(30, 0);
        preparedStatement.setString(31, "");
        preparedStatement.setInt(32, 1); // locked
        preparedStatement.setString(33, "");
        preparedStatement.setInt(34, 10);
        preparedStatement.setInt(35, 1);
        preparedStatement.setInt(36, 0); // client_credit_limit
        preparedStatement.setInt(37, 0);
        preparedStatement.setInt(38, 0);
        preparedStatement.setDate(39, client.getRegister()); // register
        preparedStatement.setString(40, "");
        preparedStatement.setInt(41, 0);
        preparedStatement.setInt(42, 1);
        preparedStatement.setInt(43, 0); // value2
        preparedStatement.setInt(44, 0);
        preparedStatement.setInt(45, 0);
        preparedStatement.setInt(46, 0); // external_id
        preparedStatement.setString(47, "");
        preparedStatement.setString(48, "");
        preparedStatement.setInt(49, 0);
        preparedStatement.execute();
    }
}
