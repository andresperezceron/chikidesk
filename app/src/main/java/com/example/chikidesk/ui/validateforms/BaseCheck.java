package com.example.chikidesk.ui.validateforms;


import com.example.chikidesk.db.IEntity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCheck<T extends IEntity<K>, K> {
    protected final TextInputLayout[] arrayTils;
    protected boolean checkStatus, isEmpty;
    protected List<String> info;

    public BaseCheck(TextInputLayout[] arrayTils) {
        this.arrayTils = arrayTils;
        info = new ArrayList<>();
    }

    public abstract void checkInsert(T newEntity);
    public abstract void checkUpdate(T oldEntity, T newEntity);
    public abstract T getCheckedEntity();
    protected abstract boolean areEquals(T oldEntity, T newEntity);

    public boolean getCheckStatus() {
        return checkStatus;
    }

    public TextInputLayout getTil(int indexTil) {
        return arrayTils[indexTil];
    }

    public void resetAllTils() {
        for(TextInputLayout til : arrayTils) {
            til.setError(null);
        }
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public List<String> getInfo() {
        return checkStatus? info : null;
    }

    public void addInfo(String info) {
        this.info.add(info);
    }
}