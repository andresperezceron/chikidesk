package com.example.chikidesk.ui.validateforms;

import androidx.annotation.NonNull;

import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.google.android.material.textfield.TextInputLayout;

public class CheckMaquina extends BaseCheck<Maquina> {
    private final TextInputLayout tilNombre;
    private final TextInputLayout tilRef;
    private final MaquinaDao dao;
    private Maquina maquinaChecked;
    public CheckMaquina(MaquinaDao dao, TextInputLayout[] arrayTils) {
        super(arrayTils);
        this.dao = dao;
        tilNombre = arrayTils[0];
        tilRef = arrayTils[1];
        checkStatus = false;
    }

    @Override
    public void checkInsert(@NonNull Maquina newEntity) {
        maquinaChecked = newEntity;
        resetAllTils();

        checkStatus = true;
        if(newEntity.getNombre().isEmpty()) {
            checkStatus = false;
            tilNombre.setError("El campo Nombre es obligatorio");
        }else {
            if(dao.isNameDuplicate(newEntity.getNombre())) {
                tilNombre.setError("Nombre ulizado por otra maquina");
                checkStatus = false;
            }
        }

        if(newEntity.getReferencia().isEmpty()) {
            checkStatus = false;
            tilRef.setError("El campo Referencia es obligatorio");
        }else {
            if(dao.isRefDuplicate(newEntity.getReferencia())) {
                tilRef.setError("Referencia ulizada por otra maquina");
                checkStatus = false;
            }
        }

        if(newEntity.getDescripcion().isEmpty())
            maquinaChecked.setDescripcion("Sin descripcon");
    }

    @Override
    public void checkUpdate(Maquina oldEntity, Maquina newEntity) {
        maquinaChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            return;
        }

        checkStatus = true;
        resetAllTils();

        if(!oldEntity.getNombre().equals(newEntity.getNombre())) {
            if(newEntity.getNombre().isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Nombre es obligatorio");
            }else if(dao.isNameDuplicate(newEntity.getNombre())) {
                checkStatus = false;
                tilNombre.setError("Ya hay una maquina con este nombre");
            }else addInfo("Nombre: " + oldEntity.getNombre() + " -> " + newEntity.getNombre());
        }
        if(!oldEntity.getReferencia().equals(newEntity.getReferencia())) {
            if(newEntity.getReferencia().isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Referencia es obligatorio");
            }else if(dao.isRefDuplicate(newEntity.getReferencia())) {
                checkStatus = false;
                tilRef.setError("Ya hay una maquina con esta referencia");
            }else addInfo("Ref: " + oldEntity.getReferencia() + " -> " + newEntity.getReferencia());
        }

        if(newEntity.getDescripcion().isEmpty())
            maquinaChecked.setDescripcion("Sin descripcion");
    }

    @Override
    public Maquina getCheckedEntity() {
        return checkStatus ? maquinaChecked : null;
    }

    @Override
    protected boolean areEquals(Maquina oldEntity, Maquina newEntity) {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}