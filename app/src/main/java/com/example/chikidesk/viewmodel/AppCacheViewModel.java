package com.example.chikidesk.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.repository.RollBackConfig;

import java.util.List;

public class AppCacheViewModel extends ViewModel {
    public List<Molde> moldeList;
    public List<Maquina> maquinaList;
    public List<Configuracion> configList;
    public List<Temperatura> temperaturaList;
    public List<Inyeccion> inyeccionList;
    public List<RetenPresion> retenPresionList;
    public List<Expulsor> expulsorList;

    public boolean getStatus() {
        return maquinaList != null && moldeList != null && configList != null &&
                temperaturaList != null && inyeccionList != null && retenPresionList != null &&
                expulsorList != null;
    }

    public RollBackConfig createRollBackConfig() {
        return new RollBackConfig(configList, temperaturaList, inyeccionList, retenPresionList,
                expulsorList);
    }

    public void loadRollBackConfig(@NonNull RollBackConfig rollBack) {
        configList = rollBack.getConfig();
        temperaturaList = rollBack.getTemp();
        inyeccionList = rollBack.getInyec();
        retenPresionList = rollBack.getReten();
        expulsorList = rollBack.getExpulsorList();
    }
}
