package com.example.chikidesk.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.chikidesk.model.Configuracion;
import com.example.chikidesk.model.Maquina;
import com.example.chikidesk.model.Molde;

import java.util.List;

public class AppCacheViewModel extends ViewModel {
    private List<Molde> listaMoldes;
    private List<Maquina> listaMaquinas;
    private List<Configuracion> listaConfigs;

    public void Construct(List<Maquina> listaMaquinas, List<Molde> listaMoldes,
                          List<Configuracion> listaConfigs) {
        this.listaMaquinas = listaMaquinas;
        this.listaMoldes = listaMoldes;
        this.listaConfigs = listaConfigs;
    }

    public List<Molde> getListaMoldes() {
        return listaMoldes;
    }

    public void setListaMoldes(List<Molde> listaMoldes) {
        this.listaMoldes = listaMoldes;
    }

    public List<Maquina> getListaMaquinas() {
        return listaMaquinas;
    }

    public void setListaMaquinas(List<Maquina> listaMaquinas) {
        this.listaMaquinas = listaMaquinas;
    }

    public List<Configuracion> getListaConfigs() {
        return listaConfigs;
    }

    public void setListaConfigs(List<Configuracion> listaConfigs) {
        this.listaConfigs = listaConfigs;
    }
}
