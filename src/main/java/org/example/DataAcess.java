package org.example;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAcess {
    public boolean createDatabase() {
        String filePath;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Arquivo Selecionado" + filePath);
        } else {
            String error = JOptionPane.showInputDialog("Arquivo não selecionado");
        }
        return false;
    }
    public Connection connectionDB() throws SQLException {
        String username = "root";
        String storedPassword = JOptionPane.showInputDialog("Insira a senha do usuário <root>");
        String storedDb = JOptionPane.showInputDialog("Insira o banco de dados");
        String url = "jdbc:mysql://localhost:3306/" + storedDb;
        return DriverManager.getConnection(url, username, storedPassword);
    }

    public String accessSheet() {
        String filePath = null;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Arquivo Selecionado" + filePath);
        }
        return filePath;
    }
}
