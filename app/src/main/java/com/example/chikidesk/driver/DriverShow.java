package com.example.chikidesk.driver;

public interface DriverShow extends Driver {
    void setKeysByBundle();
    void initProperties();
    void populateForm();
    void setupListeners();
    void setupNavigationButtons();
}
