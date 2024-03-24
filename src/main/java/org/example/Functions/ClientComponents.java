package org.example.Functions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.example.entidades.Client;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientComponents {
    DataFormatter dataFormatter = new DataFormatter();
    private Get get;
    public ClientComponents (Connection connection) throws SQLException {
        this.get = new Get(connection);
    }
    public String isNull(String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }
        else {
            return string;
        }
    }
    public int idClient(Cell codeCell) throws SQLException {
        int id = Integer.parseInt(dataFormatter.formatCellValue(codeCell));
        if (codeCell == null) {
            return get.getLastInsertId("client") + 1;
        } else {
            return id;
        }
    }
    public int cpfOrCnpj(Cell numDocCell) {
        String valor = dataFormatter.formatCellValue(numDocCell).replaceAll("\\D", "");
        if (valor.equals("")) {
            return 1;
        } else if (valor.length() >= 13) {
            return 2;
        } else {
            return 1;
        }
    }

    public int gender(Cell genderCell) {
        String valor = dataFormatter.formatCellValue(genderCell);
        if (valor == null || valor.isEmpty()) {
            return 0;
        }
        if (valor.contains("Masculino") || valor.contains("M") || valor.contains("masculino")) {
            return 1;
        } else if (valor.contains("Feminino") || valor.contains("F") || valor.contains("feminino")) {
            return 2;
        } else {
            return 0;
        }
    }

    public String document(Cell valorCell) {
        String doc = dataFormatter.formatCellValue(valorCell);
        String regex = "[1-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(doc);
        if (matcher.find()) {
            return doc;
        } else {
            return "";
        }
    }

    public Date dateBrithday(Cell valorCell) {
        try {
            String valor = dataFormatter.formatCellValue(valorCell);
            if (valor == null || valor.isEmpty()) {
                return null;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return (Date) formatter.parse(valor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void verifyCell(Client client, Cell celPhone) {
        String fone = dataFormatter.formatCellValue(celPhone);
        if (!fone.isEmpty()) {
            if (fone.contains("(") || fone.contains(")")) {
                fone = fone.replace("(", "").replace(")", "");
            }
            if (fone.contains(" ")) {
                fone = fone.replace(" ", "");
            }
            if (fone.contains("-")) {
                fone = fone.replace("-", "");
            }
            StringBuilder stringBuilder = new StringBuilder(fone);
            char primeiroCaractere;
            primeiroCaractere = stringBuilder.charAt(0);
            if (fone.length() == 8) {
                if ((primeiroCaractere == '9') || (primeiroCaractere == '3')) {
                    stringBuilder.insert(0, "(");
                    stringBuilder.insert(1, "1");
                    stringBuilder.insert(2, "4");
                    stringBuilder.insert(3, ")");
                    stringBuilder.insert(8, "-");
                    System.out.println(stringBuilder);
                    client.setTel(String.valueOf(stringBuilder));
                    client.setCellPhone("");
                } else if (primeiroCaractere == '1') {
                    stringBuilder.insert(0, "(");
                    stringBuilder.insert(3, ")");
                    stringBuilder.insert(8, "-");
                    System.out.println(stringBuilder);
                    client.setTel(String.valueOf(stringBuilder));
                    client.setCellPhone("");
                } else {
                    System.out.println(stringBuilder);
                    client.setTel(String.valueOf(stringBuilder));
                    client.setCellPhone("");
                }
            }
            if (fone.length() == 9) {
                if (primeiroCaractere == '9') {
                    stringBuilder.insert(0, "(");
                    stringBuilder.insert(1, "1");
                    stringBuilder.insert(2, "4");
                    stringBuilder.insert(3, ")");
                    stringBuilder.insert(9, "-");
                    System.out.println(stringBuilder);
                    client.setTel("");
                    client.setCellPhone(String.valueOf(stringBuilder));
                } else if (primeiroCaractere == '1') {
                    stringBuilder.insert(0, "(");
                    stringBuilder.insert(3, ")");
                    stringBuilder.insert(9, "-");
                    System.out.println(stringBuilder);
                    client.setTel("");
                    client.setCellPhone(String.valueOf(stringBuilder));
                } else {
                    System.out.println(stringBuilder);
                    client.setTel("");
                    client.setCellPhone(String.valueOf(stringBuilder));
                }
            } else {
                stringBuilder.insert(0, "(");
                stringBuilder.insert(3, ")");
                stringBuilder.insert(10, "-");
                System.out.println(stringBuilder);
                client.setTel("");
                client.setCellPhone(String.valueOf(stringBuilder));
            }
        }
    }
}
