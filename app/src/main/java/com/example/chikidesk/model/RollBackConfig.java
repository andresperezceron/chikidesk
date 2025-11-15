package com.example.chikidesk.model;

import java.util.List;

public class RollBackConfig {
    private final List<Configuracion> config;
    private final List<Temperatura> temp;
    private final List<Inyeccion> inyec;
    private final List<RetenPresion> reten;
    private final List<Expulsor> expulsorList;
    
    public RollBackConfig(List<Configuracion> config, List<Temperatura> temp, List<Inyeccion> inyec,
                          List<RetenPresion> reten, List<Expulsor> expulsor) {
        this.config = config;
        this.temp = temp;
        this.inyec = inyec;
        this.reten = reten;
        this.expulsorList = expulsor;
    }

    public List<Configuracion> getConfig() {
        return config;
    }

    public List<Temperatura> getTemp() {
        return temp;
    }

    public List<Inyeccion> getInyec() {
        return inyec;
    }

    public List<RetenPresion> getReten() {
        return reten;
    }

    public List<Expulsor> getExpulsorList() {
        return expulsorList;
    }
}