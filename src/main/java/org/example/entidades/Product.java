package org.example.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String internalCode;
    private String barcode;
    private String name;
    private int category;
    private String description;
    private int price;
    private int type;
    private int type2;
    private String combo;
    private int cost;
    private String ncm;
    private String cfop;
    private String cest;
    private String cst;
    private int icms;
    private String piscod;
    private String pis;
    private String cofinscod;
    private String cofins;
    private int icmdRed;
    private int currentStock;
    private String measureUnit;
    private int delivery;
    private int hall_table;
    private int card;
    private int balcony;
    private int status;
}
