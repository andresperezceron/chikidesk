package com.example.chikidesk.check;

import com.example.chikidesk.ui.validateforms.CheckReten;

public class CheckFullConfig {
    private final CheckNewConfig checkConfig;
    private final CheckNewInyeccion checkInyeccion;
    private final CheckNewTemperatura checkTemp;
    private final CheckNewExpulsor checkExpulsor;
    private final CheckReten checkReten;

    public CheckFullConfig(CheckNewConfig checkConfig, CheckNewInyeccion chekInyeccion,
                           CheckNewTemperatura checkTemp, CheckNewExpulsor checkExpulsor,
                           CheckReten checkReten) {
        this.checkConfig = checkConfig;
        this.checkInyeccion = chekInyeccion;
        this.checkTemp = checkTemp;
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

    public CheckReten getCheckReten() {
        return checkReten;
    }

    public boolean isEmpty() {
        return checkConfig.empty || checkTemp.empty || checkInyeccion.empty ||
                checkReten.isEmpty || checkExpulsor.empty;
    }

    public boolean areEqualToUpdate() {
        return checkConfig.areEqualsToUpdate && checkTemp.areEqualsToUpdate &&
                checkInyeccion.areEqualsToUpdate && checkExpulsor.areEqualsToUpdate &&
                checkReten.equalToUpdate;
    }
}