package org.example.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {
    private String id;
    private String name;
    private String aux_name;
    private String email;
    private String numDoc;
    private String numDoc2;
    private String tel;
    private String phone;
    private String street;
    private String numberStreet;
    private String cep;
    private int state;
    private int city;
}
