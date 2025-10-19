package com.example.chikidesk.ui.validateforms;

import com.example.chikidesk.model.Temperatura;
import com.google.android.material.textfield.TextInputLayout;

public class CheckTemp extends BaseCheck<Temperatura> {
    private Temperatura tempChecked;
    private final int TEMP1 = 0;
    private final int TEMP2 = 1;
    private final int TEMP3 = 2;
    private final int TEMP4 = 3;

    public CheckTemp(TextInputLayout[] arrayTils) {
        super(arrayTils);
    }

    @Override
    public void checkInsert(Temperatura newEntity) {
        tempChecked = newEntity;
        checkStatus = true;
        isEmpty = false;

        resetAllTils();
        if(tempChecked.getTemp1().isEmpty()) {
            isEmpty = true;
            getTil(TEMP1).setError("T1 = 0");
            tempChecked.setTemp1("0");
        }

        if(tempChecked.getTemp2().isEmpty()) {
            isEmpty = true;
            getTil(TEMP2).setError("T2 = 0");
            tempChecked.setTemp2("0");
        }

        if(tempChecked.getTemp3().isEmpty()) {
            isEmpty = true;
            getTil(TEMP3).setError("T3 = 0");
            tempChecked.setTemp3("0");
        }

        if(tempChecked.getTemp4().isEmpty()) {
            isEmpty = true;
            getTil(TEMP4).setError("T4 = 0");
            tempChecked.setTemp4("0");
        }
    }

    @Override
    public void checkUpdate(Temperatura oldEntity, Temperatura newEntity) {
        tempChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            isEmpty = false;
            return;
        }

        checkStatus = true;
        isEmpty = false;
        resetAllTils();

        if(newEntity.getTemp1().isEmpty()) {
            checkStatus = false;
            isEmpty = true;
            getTil(TEMP1).setError(" ");
        }
        if(newEntity.getTemp2().isEmpty()) {
            checkStatus = false;
            isEmpty = true;
            getTil(TEMP2).setError(" ");
        }
        if(newEntity.getTemp3().isEmpty()) {
            checkStatus = false;
            isEmpty = true;
            getTil(TEMP3).setError(" ");
        }
        if(newEntity.getTemp4().isEmpty()) {
            checkStatus = false;
            isEmpty = true;
            getTil(TEMP4).setError(" ");
        }
    }

    @Override
    public Temperatura getCheckedEntity() {
        return checkStatus ? tempChecked : null;
    }

    @Override
    protected boolean areEquals(Temperatura oldEntity, Temperatura newEntity) {
        return oldEntity.getTemp1().equals(newEntity.getTemp1()) &&
                oldEntity.getTemp2().equals(newEntity.getTemp2()) &&
                oldEntity.getTemp3().equals(newEntity.getTemp3()) &&
                oldEntity.getTemp4().equals(newEntity.getTemp4());
    }
}
