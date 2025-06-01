package com.example.chikidesk.model;

import androidx.annotation.NonNull;

public class Molde {
    private int id;
    private String nombre;
    private String referencia;
    private String descripcion;

    public Molde(int id, String nombre, String referencia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre + " (" + referencia + ")";

    }
}