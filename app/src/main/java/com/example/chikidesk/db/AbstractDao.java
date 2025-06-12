package com.example.chikidesk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {
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

    public long insert(T obj) {
        return db.insert(getTableName(), null, getContentValues(obj));
    }

    public int update(T obj) {
        return db.update(getTableName(), getContentValues(obj), "id = ?",
                new String[]{String.valueOf(getId(obj))});
    }

    public int delete(T obj) {
        String strId = String.valueOf(getId(obj));
        return db.delete(getTableName(), "id =?", new String[]{strId});
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try(Cursor cursor = db.rawQuery("SELECT * FROM " + getTableName(), null)) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }

    public List<T> getAllOderBy(String orderBy) {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName() + " ORDER BY " + orderBy + ";";
        try(Cursor cursor = db.rawQuery(query, null)) {
            if(cursor.moveToFirst())
                do list.add(fromCursor(cursor));
                while(cursor.moveToNext());
        }
        return list;
    }

    public T getById(int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id =?";
        String strId = String.valueOf(id);
        try(Cursor cursor = db.rawQuery(query, new String[]{strId})) {
            if(cursor.moveToFirst())
                return fromCursor(cursor);
        }
        return null;
    }

    public T getByUniqueKey(String keyName, Object value) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + keyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            if(cursor.moveToFirst())
                return fromCursor(cursor);
        }
        return null;
    }

    public boolean duplicateUniqueKey(String uniqueKeyName, Object value) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + uniqueKeyName + "=?";
        String strValue = String.valueOf(value);
        try(Cursor cursor = db.rawQuery(query, new String[]{strValue})) {
            return cursor.moveToFirst();
        }catch(Exception e) { return true; }
    }

    public void close() {
        db.close();
        dbHelper.close();
    }
}