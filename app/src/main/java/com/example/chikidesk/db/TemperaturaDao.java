package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Temperatura;

public class TemperaturaDao extends Dao<Temperatura, Integer> {
    public TemperaturaDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "temperatura";
    }

    @Override
    protected Temperatura fromCursor(@NonNull Cursor cursor) {
        return new Temperatura(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp1")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp2")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp3")),
                cursor.getString(cursor.getColumnIndexOrThrow("temp4"))
        );
    }

    @Override
    protected ContentValues getContentValues(@NonNull Temperatura entity) {
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("temp1", entity.getTemp1());
        values.put("temp2", entity.getTemp2());
        values.put("temp3", entity.getTemp3());
        values.put("temp4", entity.getTemp4());
        return values;
    }

    @Override
    protected Integer getId(@NonNull Temperatura entity) {
        return entity.getId();
    }
}