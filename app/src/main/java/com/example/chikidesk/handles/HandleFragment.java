package com.example.chikidesk.handles;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class HandleFragment<F extends Fragment, B, K> {
    protected F fragment;
    protected B binding;
    protected K id;
    protected K idAux1, idAux2, idAux3;
    protected final AppCacheViewModel appCache;

    public HandleFragment(@NonNull AppCacheViewModel appCache, @NonNull F fragment) {
        this.fragment = fragment;
        this.appCache = appCache;
        setKeysByBundle();
    }

    public abstract B setBinding(B binding);
    public abstract void setupListener();
    public abstract void populateForm();
    public abstract void setupNavigationButtons();
    public abstract void destroyHandle();
    protected abstract void setKeysByBundle();


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