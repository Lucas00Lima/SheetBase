package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DataAcess {
    public Connection connectionDB() {
        try {
            String username = "root";
            String storedPassword = "@soma+";
            String bancoCliente = JOptionPane.showInputDialog("Insira o numero do cliente");
            String url = "jdbc:mysql://localhost:3306/db" + bancoCliente;
            return DriverManager.getConnection(url, username, storedPassword);
        } catch (SQLException e) {
            LogTex.textError("Erro na inclusão do BANCO");
            return null;
        }
    }

    public String accessSheet() {
        String filePath = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de planilha", "xls", "xlsx");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
        if (filePath == null) {
            LogTex.textError("Seleciona uma planilha");
        }
        return filePath;
    }
}
