package com.example.chikidesk.check.insert;


public class CheckInsertFullConfig {
    private final CheckInsertConfig checkConfig;
    private final CheckInsertInyeccion checkInyeccion;
    private final CheckInsertTemperatura checkTemp;
    private final CheckInsertExpulsor checkExpulsor;
    private final CheckInsertRetenPresion checkReten;

    public CheckInsertFullConfig(CheckInsertConfig checkConfig, CheckInsertTemperatura checkTemp,
                                 CheckInsertInyeccion checkInyeccion, CheckInsertExpulsor checkExpulsor,
                                 CheckInsertRetenPresion checkReten) {
        this.checkConfig = checkConfig;
        this.checkTemp = checkTemp;
        this.checkInyeccion = checkInyeccion;
        this.checkExpulsor = checkExpulsor;
        this.checkReten = checkReten;
    }

    public CheckInsertConfig getCheckConfig() {
        return checkConfig;
    }
    public CheckInsertInyeccion getCheckInyeccion() {
        return checkInyeccion;
    }
    public CheckInsertTemperatura getCheckTemp() {
        return checkTemp;
    }
    public CheckInsertExpulsor getCheckExpulsor() { return checkExpulsor; }
    public CheckInsertRetenPresion getCheckReten() {
        return checkReten;
    }

    public boolean isEmpty() {
        return checkConfig.isEmpty() || checkTemp.isEmpty() || checkInyeccion.isEmpty() ||
                checkReten.isEmpty() || checkExpulsor.isEmpty();
    }
}