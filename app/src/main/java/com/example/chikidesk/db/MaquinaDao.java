package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Maquina;

import java.util.HashMap;
import java.util.Map;

public class MaquinaDao extends AbstractDao<Maquina> {
    public MaquinaDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "maquina";
    }

    @Override
    protected Maquina fromCursor(Cursor cursor) {
        return new Maquina(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("referencia")),
                cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
        );
    }

    @Override
    protected ContentValues getContentValues(Maquina maquina) {
        ContentValues values = new ContentValues();
        values.put("nombre", maquina.getNombre());
        values.put("referencia", maquina.getReferencia());
        values.put("descripcion", maquina.getDescripcion());
        return values;
    }

    @Override
    protected int getId(Maquina maquina) {
        return maquina.getId();
    }

    public Map<Maquina, Integer> obtenerTotalesConfiguraciones() {
        Map<Maquina, Integer> resultado = new HashMap<>();

        String query = "SELECT m.id, m.nombre, m.referencia, m.descripcion, COUNT(c.id) as total " +
                "FROM configuracion c " +
                "JOIN maquina m ON c.id_maquina = m.id " +
                "GROUP BY m.id, m.nombre, m.referencia, m.descripcion";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Maquina maquina = fromCursor(cursor);
                int total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
                resultado.put(maquina, total);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return resultado;
    }

    public boolean existeMaquina(String nuevaMaquina) {
        String query = "SELECT nombre FROM maquina WHERE nombre = ?";
        Cursor cursor = db.rawQuery(query, new String[]{nuevaMaquina});
        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}