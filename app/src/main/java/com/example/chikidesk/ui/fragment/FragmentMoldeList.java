package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.driver.DriverList;
import com.example.chikidesk.handle.HandleMoldeList;
import com.example.chikidesk.databinding.MoldeListBinding;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class FragmentMoldeList extends BaseFragment<MoldeListBinding,DriverList<MoldeListBinding>> {
    @Override
    protected DriverList<MoldeListBinding> createDriver(DriverList<MoldeListBinding> driver) {
        return new HandleMoldeList(new ViewModelProvider(requireActivity())
                .get(AppCacheViewModel.class), this);
    }

    @Override
    protected View bindingBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return driver.setBinding(MoldeListBinding
                .inflate(inflater, container, false)).getRoot();
    }
}