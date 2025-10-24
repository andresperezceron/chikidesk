package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;

public class ExpulsorDao extends BaseDao<Expulsor, Integer> implements SubTableConfig<Expulsor> {

    public ExpulsorDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "expulsor";
    }

    @Override
    protected Expulsor fromCursor(Cursor cursor) {
        return new Expulsor(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad1")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion1")),
                cursor.getString(cursor.getColumnIndexOrThrow("posicion1")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad2")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion2")),
                cursor.getString(cursor.getColumnIndexOrThrow("posicion2"))
        );
    }

    @Override
    protected ContentValues getContentValues(Expulsor entity) {
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("velocidad1", entity.getVelocidad1());
        values.put("presion1", entity.getPresion1());
        values.put("posicion1", entity.getPosicion1());
        values.put("velocidad2", entity.getVelocidad2());
        values.put("presion2", entity.getPresion2());
        values.put("posicion2", entity.getPosicion2());
        return values;
    }

    @Override
    protected Integer getId(Expulsor entity) {
        return entity.getId();
    }

    @Override
    public Expulsor getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}