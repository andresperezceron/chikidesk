package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements BaseDao<T> {
    protected final SQLiteDatabase db;
    protected final MiDbHelper dbHelper;

    public AbstractDao(Context context) {
        dbHelper = new MiDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    protected abstract String getTableName();
    protected abstract T fromCursor(Cursor cursor);
    protected abstract ContentValues getContentValues(T obj);
    protected abstract int getId(T obj);


    @Override
    public int actualizar(T obj) {
        ContentValues values = getContentValues(obj);
        int id = getId(obj);
        return db.update(getTableName(), values, "id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public int eliminarPorId(int id) {
        return db.delete(getTableName(), "id = ?", new String[]{String.valueOf(id)});
    }

    @Override
    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + getTableName(), null);
            if(cursor.moveToFirst())
                do lista.add(fromCursor(cursor));
                while(cursor.moveToNext());

        }finally { if(cursor != null) cursor.close(); }
        return lista;
    }

    @Override
    public T buscarPorNombre(String nombre) {
        try (Cursor cursor = db.rawQuery(
                "SELECT * FROM " + getTableName() + " WHERE nombre = ?",
                new String[]{nombre}
        )) {

            if (cursor.moveToFirst())
                return fromCursor(cursor);

        }
        return null;
    }
    @Override
    public T obtenerPorId(int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id =" + id +";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            return fromCursor(cursor);
        }

        return null;
    }

    @Override
    public T obtenerPorIdConfig(int idConfig) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id_configuracion =" + idConfig +";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            return fromCursor(cursor);
        }

        return null;
    }

    @Override
    public void close() {
        db.close();
        dbHelper.close();
    }
}