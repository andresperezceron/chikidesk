package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.handles.HandleBinding;
import com.example.chikidesk.handles.HandleMoldeShow;
import com.example.chikidesk.databinding.FragmentMoldeShowBinding;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMoldeShow extends Fragment implements HandleBinding {
    private FragmentMoldeShowBinding binding;
    private HandleMoldeShow handle;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMoldeShow(
                new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class),
                getArguments() != null ? getArguments().getInt("id") : 0,
                new ImageManager(requireContext(), "molde_", "jpg"),
                this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeShowBinding.inflate(inflater, container, false);
        handle.setBinding(binding);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handle.populateForm();
        handle.setupNavigationButtons();
    }

    @Override
    public Object getBinding() {
        return this.binding;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}