package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.chikidesk.model.Maquina;

public class MaquinaDao extends Dao<Maquina, Integer> {
    public MaquinaDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "maquina";
    }

    @Override
    protected Maquina fromCursor(@NonNull Cursor cursor) {
        return new Maquina(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("referencia")),
                cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
        );
    }

    @Override
    protected ContentValues getContentValues(@NonNull Maquina entity) {
        ContentValues values = new ContentValues();
        values.put("nombre", entity.getNombre());
        values.put("referencia", entity.getReferencia());
        values.put("descripcion", entity.getDescripcion());
        return values;
    }

    @Override
    protected Integer getId(@NonNull Maquina entity) {
        return entity.getId();
    }
}