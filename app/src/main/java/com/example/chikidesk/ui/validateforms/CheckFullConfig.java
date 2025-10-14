package com.example.chikidesk.ui.validateforms;


public class CheckFullConfig {
    private final CheckConfig checkConfig;
    private final CheckInyeccion checkInyeccion;
    private final CheckTemp checkTemp;
    private final CheckExpulsor checkExpulsor;
    private final CheckReten checkReten;

    public CheckFullConfig(CheckConfig checkConfig, CheckInyeccion chekInyeccion,
                           CheckTemp checkTemp, CheckExpulsor checkExpulsor,
                           CheckReten checkReten) {
        this.checkConfig = checkConfig;
        this.checkInyeccion = chekInyeccion;
        this.checkTemp = checkTemp;
        this.checkExpulsor = checkExpulsor;
        this.checkReten = checkReten;
    }

    public CheckConfig getCheckConfig() {
        return checkConfig;
    }

    public CheckInyeccion getCheckInyeccion() {
        return checkInyeccion;
    }

    public CheckTemp getCheckTemp() {
        return checkTemp;
    }

    public CheckExpulsor getCheckExpulsor() {
        return checkExpulsor;
    }

    public CheckReten getCheckReten() {
        return checkReten;
    }

    public boolean isEmpty() {
        return checkConfig.isEmpty || checkTemp.isEmpty || checkInyeccion.isEmpty ||
                checkReten.isEmpty || checkExpulsor.isEmpty;
    }

    public boolean areEqualToUpdate() {
        return checkConfig.equalToUpdate && checkTemp.equalToUpdate &&
                checkInyeccion.equalToUpdate && checkExpulsor.equalToUpdate &&
                checkReten.equalToUpdate;
    }
}