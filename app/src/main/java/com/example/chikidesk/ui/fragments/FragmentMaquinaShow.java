package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.handles.HandleMaquinaShow;
import com.example.chikidesk.databinding.FragmentMaquinaShowBinding;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMaquinaShow extends Fragment {
    private FragmentMaquinaShowBinding binding;
    private HandleMaquinaShow handle;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle = new HandleMaquinaShow(
                new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class),
                getArguments() != null ? getArguments().getInt("id") : 0,
                new ImageManager(requireContext(), "molde_", "jpg"),
                this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMaquinaShowBinding.inflate(inflater, container, false);
        handle.setBinding(binding);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handle.populateForm();
        handle.setupNavigationButtons();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}