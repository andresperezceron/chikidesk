package com.example.chikidesk.handle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.driver.BaseDriver;
import com.example.chikidesk.ui.fragment.BaseFragment;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseHandle<F extends BaseFragment, K>  {
    protected F fragment;
    protected K id;
    protected K idAux1, idAux2, idAux3;
    protected final AppCacheViewModel appCache;
    protected ViewBinding bindingInflated;
    protected BaseDriver driver;

    public BaseHandle(AppCacheViewModel appCache, F fragment) {
        this.appCache = appCache;
        this.fragment = fragment;
        bindingInflated = fragment.getBindingInflated();
        setKeysByBundle();
    }

    protected abstract void drive();
    protected abstract void setKeysByBundle();
    protected abstract void initProperties();
    protected abstract void populateForm();
    protected abstract void driveActionDao();
    protected abstract void setAdapters();
    protected abstract void setupListeners();
    protected abstract void setupNavigationButtons();
    protected abstract void destroyDriver();

    protected View getView() {
        return fragment.getView();
    }

    protected Context getContext() {
        return fragment.requireContext();
    }
    protected Bundle getBundle() {
        return fragment.getArguments();
    }
}