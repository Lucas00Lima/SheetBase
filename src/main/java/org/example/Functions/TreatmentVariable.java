package org.example.Functions;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.example.entidades.Client;

public class TreatmentVariable {
    private DataFormatter dataFormatter = new DataFormatter();

    public int variavelValue(String valor) {
        int priceValue;
        if (valor.equals("")) {
            priceValue = 0;
        } else if (valor.contains(",") && valor.contains(".")) {
            valor = valor.replace(".", "");
            valor = valor.replace(",", "");
            priceValue = Integer.parseInt(valor);
        } else if (valor.contains(",")) {
            int index = valor.indexOf(",");
            valor = valor.replace(",", "");
            if (valor.length() == 1) {
                priceValue = Integer.parseInt(valor) * 100;
            } else if (valor.length() == 2) {
                priceValue = Integer.parseInt(valor) * 10;
            } else if (index == 2 && valor.length() == 3) {
                valor += "0";
                priceValue = Integer.parseInt(valor);
            } else if (index != 2 && valor.length() == 3) {
                priceValue = Integer.parseInt(valor);
            } else {
                priceValue = Integer.parseInt(valor);
            }
        } else {
            if (valor.length() == 1) {
                priceValue = Integer.parseInt(valor) * 100;
            } else if (valor.length() == 2) {
                priceValue = Integer.parseInt(valor) * 100;
            } else if (valor.length() == 3) {
                priceValue = Integer.parseInt(valor) * 100;
            } else {
                priceValue = Integer.parseInt(valor);
            }
        }
        return priceValue;
    }

    public int tratmentIcms(Cell icmsCell) {
        String valor = dataFormatter.formatCellValue(icmsCell);
        int value;
        if (valor.equals("")) {
            value = 0;
        } else {
            value = Integer.parseInt(valor) * 1000;
        }
        return value;
    }

    public int tratmentOrigem(Cell valorCell) {
        String valor = dataFormatter.formatCellValue(valorCell);
        int valueZero;
        if (valor.equals("")) {
            valueZero = 1;
        } else {
            valueZero = Integer.parseInt(valor);
        }
        return valueZero;
    }

    public int tratmentZero(String valor) {
        int valueZero;
        if (valor.equals("")) {
            valueZero = 0;
        } else {
            valueZero = Integer.parseInt(valor);
        }
        return valueZero;
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
        if (valor == null || valor.equals("")) {
            return 0;
        }
        if (valor.contains("Masculino") || valor.contains("M") || valor.contains("Masculino")) {
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
            return doc = "";
        }
    }

    //TODO: colocar um verificador de ID
    public int idClient(Cell codeCell) {
        if (codeCell == null) {

        }
        int id = Integer.parseInt(dataFormatter.formatCellValue(codeCell));


        return id;
    }

    public Date dateBrithday(Cell valorCell) {
        try {
            String valor = dataFormatter.formatCellValue(valorCell);
            if (valor == null || valor.isEmpty() || valor.contains("") || valor.contains(" ")) {
                return null;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return (Date) formatter.parse(valor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String measureUnit(Cell measureUnitCell) {
        String measureUnit = dataFormatter.formatCellValue(measureUnitCell);
        if (measureUnit.isEmpty()) {
            measureUnit = "u";
        }
        return measureUnit;
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
