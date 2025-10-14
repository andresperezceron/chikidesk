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

public class ConfigRepository {
    private final MoldeDao moldeDao;
    private final MaquinaDao maquinaDao;
    private final ConfigDao configDao;
    private final TemperaturaDao temperaturaDao;
    private final InyeccionDao inyeccionDao;
    private final RetenPresionDao retenPresionDao;
    private final ExpulsorDao expulsorDao;
    private final MiDbHelper dbHelper;

    public ConfigRepository(Context context) {
        this.moldeDao = new MoldeDao(context);
        this.maquinaDao = new MaquinaDao(context);
        this.configDao = new ConfigDao(context);
        this.temperaturaDao = new TemperaturaDao(context);
        this.inyeccionDao = new InyeccionDao(context);
        this.retenPresionDao = new RetenPresionDao(context);
        this.expulsorDao = new ExpulsorDao(context);
        this.dbHelper = MiDbHelper.getInstance(context);
    }

    public FullConfig createFullConfigByConfig(Configuracion configuracion) {
        return new FullConfig(
                moldeDao.getById(configuracion.getId_molde()),
                maquinaDao.getById(configuracion.getId_maquina()),
                configuracion,
                temperaturaDao.getByConfig(configuracion),
                inyeccionDao.getByConfig(configuracion),
                retenPresionDao.getByConfig(configuracion),
                expulsorDao.getByConfig(configuracion)
        );
    }

    public boolean updateFullConfig(@Nullable Configuracion config, @Nullable Temperatura temp,
                                    @Nullable Inyeccion iny, @Nullable RetenPresion reten,
                                    @Nullable Expulsor exp) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            if(config != null && configDao.update(config) < 1) return false;
            if(temp != null && temperaturaDao.update(temp) < 1) return false;
            if(iny != null && inyeccionDao.update(iny) < 1) return false;
            if(reten != null && retenPresionDao.update(reten) < 1) return false;
            if(exp != null && expulsorDao.update(exp) < 1) return false;

            db.setTransactionSuccessful();
            return true;
        } catch(Exception e) {
            Log.e("ConfigRepository", "...", e);
            return false;
        } finally { db.endTransaction(); }
    }

    public boolean insertFullConfig(@NonNull Configuracion config, @NonNull Temperatura temperatura,
                                    @NonNull Inyeccion inyeccion, @NonNull RetenPresion reten,
                                    @NonNull Expulsor expulsor) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            // 2. Insertar la entidad principal (Configuracion) para obtener su ID
            long newConfigId = configDao.insert(config);

            // Si la inserción principal falla, no se puede continuar.
            if(newConfigId == -1) {
                Log.e("ConfigRepository", "Falló la inserción de la entidad Configuracion principal.");
                return false; // Esto causará un rollback en el bloque 'finally'
            }

            // 3. Asignar el nuevo ID a todas las entidades hijas
            temperatura.setId((int) newConfigId);
            inyeccion.setId((int) newConfigId);
            reten.setId((int) newConfigId);
            expulsor.setId((int) newConfigId);

            // 4. Insertar las entidades hijas
            if (temperaturaDao.insert(temperatura) == -1) return false;
            if (inyeccionDao.insert(inyeccion) == -1) return false;
            if (retenPresionDao.insert(reten) == -1) return false;
            if (expulsorDao.insert(expulsor) == -1) return false;

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