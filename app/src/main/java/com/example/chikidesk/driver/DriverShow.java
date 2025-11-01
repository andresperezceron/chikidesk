package com.example.chikidesk.driver;

public interface DriverShow extends BaseDriver {
    void setKeysByBundle();
    void initProperties();
    void populateForm();
    void setupListeners();
    void setupNavigationButtons();
}
