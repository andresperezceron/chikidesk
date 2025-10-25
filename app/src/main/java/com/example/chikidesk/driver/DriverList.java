package com.example.chikidesk.driver;

public interface DriverList<B> extends BaseDriver<B> {
    void setAdapters();
    void populateForm();
    void setupNavigationButtons();
}
