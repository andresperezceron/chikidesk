package com.example.chikidesk.handle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.ui.fragment.MainFragment;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class Handle<F extends MainFragment, K>  {
    protected F fragment;
    protected K id;
    protected K idAux1, idAux2, idAux3;
    protected final AppCacheViewModel appCache;
    protected ViewBinding binding;

    public Handle(F fragment) {
        this.fragment = fragment;
        appCache = fragment.getAppCache();
        binding = fragment.getBinding();
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