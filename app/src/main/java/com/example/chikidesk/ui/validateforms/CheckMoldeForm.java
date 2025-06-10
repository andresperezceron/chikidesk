package com.example.chikidesk.ui.validateforms;

import android.content.Context;
import com.example.chikidesk.db.MoldeDao;
import com.google.android.material.textfield.TextInputLayout;

public class CheckMoldeForm {
    private final MoldeDao dao;
    TextInputLayout tilNombre, tilReferencia, tilDescripcion;
    private String nombre, referencia, descripcion;
    private boolean checkStatus;

    public CheckMoldeForm(Context context, TextInputLayout tilNombre,
                          TextInputLayout tilReferencia, TextInputLayout tilDescripcion,
                          String nombre, String referencia, String descripcion) {
        this.tilNombre = tilNombre;
        this.tilReferencia = tilReferencia;
        this.tilDescripcion = tilDescripcion;
        this.nombre = nombre;
        this.referencia = referencia;
        this.descripcion = descripcion;
        checkStatus = true;
        dao = new MoldeDao(context);
        _checkNombre();
        _checkReferencia();
        dao.close();
        _checkDescripcion();
    }

    private void _checkNombre() {
        tilNombre.setError(null);
        if(nombre.isEmpty()) {
            checkStatus = false;
            tilNombre.setError("El campo Nombre es obligatorio");
        }else if(dao.propertyNameDuplicate(nombre)) {
            tilNombre.setError("Nombre ulizado por otro molde");
            checkStatus = false;
        }
    }

    private void _checkReferencia() {
        tilReferencia.setError(null);
        if(referencia.isEmpty()) {
            checkStatus = false;
            tilReferencia.setError("El campo Referencia es obligatorio");
        }else if(dao.propertyRefDuplicate(referencia)) {
            tilReferencia.setError("Referencia utilizada por otro molde");
            checkStatus = false;
        }
    }

    private void _checkDescripcion() {
        if(descripcion.isEmpty())
            descripcion = "Sin Descripción";
    }

    public boolean getCheckStatus() {
        return checkStatus;
    }

    public String getNombre() {
        return nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }
}