package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Molde;

import java.util.ArrayList;
import java.util.List;

public class MoldeDao extends BaseDao<Molde, Integer> {
    public MoldeDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return "molde";
    }

    @Override
    protected Molde fromCursor(Cursor cursor) {
        return new Molde(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                cursor.getString(cursor.getColumnIndexOrThrow("referencia")),
                cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
        );
    }

    @Override
    protected ContentValues getContentValues(Molde entity) {
        ContentValues values = new ContentValues();
        values.put("nombre", entity.getNombre());
        values.put("referencia", entity.getReferencia());
        values.put("descripcion", entity.getDescripcion());
        return values;
    }

    @Override
    protected Integer getId(Molde entity) {
        return entity.getId();
    }

    public List<Molde> getMoldesNotConfig(int idMaquina) {
        List<Molde> moldes = new ArrayList<>();
        String query = "SELECT m.id, m.nombre, m.referencia, m.descripcion " +
                "FROM molde m " +
                "LEFT JOIN configuracion c ON m.id = c.id_molde AND c.id_maquina = ? " +
                "WHERE c.id_molde IS NULL";

        try(Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idMaquina)})) {
            if(cursor.moveToFirst())
                do moldes.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return moldes;
    }
}