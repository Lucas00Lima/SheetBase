package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAcess {
    public Connection connectionDB() throws SQLException {
        String username = "root";
        String storedPassword = "@soma+";
        String bancoCliente = "000"; //JOptionPane.showInputDialog("Insira o numero do cliente EX: (000) ");
        String url = "jdbc:mysql://localhost:3306/db" + bancoCliente;
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
