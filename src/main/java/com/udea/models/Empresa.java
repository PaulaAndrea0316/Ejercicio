package com.udea.models;

import lombok.Data;

@Data
public class Empresa {
    private String nombre;
    private String direccion;
    private String telefono;
    private String nit;

    public Empresa() {
    }

    public Empresa(String nombre, String direccion, String telefono, String nit) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nit = nit;
    }
}
