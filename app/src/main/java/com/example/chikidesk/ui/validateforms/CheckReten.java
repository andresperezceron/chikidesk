package com.example.chikidesk.ui.validateforms;

import com.example.chikidesk.model.RetenPresion;
import com.google.android.material.textfield.TextInputLayout;

public class CheckReten extends BaseCheck<RetenPresion> {
    private final int VELOCIDAD = 0;
    private final int PRESION = 1;
    private final int TIEMPO = 2;
    private RetenPresion retenChecked;
    public CheckReten(TextInputLayout[] arrayTils) {
        super(arrayTils);
    }

    @Override
    public void checkInsert(RetenPresion newEntity) {
        retenChecked = newEntity;
        checkStatus = true;
        isEmpty = false;

        resetAllTils();
        if(retenChecked.getVelocidad().isEmpty()) {
            isEmpty = true;
            getTil(VELOCIDAD).setError("Velocidad = 0");
            retenChecked.setVelocidad("0");
        }

        if(retenChecked.getPresion().isEmpty()) {
            isEmpty = true;
            getTil(PRESION).setError("Presion = 0");
            retenChecked.setPresion("0");
        }

        if(retenChecked.getTiempo().isEmpty()) {
            isEmpty = true;
            getTil(TIEMPO).setError("Tiempo = 0");
            retenChecked.setTiempo("0");
        }
    }

    @Override
    public void checkUpdate(RetenPresion oldEntity, RetenPresion newEntity) {
        retenChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate){
            checkStatus = false;
            isEmpty = false;
            return;
        }

        checkStatus = true;
        isEmpty = false;
        resetAllTils();

        if(retenChecked.getVelocidad().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(VELOCIDAD).setError(" ");
        }
        if(retenChecked.getPresion().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(PRESION).setError(" ");
        }
        if(retenChecked.getTiempo().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(TIEMPO).setError(" ");
        }
    }

    @Override
    public RetenPresion getCheckedEntity() {
        return checkStatus ? retenChecked : null;
    }

    @Override
    protected boolean areEquals(RetenPresion oldEntity, RetenPresion newEntity) {
        return oldEntity.getVelocidad().equals(newEntity.getVelocidad()) &&
                oldEntity.getPresion().equals(newEntity.getTiempo()) &&
                oldEntity.getTiempo().equals(newEntity.getTiempo());
    }
}
