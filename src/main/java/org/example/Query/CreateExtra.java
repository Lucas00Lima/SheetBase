package org.example.Query;

import org.example.DataAcess;
import org.example.Functions.Get;
import org.example.Functions.InsertQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CreateExtra {
    InsertQuery insertQuery = new InsertQuery();

    public void createCategoryExtra(Connection connection) throws SQLException {
        Get get = new Get(connection);
        int idCategoryIfood = get.getLastInsertId("category") + 1;
        String tableCategory1 = "category";
        String insertInto = "INSERT INTO ";
        String values = " (id,name,description,type,sub_type,panel_position,panel,web,active,apply_taxes,parameters,panel_order_elements,panel_name,father_id,is_father_category,kitchen_notes) ";
        String online = "VALUES (" + idCategoryIfood + ",'Online','Usada para produto ifood generico',1,1," + idCategoryIfood + ",0,0,1,1,'',0,'Online',0,0,'')";
        int idMateria = idCategoryIfood + 1;
        String materia_prima = "VALUES (" + idMateria + ",'MATÉRIA-PRIMA','',3,0," + idMateria + ",1,0,1,1,'',0,'MATÉRIA-PRIMA',0,0,'')";
        int idPreparo = idMateria + 1;
        String preparo = "VALUES (" + idPreparo + ",'PREPARO','',3,0," + idPreparo + ",1,0,1,1,'',0,'PREPARO',0,0,'')";
        PreparedStatement categoryPreparedStatement = connection.prepareStatement(insertInto + tableCategory1 + values + online);
        categoryPreparedStatement.addBatch(insertInto + tableCategory1 + values + materia_prima);
        categoryPreparedStatement.addBatch(insertInto + tableCategory1 + values + preparo);
        categoryPreparedStatement.execute();
        categoryPreparedStatement.executeBatch();
        String table = "product";
        String insertQueryProduct = insertQuery.insert(table,connection);
        PreparedStatement vincularCodigo = connection.prepareStatement(insertQueryProduct);
        vincularCodigo.setString(1, "Vincular codigo PDV");//nameValue);
        vincularCodigo.setString(2, "Usada para vincular código PDV");//descriptionValue);
        vincularCodigo.setInt(3, 999);//internal_code);
        vincularCodigo.setString(4, "");//barcodeValue);
        vincularCodigo.setInt(5, 1);//department_id);
        vincularCodigo.setInt(6, 1);//typeValue);
        vincularCodigo.setInt(7, 0);//brand_id);
//        vincularCodigo.setInt(8, );//category_id);
        vincularCodigo.setString(9, "u");//measure_unit);
        vincularCodigo.setInt(10, 0);//priceValue);
        vincularCodigo.setInt(11, 0);//aux_price);
        vincularCodigo.setInt(12, 0);//minimum_price);
        vincularCodigo.setInt(13, 0);//maximum_price);
        vincularCodigo.setInt(14, 0);//web_sale_price);
        vincularCodigo.setInt(15, 0);//costValue);
        vincularCodigo.setInt(16, 0);//markup);
        vincularCodigo.setInt(17, 0);//minimum_stock);
        vincularCodigo.setInt(18, 0);//currentStockValue);
        vincularCodigo.setInt(19, 1000);//amount);
        vincularCodigo.setInt(20, 1);//print_production);
        vincularCodigo.setInt(21, 1);//production_group);
        vincularCodigo.setInt(22, 0);//type2Value);
        vincularCodigo.setInt(23, 1);//web);
        vincularCodigo.setInt(24, 0);//panel);
        vincularCodigo.setInt(25, 0);//supplier_id);
        vincularCodigo.setInt(26, 0);//purchase_amount);
        vincularCodigo.setInt(27, 0);//tax1);
        vincularCodigo.setInt(28, 0);//tax2);
        vincularCodigo.setInt(29, 0);//tax3);
        vincularCodigo.setInt(30, 0);//tax4);
        vincularCodigo.setInt(31, 0);//tax5);
        vincularCodigo.setInt(32, 0);//tax6);
        vincularCodigo.setString(33, "0");//tax1_code);
        vincularCodigo.setString(34, "0");//tax2_code);
        vincularCodigo.setString(35, "0");//tax3_code);
        vincularCodigo.setString(36, "0");//tax4_code);
        vincularCodigo.setString(37, "0");//tax5_code);
        vincularCodigo.setString(38, "0");//tax6_code);
        vincularCodigo.setString(39, "0");//ncmValue);
        vincularCodigo.setString(40, "0");//cfopValue);
        vincularCodigo.setInt(41, 1);//active);
        vincularCodigo.setNull(42, Types.DATE);//validity);
        vincularCodigo.setInt(43, 1);//delivery);
        vincularCodigo.setInt(44, 1);//card);
        vincularCodigo.setInt(45, 1);//hall_table);
        vincularCodigo.setInt(46, 1);//balcony);
        vincularCodigo.setString(47, "");//parameters);
        vincularCodigo.setInt(48, 0);//combo_price);
        vincularCodigo.setInt(49, 0);//production_group2);
        vincularCodigo.setInt(50, 100);//pack_amount);
//        vincularCodigo.setInt(51, 0);
        vincularCodigo.setInt(51, 0);
        vincularCodigo.setNull(52, Types.DATE);//deleted_at);
        vincularCodigo.execute();
    }
}
