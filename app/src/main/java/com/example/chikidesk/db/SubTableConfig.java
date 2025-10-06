package com.example.chikidesk.db;


import com.example.chikidesk.model.Configuracion;

public interface SubTableConfig<T> {
    T getByConfig(Configuracion config);
}