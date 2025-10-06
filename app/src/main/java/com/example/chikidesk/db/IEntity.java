package com.example.chikidesk.db;

public interface IEntity<K> {
    K getId();
    void setId(K id);
}
