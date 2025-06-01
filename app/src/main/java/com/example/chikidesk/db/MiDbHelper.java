package com.example.chikidesk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "maquina_config.db";
    private static final int DATABASE_VERSION = 1;
    public MiDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE molde (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL UNIQUE, " +
                "referencia TEXT NOT NULL UNIQUE, " +
                "descripcion TEXT);");

        db.execSQL("CREATE TABLE maquina (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL UNIQUE, " +
                "referencia TEXT NOT NULL UNIQUE, " +
                "descripcion TEXT);");

        db.execSQL("CREATE TABLE configuracion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_maquina INTEGER NOT NULL, " +
                "id_molde INTEGER NOT NULL," +
                "plastificacion TEXT, " +
                "tiempo_ciclo TEXT, " +
                "tiempo_ciclo_real TEXT, " +
                "tiempo_enfriar TEXT, " +
                "time_out TEXT, " +
                "material TEXT, " +
                "observaciones TEXT, " +
                "UNIQUE(id_maquina, id_molde), " +
                "FOREIGN KEY(id_maquina) REFERENCES maquina(id), " +
                "FOREIGN KEY(id_molde) REFERENCES molde(id))");

        db.execSQL("CREATE TABLE temperatura (" +
                "id_configuracion INTEGER PRIMARY KEY, " +
                "temp1 TEXT, " +
                "temp2 TEXT, " +
                "temp3 TEXT, " +
                "temp4 TEXT, " +
                "FOREIGN KEY(id_configuracion) REFERENCES configuracion(id));");

        db.execSQL("CREATE TABLE inyeccion (" +
                "id_configuracion INTEGER PRIMARY KEY, " +
                "velocidad1 TEXT, " +
                "presion1 TEXT, " +
                "velocidad2 TEXT, " +
                "presion2 TEXT, " +
                "velocidad3 TEXT, " +
                "presion3 TEXT, " +
                "velocidad4 TEXT, " +
                "presion4 TEXT, " +
                "velocidad5 TEXT, " +
                "presion5 TEXT, " +
                "FOREIGN KEY(id_configuracion) REFERENCES configuracion(id));");

        db.execSQL("CREATE TABLE retenpresion (" +
                "id_configuracion INTEGER PRIMARY KEY, " +
                "velocidad TEXT, " +
                "presion TEXT, " +
                "tiempo TEXT, " +
                "FOREIGN KEY(id_configuracion) REFERENCES configuracion(id));");

        db.execSQL("CREATE TABLE expulsor (" +
                "id_configuracion INTEGER PRIMARY KEY, " +
                "velocidad1 TEXT, " +
                "presion1 TEXT, " +
                "posicion1 TEXT, " +
                "velocidad2 TEXT, " +
                "presion2 TEXT, " +
                "posicion2 TEXT, " +
                "FOREIGN KEY(id_configuracion) REFERENCES configuracion(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualización de esquema: eliminar y recrear
        db.execSQL("DROP TABLE IF EXISTS expulsor;");
        db.execSQL("DROP TABLE IF EXISTS retenpresion;");
        db.execSQL("DROP TABLE IF EXISTS inyeccion;");
        db.execSQL("DROP TABLE IF EXISTS temperatura;");
        db.execSQL("DROP TABLE IF EXISTS configuracion;");
        db.execSQL("DROP TABLE IF EXISTS molde;");
        db.execSQL("DROP TABLE IF EXISTS maquina;");
        onCreate(db);
    }
}