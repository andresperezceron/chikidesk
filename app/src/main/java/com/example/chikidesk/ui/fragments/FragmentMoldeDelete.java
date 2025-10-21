package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.chikidesk.R;
import com.example.chikidesk.handles.HandleBinding;
import com.example.chikidesk.handles.HandleMoldeDelete;
import com.example.chikidesk.databinding.FragmentMoldeDeleteBinding;
import com.example.chikidesk.db.MoldeDao;
import com.example.chikidesk.model.Molde;
import com.example.chikidesk.utils.ImageManager;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentMoldeDelete extends Fragment implements HandleBinding {
    private FragmentMoldeDeleteBinding binding;
    private HandleMoldeDelete handle;

    private Molde molde;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handle = new HandleMoldeDelete(
            new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class),
            new MoldeDao(requireContext()),
            getArguments() != null ? getArguments().getInt("id") : 0,
            new ImageManager(requireContext(), "molde_", "jpg"),
            this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMoldeDeleteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        handle.setBinding();
        handle.populateForm();
        handle.setupNavigationButtons();

        binding.btnMoldeDeleteDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setMessage(R.string.alert_new_molde)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.alert_confirm),
                            (dialogInterface, i) -> handle.delete())
                    .setNegativeButton("No", null)
                    .show();
        });
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