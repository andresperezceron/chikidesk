package com.example.chikidesk.cofigurator;

import com.example.chikidesk.driver.BaseDriver;

public interface ItemHandle {
    boolean getId();
    BaseDriver createDriver();
}
