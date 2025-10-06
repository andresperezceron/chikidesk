package com.example.chikidesk.ui.validateforms;

import androidx.annotation.NonNull;

import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.model.Maquina;
import com.google.android.material.textfield.TextInputLayout;

public class CheckMaquina extends BaseCheck<Maquina, Integer> {
    private final TextInputLayout tilNombre;
    private final TextInputLayout tilRef;
    private final MaquinaDao dao;
    private Maquina maquinaChecked;
    public CheckMaquina(MaquinaDao dao, TextInputLayout[] arrayTils) {
        super(arrayTils);
        this.dao = dao;
        tilNombre = arrayTils[0];
        tilRef = arrayTils[1];
    }

    @Override
    public void checkInsert(@NonNull Maquina newEntity) {
        String nombre = newEntity.getNombre();
        String ref = newEntity.getReferencia();
        String des = newEntity.getDescripcion();
        tilNombre.setError(null);
        tilRef.setError(null);

        checkStatus = true;
        if(nombre.isEmpty()) {
            checkStatus = false;
            tilNombre.setError("El campo Nombre es obligatorio");
        }else {
            if(dao.duplicateUniqueKey("nombre", nombre)) {
                tilNombre.setError("Nombre ulizado por otra maquina");
                checkStatus = false;
            }
        }

        if(ref.isEmpty()) {
            checkStatus = false;
            tilRef.setError("El campo Referencia es obligatorio");
        }else {
            if(dao.duplicateUniqueKey("referencia", ref)) {
                tilRef.setError("Referencia ulizada por otra maquina");
                checkStatus = false;
            }
        }

        des = des.isEmpty() ? "Sin descripcion." : des;

        if(checkStatus) {
            info.add("Maquina creada correctamente.");
            maquinaChecked = new Maquina(0, nombre, ref, des);
        }
    }

    @Override
    public void checkUpdate(Maquina oldEntity, Maquina newEntity) {
        checkStatus = true;
        if(areEquals(oldEntity, newEntity)) {
            checkStatus = false;
            return;
        }

        int id = oldEntity.getId();
        String nombre = newEntity.getNombre();
        String ref = newEntity.getReferencia();
        String des = newEntity.getDescripcion();
        tilNombre.setError(null);
        tilRef.setError(null);

        if(!oldEntity.getNombre().equals(nombre)) {
            if(nombre.isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Nombre es obligatorio");
            }else if(dao.duplicateUniqueKey("nombre", nombre)) {
                checkStatus = false;
                tilNombre.setError("Ya hay una maquina con este nombre");
            }else addInfo("Nombre: " + oldEntity.getNombre() + " -> " + nombre);
        }
        if(!oldEntity.getReferencia().equals(ref)) {
            if(nombre.isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Nombre es obligatorio");
            }else if(dao.duplicateUniqueKey("referencia", ref)) {
                checkStatus = false;
                tilRef.setError("Ya hay una maquina con esta referencia");
            }else addInfo("Ref: " + oldEntity.getReferencia() + " -> " + ref);
        }
        if(!oldEntity.getDescripcion().equals(des)) {
            des = des.isEmpty() ? "Sin descripcion." : des;
            addInfo("Desc: " + oldEntity.getDescripcion() + "->" + des);
        }

        if(checkStatus) {
            maquinaChecked = new Maquina(id, nombre, ref, des);
            addInfo("Maquina modificada correctamente.");
        }
    }

    @Override
    public Maquina getCheckedEntity() {
        return maquinaChecked;
    }

    @Override
    protected boolean areEquals(Maquina oldEntity, Maquina newEntity) {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}