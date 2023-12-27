package org.example.questions;

import org.example.DataAcess;
import org.example.Functions.Update;
import org.example.Generetor.Generator;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Question {
    public void question() throws SQLException, IOException {
        DataAcess dataAcess = new DataAcess();
        int responseUpdate = JOptionPane.showConfirmDialog(null, "Deseja atualizar ?", "Confirmação",JOptionPane.YES_NO_OPTION);
            if (responseUpdate == JOptionPane.YES_OPTION) {
                String filePath = dataAcess.accessSheet();
                Generator generator = new Generator(dataAcess, "product", filePath);
                generator.generetor();
            } else {
                System.out.println("-----------------------");
                System.out.println("Você não atualizou os produtos");
            }
        int responseProduct = JOptionPane.showConfirmDialog(null, "Importar Produto ?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (responseProduct == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess, "product", filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa produtos");
        }
        Update update = new Update();
        Connection connection = dataAcess.connectionDB();
        update.update(connection);
        if (update.update(connection)) {
            System.out.println("Update concluido");
        } else {
            System.out.println("Update não foi feito");
        }
        connection.close();

    }
}
