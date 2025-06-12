package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Temperatura;

public class TemperaturaDao extends AbstractDao<Temperatura> implements SubTableConfig<Temperatura>{
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
    protected ContentValues getContentValues(Temperatura temperatura) {
        ContentValues values = new ContentValues();
        values.put("id", temperatura.getId());
        values.put("temp1", temperatura.getTemp1());
        values.put("temp2", temperatura.getTemp2());
        values.put("temp3", temperatura.getTemp3());
        values.put("temp4", temperatura.getTemp4());
        return values;
    }

    @Override
    protected int getId(Temperatura temperatura) {
        return temperatura.getId();
    }

    @Override
    public long insert(Temperatura temperatura) {
        return db.insert(getTableName(), null, getContentValues(temperatura));
    }

    @Override
    public Temperatura getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}
