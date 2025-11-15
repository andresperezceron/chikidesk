package com.example.chikidesk.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.FullConfig;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;
import com.example.chikidesk.model.RollBackConfig;

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

    public FullConfig createFullConfigByConfig(Configuracion config) {
        Maquina maquina = maquinaList.stream()
                .filter(m -> m.getId() == config.getId_maquina())
                .findFirst()
                .orElse(null);
        if(maquina == null) return null;
        Molde molde = moldeList.stream()
                .filter(m -> m.getId() == config.getId_molde())
                .findFirst()
                .orElse(null);
        if(molde == null) return null;
        Temperatura temp = temperaturaList.stream()
                .filter(t -> t.getId() == config.getId())
                .findFirst()
                .orElse(null);
        if(temp == null) return null;
        Inyeccion inyec = inyeccionList.stream()
                .filter(i -> i.getId() == config.getId())
                .findFirst()
                .orElse(null);
        if(inyec == null) return null;
        RetenPresion reten = retenPresionList.stream()
                .filter(r -> r.getId() == config.getId())
                .findFirst()
                .orElse(null);
        if(reten == null) return null;
        Expulsor expulsor = expulsorList.stream()
                .filter(e -> e.getId() == config.getId())
                .findFirst()
                .orElse(null);
        if(expulsor == null) return null;
        return new FullConfig(maquina, molde, config, temp, inyec, reten, expulsor);
    }
}