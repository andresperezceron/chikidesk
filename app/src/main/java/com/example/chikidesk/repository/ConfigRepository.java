package com.example.chikidesk.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MiDbHelper;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.db.RetenPresionDao;
import com.example.chikidesk.db.TemperaturaDao;
import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class ConfigRepository {
    private final MoldeDao moldeDao;
    private final MaquinaDao maquinaDao;
    private final ConfigDao configDao;
    private final TemperaturaDao tempDao;
    private final InyeccionDao inyDao;
    private final RetenPresionDao retenDao;
    private final ExpulsorDao expDao;
    private final MiDbHelper dbHelper;
    private final AppCacheViewModel appCache;

    public ConfigRepository(Context context, AppCacheViewModel appCache) {
        this.appCache = appCache;
        this.moldeDao = new MoldeDao(context);
        this.maquinaDao = new MaquinaDao(context);
        this.configDao = new ConfigDao(context);
        this.tempDao = new TemperaturaDao(context);
        this.inyDao = new InyeccionDao(context);
        this.retenDao = new RetenPresionDao(context);
        this.expDao = new ExpulsorDao(context);
        this.dbHelper = MiDbHelper.getInstance(context);
    }

    public ConfigRepository(Context context) {
        this.moldeDao = new MoldeDao(context);
        this.maquinaDao = new MaquinaDao(context);
        this.configDao = new ConfigDao(context);
        this.tempDao = new TemperaturaDao(context);
        this.inyDao = new InyeccionDao(context);
        this.retenDao = new RetenPresionDao(context);
        this.expDao = new ExpulsorDao(context);
        this.dbHelper = MiDbHelper.getInstance(context);
        appCache = null;
    }

    public FullConfig createFullConfigByConfig(Configuracion configuracion) {
        return new FullConfig(
                moldeDao.getById(configuracion.getId_molde()),
                maquinaDao.getById(configuracion.getId_maquina()),
                configuracion,
                tempDao.getByConfig(configuracion),
                inyDao.getByConfig(configuracion),
                retenDao.getByConfig(configuracion),
                expDao.getByConfig(configuracion)
        );
    }

    public boolean updateFullConfig(@Nullable Configuracion config, @Nullable Temperatura temp,
                                    @Nullable Inyeccion iny, @Nullable RetenPresion reten,
                                    @Nullable Expulsor exp) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            if(config != null && !appCache.setConfigList(configDao.exeCrudAction(config,
                    ConfigDao.ACTION_UPDATE))) return false;
            if(temp != null && !appCache.setTempList(tempDao.exeCrudAction(temp,
                    ConfigDao.ACTION_UPDATE))) return false;
            if(iny != null && !appCache.setInyList(inyDao.exeCrudAction(iny,
                    ConfigDao.ACTION_UPDATE))) return false;
            if(reten != null && !appCache.setRetenList(retenDao.exeCrudAction(reten,
                    ConfigDao.ACTION_UPDATE))) return false;
            if(exp != null && !appCache.setExpList(expDao.exeCrudAction(exp,
                    ConfigDao.ACTION_UPDATE))) return false;

            db.setTransactionSuccessful();
            return true;
        } catch(Exception e) {
            Log.e("ConfigRepository", "...", e);
            return false;
        } finally { db.endTransaction(); }
    }

    public boolean insertFullConfig(@NonNull Configuracion config, @NonNull Temperatura temp,
                                    @NonNull Inyeccion iny, @NonNull RetenPresion reten,
                                    @NonNull Expulsor exp) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            if(!appCache.setConfigList(configDao.exeCrudAction(config, ConfigDao.ACTION_INSERT)))
                return false;

            temp.setId(configDao.getIdNewConfig());
            iny.setId(configDao.getIdNewConfig());
            reten.setId(configDao.getIdNewConfig());
            exp.setId(configDao.getIdNewConfig());

            if(!appCache.setTempList(tempDao.exeCrudAction(temp, ConfigDao.ACTION_INSERT)))
                return false;
            if(!appCache.setInyList(inyDao.exeCrudAction(iny, ConfigDao.ACTION_INSERT)))
                return false;
            if(!appCache.setRetenList(retenDao.exeCrudAction(reten, ConfigDao.ACTION_INSERT)))
                return false;
            if(!appCache.setExpList(expDao.exeCrudAction(exp, ConfigDao.ACTION_INSERT)))
                return false;

            // 5. Si todo ha ido bien, marcamos la transacción como exitosa
            db.setTransactionSuccessful();
            return true;

        } catch (Exception e) {
            Log.e("ConfigRepository", "Error durante la transacción de inserción.", e);
            return false;
        } finally {
            // 6. Finalizar la transacción.
            // Si setTransactionSuccessful() se llamó, se aplicarán los cambios (commit).
            // Si no se llamó (porque hubo un error o un return false), se desharán todos los cambios (rollback).
            db.endTransaction();
        }
    }
}