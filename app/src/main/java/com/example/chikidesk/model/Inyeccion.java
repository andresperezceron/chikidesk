package com.example.chikidesk.model;

import com.example.chikidesk.db.IEntity;

public class Inyeccion implements IEntity<Integer> {
    private int id;
    private String velocidad1;
    private String presion1;
    private String velocidad2;
    private String presion2;
    private String velocidad3;
    private String presion3;
    private String velocidad4;
    private String presion4;
    private String velocidad5;
    private String presion5;

    public Inyeccion(int id, String velocidad1, String presion1, String velocidad2, String presion2,
                     String velocidad3, String presion3, String velocidad4, String presion4,
                     String velocidad5, String presion5) {
        this.id = id;
        this.velocidad1 = velocidad1;
        this.presion1 = presion1;
        this.velocidad2 = velocidad2;
        this.presion2 = presion2;
        this.velocidad3 = velocidad3;
        this.presion3 = presion3;
        this.velocidad4 = velocidad4;
        this.presion4 = presion4;
        this.velocidad5 = velocidad5;
        this.presion5 = presion5;
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

    public String getVelocidad3() {
        return velocidad3;
    }

    public void setVelocidad3(String velocidad3) {
        this.velocidad3 = velocidad3;
    }

    public String getPresion3() {
        return presion3;
    }

    public void setPresion3(String presion3) {
        this.presion3 = presion3;
    }

    public String getVelocidad4() {
        return velocidad4;
    }

    public void setVelocidad4(String velocidad4) {
        this.velocidad4 = velocidad4;
    }

    public String getPresion4() {
        return presion4;
    }

    public void setPresion4(String presion4) {
        this.presion4 = presion4;
    }

    public String getVelocidad5() {
        return velocidad5;
    }

    public void setVelocidad5(String velocidad5) {
        this.velocidad5 = velocidad5;
    }

    public String getPresion5() {
        return presion5;
    }

    public void setPresion5(String presion5) {
        this.presion5 = presion5;
    }
}