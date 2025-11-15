package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigDao extends Dao<Configuracion, Integer> {
    public ConfigDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "configuracion";
    }

    @Override
    protected Configuracion fromCursor(@NonNull Cursor cursor) {
        return new Configuracion(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getInt(cursor.getColumnIndexOrThrow("id_maquina")),
                cursor.getInt(cursor.getColumnIndexOrThrow("id_molde")),
                cursor.getString(cursor.getColumnIndexOrThrow("plastificacion")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_ciclo")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_ciclo_real")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_enfriar")),
                cursor.getString(cursor.getColumnIndexOrThrow("time_out")),
                cursor.getString(cursor.getColumnIndexOrThrow("material")),
                cursor.getString(cursor.getColumnIndexOrThrow("observaciones"))
        );
    }

    @Override
    protected ContentValues getContentValues(@NonNull Configuracion entity) {
        ContentValues values = new ContentValues();
        values.put("id_maquina", entity.getId_maquina());
        values.put("id_molde", entity.getId_molde());
        values.put("plastificacion", entity.getPlastificacion());
        values.put("tiempo_ciclo", entity.getTiempoCiclo());
        values.put("tiempo_ciclo_real", entity.getTiempoCicloReal());
        values.put("tiempo_enfriar", entity.getTiempoEnfriar());
        values.put("time_out", entity.getTimeOut());
        values.put("material", entity.getMaterial());
        values.put("observaciones", entity.getObservaciones());
        return values;
    }

    @Override
    protected Integer getId(@NonNull Configuracion entity) {
        return entity.getId();
    }

    public int getIdNewConfig() {
        return (int) idLastEntityCreated;
    }
}