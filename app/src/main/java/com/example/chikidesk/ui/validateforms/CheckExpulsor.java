package com.example.chikidesk.ui.validateforms;

import com.example.chikidesk.model.Expulsor;
import com.google.android.material.textfield.TextInputLayout;

public class CheckExpulsor extends BaseCheck<Expulsor, Integer> {
    private final int VEL1 = 0;
    private final int VEL2 = 1;
    private final int PRE1 = 2;
    private final int PRE2 = 3;
    private final int POS1 = 4;
    private final int POS2 = 5;
    private Expulsor expChecked;

    public CheckExpulsor(TextInputLayout[] arrayTils) {
        super(arrayTils);
    }

    @Override
    public void checkInsert(Expulsor newEntity) {
        expChecked = newEntity;
        checkStatus = true;
        isEmpty = false;

        resetAllTils();
        if(expChecked.getVelocidad1().isEmpty()) {
            isEmpty = true;
            getTil(VEL1).setError("Vel 1 = 0");
            expChecked.setVelocidad1("0");
        }

        if(expChecked.getVelocidad2().isEmpty()) {
            isEmpty = true;
            getTil(VEL2).setError("Vel 2 = 0");
            expChecked.setVelocidad2("0");
        }

        if(expChecked.getPresion1().isEmpty()) {
            isEmpty = true;
            getTil(PRE1).setError("Pre 1 = 0");
            expChecked.setPresion1("0");
        }

        if(expChecked.getPresion2().isEmpty()) {
            isEmpty = true;
            getTil(PRE2).setError("Pre 2 = 0");
            expChecked.setPresion2("0");
        }

        if(expChecked.getPosicion1().isEmpty()) {
            isEmpty = true;
            getTil(POS1).setError("Pos 1 = 0");
            expChecked.setPosicion1("0");
        }

        if(expChecked.getPosicion2().isEmpty()) {
            isEmpty = true;
            getTil(POS2).setError("Pos 2 = 0");
            expChecked.setPosicion2("0");
        }
    }

    @Override
    public void checkUpdate(Expulsor oldEntity, Expulsor newEntity) {
        expChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            isEmpty = false;
            return;
        }

        checkStatus = true;
        isEmpty = false;
        resetAllTils();

        if(expChecked.getVelocidad1().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(VEL1).setError(" ");
        }
        if(expChecked.getVelocidad2().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(VEL2).setError(" ");
        }
        if(expChecked.getPresion1().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(PRE1).setError(" ");
        }
        if(expChecked.getPresion2().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(PRE2).setError(" ");
        }
        if(expChecked.getPosicion1().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(POS1).setError(" ");
        }
        if(expChecked.getPosicion2().isEmpty()) {
            isEmpty = true;
            checkStatus = false;
            getTil(POS2).setError(" ");
        }
    }

    @Override
    public Expulsor getCheckedEntity() {
        return checkStatus ?  expChecked : null;
    }

    @Override
    protected boolean areEquals(Expulsor oldEntity, Expulsor newEntity) {
        return oldEntity.getVelocidad1().equals(newEntity.getVelocidad1()) &&
                oldEntity.getVelocidad2().equals(newEntity.getVelocidad2()) &&
                oldEntity.getPresion1().equals(newEntity.getPresion1()) &&
                oldEntity.getPresion2().equals(newEntity.getPresion2()) &&
                oldEntity.getPosicion1().equals(newEntity.getPosicion1()) &&
                oldEntity.getPosicion2().equals(newEntity.getPosicion2());
    }
}