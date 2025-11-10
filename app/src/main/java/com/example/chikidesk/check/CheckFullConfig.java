package com.example.chikidesk.check;


public class CheckFullConfig {
    private final CheckNewConfig checkConfig;
    private final CheckNewInyeccion checkInyeccion;
    private final CheckNewTemperatura checkTemp;
    private final CheckNewExpulsor checkExpulsor;
    private final CheckNewReten checkReten;

    public CheckFullConfig(CheckNewConfig checkConfig, CheckNewTemperatura checkTemp,
                           CheckNewInyeccion checkInyeccion, CheckNewExpulsor checkExpulsor,
                           CheckNewReten checkReten) {
        this.checkConfig = checkConfig;
        this.checkTemp = checkTemp;
        this.checkInyeccion = checkInyeccion;
        this.checkExpulsor = checkExpulsor;
        this.checkReten = checkReten;
    }

    public CheckNewConfig getCheckConfig() {
        return checkConfig;
    }

    public CheckNewInyeccion getCheckInyeccion() {
        return checkInyeccion;
    }

    public CheckNewTemperatura getCheckTemp() {
        return checkTemp;
    }

    public CheckNewExpulsor getCheckExpulsor() {
        return checkExpulsor;
    }

    public CheckNewReten getCheckReten() {
        return checkReten;
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