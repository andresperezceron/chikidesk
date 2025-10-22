package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.handles.HandleMoldeDelete;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMoldeDelete extends Fragment {
    private HandleMoldeDelete handle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMoldeDelete(new ViewModelProvider(requireActivity())
                .get(AppCacheViewModel.class) , this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return handle.setBinding(FragmentMoldeDeleteBinding
                .inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handle.populateForm();
        handle.setupListener();
        handle.setupNavigationButtons();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handle.destroyHandle();
    }
}