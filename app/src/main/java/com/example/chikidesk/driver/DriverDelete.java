package com.example.chikidesk.driver;

public interface DriverDelete extends Driver {
    void setKeysByBundle();
    void initProperties();
    void populateForm();
    void setupListeners();
    void setupNavigationButtons();
}
