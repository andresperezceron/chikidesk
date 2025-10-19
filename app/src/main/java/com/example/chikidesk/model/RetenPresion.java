package com.example.chikidesk.model;


public class RetenPresion {
    private int id;
    private String velocidad;
    private String presion;
    private String tiempo;

    public RetenPresion(int id, String velocidad, String presion, String tiempo) {
        this.id = id;
        this.velocidad = velocidad;
        this.presion = presion;
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}