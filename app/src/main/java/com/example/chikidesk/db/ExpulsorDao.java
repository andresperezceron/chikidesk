package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;

public class ExpulsorDao extends AbstractDao<Expulsor> implements SubTableConfig<Expulsor> {

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
    protected ContentValues getContentValues(Expulsor expulsor) {
        ContentValues values = new ContentValues();
        values.put("id", expulsor.getId());
        values.put("velocidad1", expulsor.getVelocidad1());
        values.put("presion1", expulsor.getPresion1());
        values.put("posicion1", expulsor.getPosicion1());
        values.put("velocidad2", expulsor.getVelocidad2());
        values.put("presion2", expulsor.getPresion2());
        values.put("posicion2", expulsor.getPosicion2());
        return values;
    }

    @Override
    protected int getId(Expulsor expulsor) {
        return expulsor.getId();
    }

    @Override
    public long insert(Expulsor expulsor) {
        return db.insert(getTableName(), null, getContentValues(expulsor));
    }

    @Override
    public Expulsor getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}
