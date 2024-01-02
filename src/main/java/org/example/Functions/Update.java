package org.example.Functions;

import org.example.LogTex;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    public boolean update(Connection connection) {
        try {
            Get get = new Get(connection);
            String waiterdevices = JOptionPane.showInputDialog("Quantos dispositivos o cliente irá usar ?");
            String update = "UPDATE ";
            int idProduct = get.getLastInsertId("product") + 1;
            int idCategory = get.getLastInsertId("category") + 1;
            int idMaterial = get.getLastInsertId("material") + 1;
            int idService = get.getLastInsertId("service") + 1;
            int idSupplier = get.getLastInsertId("supplier") + 1;
            int idClient = get.getLastInsertId("client") + 1;
            int idStreet = get.getLastInsertId("street") + 1;
            int idNeighborhood = get.getLastInsertId("neighborhood") + 1;
            PreparedStatement addStatement = connection.prepareStatement(update + "next_id SET product_id = " + idProduct);
            addStatement.addBatch(update + "next_id SET client_id = " + idClient);
            addStatement.addBatch(update + "next_id SET category_id = " + idCategory);
            addStatement.addBatch(update + "next_id SET material_id = " + idMaterial);
            addStatement.addBatch(update + "next_id SET service_id = " + idService);
            addStatement.addBatch(update + "next_id SET supplier_id = " + idSupplier);
            addStatement.addBatch(update + "next_id SET street_id = " + idStreet);
            addStatement.addBatch(update + "next_id SET neighborhood_id = " + idNeighborhood);
            addStatement.addBatch("UPDATE app_config SET waiter_devices = " + waiterdevices);
            System.out.println("Inserindo " + waiterdevices + " Waiter_Devices.......");
            addStatement.executeBatch();
            addStatement.execute();
            return true;
        } catch (SQLException e) {
            LogTex.textError("Erro na verificação de categoria");
            LogTex.textError(String.valueOf(e));
        }
        return false;
    }
}
