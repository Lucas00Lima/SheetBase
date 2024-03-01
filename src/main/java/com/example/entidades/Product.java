package com.example.entidades;
public class Product {
    final private String internalCode;
    final private String barcode;
    final private String name;
    final private int category;
    final private int price;
    final private int type;
    final private int type2;
    final private String ncm;
    final private String cfop;
    final private String cest;
    final private String cst;
    final private int icms;
    final private String piscod;
    final private String pis;
    final private String cofinscod;
    final private String cofins;
    final private int icmdRed;

    public Product(String internalCode, String barcode, String name, int category, int price, int type, int type2, String ncm, String cfop, String cest, String cst, int icms, String piscod, String pis, String cofinscod, String cofins, int icmdRed) {
        this.internalCode = internalCode;
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.type = type;
        this.type2 = type2;
        this.ncm = ncm;
        this.cfop = cfop;
        this.cest = cest;
        this.cst = cst;
        this.icms = icms;
        this.piscod = piscod;
        this.pis = pis;
        this.cofinscod = cofinscod;
        this.cofins = cofins;
        this.icmdRed = icmdRed;
    }
    public String getInternalCode() {
        return internalCode;
    }
    public String getBarcode() {
        return barcode;
    }
    public String getName() {
        return name;
    }
    public int getCategory() {
        return category;
    }
    public int getPrice() {
        return price;
    }
    public int getType() {
        return type;
    }
    public int getType2() {
        return type2;
    }
    public String getNcm() {
        return ncm;
    }
    public String getCfop() {
        return cfop;
    }
    public String getCest() {
        return cest;
    }
    public String getCst() {
        return cst;
    }
    public int getIcms() {
        return icms;
    }
    public String getPiscod() {
        return piscod;
    }
    public String getPis() {
        return pis;
    }
    public String getCofinscod() {
        return cofinscod;
    }
    public String getCofins() {
        return cofins;
    }
    public int getIcmdRed() {
        return icmdRed;
    }
}
