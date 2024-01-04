package com.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.example.Functions.Get;
import com.example.TreatmentRows;
import com.example.entidades.Product;
import java.sql.Connection;

public class ProductFactory implements TreatmentRows {

    public Product produto(Sheet sheet, int rowIndex, Connection connection) {
        Get get = new Get(connection);
        Row row = sheet.getRow(rowIndex);
        Cell codeCell = row.getCell(0);
        Cell barcodeCell = row.getCell(1);
        Cell nameCell = row.getCell(2);
        Cell descriptionCell = row.getCell(3);
        Cell categoryCell = row.getCell(4);
        Cell categoryCellPrincipal = row.getCell(5);
        Cell priceCell = row.getCell(6);
        Cell typeCell = row.getCell(7);
        Cell type2Cell = row.getCell(8);
        Cell comboCell = row.getCell(9);
        Cell costCell = row.getCell(10);
        Cell ncmCell = row.getCell(11);
        Cell cfopCell = row.getCell(12);
        Cell cestCell = row.getCell(13);
        Cell cstCell = row.getCell(14);
        Cell icmsCell = row.getCell(15);
        Cell piscodCell = row.getCell(16);
        Cell pisCell = row.getCell(17);
        Cell cofinscodCell = row.getCell(18);
        Cell cofinsCell = row.getCell(19);
        Cell icmsRedCell = row.getCell(20);
        Cell currentStockCell = row.getCell(21);
        Cell measureUnitCell = row.getCell(22);
        Cell deliveryCell = row.getCell(23);
        Cell tableCell = row.getCell(24);
        Cell cardCell = row.getCell(25);
        Cell balconyCell = row.getCell(26);
        Cell statusCell = row.getCell(27);
        int categoryId = get.numberCategory(categoryCell,categoryCellPrincipal);
        String internalCode = get.verifyInternalCode(codeCell, categoryId);
        String barcode = dataFormatter.formatCellValue(barcodeCell);
        String name = dataFormatter.formatCellValue(nameCell);
        String description = dataFormatter.formatCellValue(descriptionCell);
        String priceString = dataFormatter.formatCellValue(priceCell);
        int price = treatmentVariable.variavelValue(priceString);
        int type = treatmentVariable.tratmentOrigem(typeCell);
        String type2String = dataFormatter.formatCellValue(type2Cell);
        int type2 = treatmentVariable.tratmentZero(type2String);
        String costString = dataFormatter.formatCellValue(costCell);
        String combo = dataFormatter.formatCellValue(comboCell);
        int cost = treatmentVariable.variavelValue(costString);
        String ncm = dataFormatter.formatCellValue(ncmCell);
        String cfop = dataFormatter.formatCellValue(cfopCell);
        String cest = dataFormatter.formatCellValue(cestCell);
        String cst = dataFormatter.formatCellValue(cstCell);
        int icms = treatmentVariable.tratmentIcms(icmsCell);
        String piscod = dataFormatter.formatCellValue(piscodCell);
        String pis = dataFormatter.formatCellValue(pisCell);
        String cofinscod = dataFormatter.formatCellValue(cofinscodCell);
        String cofins = dataFormatter.formatCellValue(cofinsCell);
        int icmsRed = treatmentVariable.tratmentIcms(icmsRedCell);
        String currentStockString = dataFormatter.formatCellValue(currentStockCell);
        int currentStock = treatmentVariable.tratmentZero(currentStockString) * 1000;
        String measureUnit = treatmentVariable.measureUnit(measureUnitCell);
        int delivery = treatmentVariable.tratmentOrigem(deliveryCell);
        int hall_table = treatmentVariable.tratmentOrigem(tableCell);
        int card = treatmentVariable.tratmentOrigem(cardCell);
        int balcony = treatmentVariable.tratmentOrigem(balconyCell);
        int status = treatmentVariable.tratmentOrigem(statusCell);
        return new Product(internalCode, barcode, name, categoryId, description, price, type, type2, combo , cost,  ncm, cfop, cest, cst,
                icms, piscod, pis, cofinscod, cofins, icmsRed, currentStock, measureUnit, delivery, hall_table, card, balcony, status);
    }
}
