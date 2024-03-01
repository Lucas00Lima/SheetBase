package com.example.factory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.example.functions.Get;
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
        Cell categoryCell = row.getCell(3);
        Cell categoryCellPrincipal = row.getCell(4);
        Cell priceCell = row.getCell(5);
        Cell typeCell = row.getCell(6);
        Cell type2Cell = row.getCell(7);
        Cell ncmCell = row.getCell(8);
        Cell cfopCell = row.getCell(9);
        Cell cestCell = row.getCell(10);
        Cell cstCell = row.getCell(11);
        Cell icmsCell = row.getCell(12);
        Cell piscodCell = row.getCell(13);
        Cell pisCell = row.getCell(14);
        Cell cofinscodCell = row.getCell(15);
        Cell cofinsCell = row.getCell(16);
        Cell icmsRedCell = row.getCell(17);
        int categoryId = get.numberCategory(categoryCell,categoryCellPrincipal);
        String internalCode = get.verifyInternalCode(codeCell, categoryId);
        String barcode = dataFormatter.formatCellValue(barcodeCell);
        String name = dataFormatter.formatCellValue(nameCell);
        String priceString = dataFormatter.formatCellValue(priceCell);
        int price = treatmentVariable.variavelValue(priceString);
        int type = treatmentVariable.tratmentOrigem(typeCell);
        String type2String = dataFormatter.formatCellValue(type2Cell);
        int type2 = treatmentVariable.tratmentZero(type2String);
        String ncm = dataFormatter.formatCellValue(ncmCell);
        String cfop = dataFormatter.formatCellValue(cfopCell);
        String cest = dataFormatter.formatCellValue(cestCell);
        String cst = dataFormatter.formatCellValue(cstCell);
        int icms = treatmentVariable.tratmentIcms(icmsCell);
        String piscod = dataFormatter.formatCellValue(piscodCell);
        int pis = treatmentVariable.tratmentIcms(pisCell);
        String cofinscod = dataFormatter.formatCellValue(cofinscodCell);
        int cofins = treatmentVariable.tratmentIcms(cofinsCell);
        int icmsRed = treatmentVariable.tratmentIcms(icmsRedCell);
        return new Product(internalCode, barcode, name, categoryId, price, type, type2 , ncm, cfop, cest, cst,
                icms, piscod, pis, cofinscod, cofins, icmsRed);
    }
}
