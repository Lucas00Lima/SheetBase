package org.example.entidades;

public class Fornecedor {
   final private String id;
   final private String name;
   final private String aux_name;
   final private String email;
   final private String numDoc;
   final private String numDoc2;
   final private String tel;
   final private String phone;
   final private String street;
   final private String numberStreet;
   final private String cep;
   final private int state;
   final private int city;

    public Fornecedor(String id, String name, String aux_name, String email, String numDoc, String numDoc2, String tel, String phone, String street, String numberStreet, String cep, int state, int city) {
        this.id = id;
        this.name = name;
        this.aux_name = aux_name;
        this.email = email;
        this.numDoc = numDoc;
        this.numDoc2 = numDoc2;
        this.tel = tel;
        this.phone = phone;
        this.street = street;
        this.numberStreet = numberStreet;
        this.cep = cep;
        this.state = state;
        this.city = city;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAux_name() {
        return aux_name;
    }
    public String getEmail() {
        return email;
    }
    public String getNumDoc() {
        return numDoc;
    }
    public String getNumDoc2() {
        return numDoc2;
    }
    public String getTel() {
        return tel;
    }
    public String getPhone() {
        return phone;
    }
    public String getStreet() {
        return street;
    }
    public String getNumberStreet() {
        return numberStreet;
    }
    public String getCep() {
        return cep;
    }
    public int getState() {
        return state;
    }
    public int getCity() {
        return city;
    }
}
