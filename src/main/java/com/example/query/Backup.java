package com.example.query;

import javax.swing.*;
import java.io.*;

public class Backup {

    public void generateBackup() {
        String filePath = "C:\\Users\\lukin\\Desktop\\Temporaria\\Bancos\\bancoCliente.sql";
        String username = "root";
        String password = "@soma+";
        String databaseName = "db000";
        String command = String.format("cmd.exe /c mysqldump -u %s -p%s --databases %s -r \"%s\"",
                username, password, databaseName, filePath);
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup gerado com sucesso.");
            } else {
                System.out.println("Erro ao gerar o backup. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
