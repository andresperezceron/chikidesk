package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.example.chikidesk.model.Molde;

import java.util.ArrayList;
import java.util.List;

public class MoldeDao extends Dao<Molde, Integer> {
    public MoldeDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "molde";
    }

    @Override
    protected Molde fromCursor(@NonNull Cursor cursor) {
        return new Molde(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("referencia")),
                cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
        );
    }

    @Override
    protected ContentValues getContentValues(@NonNull Molde entity) {
        ContentValues values = new ContentValues();
        values.put("nombre", entity.getNombre());
        values.put("referencia", entity.getReferencia());
        values.put("descripcion", entity.getDescripcion());
        return values;
    }

    @Override
    protected Integer getId(@NonNull Molde entity) {
        return entity.getId();
    }
}