package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Temperatura;

public class TemperaturaDao extends AbstractDao<Temperatura> {
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
                cursor.getInt(cursor.getColumnIndexOrThrow("id_configuracion")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp1")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp2")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp3")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp4"))
        );
    }

    @Override
    protected ContentValues getContentValues(Temperatura temperatura) {
        ContentValues values = new ContentValues();
        values.put("id_configuracion", temperatura.getId_configuracion());
        values.put("temp1", temperatura.getTemp1());
        values.put("temp2", temperatura.getTemp2());
        values.put("temp3", temperatura.getTemp3());
        values.put("temp4", temperatura.getTemp4());
        return values;
    }

    @Override
    protected int getId(Temperatura temperatura) {
        return temperatura.getId_configuracion();
    }

    @Override
    public long insertar(Temperatura temperatura) {
        return db.insert(getTableName(), null, getContentValues(temperatura));
    }
}
