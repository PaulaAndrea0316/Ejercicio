package com.udea.models;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Empresa {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
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
