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

import com.example.chikidesk.driver.BaseDriver;
import com.example.chikidesk.factory.FactoryBinding;
import com.example.chikidesk.factory.FactoryDriver;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public abstract class BaseFragment extends Fragment {
    protected BaseDriver driver;
    protected ViewBinding binding;
    protected AppCacheViewModel appCache;

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
        binding = FactoryBinding.getInflatedBinding(getClass(), inflater,container);
        driver = FactoryDriver.getDriver(getClass(), this);
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
    }

    public ViewBinding getBindingInflated() {
        return binding;
    }

    public AppCacheViewModel getAppCache() {
        return appCache;
    }
}