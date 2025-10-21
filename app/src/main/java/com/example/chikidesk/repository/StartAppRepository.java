package com.example.chikidesk.repository;

import android.content.Context;

import com.example.chikidesk.db.ConfigDao;
import com.example.chikidesk.db.ExpulsorDao;
import com.example.chikidesk.db.InyeccionDao;
import com.example.chikidesk.db.MaquinaDao;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.db.RetenPresionDao;
import com.example.chikidesk.db.TemperaturaDao;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class StartAppRepository {
    private final boolean loadDataSuccess;

    public StartAppRepository(Context context, AppCacheViewModel appCache) {
        MoldeDao moldeDao = new MoldeDao(context);
        MaquinaDao maquinaDao = new MaquinaDao(context);
        ConfigDao configDao = new ConfigDao(context);
        TemperaturaDao temperaturaDao = new TemperaturaDao(context);
        InyeccionDao inyeccionDao = new InyeccionDao(context);
        RetenPresionDao retenPresionDao = new RetenPresionDao(context);
        ExpulsorDao expulsorDao = new ExpulsorDao(context);

        appCache.maquinaList = maquinaDao.getAll();
        appCache.moldeList = moldeDao.getAll();
        appCache.configList =configDao.getAll();
        appCache.temperaturaList = temperaturaDao.getAll();
        appCache.inyeccionList = inyeccionDao.getAll();
        appCache.retenPresionList = retenPresionDao.getAll();
        appCache.expulsorList = expulsorDao.getAll();

        loadDataSuccess = appCache.getStatus();
    }

    public boolean isLoadDataSuccess() {
        return loadDataSuccess;
    }
}