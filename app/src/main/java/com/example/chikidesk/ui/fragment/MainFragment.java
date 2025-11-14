package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.cofigurator.Configurator;
import com.example.chikidesk.handle.Driver;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class MainFragment extends Fragment {
    protected Driver driver;
    protected ViewBinding binding;
    protected AppCacheViewModel appCache;

    private ViewGroup container;
    private LayoutInflater inflater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCache = new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        new Configurator(this);
        return binding != null ? binding.getRoot() : null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        driver.drive();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        driver.destroyDriver();
        binding = null;
    }

    public AppCacheViewModel getAppCache() {
        return appCache;
    }

    public void setBinding(ViewBinding binding) {
        this.binding = binding;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ViewBinding getBinding() {
        return binding;
    }

    public ViewGroup getContainer() {
        return container;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }
}