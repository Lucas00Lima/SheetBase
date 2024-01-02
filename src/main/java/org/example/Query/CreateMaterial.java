package org.example.Query;

import org.apache.poi.ss.usermodel.Sheet;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;
import org.example.LogTex;
import org.example.entidades.Material;
import org.example.factory.MaterialFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateMaterial {
    private final InsertQuery insertQuery = new InsertQuery();
    public void createMaterial(Connection connection, Sheet sheet, int rowIndex) {
        try {
            Get get = new Get(connection);
            String table = "material";
            MaterialFactory materials = new MaterialFactory();
            Material material = materials.material(sheet,rowIndex);
            int id = get.getLastInsertId(table) + 1;
            String insertQueryMaterial = insertQuery.insert(table,connection);
            PreparedStatement ps = connection.prepareStatement(insertQueryMaterial);
            ps.setInt(1, id);
            ps.setString(2, material.getName());
            ps.setString(3, "");
            ps.setLong(4, id);
            ps.setString(5, "");
            ps.setInt(6, 0);
            ps.setLong(7, get.idGet()); //category_id
            ps.setInt(8, 0); //brand_id
            ps.setInt(9, 0); //purchase_amount
            ps.setInt(10, 1);
            ps.setString(11, material.getMedida()); //measure_unit
            ps.setInt(12, 0); //
            ps.setInt(13, 0); //
            ps.setInt(14, 0); //
            ps.setInt(15, 0); //
            ps.setInt(16, 0); //
            ps.setInt(17, 0); //
            ps.setInt(18, 0); //
            ps.setInt(19, 0); // current_stock
            ps.setInt(20, 0); //
            ps.setInt(21, 0); //
            ps.setInt(22, 0); //
            ps.setInt(23, 0); //
            ps.setInt(24, 0); //
            ps.setInt(25, 0); //
            ps.setInt(26, 1); // active
            ps.setInt(27, 0); // loss
            ps.setInt(28, 3); //
            ps.setInt(29, 0);
            ps.setInt(30, 1000);
            ps.execute();
            System.out.println(insertQueryMaterial);
        } catch (Exception e) {
            LogTex.textError("Erro na criação de Material");
            LogTex.textError(String.valueOf(e));
        }
    }
}
