package com.example.chikidesk.driver;

public interface BaseDriver<B> {
    void drive();
    B setBinding(B binding);
    void destroyDriver();
}
