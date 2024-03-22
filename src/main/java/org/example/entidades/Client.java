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
    private int id;
    private String name;
    private String numDoc;
    private int type1;
    private String numDoc2;
    private String companyName;
    private String tel;
    private String cellPhone;
    private int gender;
    private String email;
    private Date birthday;
    private int state;
    private int city;
    private String street;
    private String nameContact1;
    private String celContact1;
    private String nameContact2;
    private String celContact2;
    private String obs;
    private Date register;
}