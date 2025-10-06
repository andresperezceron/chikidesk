package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Temperatura;

public class TemperaturaDao extends AbstractDao<Temperatura, Integer> implements SubTableConfig<Temperatura>{
    public TemperaturaDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "temperatura";
    }

    @Override
    protected Temperatura fromCursor(Cursor cursor) {
        return new Temperatura(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp1")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp2")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp3")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp4"))
        );
    }

    @Override
    protected ContentValues getContentValues(Temperatura entity) {
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("temp1", entity.getTemp1());
        values.put("temp2", entity.getTemp2());
        values.put("temp3", entity.getTemp3());
        values.put("temp4", entity.getTemp4());
        return values;
    }

    @Override
    public Temperatura getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}