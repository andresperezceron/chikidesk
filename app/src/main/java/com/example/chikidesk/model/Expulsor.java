package com.example.chikidesk.model;

import com.example.chikidesk.db.IEntity;

public class Expulsor implements IEntity<Integer> {
    private int id;
    private String velocidad1;
    private String presion1;
    private String posicion1;
    private String velocidad2;
    private String presion2;
    private String posicion2;

    public Expulsor(int id, String velocidad1, String presion1, String posicion1,
                    String velocidad2, String presion2, String posicion2) {
        this.id = id;
        this.velocidad1 = velocidad1;
        this.presion1 = presion1;
        this.posicion1 = posicion1;
        this.velocidad2 = velocidad2;
        this.presion2 = presion2;
        this.posicion2 = posicion2;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getVelocidad1() {
        return velocidad1;
    }

    public void setVelocidad1(String velocidad1) {
        this.velocidad1 = velocidad1;
    }

    public String getPresion1() {
        return presion1;
    }

    public void setPresion1(String presion1) {
        this.presion1 = presion1;
    }

    public String getPosicion1() {
        return posicion1;
    }

    public void setPosicion1(String posicion1) {
        this.posicion1 = posicion1;
    }

    public String getVelocidad2() {
        return velocidad2;
    }

    public void setVelocidad2(String velocidad2) {
        this.velocidad2 = velocidad2;
    }

    public String getPresion2() {
        return presion2;
    }

    public void setPresion2(String presion2) {
        this.presion2 = presion2;
    }

    public String getPosicion2() {
        return posicion2;
    }

    public void setPosicion2(String posicion2) {
        this.posicion2 = posicion2;
    }
}