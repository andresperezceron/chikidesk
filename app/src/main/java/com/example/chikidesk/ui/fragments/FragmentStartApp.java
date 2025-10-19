package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentStartAppBinding;
import com.example.chikidesk.repository.StartAppRepository;
import com.example.chikidesk.viewmodel.AppCacheViewModel;


public class FragmentStartApp extends Fragment {
    private FragmentStartAppBinding binding;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppRepository startRepo = new StartAppRepository(requireContext(),
                new ViewModelProvider(requireActivity()).get(AppCacheViewModel.class));
        assert startRepo.isLoadDataSuccess();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStartAppBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnStarAppMoldes.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_startApp_to_moldeList));

        binding.btnStarAppMaquinas.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_maquinaList));

        binding.btnStarAppConfig.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_configList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}