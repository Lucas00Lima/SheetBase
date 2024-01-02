package org.example.Functions;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.example.LogTex;

public class TreatmentVariable {
    private final DataFormatter dataFormatter = new DataFormatter();
    public int variavelValue(String valor) {
        int priceValue;
        if (valor.isEmpty()) {
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
        if (valor.isEmpty()) {
            value = 0;
        } else {
            value = Integer.parseInt(valor) * 1000;
        }
        return value;
    }
    public int tratmentOrigem(Cell valorCell) {
        String valor = dataFormatter.formatCellValue(valorCell);
        int valueZero;
        if (valor.isEmpty()) {
            valueZero = 1;
        } else {
            valueZero = Integer.parseInt(valor);
        }
        return valueZero;
    }
    public int tratmentZero(String valor) {
        int valueZero;
        if (valor.isEmpty()) {
            valueZero = 0;
        } else {
            valueZero = Integer.parseInt(valor);
        }
        return valueZero;
    }
    public int cpfOrCnpj(Cell numDocCell) {
        String valor = dataFormatter.formatCellValue(numDocCell).replaceAll("\\D", "");
        if (valor.isEmpty()) {
            return 1;
        } else if (valor.length() >= 14) {
            return 2;
        } else if (valor.length() <= 11) {
            return 1;
        } else {
            return 0;
        }
    }
    public int gender(Cell genderCell) {
        String valor = dataFormatter.formatCellValue(genderCell);
        if ( valor == null || valor.isEmpty()) { return 0; }
        if (valor.contains("Masculino") || valor.contains("M") || valor.contains("masculino")) {
            return 1;
        } else if (valor.contains("Feminino") || valor.contains("F") || valor.contains("feminino")) {
            return 2;
        } else {
            return 0;
        }
    }
    public Date dateBrithday(Cell valorCell) {
        try {
            String valor = dataFormatter.formatCellValue(valorCell);
            if (valor == null || valor.isEmpty()) { return null; }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return (Date) formatter.parse(valor);
        } catch (ParseException e) {
            LogTex.textError("Erro DATA");
        }
        return null;
    }
    public String measureUnit(Cell measureUnitCell) {
        String measureUnit = dataFormatter.formatCellValue(measureUnitCell);
        if (measureUnit.isEmpty()) { measureUnit = "u"; }
        return measureUnit;
    }
    public String celTreatment(Cell celFone) {
        String fone = dataFormatter.formatCellValue(celFone);
        StringBuilder stringBuilder = new StringBuilder(fone);
        if (fone.length() == 10) {
            // Inserir parênteses no início e fechá-los no índice 2 e 6
            stringBuilder.insert(0, "(");
            stringBuilder.insert(3, ")");
            stringBuilder.insert(8, "-");
        } else if (fone.length() == 11) {
            // Inserir parênteses no início e fechá-los no índice 3 e 7
            stringBuilder.insert(0, "(");
            stringBuilder.insert(4, ")");
            stringBuilder.insert(8, "-");
        }
        System.out.println(fone + " Phone");
        return stringBuilder.toString();
    }
}
