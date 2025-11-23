package com.example.chikidesk.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MiDbHelper;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.db.RetenPresionDao;
import com.example.chikidesk.db.TemperaturaDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.model.RollBackConfig;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

import java.util.List;

public class MoldeRepository {

    private final AppCacheViewModel appCache;
    private final MiDbHelper dbHelper;
    private final MoldeDao moldeDao;
    private final MaquinaDao maquinaDao;
    private final ConfigDao configDao;
    private final TemperaturaDao tempDao;
    private final InyeccionDao inyDao;
    private final RetenPresionDao retenDao;
    private final ExpulsorDao expDao;

    public MoldeRepository(Context context, AppCacheViewModel appCache) {
        this.appCache = appCache;
        this.dbHelper = MiDbHelper.getInstance(context);
        this.moldeDao = new MoldeDao(context);
        this.maquinaDao = new MaquinaDao(context);
        this.configDao = new ConfigDao(context);
        this.tempDao = new TemperaturaDao(context);
        this.inyDao = new InyeccionDao(context);
        this.retenDao = new RetenPresionDao(context);
        this.expDao = new ExpulsorDao(context);
    }

    public boolean insertMolde(@NonNull Molde molde) {
        // Lean version for simple, single-table inserts. No transaction needed.
        List<Molde> updatedList = moldeDao.exeCrudAction(molde, MoldeDao.ACTION_INSERT);
        if(updatedList != null) {
            appCache.moldeList = updatedList;
            return true;
        }
        return false;
    }

    public boolean deleteMolde(@NonNull Molde molde) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        RollBackConfig roll = appCache.createRollBackConfig();
        db.beginTransaction();
        try {
            // ON DELETE CASCADE will handle configs and their sub-tables
            appCache.moldeList = moldeDao.exeCrudAction(molde, MoldeDao.ACTION_DELETE);
            if(appCache.moldeList == null) {
                throw new Exception("Falló la eliminación del Molde");
            }

            // Resync all other lists to reflect the cascade deletion
            appCache.maquinaList = maquinaDao.getAll();
            appCache.configList = configDao.getAll();
            appCache.temperaturaList = tempDao.getAll();
            appCache.inyeccionList = inyDao.getAll();
            appCache.retenPresionList = retenDao.getAll();
            appCache.expulsorList = expDao.getAll();

            if(!appCache.getStatus()) {
                throw new Exception("Falló la resincronización de la caché después de eliminar el molde");
            }

            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e("MoldeRepository", "Error durante la transacción de eliminación", e);
            appCache.loadRollBackConfig(roll);
            return false;
        } finally {
            db.endTransaction();
        }
    }
}