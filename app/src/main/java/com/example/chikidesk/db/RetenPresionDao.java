package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.RetenPresion;

public class RetenPresionDao extends AbstractDao<RetenPresion> {
    public RetenPresionDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "retenpresion";
    }

    @Override
    protected RetenPresion fromCursor(Cursor cursor) {
        return new RetenPresion(
                cursor.getInt(cursor.getColumnIndexOrThrow("id_configuracion")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo"))
        );
    }

    @Override
    protected ContentValues getContentValues(RetenPresion retenPresion) {
        ContentValues values = new ContentValues();
        values.put("id_configuracion", retenPresion.getId_configuracion());
        values.put("velocidad", retenPresion.getVelocidad());
        values.put("presion", retenPresion.getPresion());
        values.put("tiempo", retenPresion.getTiempo());
        return values;
    }

    @Override
    protected int getId(RetenPresion retenPresion) {
        return retenPresion.getId_configuracion();
    }

    @Override
    public long insertar(RetenPresion retenPresion) {
        return db.insert(getTableName(), null, getContentValues(retenPresion));
    }
}