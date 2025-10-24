package com.example.chikidesk.handle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseHandle<F extends Fragment, B, K> {
    protected F fragment;
    protected B binding;
    protected K id;
    protected K idAux1, idAux2, idAux3;
    protected final AppCacheViewModel appCache;

    public BaseHandle(@NonNull AppCacheViewModel appCache, @NonNull F fragment) {
        this.fragment = fragment;
        this.appCache = appCache;
        setKeysByBundle();
    }

    public abstract void drive();

    protected abstract void setKeysByBundle();
    protected abstract void initProperties();
    protected abstract void populateForm();
    protected abstract void driveActionDao();
    protected abstract void setupListeners();
    protected abstract void setupNavigationButtons();
    public abstract void destroyHandle();

    public B setBinding(B binding) {
        this.binding = binding;
        return binding;
    }

    protected View getView() {
        return fragment.getView();
    }

    protected Context getContext() {
        return fragment.requireContext();
    }

    protected Bundle getBundle() {
        return fragment.getArguments();
    }

    protected void onDestroyHandle() {
        binding = null;
    }
}