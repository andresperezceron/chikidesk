package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.chikidesk.driver.BaseDriver;

public abstract class BaseFragment<B extends ViewBinding, D extends BaseDriver<B>> extends Fragment {
    protected D driver;

    protected abstract D createDriver(D driver);
    protected abstract View bindingBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.driver = createDriver(driver);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return bindingBinding(inflater, container);
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
}
