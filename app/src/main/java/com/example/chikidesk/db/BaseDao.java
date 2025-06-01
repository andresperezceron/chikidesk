package com.example.chikidesk.db;

import java.util.List;

public interface BaseDao<T> {
    long insertar(T obj);
    int actualizar(T obj);
    int eliminarPorId(int id);
    List<T> obtenerTodos();
    T buscarPorNombre(String nombre);
    T obtenerPorId(int id);
    T obtenerPorIdConfig(int idConfig);
    void close();
}
