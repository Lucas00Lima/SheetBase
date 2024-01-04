package com.example.Query;

import com.example.Functions.InsertQuery;
import com.example.entidades.Client;
import com.example.factory.ClienteFactory;
import org.apache.poi.ss.usermodel.Sheet;
import com.example.LogTex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateClient {
    public void createClient(Connection connection, Sheet sheet, int rowIndex) {
        try {
            String table = "client";
            ClienteFactory cliente = new ClienteFactory();
            Client clientData = cliente.cliente(sheet, rowIndex, connection);
            InsertQuery insertQuery = new InsertQuery();
            String insertQueryClient = insertQuery.insert(table, connection);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryClient);
            preparedStatement.setString(1, clientData.getId());
            preparedStatement.setString(2, clientData.getName());
            preparedStatement.setString(3, clientData.getCompanyName());
            preparedStatement.setString(4, "");
            preparedStatement.setInt(5, clientData.getType1()); // CPF ou CNPJ
            preparedStatement.setInt(6, 1); // type2 ??
            preparedStatement.setInt(7, clientData.getGender()); // M ou F
            preparedStatement.setDate(8, clientData.getBirthday());
            preparedStatement.setString(9, "");
            preparedStatement.setString(10, clientData.getTel());
            preparedStatement.setString(11, clientData.getCelContact1());
            preparedStatement.setString(12, clientData.getNameContact1());
            preparedStatement.setString(13, ""); // phone2
            preparedStatement.setString(14, clientData.getCelContact2());
            preparedStatement.setString(15, clientData.getNameContact2());
            preparedStatement.setString(16, clientData.getEmail());
            preparedStatement.setInt(17, 0);
            preparedStatement.setInt(18, clientData.getState());
            preparedStatement.setInt(19, clientData.getCity());
            preparedStatement.setString(20, clientData.getStreet());
            preparedStatement.setString(21, "");
            preparedStatement.setString(22, "");
            preparedStatement.setInt(23, 0); // neighborhood
            preparedStatement.setString(24, "");
            preparedStatement.setString(25, "");
            if (clientData.getType1() == 1) {
                preparedStatement.setString(26, clientData.getNumDoc()); // doc_number2 = CPF
                preparedStatement.setString(27, ""); // doc_number3 = CNPJ
                preparedStatement.setString(28, ""); // doc_number4 = IE
            } else if (clientData.getType1() == 2) {
                preparedStatement.setString(26, ""); // doc_number2 = CPF
                preparedStatement.setString(27, clientData.getNumDoc()); // doc_number3 = CNPJ
                preparedStatement.setString(28, clientData.getNumDoc2()); // doc_number4 = IE
            }
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
            preparedStatement.setDate(39, clientData.getRegister()); // register
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
        } catch (SQLException e) {
            LogTex.textError("Erro na criação de cliente");
            LogTex.textError(String.valueOf(e));
        }
    }
}
