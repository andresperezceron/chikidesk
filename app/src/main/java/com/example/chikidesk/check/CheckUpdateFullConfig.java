package com.example.chikidesk.check;

public class CheckUpdateFullConfig {
    private final CheckUpdateConfig checkConfig;
    private final CheckUpdateTemperatura checkTemp;
    private final CheckUpdateInyeccion checkInyeccion;
    private final CheckUpdateRetenPresion checkReten;
    private final CheckUpdateExpulsor checkExpulsor;

    public CheckUpdateFullConfig(CheckUpdateConfig checkConfig, CheckUpdateTemperatura checkTemp,
                                 CheckUpdateInyeccion checkInyeccion, CheckUpdateRetenPresion checkReten,
                                 CheckUpdateExpulsor checkExpulsor) {
        this.checkConfig = checkConfig;
        this.checkTemp = checkTemp;
        this.checkInyeccion = checkInyeccion;
        this.checkReten = checkReten;
        this.checkExpulsor = checkExpulsor;
    }

    public CheckUpdateConfig getCheckConfig() {
        return checkConfig;
    }

    public CheckUpdateTemperatura getCheckTemp() {
        return checkTemp;
    }

    public CheckUpdateInyeccion getCheckInyeccion() {
        return checkInyeccion;
    }

    public CheckUpdateRetenPresion getCheckReten() {
        return checkReten;
    }

    public CheckUpdateExpulsor getCheckExpulsor() {
        return checkExpulsor;
    }

    public boolean isSuccess() {
        return checkConfig.success && checkTemp.success && checkInyeccion.success &&
                checkReten.success && checkExpulsor.success;
    }

    public boolean isEmpty() {
        return checkConfig.empty || checkTemp.empty || checkInyeccion.empty ||
                checkReten.empty || checkExpulsor.empty;
    }

    public boolean areEqualToUpdate() {
        return checkConfig.areEqualsToUpdate && checkTemp.areEqualsToUpdate &&
                checkInyeccion.areEqualsToUpdate && checkExpulsor.areEqualsToUpdate &&
                checkReten.areEqualsToUpdate;
    }
}
