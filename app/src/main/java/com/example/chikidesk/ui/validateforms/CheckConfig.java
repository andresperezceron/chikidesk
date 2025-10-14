package com.example.chikidesk.ui.validateforms;


import com.example.chikidesk.model.Configuracion;
import com.google.android.material.textfield.TextInputLayout;

public class CheckConfig extends BaseCheck<Configuracion, Integer> {
    private Configuracion configChecked;
    private final int PLASTIFICACION = 0;
    private final int CICLO = 1;
    private final int CICLO_REAL = 2;
    private final int ENFRIAMIENTO = 3;
    private final int TIMEOUT = 4;
    private final int MATERIAL = 5;
    private final int OBSERVACIONES = 6;

    public CheckConfig(TextInputLayout[] arrayTils) {
        super(arrayTils);
    }

    @Override
    public void checkInsert(Configuracion newEntity) {
        configChecked = newEntity;
        checkStatus = true;
        isEmpty = false;

        resetAllTils();
        if(configChecked.getPlastificacion().isEmpty()) {
            isEmpty = true;
            getTil(PLASTIFICACION).setError("Sin plastificacion");
            configChecked.setPlastificacion("Sin plastificacion");
        }

        if(configChecked.getTiempoCiclo().isEmpty()) {
            isEmpty = true;
            getTil(CICLO).setError("Ciclo = 0");
            configChecked.setTiempoCiclo("0");
        }

        if(configChecked.getTiempoCicloReal().isEmpty()) {
            isEmpty = true;
            getTil(CICLO_REAL).setError("Real = 0");
            configChecked.setTiempoCicloReal("0");
        }

        if(configChecked.getTiempoEnfriar().isEmpty()) {
            isEmpty = true;
            getTil(ENFRIAMIENTO).setError("Enfriar = 0");
            configChecked.setTiempoEnfriar("0");
        }

        if(configChecked.getTimeOut().isEmpty()) {
            isEmpty = true;
            getTil(TIMEOUT).setError("TimeOut = 0");
            configChecked.setTimeOut("0");
        }

        if(configChecked.getMaterial().isEmpty()) {
            isEmpty = true;
            getTil(MATERIAL).setError("Sin material");
            configChecked.setMaterial("Sin material");
        }

        if(configChecked.getObservaciones().isEmpty()) {
            configChecked.setObservaciones("Sin observaciones");
        }
    }

    @Override
    public void checkUpdate(Configuracion oldEntity, Configuracion newEntity) {
        configChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            isEmpty = false;
            return;
        }

        checkStatus = true;
        isEmpty = false;
        resetAllTils();

        if(configChecked.getPlastificacion().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(PLASTIFICACION).setError(" ");
        }
        if(configChecked.getTiempoCiclo().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(CICLO).setError(" ");
        }
        if(configChecked.getTiempoCicloReal().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(CICLO_REAL).setError(" ");
        }
        if(configChecked.getTiempoEnfriar().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(ENFRIAMIENTO).setError(" ");
        }
        if(configChecked.getTimeOut().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(TIMEOUT).setError(" ");
        }
        if(configChecked.getMaterial().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(MATERIAL).setError(" ");
        }
        if(configChecked.getObservaciones().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(OBSERVACIONES).setError(" ");
        }
    }

    @Override
    public Configuracion getCheckedEntity() {
        return checkStatus ?  configChecked : null;
    }

    @Override
    protected boolean areEquals(Configuracion oldEntity, Configuracion newEntity) {
        return oldEntity.getPlastificacion().equals(newEntity.getPlastificacion()) &&
                oldEntity.getTiempoCiclo().equals(newEntity.getTiempoCiclo()) &&
                oldEntity.getTiempoCicloReal().equals(newEntity.getTiempoCicloReal()) &&
                oldEntity.getTiempoEnfriar().equals(newEntity.getTiempoEnfriar()) &&
                oldEntity.getTimeOut().equals(newEntity.getTimeOut()) &&
                oldEntity.getMaterial().equals(newEntity.getMaterial()) &&
                oldEntity.getObservaciones().equals(newEntity.getObservaciones());
    }

    public Configuracion getConfigForBundle() {
        return configChecked;
    }
}