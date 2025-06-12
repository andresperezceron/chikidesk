package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.RetenPresion;

public class RetenPresionDao extends AbstractDao<RetenPresion> implements SubTableConfig {
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
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("velocidad")),
                cursor.getString(cursor.getColumnIndexOrThrow("presion")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo"))
        );
    }

    @Override
    protected ContentValues getContentValues(RetenPresion retenPresion) {
        ContentValues values = new ContentValues();
        values.put("id", retenPresion.getId());
        values.put("velocidad", retenPresion.getVelocidad());
        values.put("presion", retenPresion.getPresion());
        values.put("tiempo", retenPresion.getTiempo());
        return values;
    }

    @Override
    protected int getId(RetenPresion retenPresion) {
        return retenPresion.getId();
    }

    @Override
    public long insert(RetenPresion retenPresion) {
        return db.insert(getTableName(), null, getContentValues(retenPresion));
    }

    @Override
    public RetenPresion getByConfig(Configuracion config) {
        return getById(config.getId());
    }
}