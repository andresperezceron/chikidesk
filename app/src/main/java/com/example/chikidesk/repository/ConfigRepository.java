package com.example.chikidesk.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.MiDbHelper;
import com.example.chikidesk.db.RetenPresionDao;
import com.example.chikidesk.db.TemperaturaDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class ConfigRepository {
    private final ConfigDao configDao;
    private final TemperaturaDao tempDao;
    private final InyeccionDao inyDao;
    private final RetenPresionDao retenDao;
    private final ExpulsorDao expDao;
    private final MiDbHelper dbHelper;
    private final AppCacheViewModel appCache;

    public ConfigRepository(Context context, AppCacheViewModel appCache) {
        this.appCache = appCache;
        this.configDao = new ConfigDao(context);
        this.tempDao = new TemperaturaDao(context);
        this.inyDao = new InyeccionDao(context);
        this.retenDao = new RetenPresionDao(context);
        this.expDao = new ExpulsorDao(context);
        this.dbHelper = MiDbHelper.getInstance(context);
    }

    public boolean updateFullConfig(@Nullable Configuracion config, @Nullable Temperatura temp,
                                    @Nullable Inyeccion iny, @Nullable RetenPresion reten,
                                    @Nullable Expulsor exp) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        RollBackConfig roll = appCache.createRollBackConfig();
        db.beginTransaction();
        try {
            if(config != null) {
                appCache.configList = configDao.exeCrudAction(config, ConfigDao.ACTION_UPDATE);
                if(!appCache.getStatus())
                    throw new Exception("Falló la actualización de Configuración");
            }
            if(temp != null) {
                appCache.temperaturaList = tempDao.exeCrudAction(temp, TemperaturaDao.ACTION_UPDATE);
                if(!appCache.getStatus())
                    throw new Exception("Falló la actualización de Temperatura");
            }
            if(iny != null) {
                appCache.inyeccionList = inyDao.exeCrudAction(iny, InyeccionDao.ACTION_UPDATE);
                if(!appCache.getStatus())
                    throw new Exception("Falló la actualización de Inyección");
            }
            if(reten != null) {
                appCache.retenPresionList = retenDao.exeCrudAction(reten, RetenPresionDao.ACTION_UPDATE);
                if(!appCache.getStatus())
                    throw new Exception("Falló la actualizacion de RetenPresion");
            }
            if(exp != null) {
                appCache.expulsorList = expDao.exeCrudAction(exp, ConfigDao.ACTION_UPDATE);
                if(!appCache.getStatus())
                    throw new Exception("Falló la actualización de RetenPresión");
            }

            db.setTransactionSuccessful();
            return true;
        } catch(Exception e) {
            Log.e("ConfigRepository", "Error durante la transaccion de actualización", e);
            appCache.loadRollBackConfig(roll);
            return false;
        } finally { db.endTransaction(); }
    }

    public boolean insertFullConfig(@NonNull Configuracion config, @NonNull Temperatura temp,
                                    @NonNull Inyeccion iny, @NonNull RetenPresion reten,
                                    @NonNull Expulsor exp) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        RollBackConfig roll = appCache.createRollBackConfig();
        db.beginTransaction();
        try {
            appCache.configList = configDao.exeCrudAction(config, ConfigDao.ACTION_INSERT);
            if(!appCache.getStatus()) {
                throw new Exception("Falló la inserción de Configuracion");
            }

            temp.setId(configDao.getIdNewConfig());
            iny.setId(configDao.getIdNewConfig());
            reten.setId(configDao.getIdNewConfig());
            exp.setId(configDao.getIdNewConfig());
            appCache.temperaturaList = tempDao.exeCrudAction(temp, TemperaturaDao.ACTION_INSERT);
            appCache.inyeccionList = inyDao.exeCrudAction(iny, InyeccionDao.ACTION_INSERT);
            appCache.retenPresionList = retenDao.exeCrudAction(reten, RetenPresionDao.ACTION_INSERT);
            appCache.expulsorList = expDao.exeCrudAction(exp, ExpulsorDao.ACTION_INSERT);

            if(!appCache.getStatus()) {
                throw new Exception("Falló la inserción de los detalles de Configuracion");
            }

            db.setTransactionSuccessful();
            return true;

        } catch(Exception e) {
            Log.e("ConfigRepository", "Error durante la transacción de inserción.", e);
            appCache.loadRollBackConfig(roll);
            return false;
        } finally { db.endTransaction(); }
    }
}