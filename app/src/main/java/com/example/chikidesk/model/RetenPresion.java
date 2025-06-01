package com.example.chikidesk.model;

public class RetenPresion {
    private int id_configuracion;
    private String velocidad;
    private String presion;
    private String tiempo;

    public RetenPresion(int id_configuracion, String velocidad, String presion, String tiempo) {
        this.id_configuracion = id_configuracion;
        this.velocidad = velocidad;
        this.presion = presion;
        this.tiempo = tiempo;
    }

    public int getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(int id_configuracion) {
        this.id_configuracion = id_configuracion;
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
