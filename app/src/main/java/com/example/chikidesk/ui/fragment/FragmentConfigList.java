package com.example.chikidesk.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.databinding.FragmentConfigListBinding;
import com.example.chikidesk.handle.HandleConfigList;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentConfigList extends Fragment {
    private HandleConfigList handle;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleConfigList(new ViewModelProvider(requireActivity())
                .get(AppCacheViewModel.class), this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return handle.setBinding(FragmentConfigListBinding
                .inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handle.populateForm();
        handle.setupListeners();
        handle.setupNavigationButtons();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handle.destroyHandle();
    }
}