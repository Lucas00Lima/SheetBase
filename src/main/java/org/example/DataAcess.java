package org.example;

import lombok.Getter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Getter

public class DataAcess {
    private Connection connection;
    private String storedPassword;
    private String storedDb;
    public Connection connectionDB() throws SQLException {
        String username = "root";
        storedPassword = JOptionPane.showInputDialog("Insira a senha do usuário <root>");
        storedDb = JOptionPane.showInputDialog("Insira o banco de dados");
        String url = "jdbc:mysql://localhost:3306/" + storedDb;
        connection = DriverManager.getConnection(url, username, storedPassword);
        return connection;
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
