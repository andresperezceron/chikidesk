package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigDao extends BaseDao<Configuracion, Integer> {
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
    protected ContentValues getContentValues(Configuracion entity) {
        ContentValues values = new ContentValues();
        values.put("id_maquina", entity.getId_maquina());
        values.put("id_molde", entity.getId_molde());
        values.put("plastificacion", entity.getPlastificacion());
        values.put("tiempo_ciclo", entity.getTiempoCiclo());
        values.put("tiempo_ciclo_real", entity.getTiempoCicloReal());
        values.put("tiempo_enfriar", entity.getTiempoEnfriar());
        values.put("time_out", entity.getTimeOut());
        values.put("material", entity.getMaterial());
        values.put("observaciones", entity.getObservaciones());
        return values;
    }

    @Override
    protected Integer getId(Configuracion entity) {
        return entity.getId();
    }

    public int getIdNewConfig() {
        return (int) idLastEntityCreated;
    }

    public Map<Configuracion, String> getListToNewConfig(Maquina maquina) {
        Map<Configuracion, String> mapList = new HashMap<>();
        String query = "SELECT c.id, c.id_maquina, c.id_molde, c.plastificacion, c.tiempo_ciclo, " +
                "c.tiempo_ciclo_real, c.tiempo_enfriar, c.time_out, c.material, c.observaciones, " +
                "m.nombre AS nombre_molde FROM configuracion c " +
                "INNER JOIN molde m ON m.id = c.id_molde " +
                "WHERE c.id_maquina = ?;";

        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(maquina.getId())});
        if(cursor.moveToFirst()) {
            do {
                mapList.put(fromCursor(cursor),
                        cursor.getString(cursor.getColumnIndexOrThrow("nombre_molde")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return mapList;
    }

    public int getTotalConfigsByMolde(Molde molde) {
        String strIdMolde = String.valueOf(molde.getId());
        String query = "SELECT COUNT(*) FROM " + getTableName() + " WHERE id_molde=?";

        try(Cursor cursor = db.rawQuery(query, new String[]{strIdMolde})) {
            if(cursor.moveToFirst())
                return cursor.getInt(0);
        }
        return 0;
    }

    public int getTotalConfigsByMaquina(Maquina maquina) {
        String strIdMaquina = String.valueOf(maquina.getId());
        String query = "SELECT COUNT(*) FROM " + getTableName() + " WHERE id_maquina=?";

        try(Cursor cursor = db.rawQuery(query, new String[]{strIdMaquina})) {
            if(cursor.moveToFirst())
                return cursor.getInt(0);
        }
        return 0;
    }

    public List<Configuracion> getConfigsByMolde(Molde molde) {
        List<Configuracion> list = new ArrayList<>();
        String strIdMolde = String.valueOf(molde.getId());
        String query = "SELECT * FROM " + getTableName() + " WHERE id_molde=?";

        try(Cursor cursor = db.rawQuery(query, new String[]{strIdMolde})) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }

    public List<Configuracion> getConfigsByMaquina(Maquina maquina) {
        List<Configuracion> list = new ArrayList<>();
        String strIdMaquina = String.valueOf(maquina.getId());
        String query = "SELECT * FROM " + getTableName() + " WHERE id_maquina=?";

        try(Cursor cursor = db.rawQuery(query, new String[]{strIdMaquina})) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }
}