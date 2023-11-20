package org.example.entidades;


public class Service {
    final private String id;
    final private String name;

    public Service(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
