package com.example.chikidesk.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.chikidesk.R;
import com.example.chikidesk.databinding.FragmentStartAppBinding;


public class FragmentStartApp extends Fragment {
    private FragmentStartAppBinding binding;
    private int chivato;
    public FragmentStartApp() {}

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chivato = 0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(binding != null) return binding.getRoot();
        binding = FragmentStartAppBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chivato = chivato +1;
        Toast.makeText(getContext(), String.valueOf(chivato), Toast.LENGTH_SHORT).show();
        binding.btnStarAppMoldes.setOnClickListener(v ->
            Navigation.findNavController(v).navigate(R.id.action_startApp_to_moldeList));

        binding.btnStarAppMaquinas.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_maquinaList));

        binding.btnStarAppConfig.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_startApp_to_configList));
    }
}