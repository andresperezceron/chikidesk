package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Inyeccion;

public class InyeccionDao extends AbstractDao<Inyeccion, Integer> implements SubTableConfig<Inyeccion> {
    public InyeccionDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "inyeccion";
    }

    @Override
    protected Inyeccion fromCursor(Cursor cursor) {
        return new Inyeccion(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad1")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion1")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad2")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion2")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad3")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion3")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad4")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion4")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad5")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion5"))
        );
    }

    @Override
    protected ContentValues getContentValues(Inyeccion entity) {
        ContentValues values = new ContentValues();
        values.put("id", entity.getId());
        values.put("velocidad1", entity.getVelocidad1());
        values.put("presion1", entity.getPresion1());
        values.put("velocidad2", entity.getVelocidad2());
        values.put("presion2", entity.getPresion2());
        values.put("velocidad3", entity.getVelocidad3());
        values.put("presion3", entity.getPresion3());
        values.put("velocidad4", entity.getVelocidad4());
        values.put("presion4", entity.getPresion4());
        values.put("velocidad5", entity.getVelocidad5());
        values.put("presion5", entity.getPresion5());
        return values;
    }

    @Override
    public Inyeccion getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}