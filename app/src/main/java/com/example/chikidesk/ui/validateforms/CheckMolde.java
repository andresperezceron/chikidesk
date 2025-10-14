package com.example.chikidesk.ui.validateforms;

import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.google.android.material.textfield.TextInputLayout;

public class CheckMolde extends BaseCheck<Molde, Integer> {
    private final TextInputLayout tilNombre;
    private final TextInputLayout tilRef;
    private final MoldeDao dao;
    private Molde moldeChecked;

    public CheckMolde(MoldeDao dao, TextInputLayout[] arrayTils) {
        super(arrayTils);
        this.dao = dao;
        tilNombre = getTil(0);
        tilRef = getTil(1);
        checkStatus = false;
    }

    @Override
    public void checkInsert(Molde newEntity) {
        String nombre = newEntity.getNombre();
        String ref = newEntity.getReferencia();
        String des = newEntity.getDescripcion();
        tilNombre.setError(null);
        tilRef.setError(null);

        checkStatus = true;
        if(nombre.isEmpty()) {
            checkStatus = false;
            tilNombre.setError("El campo Nombre es obligatorio");
        }else if(dao.duplicateUniqueKey("nombre", nombre)) {
            tilNombre.setError("Nombre ulizado por otro molde");
            checkStatus = false;
        }

        if(ref.isEmpty()) {
            checkStatus = false;
            tilRef.setError("El campo Referencia es obligatorio");
        }else if(dao.duplicateUniqueKey("referencia", ref)) {
            tilRef.setError("Referencia ulizada por otro molde");
            checkStatus = false;
        }

        des = des.isEmpty() ? "Sin descripcion." : des;

        if(checkStatus) {
            info.add("Molde creado correctamente.");
            moldeChecked = new Molde(0, nombre, ref, des);
        }
    }

    @Override
    public void checkUpdate(Molde oldEntity, Molde newEntity) {
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
                tilNombre.setError("Ya hay un molde con este nombre");
            }else addInfo("Nombre: " + oldEntity.getNombre() + " -> " + nombre);
        }
        if(!oldEntity.getReferencia().equals(ref)) {
            if(ref.isEmpty()) {
                checkStatus = false;
                tilNombre.setError("El campo Nombre es obligatorio");
            }else if(dao.duplicateUniqueKey("referencia", ref)) {
                checkStatus = false;
                tilRef.setError("Ya hay un molde con esta referencia");
            }else addInfo("Ref: " + oldEntity.getReferencia() + " -> " + ref);
        }
        if(!oldEntity.getDescripcion().equals(des)) {
            des = des.isEmpty() ? "Sin descripcion." : des;
            addInfo("Desc: " + oldEntity.getDescripcion() + "->" + des);
        }

        if(checkStatus) {
            moldeChecked = new Molde(id, nombre, ref, des);
            addInfo("Molde modificado correctamente.");
        }
    }

    @Override
    public Molde getCheckedEntity() {
        return moldeChecked;
    }

    @Override
    protected boolean areEquals(Molde oldEntity, Molde newEntity) {
        return oldEntity.getNombre().equals(newEntity.getNombre()) &&
                oldEntity.getReferencia().equals(newEntity.getReferencia()) &&
                oldEntity.getDescripcion().equals(newEntity.getDescripcion());
    }
}