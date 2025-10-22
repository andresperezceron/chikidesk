package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.handles.HandleMoldeList;
import com.example.chikidesk.databinding.FragmentMoldeListBinding;
import com.example.chikidesk.viewmodel.AppCacheViewModel;

public class FragmentMoldeList extends Fragment {
    private HandleMoldeList handle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMoldeList(new ViewModelProvider(requireActivity())
                .get(AppCacheViewModel.class), this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return handle.setBinding(FragmentMoldeListBinding
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