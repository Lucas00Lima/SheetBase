package org.example.entidades;

public class Material {
    final private double id;
    final private String name;
    final private String medida;
    public Material(double id, String name, String medida) {
        this.id = id;
        this.name = name;
        this.medida = medida;
    }
    public double getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMedida() {
        return medida;
    }
}
