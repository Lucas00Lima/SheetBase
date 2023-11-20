package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.example.Functions.Update;
import org.example.Generetor.*;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        DataAcess dataAcess = new DataAcess();
        int responseUpdate = JOptionPane.showConfirmDialog(null, "Deseja atualizar ?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (responseUpdate == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess,"product",filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não atualizou os produtos");
        }
        int responseProduct = JOptionPane.showConfirmDialog(null, "Importar Produto ?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (responseProduct == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
        Generator generator = new Generator(dataAcess,"product",filePath);
        generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa produtos");
        }
        int responseMaterial = JOptionPane.showConfirmDialog(null, "Importa Material ?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (responseMaterial == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess, "material", filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa Material");
        }
        int responseClient = JOptionPane.showConfirmDialog(null, "Importa Cliente ?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (responseClient == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess,"client",filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa Clientes");
        }
        int responseSupplier = JOptionPane.showConfirmDialog(null, "Importar Fornecedor ?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (responseSupplier == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess,"supplier",filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa Fornecedor");
        }
        int responseService = JOptionPane.showConfirmDialog(null, "Importar Serviços ?", "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (responseService == JOptionPane.YES_OPTION) {
            String filePath = dataAcess.accessSheet();
            Generator generator = new Generator(dataAcess,"service",filePath);
            generator.generetor();
        } else {
            System.out.println("-----------------------");
            System.out.println("Você não quer importa Serviços");
        }
        Update update = new Update();
        Connection connection = dataAcess.connectionDB();
        update.update(connection);
        if(update.update(connection)) {
            System.out.println("Update concluido");
        } else {
            System.out.println("Update não foi feito");
        }
        connection.close();
    }
}