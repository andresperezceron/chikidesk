package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Inyeccion;

public class InyeccionDao extends AbstractDao<Inyeccion> implements SubTableConfig<Inyeccion> {
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
    protected ContentValues getContentValues(Inyeccion inyeccion) {
        ContentValues values = new ContentValues();
        values.put("id", inyeccion.getId());
        values.put("velocidad1", inyeccion.getVelocidad1());
        values.put("presion1", inyeccion.getPresion1());
        values.put("velocidad2", inyeccion.getVelocidad2());
        values.put("presion2", inyeccion.getPresion2());
        values.put("velocidad3", inyeccion.getVelocidad3());
        values.put("presion3", inyeccion.getPresion3());
        values.put("velocidad4", inyeccion.getVelocidad4());
        values.put("presion4", inyeccion.getPresion4());
        values.put("velocidad5", inyeccion.getVelocidad5());
        values.put("presion5", inyeccion.getPresion5());
        return values;
    }

    @Override
    protected int getId(Inyeccion inyeccion) {
        return inyeccion.getId();
    }

    @Override
    public long insert(Inyeccion inyeccion) {
        return db.insert(getTableName(), null, getContentValues(inyeccion));
    }

    @Override
    public Inyeccion getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}
