package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.chikidesk.model.RetenPresion;

public class RetenPresionDao extends Dao<RetenPresion, Integer> {
    public RetenPresionDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "retenpresion";
    }

    @Override
    protected RetenPresion fromCursor(@NonNull Cursor cursor) {
        return new RetenPresion(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo"))
        );
    }

    @Override
    protected ContentValues getContentValues(@NonNull RetenPresion entity) {
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("velocidad", entity.getVelocidad());
        values.put("presion", entity.getPresion());
        values.put("tiempo", entity.getTiempo());
        return values;
    }

    @Override
    protected Integer getId(@NonNull RetenPresion entity) {
        return entity.getId();
    }
}