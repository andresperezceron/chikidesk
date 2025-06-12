package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;

import java.util.HashMap;
import java.util.Map;


public class ConfigDao extends AbstractDao<Configuracion> {
    public ConfigDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "configuracion";
    }

    @Override
    protected Configuracion fromCursor(Cursor cursor) {
        return new Configuracion(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getInt(cursor.getColumnIndexOrThrow("id_maquina")),
                cursor.getInt(cursor.getColumnIndexOrThrow("id_molde")),
                cursor.getString(cursor.getColumnIndexOrThrow("plastificacion")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_ciclo")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_ciclo_real")),
                cursor.getString(cursor.getColumnIndexOrThrow("tiempo_enfriar")),
                cursor.getString(cursor.getColumnIndexOrThrow("time_out")),
                cursor.getString(cursor.getColumnIndexOrThrow("material")),
                cursor.getString(cursor.getColumnIndexOrThrow("observaciones"))
        );
    }

    @Override
    protected ContentValues getContentValues(Configuracion configuracion) {
        ContentValues values = new ContentValues();
        values.put("id_maquina", configuracion.getId_maquina());
        values.put("id_molde", configuracion.getId_molde());
        values.put("plastificacion", configuracion.getPlastificacion());
        values.put("tiempo_ciclo", configuracion.getTiempoCiclo());
        values.put("tiempo_ciclo_real", configuracion.getTiempoCicloReal());
        values.put("tiempo_enfriar", configuracion.getTiempoEnfriar());
        values.put("time_out", configuracion.getTimeOut());
        values.put("material", configuracion.getMaterial());
        values.put("observaciones", configuracion.getObservaciones());
        return values;
    }

    @Override
    protected int getId(Configuracion configuracion) {
        return configuracion.getId();
    }

    @Override
    public long insert(Configuracion configuracion) {
        return db.insert(getTableName(), null, getContentValues(configuracion));
    }

    public Map<Configuracion, String> obtenerListaConfigSelect(int idMaquina) {
        Map<Configuracion, String> mapList = new HashMap<>();
        String query = "SELECT c.id, c.id_maquina, c.id_molde, c.plastificacion, c.tiempo_ciclo, " +
                "c.tiempo_ciclo_real, c.tiempo_enfriar, c.time_out, c.material, c.observaciones, " +
                "m.nombre AS nombre_molde FROM configuracion c " +
                "INNER JOIN molde m ON m.id = c.id_molde " +
                "WHERE c.id_maquina = ?;";

        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(idMaquina)});
        if(cursor.moveToFirst()) {
            do {
                mapList.put(fromCursor(cursor),
                        cursor.getString(cursor.getColumnIndexOrThrow("nombre_molde")));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return mapList;
    }
}