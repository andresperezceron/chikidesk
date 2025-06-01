package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.chikidesk.model.Molde;

import java.util.ArrayList;
import java.util.List;

public class MoldeDao extends AbstractDao<Molde> {
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
    protected ContentValues getContentValues(Molde molde) {
        ContentValues values = new ContentValues();
        values.put("nombre", molde.getNombre());
        values.put("referencia", molde.getReferencia());
        values.put("descripcion", molde.getDescripcion());
        return values;
    }


    @Override
    protected int getId(Molde molde) {
        return molde.getId();
    }

    @Override
    public long insertar(Molde molde) {
        return db.insert(getTableName(), null, getContentValues(molde));
    }


    public List<Molde> obtenerMoldesNoConfigurados(int idMaquina) {
        List<Molde> moldes = new ArrayList<>();
        String query = "SELECT m.id, m.nombre, m.referencia, m.descripcion " +
                "FROM molde m " +
                "LEFT JOIN configuracion c ON m.id = c.id_molde AND c.id_maquina = ? " +
                "WHERE c.id_molde IS NULL";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idMaquina)});

        if(cursor.moveToFirst())
            do moldes.add(fromCursor(cursor));
            while(cursor.moveToNext());

        cursor.close();
        return moldes;
    }
}