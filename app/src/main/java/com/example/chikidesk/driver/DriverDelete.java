package com.example.chikidesk.driver;

public interface DriverDelete extends BaseDriver {
    void setKeysByBundle();
    void initProperties();
    void populateForm();
    void setupListeners();
    void setupNavigationButtons();
}
