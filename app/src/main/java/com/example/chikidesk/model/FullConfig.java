package com.example.chikidesk.model;


public class FullConfig {
    private Molde molde;
    private Maquina maquina;
    private Configuracion configuracion;
    private Temperatura temperatura;
    private Inyeccion inyeccion;
    private RetenPresion rentenPresion;
    private Expulsor expulsor;

    public FullConfig() {}

    public FullConfig(Molde molde, Maquina maquina, Configuracion configuracion,
                      Temperatura temperatura, Inyeccion inyeccion, RetenPresion rentenPresion,
                      Expulsor expulsor) {
        this.molde = molde;
        this.maquina = maquina;
        this.configuracion = configuracion;
        this.temperatura = temperatura;
        this.inyeccion = inyeccion;
        this.rentenPresion = rentenPresion;
        this.expulsor = expulsor;
    }

    public Molde getMolde() {
        return molde;
    }

    public void setMolde(Molde molde) {
        this.molde = molde;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public Inyeccion getInyeccion() {
        return inyeccion;
    }

    public void setInyeccion(Inyeccion inyeccion) {
        this.inyeccion = inyeccion;
    }

    public RetenPresion getRentenPresion() {
        return rentenPresion;
    }

    public void setRentenPresion(RetenPresion rentenPresion) {
        this.rentenPresion = rentenPresion;
    }

    public Expulsor getExpulsor() {
        return expulsor;
    }

    public void setExpulsor(Expulsor expulsor) {
        this.expulsor = expulsor;
    }
}
