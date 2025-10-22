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
}
