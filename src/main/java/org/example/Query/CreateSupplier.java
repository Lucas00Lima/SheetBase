package org.example.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.poi.ss.usermodel.Sheet;
import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;
import org.example.LogTex;
import org.example.factory.SupplierFactory;
import org.example.entidades.Fornecedor;

public class CreateSupplier {
    public void createSupplier(Connection connection, Sheet sheet, int rowIndex) {
        try {
            String table = "supplier";
            Get get = new Get(connection);
            SupplierFactory fornecedor = new SupplierFactory();
            Fornecedor fornecedorData = fornecedor.supplier(sheet, rowIndex, connection);
            InsertQuery insertQuery = new InsertQuery();
            String insertQueryService = insertQuery.insert(table, connection);
            int id = get.getLastInsertId(table) + 1;
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryService);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, fornecedorData.getName());
            preparedStatement.setString(3, fornecedorData.getAux_name());
            preparedStatement.setString(4, fornecedorData.getTel());
            preparedStatement.setString(5, fornecedorData.getPhone());
            preparedStatement.setString(6, fornecedorData.getEmail());
            preparedStatement.setInt(7, fornecedorData.getState());
            preparedStatement.setInt(8, fornecedorData.getCity());
            preparedStatement.setString(9, fornecedorData.getStreet());
            preparedStatement.setString(10, fornecedorData.getNumberStreet());
            preparedStatement.setString(11, fornecedorData.getCep());
            preparedStatement.setInt(12, 0);
            preparedStatement.setString(13, fornecedorData.getNumDoc());
            preparedStatement.setInt(14, 1);
            preparedStatement.setString(15, "");
            preparedStatement.setString(16, "");
            preparedStatement.setString(17, "");
            preparedStatement.setString(18, fornecedorData.getNumDoc2());
            preparedStatement.execute();
        } catch (Exception e) {
            LogTex.textError("Erro na criação de Fornecedor");
            LogTex.textError(String.valueOf(e));
        }
    }
}
