package com.example.chikidesk.viewmodel;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Expulsor;
import com.example.chikidesk.model.Inyeccion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.model.RetenPresion;
import com.example.chikidesk.model.Temperatura;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppCacheViewModel extends ViewModel {
    private List<Molde> moldeList;
    private List<Maquina> maquinaList;
    private List<Configuracion> configList;
    private List<Temperatura> temperaturaList;
    private List<Inyeccion> inyeccionList;
    private List<RetenPresion> retenPresionList;
    private List<Expulsor> expulsorList;

    public boolean setMoldeList(@Nullable List<Molde> moldeList) {
        if(moldeList == null) return false;
        this.moldeList = moldeList.stream()
                .sorted(Comparator.comparing(Molde::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        return true;
    }

    public boolean setMaquinaList(@Nullable List<Maquina> maquinaList) {
        if(maquinaList == null) return false;
        this.maquinaList = maquinaList.stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        return true;
    }

    public boolean setConfigList(@Nullable List<Configuracion> configList) {
        if(configList == null) return false;
        this.configList = configList;
        return true;
    }

    public boolean setTempList(@Nullable List<Temperatura> temperaturaList) {
        if(temperaturaList == null) return false;
        this.temperaturaList = temperaturaList;
        return true;
    }

    public boolean setInyList(@Nullable List<Inyeccion> inyeccionList) {
        if(inyeccionList == null) return false;
        this.inyeccionList = inyeccionList;
        return true;
    }

    public boolean setRetenList(@Nullable List<RetenPresion> retenPresionList) {
        if(retenPresionList == null) return false;
        this.retenPresionList = retenPresionList;
        return true;
    }

    public boolean setExpList(@Nullable List<Expulsor> expulsorList) {
        if(expulsorList == null) return false;
        this.expulsorList = expulsorList;
        return true;
    }

    public Maquina getMaquinaById(Integer id) {
        return maquinaList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
    }

    public Molde getMoldeById(Integer id) {
        return moldeList.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst().orElse(null);
    }

    public int getTotalConfigByMolde(Integer id) {
        return (int) configList.stream()
                .filter(c -> Objects.equals(c.getId_molde(), id))
                .count();
    }

    public Map<Maquina, Long> getMapToConfigList() {
        return maquinaList.stream()
                .sorted(Comparator.comparing(Maquina::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors
                        .toMap(Function.identity(),
                                maquina -> configList.stream()
                                        .filter(config -> config.getId_maquina() == maquina.getId())
                                        .count(),
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new
                        ));
    }

    public List<Maquina> getMaquinaList() {
        return maquinaList;
    }

    public List<Molde> getMoldeList() {
        return moldeList;
    }

    public List<Configuracion> getConfigList() {
        return configList;
    }

    public boolean getStatus() {
        return maquinaList != null && moldeList != null && configList != null &&
                temperaturaList != null && inyeccionList != null && retenPresionList != null &&
                expulsorList != null;
    }
}
