package com.example.chikidesk.ui.validateforms;

import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.viewmodel.AppCacheViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CheckMolde extends BaseCheck<Molde> {
    private final MoldeDao dao;
    private Molde moldeChecked;
    private final AppCacheViewModel appCache;

    public CheckMolde(AppCacheViewModel appCache, TextInputLayout[] arrayTils) {
        super(arrayTils);
        this.appCache = appCache;
        dao = null;

    }

    @Override
    public void checkInsert(Molde newEntity) {
        moldeChecked = newEntity;
        checkStatus = true;
        resetAllTils();

        if(newEntity.getNombre().isEmpty()) {
            checkStatus = false;
            getTil(0).setError("El campo Nombre es obligatorio");
        }else if(appCache.moldeList.stream().anyMatch(molde ->
                        Objects.equals(molde.getNombre(), newEntity.getNombre()))) {
            getTil(0).setError("Nombre ulizado por otro molde");
            checkStatus = false;
        }

        if(newEntity.getReferencia().isEmpty()) {
            checkStatus = false;
            getTil(1).setError("El campo Referencia es obligatorio");
        }else if(appCache.moldeList.stream().anyMatch(molde ->
                molde.getReferencia().equals(newEntity.getReferencia()))) {
            getTil(1).setError("Referencia ulizada por otro molde");
            checkStatus = false;
        }

        if(newEntity.getDescripcion().isEmpty())
            moldeChecked.setDescripcion("Sin descripcon");
    }

    @Override
    public void checkUpdate(Molde oldEntity, Molde newEntity) {
        moldeChecked = newEntity;
        equalToUpdate = areEquals(oldEntity, newEntity);
        if(equalToUpdate) {
            checkStatus = false;
            return;
        }

        checkStatus = true;
        resetAllTils();

       /* if(!oldEntity.getNombre().equals(newEntity.getNombre())) {
            if(newEntity.getNombre().isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Nombre es obligatorio");
            }else if(dao.isNameDuplicate(newEntity.getNombre())) {
                checkStatus = false;
                tilNombre.setError("Ya hay un molde con este nombre");
            }else addInfo("Nombre: " + oldEntity.getNombre() + " -> " + newEntity.getNombre());
        }
        if(!oldEntity.getReferencia().equals(newEntity.getReferencia())) {
            if(newEntity.getReferencia().isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Referencia es obligatorio");
            }else if(dao.isRefDuplicate(newEntity.getReferencia())) {
                checkStatus = false;
                tilRef.setError("Ya hay un molde con esta referencia");
            }else addInfo("Ref: " + oldEntity.getReferencia() + " -> " + newEntity.getReferencia());
        }*/

        if(newEntity.getDescripcion().isEmpty())
            moldeChecked.setDescripcion("Sin descripcion");
    }

    @Override
    public Molde getCheckedEntity() {
        return checkStatus ? moldeChecked : null;
    }

    @Override
    protected boolean areEquals(Molde oldEntity, Molde newEntity) {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }

    private boolean nombreDuplicado(String nombre) {
        return appCache.moldeList.stream()
                .anyMatch(molde -> Objects.equals(molde.getNombre(), nombre));
    }
}