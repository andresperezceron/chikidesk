package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T, K> {
    public static final int ACTION_INSERT = 0;
    public static final int ACTION_UPDATE = 1;
    public static final int ACTION_DELETE = 2;

    protected SQLiteDatabase db;
    protected final MiDbHelper miDbHelperSingleton;
    protected long idLastEntityCreated;

    public BaseDao(Context context) {
        this.miDbHelperSingleton = MiDbHelper.getInstance(context);
        this.db = this.miDbHelperSingleton.getWritableDatabase();
    }

    protected abstract String getTableName();
    protected abstract T fromCursor(Cursor cursor);
    protected abstract ContentValues getContentValues(T entity);
    protected abstract K getId(T entity);

    public List<T> exeCrudAction(T entity, int crudAction) {
        switch(crudAction) {
            case ACTION_INSERT : return insert(entity) ? getAll() : null;
            case ACTION_UPDATE : return update(entity) ? getAll() : null;
            case ACTION_DELETE : return delete(entity) ? getAll() : null;
            default : return null;
        }
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

    protected boolean duplicateUniqueKey(String uniqueKeyName, Object value) {
        open();
        String query = "SELECT * FROM " + getTableName() + " WHERE " + uniqueKeyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            return cursor.moveToFirst();
        }catch(Exception e) { return true; }
    }

    protected T getByUniqueKey(String keyName, Object value) {
        open();
        String query = "SELECT * FROM " + getTableName() + " WHERE " + keyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            if(cursor.moveToFirst())
                return fromCursor(cursor);
        }
        return null;
    }

    protected void open() {
        if(this.db == null || !this.db.isOpen()) {
            this.db = this.miDbHelperSingleton.getWritableDatabase();
        }
    }

    private boolean insert(T entity) {
        open();
        idLastEntityCreated = db.insert(getTableName(), null, getContentValues(entity));
        return idLastEntityCreated > 0;
    }

    private boolean update(T entity) {
        open();
        return db.update(getTableName(), getContentValues(entity), "id = ?",
                new String[]{String.valueOf(this.getId(entity))}) > 0;
    }

    private boolean delete(T entity) {
        open();
        return db.delete(getTableName(), "id =?",
                new String[]{ String.valueOf(this.getId(entity)) }) > 0;
    }
}