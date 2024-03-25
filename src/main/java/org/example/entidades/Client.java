package org.example.entidades;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Client {
    private String id;
    private String name;
    private String numDoc;
    private int type1;
    private String numDoc2;
    private String companyName;
    private String phone;
    private String cellPhone;
    private String phone2;
    private String cellPhone2;
    private int gender;
    private String email;
    private Date birthday;
    private int state;
    private int city;
    private String street;
    private String obs;
    private Date register;
}