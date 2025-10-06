package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Maquina;

import java.util.HashMap;
import java.util.Map;

public class MaquinaDao extends AbstractDao<Maquina, Integer> {
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
    protected ContentValues getContentValues(Maquina entity) {
        ContentValues values = new ContentValues();
        values.put("nombre", entity.getNombre());
        values.put("referencia", entity.getReferencia());
        values.put("descripcion", entity.getDescripcion());
        return values;
    }

    public Map<Maquina, Integer> getConfigList() {
        open();
        Map<Maquina, Integer> resultado = new HashMap<>();
        String query = "SELECT m.id, m.nombre, m.referencia, m.descripcion, COUNT(c.id) as total " +
                "FROM configuracion c " +
                "JOIN maquina m ON c.id_maquina = m.id " +
                "GROUP BY m.id, m.nombre, m.referencia, m.descripcion";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do{
                Maquina maquina = fromCursor(cursor);
                int total = cursor.getInt(cursor.getColumnIndexOrThrow("total"));
                resultado.put(maquina, total);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return resultado;
    }
}