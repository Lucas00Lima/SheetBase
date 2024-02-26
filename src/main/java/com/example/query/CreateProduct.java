package com.example.query;

import com.example.functions.InsertQuery;
import com.example.LogTex;
import com.example.entidades.Product;
import com.example.factory.ProductFactory;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.*;
//FIXME: INSERI EXCEPTION QUE QUANDO ACIONADA ELE TRAVA O PROGRAMA, REPASSAR ISSO PARA AS OUTRAS CLASSES
public class CreateProduct {
    private final InsertQuery insertQuery = new InsertQuery();
    public void createProduct(Connection connection, Sheet sheet, int rowIndex) {
        try {
            String table = "product";
            ProductFactory produto = new ProductFactory();
            Product product = produto.produto(sheet, rowIndex, connection);
            String insertQueryProduct = insertQuery.insert(table, connection);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryProduct);
            preparedStatement.setString(1, product.getName());//nameValue);
            preparedStatement.setString(2, product.getDescription());//descriptionValue);
            preparedStatement.setString(3, product.getInternalCode());//internal_code);
            preparedStatement.setString(4, product.getBarcode());//barcodeValue);
            preparedStatement.setInt(5, 1);//department_id);
            preparedStatement.setInt(6, product.getType());//typeValue);
            preparedStatement.setInt(7, 0);//brand_id);
            preparedStatement.setInt(8, product.getCategory());//category_id);
            preparedStatement.setString(9, product.getMeasureUnit());//measure_unit);
            preparedStatement.setInt(10, product.getPrice());//priceValue);
            preparedStatement.setInt(11, 0);//aux_price);
            preparedStatement.setInt(12, 0);//minimum_price);
            preparedStatement.setInt(13, 0);//maximum_price);
            preparedStatement.setInt(14, 0);//web_sale_price);
            preparedStatement.setInt(15, product.getCost());//costValue);
            preparedStatement.setInt(16, 0);//markup);
            preparedStatement.setInt(17, 0);//minimum_stock);
            preparedStatement.setInt(18, product.getCurrentStock());//currentStockValue);
            preparedStatement.setInt(19, 1000);//amount);
            preparedStatement.setInt(20, 1);//print_production);
            preparedStatement.setInt(21, 0);//production_group);
            preparedStatement.setInt(22, product.getType2());//type2Value);
            preparedStatement.setInt(23, 1);//web);
            preparedStatement.setInt(24, 1);//panel);
            preparedStatement.setInt(25, 0);//supplier_id);
            preparedStatement.setInt(26, 0);//purchase_amount);
            preparedStatement.setInt(27, product.getIcms());//tax1);
            preparedStatement.setInt(28, 0);//tax2);
            preparedStatement.setInt(29, 0);//tax3);
            preparedStatement.setInt(30, 0);//tax4);
            preparedStatement.setInt(31, 0);//tax5);
            preparedStatement.setInt(32, 0);//tax6);
            preparedStatement.setString(33, product.getCst());//tax1_code);
            preparedStatement.setString(34, product.getPiscod());//tax2_code);
            preparedStatement.setString(35, product.getCofinscod());//tax3_code);
            preparedStatement.setString(36, product.getCest());//tax4_code);
            preparedStatement.setString(37, "0");//tax5_code);
            preparedStatement.setString(38, "0");//tax6_code);
            preparedStatement.setString(39, product.getNcm());//ncmValue);
            preparedStatement.setString(40, product.getCfop());//cfopValue);
            preparedStatement.setInt(41, product.getStatus());//active);
            preparedStatement.setNull(42, Types.DATE);//validity);
            preparedStatement.setInt(43, product.getDelivery());//delivery);
            preparedStatement.setInt(44, product.getCard());//card);
            preparedStatement.setInt(45, product.getHall_table());//hall_table);
            preparedStatement.setInt(46, product.getBalcony());//balcony);
            preparedStatement.setString(47, "");//parameters);
            preparedStatement.setInt(48, 0);//combo_price);
            preparedStatement.setInt(49, 0);//production_group2);
            preparedStatement.setInt(50, 1000);//pack_amount);
            preparedStatement.setNull(51, Types.NULL);//icms_reduction);
            preparedStatement.setInt(52, 0);//window_notes);
            preparedStatement.setInt(53, 0);//produced);
            preparedStatement.setNull(54, Types.DATE);//deleted_at);
            preparedStatement.setNull(55, Types.DATE);//manufacturing_date);
            preparedStatement.execute();
        } catch (Exception e) {
            LogTex.textError("Erro na criação de Produto");
            LogTex.textError(String.valueOf(e));
            throw new RuntimeException("Erro na criação de Produto", e);
        }
    }
}