package com.example.chikidesk.check.update;

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
        return checkConfig.isSuccess() && checkTemp.isSuccess() && checkInyeccion.isSuccess() &&
                checkReten.isSuccess() && checkExpulsor.isSuccess();
    }

    public boolean isEmpty() {
        return checkConfig.isEmpty() || checkTemp.isEmpty() || checkInyeccion.isEmpty() ||
                checkReten.isEmpty() || checkExpulsor.isEmpty();
    }

    public boolean areEqualToUpdate() {
        return checkConfig.isAreEqualsToUpdate() && checkTemp.isAreEqualsToUpdate() &&
                checkInyeccion.isAreEqualsToUpdate() && checkExpulsor.isAreEqualsToUpdate() &&
                checkReten.isAreEqualsToUpdate();
    }
}
