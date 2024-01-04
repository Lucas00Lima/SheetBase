package com.example.entidades;
import java.sql.Date;
public class Client {
    final private String id;
    final private String name;
    final private String numDoc;
    final private int type1;
    final private String numDoc2;
    final private String companyName;
    final private String tel;
    final private int gender;
    final private String email;
    final private Date birthday;
    final private int state;
    final private int city;
    final private String street;
    final private String nameContact1;
    final private String celContact1;
    final private String nameContact2;
    final private String celContact2;
    final private String obs;
    final private Date register;
    public Client(String id, String name, String numDoc, int type1, String numDoc2, String companyName, String tel, int gender, String email, Date birthday, int state, int city, String street, String nameContact1, String celContact1, String nameContact2, String celContact2, String obs, Date register) {
        this.id = id;
        this.name = name;
        this.numDoc = numDoc;
        this.type1 = type1;
        this.numDoc2 = numDoc2;
        this.companyName = companyName;
        this.tel = tel;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
        this.state = state;
        this.city = city;
        this.street = street;
        this.nameContact1 = nameContact1;
        this.celContact1 = celContact1;
        this.nameContact2 = nameContact2;
        this.celContact2 = celContact2;
        this.obs = obs;
        this.register = register;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getNumDoc() {
        return numDoc;
    }
    public int getType1() {
        return type1;
    }
    public String getNumDoc2() {
        return numDoc2;
    }
    public String getCompanyName() {
        return companyName;
    }
    public String getTel() {
        return tel;
    }
    public int getGender() {
        return gender;
    }
    public String getEmail() {
        return email;
    }
    public Date getBirthday() {
        return birthday;
    }
    public int getState() {
        return state;
    }
    public int getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getNameContact1() {
        return nameContact1;
    }
    public String getCelContact1() {
        return celContact1;
    }
    public String getNameContact2() {
        return nameContact2;
    }
    public String getCelContact2() {
        return celContact2;
    }
    public String getObs() {
        return obs;
    }
    public Date getRegister() {
        return register;
    }
}