package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.databinding.FragmentMoldeUpdateBinding;
import com.example.chikidesk.handle.HandleMoldeUpdate;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMoldeUpdate extends Fragment implements HandleDriver<HandleMoldeUpdate>{
    private HandleMoldeUpdate handle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMoldeUpdate(new ViewModelProvider(requireActivity())
                .get(AppCacheViewModel.class), this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return handle.setBinding(FragmentMoldeUpdateBinding
                .inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startDriving(handle);
    }

    @Override
    public void startDriving(@NonNull HandleMoldeUpdate handleBound) {
        handleBound.drive();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handle.destroyHandle();
    }
}