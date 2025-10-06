package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends IEntity<K>, K> {
    protected SQLiteDatabase db;
    protected final MiDbHelper miDbHelperSingleton;
    protected final Context context;

    public AbstractDao(Context context) {
        this.context = context;
        this.miDbHelperSingleton = MiDbHelper.getInstance(context);
        this.db = this.miDbHelperSingleton.getWritableDatabase();
    }

    protected abstract String getTableName();
    protected abstract T fromCursor(Cursor cursor);
    protected abstract ContentValues getContentValues(T entity);

    protected K getId(T entity) {
        if(entity == null) {
            // Podríamos lanzar una excepción o devolver null, dependiendo de la política de errores.
            // Lanzar una excepción es más seguro para detectar bugs.
            throw new IllegalArgumentException("El objeto entidad no puede ser nulo.");
        }
        return entity.getId();
    }

    public void open() {
        if (this.db == null || !this.db.isOpen()) {
            this.db = this.miDbHelperSingleton.getWritableDatabase();
        }
    }
    public long insert(T entity) {
        open();
        return db.insert(getTableName(), null, getContentValues(entity));
    }

    public int update(T entity) {
        open();
        K id = getId(entity);
        return db.update(getTableName(), getContentValues(entity), "id = ?",
                new String[]{String.valueOf(id)});
    }

    public boolean delete(T entity) {
        open();
        K id = getId(entity);
        return db.delete(getTableName(), "id =?", new String[]{String.valueOf(id)}) > 0;
    }

    public T getById(K id) {
        open();
        String query = "SELECT * FROM " + getTableName() + " WHERE id =?";
        try(Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)})) {
            if(cursor.moveToFirst())
                return fromCursor(cursor);
        }
        return null;
    }

    public List<T> getAllOderBy(String orderBy) {
        open();
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName() + " ORDER BY " + orderBy + ";";
        try(Cursor cursor = db.rawQuery(query, null)) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }

    public List<T> getAll() {
        open();
        List<T> list = new ArrayList<>();
        try(Cursor cursor = db.rawQuery("SELECT * FROM " + getTableName(), null)) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }

    public T getByUniqueKey(String keyName, Object value) {
        open();
        String query = "SELECT * FROM " + getTableName() + " WHERE " + keyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            if(cursor.moveToFirst())
                return fromCursor(cursor);
        }
        return null;
    }

    public boolean duplicateUniqueKey(String uniqueKeyName, Object value) {
        open();
        String query = "SELECT * FROM " + getTableName() + " WHERE " + uniqueKeyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            return cursor.moveToFirst();
        }catch(Exception e) { return true; }
    }

    public void close() {

    }
}